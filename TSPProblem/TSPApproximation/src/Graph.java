/**
 * class Graph, including variables about the vertices and adjacency matrix about the graph
 * @author Jiaqi Zhang
 *
 */
public class Graph {

	private String[] crimeRecords;
	private double[][] adjMatrix;
	private int size;
	
	/**
	 * constructor
	 * @param size
	 */
	public Graph(int size) {
		this.size = size;
		crimeRecords = new String[size];
		adjMatrix = new double[size][size];
	}
	
	/**
	 * constructor
	 * @param size
	 * @param crimeRecords
	 * @param adj
	 */
	public Graph(int size, String[] crimeRecords, double[][] adj) {
		this.size = size;
		this.crimeRecords = crimeRecords;
		adjMatrix = adj;
	}
	
	/**
	 * get the size of the graph
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * set distance of  i and j <br>
	 * pre-condition: i >= 0 && i < size && j >= 0 && j < size <br>
	 * post-condition: adjMatrix[i][j] is updated <br>
	 * time complexity: Big Theta(1)
	 * @param i index1
	 * @param j index2
	 */
	public void setDist(int i, int j, int dist) {
		adjMatrix[i][j] = dist;
		adjMatrix[j][i] = dist;
	}
	
	/**
	 * get distance of vertex i and j <br>
	 * pre-condition: i >= 0 && i < size && j >= 0 && j < size <br>
	 * post-condition: return adjMatrix[i][j] <br>
	 * @param i index1
	 * @param j index2
	 * @return distance of vertex i and j
	 */
	public double getDist(int i, int j) {
		return adjMatrix[i][j];
	}
	
	/**
	 * get the value of vertex i <br>
	 * pre-condition: i >= 0 && i < size <br>
	 * post-condition: vertices[i]
	 * @param i the index of the vertex
	 * @return crime record
	 */
	public String getCrimeRecord(int i) {
		return crimeRecords[i];
	}
	
	/**
	 * set the value of vertex i <br>
	 * pre-condition: i >= 0 && i < MAX <br>
	 * post-condition: set str to vertices[i]
	 * @param i the index of the vertex
	 * @param str the crime record to set to the vertex
	 */
	public void setV(int i, String str) {
		crimeRecords[i] = str;
	}

	/**
	 * override the toString method
	 * @return a string
	 */
	public String toString() {
		String str = "    ";
		for (int i = 0; i < size; i++) {
			str += i + " ";
		}
		str += "\n";
		for (int i = 0; i < size; i++) {
			str += " －";
		}
		str += "\n";
		for (int i = 0; i < size; i++) {
			str += i + " | ";
			for (int j = 0; j < size; j++) {
				str += adjMatrix[i][j] + " ";
			}
			str += "\n";
		}
		return str;
	}
}
