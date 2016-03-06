import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Compress files using LZW compress algorithm.
 * @author Jiaqi Zhang
 *
 */
public class LZWCompression {
	
	/**
	 * Length of a chunk.
	 */
	private static final int CHUNK_LENGTH = 12;
	
	/**
	 * Length of a byte.
	 */
	private static final int BYTE_LENGTH = 8;
	
	/**
	 * Table storing the mapping information for strings and codes.
	 */
	private Map<String, Integer> table = new HashMap<String, Integer>();
	//private Map<String, Integer> table = new TreeMap<String, Integer>();
	
	/**
	 * Next code index.
	 */
	private int nextCode = 0;
	
	/**
	 * The bits left by the previous chunk when converting to byte.
	 */
	private int leftBits = 0;
	
	/**
	 * Length of the bits left.
	 */
	private int leftLength = 0;


	/**
	 * This program works for both ASCII files and binary files.<br>
	 * Degree of compression: words.html - from 2.5MB to 1.1MB (44%), CrimeLatLonXY1990.csv - from 279KB to 139KB (49.82%), 01_Overview.mp4 - from 25MB to 33.8MB (135.2%). <br>
	 * HashMap is better for compression.<br>
	 * HashMap: words.html - 497.0 milliseconds, CrimeLatLonXY1990.csv - 135.0 milliseconds, 01_Overview.mp4 - 4889.0 milliseconds.<br>
	 * TreeMap: words.html - 963.0 milliseconds, CrimeLatLonXY1990.csv - 180.0 milliseconds, 01_Overview.mp4 - 11596.0 milliseconds.
	 * @param args "c" or "d" for compress or decompress, and two filenames
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Timer timer = new Timer();
		if (args[0].equals("c")) {
			LZWCompression lzwComp = new LZWCompression();
			lzwComp.compress(args[1], args[2]);
		} else if (args[0].equals("d")) {
			LZWDecompression lzwDecomp = new LZWDecompression();
			lzwDecomp.decompress(args[1], args[2]);
		}
		System.out.println(timer.calcElapsedTime() + " milliseconds.");
	}
	
	/**
	 * Compress the input file and output the compressed file.
	 * @param filein the name of the input file
	 * @param fileout the name of the output file
	 * @throws IOException
	 */
	public void compress(String filein, String fileout) throws IOException {
		DataInputStream in = new DataInputStream( 
				new BufferedInputStream( 
						new FileInputStream(filein)));
		DataOutputStream out = new DataOutputStream(
				new BufferedOutputStream(
						new FileOutputStream(fileout)));

		initializeTable();

		byte byteIn; // the byte read in
		byteIn = in.readByte();
		String s = "";
		s = s + (char)((char)byteIn & 0xFF);
		try {
			while(true) {
				byteIn = in.readByte();
				if (table.containsKey(s + (char)((char)byteIn & 0xFF))) {
					// s + c is in the table
					s = s + (char)((char)byteIn & 0xFF);
				} else {
					outputBits(s, out);
					ensureTableSpace();
					table.put(s + (char)((char)byteIn & 0xFF), nextCode++);
					s = "" + (char)((char)byteIn & 0xFF);
				}
			}
		} catch(EOFException e) {
			if (s.length() != 0) {
				outputBits(s, out);
				if (leftLength > 0) {
					// write the last byte
					leftBits = leftBits << (BYTE_LENGTH - leftLength);
					byte byteOut = (byte)leftBits;
					out.writeByte(byteOut);
				}
			}
			in.close();
			out.close();
		}
	}
	
	/**
	 * Initialize the code table.
	 */
	private void initializeTable() {
		for (int i = 0; i <= 255; i++) {
			table.put("" + (char)((char)i & 0xFF), i);
			nextCode++;
		}
	}
	
	/**
	 * Ensure the space of the table.
	 * If the table is full, initialize it again.
	 */
	private void ensureTableSpace() {
		if(nextCode >= Math.pow(2, CHUNK_LENGTH)) { // detect overflow
			table = new HashMap<String, Integer>();
			//table = new TreeMap<String, Integer>();
			nextCode = 0;
			initializeTable();
		}
	}
	
	/**
	 * Output bits to the file.
	 * @param s the string corresponding to the output bits
	 * @param out the output stream
	 * @throws IOException
	 */
	private void outputBits(String s, DataOutputStream out) throws IOException {
		int bitsOut = 0; // the coded bits converted from the byteIn
		byte byteOut; // the byte to write out
		int clearBits = -1;
		bitsOut = table.get(s);
		if (leftLength > 0) { 
			// combine the left bits with the new chunk
			leftBits = leftBits << (BYTE_LENGTH - leftLength);
			leftLength = CHUNK_LENGTH - BYTE_LENGTH + leftLength;
			int addToLeftBits = bitsOut >>> leftLength;
			byteOut = (byte) (leftBits | addToLeftBits);
			out.writeByte(byteOut);
			clearBits = ~(clearBits << leftLength);
			leftBits = bitsOut & clearBits;
		} else{
			leftLength = CHUNK_LENGTH;
		}
		while (leftLength >= BYTE_LENGTH) {
			// write the byte out, and store the remain bits of the chunk in the leftBits
			leftLength -= BYTE_LENGTH;
			byteOut = (byte) (bitsOut >>> leftLength);
			out.writeByte(byteOut);
			clearBits = ~(clearBits << leftLength);
			leftBits = bitsOut & clearBits;
		}
	}
}



/**
 * Decompress files according to LZW compress algorithm.
 * @author Jiaqi Zhang
 *
 */
class LZWDecompression {
	
	/**
	 * Length of a chunk.
	 */
	private static final int CHUNK_LENGTH = 12;
	
	/**
	 * Length of a byte.
	 */
	private static final int BYTE_LENGTH = 8;
	
	/**
	 * Table storing the mapping information for codes and strings.
	 */
	private ArrayList<String> table = new ArrayList<String> ();	

	/**
	 * Decompress the input file and output it.
	 * @param filein the name of the input file
	 * @param fileout the name of the output file
	 * @throws IOException
	 */
	public void decompress(String filein, String fileout) throws IOException {
		DataInputStream in = new DataInputStream( 
				new BufferedInputStream( 
						new FileInputStream(filein)));
		DataOutputStream out = new DataOutputStream(
				new BufferedOutputStream(
						new FileOutputStream(fileout)));
		initializeTable();
		byte byteIn;
		int bitsIn = 0;
		int leftBits = 0; // bits left by the previous chunk when converting to byte
		int leftLength = 0; // length of the bits left
		String preCodeStr = "";
		int codeWord = 0;
		boolean isFirstCode = true;
		try {
			while(true) {
				byteIn = in.readByte();
				bitsIn = byteIn & 0xFF;
				if (leftLength > 0) {
					leftBits = leftBits << (CHUNK_LENGTH - leftLength);
					leftLength = BYTE_LENGTH - CHUNK_LENGTH + leftLength;
					int addToLeftBits = bitsIn >>> leftLength;
					codeWord = (leftBits | addToLeftBits);
					if(codeWord > table.size() - 1) {
						// codeword not in the table
						String str = preCodeStr + preCodeStr.charAt(0);
						ensureTableSpace();
						table.add(str);
						out.writeBytes(str);
						preCodeStr = str;
					} else {
						String strCode = table.get(codeWord);
						if (isFirstCode) {
							isFirstCode = false;
						} else {
							ensureTableSpace();
							table.add(preCodeStr + strCode.charAt(0));
						}
						out.writeBytes(strCode);
						preCodeStr = strCode;
					}
					int clearBits = -1;
					clearBits = ~(clearBits << leftLength);
					bitsIn = bitsIn & clearBits;
				} else{
					leftLength = BYTE_LENGTH;
				}
				leftBits = bitsIn;
			}
		} catch(EOFException e) {
			in.close();
			out.close();
		}
	}
	
	/**
	 * Initialize the code table.
	 */
	private void initializeTable() {
		for (int i = 0; i <= 255; i++) {
			table.add(i, "" + (char)i);
		}
	}
	
	/**
	 * Ensure the space of the table.
	 * If the table is full, initialize it again.
	 */
	private void ensureTableSpace() {
		if(table.size() >= Math.pow(2, CHUNK_LENGTH)) { // detect overflow
			table = new ArrayList<String>();
			initializeTable();
		}
	}
}

/**
 * Timer class to record the elapsed time.
 * @author Jiaqi Zhang
 */
class Timer {

    /**
     * Start time.
     */
    private final long start;

    /**
     * Constructor, set current time in milliseconds to start time.
     */
    public Timer() {
        start = System.currentTimeMillis();
    }

    /**
     * Calculate elapsed time.
     * @return time in milliseconds
     */
    public double calcElapsedTime() {
        long curr = System.currentTimeMillis();
        return curr - start;
    }
}


