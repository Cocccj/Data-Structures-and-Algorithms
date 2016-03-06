import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Driver for TSAOptimal
 * @author Jiaqi Zhang
 *
 */
public class Homework4_2 {


	/**
	 * 
	 * @param args filename
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter start index:");
		int offset1 = in.nextInt();
		System.out.println("Enter end index:");
		int offset2 = in.nextInt();
		while (offset1 < 0 || offset2 < 0 || offset1 >= offset2) {
			System.out.println("Input error!");
			System.out.println("Enter start index:");
			offset1 = in.nextInt();
			System.out.println("Enter end index:");
			offset2 = in.nextInt();
		}
		in.close();
		BufferedReader bufReader = null;
		try {
			bufReader = new BufferedReader(new FileReader(args[0]));
			int k = 0;
			String line;
			String[] crimeRecords = new String[offset2 - offset1 + 1];
			while(k < offset1 + 1 && bufReader.readLine() !=null) {
				k++;
			}
			while (k <= offset2 + 1 && (line = bufReader.readLine()) != null ) {
				crimeRecords[k++ - offset1 - 1] = line;
			}
			if (bufReader.readLine() == null && k < offset2 + 1) {
				throw new IOException();
			}
			System.out.println("Crime Records Processed:\n");
			for (int i = 0; i < crimeRecords.length; i++) {
				System.out.println(crimeRecords[i]);
			}
			bufReader.close();
			System.out.println();
			double[][] dist = new double[crimeRecords.length][crimeRecords.length];
			for (int i = 0; i < crimeRecords.length; i++) {
				for (int j = 0; j < i; j++) {
					String[] info1 = crimeRecords[i].split(",");
					String[] info2 = crimeRecords[j].split(",");
					double x1 = Double.parseDouble(info1[0]);
					double y1 = Double.parseDouble(info1[1]);
					double x2 = Double.parseDouble(info2[0]);
					double y2 = Double.parseDouble(info2[1]);
					double d1 = (x2 - x1) * 0.00018939;
					double d2 = (y2 - y1) * 0.00018939;
					double d = Math.sqrt(d1*d1 + d2*d2);
					dist[i][j] = d;
					dist[j][i] = d;
				}
			}

			Graph g = new Graph(crimeRecords.length, crimeRecords, dist);
			BruteForceTSP bruteTSP = new BruteForceTSP();
			bruteTSP.bruteForceTSP(g);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
