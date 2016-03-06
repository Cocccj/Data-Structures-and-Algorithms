import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import approximateTSP.ApproxTSPTour;
import approximateTSP.Graph;
import optimalTSP.BruteForceTSP;

/**
 * store the results of part 1 and 2 into a kml file
 * @author Jiaqi Zhang
 *
 */
public class Homework4_3 {
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
			ApproxTSPTour apxTSP = new ApproxTSPTour();
			LinkedList<Integer> listApprox = apxTSP.mstPrim(g, 0);
			System.out.println();
			BruteForceTSP bruteTSP = new BruteForceTSP();
			LinkedList<Integer> listOpt = bruteTSP.bruteForceTSP(g);
			generateKML(g, listApprox, listOpt);
			
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
	
	/**
	 * generating kml file
	 * @param g graph
	 * @param approx suboptimal hamilton cycle
	 * @param opt optimal hamilton cycle
	 * @throws IOException
	 */
	public static void generateKML(Graph g, LinkedList<Integer> approx, LinkedList<Integer> opt) throws IOException {
		String coorApprox = "";
		String coorOpt = "";
		for (int i : approx) {
			String[] info = g.getCrimeRecord(i).split(",");
			double lon = Double.parseDouble(info[8]);
			double lat = Double.parseDouble(info[7]);
			lon = lon + 0.001;
			coorApprox += lon + "," + lat + ",0.000000\n";
		}
		for (int i : opt) {
			String[] info = g.getCrimeRecord(i).split(",");
			coorOpt += info[8] + "," + info[7] + ",0.000000\n";
		}
		String kml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<kml xmlns=\"http://earth.google.com/kml/2.2\">"
						+"\n<Document>\n<name>Pittsburgh TSP</name><description>TSP on Crime</description><Style id=\"style6\">"
						+"\n<LineStyle>\n<color>73FF0000</color>"
						+"\n<width>5</width>"
						+"\n</LineStyle>\n</Style>"
						+"\n<Style id=\"style5\">"
						+"\n<LineStyle>\n<color>507800F0</color>\n<width>5</width>"
						+"\n</LineStyle>\n</Style>\n<Placemark>"
						+"\n<name>TSP Path</name>"
						+"\n<description>TSP Path</description>"
						+"\n<styleUrl>#style6</styleUrl>\n<LineString>"
						+"\n<tessellate>1</tessellate>"
						+"\n<coordinates>\n" + coorApprox + "</coordinates>"
						+"\n</LineString>\n</Placemark>\n<Placemark>"
						+"\n<name>Optimal Path</name>"
						+"\n<description>Optimal Path</description>"
						+"\n<styleUrl>#style5</styleUrl>\n<LineString>"
						+"\n<tessellate>1</tessellate>"
						+"\n<coordinates>\n" + coorOpt + "</coordinates>"
						+"\n</LineString>\n</Placemark>"
						+"\n</Document>\n</kml>";
		BufferedWriter outstream = new BufferedWriter(new FileWriter("PGHCrimes.kml"));
		outstream.write(kml);
		outstream.close();
	}
}
