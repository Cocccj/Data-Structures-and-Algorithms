/**
 * class Graph, including variables about the vertices and adjacency matrix about the graph,
 * and methods about graph coloring
 * @author Jiaqi Zhang
 *
 */
public class Graph {

	private final int MAX = 40;
	private int[] vertices;
	private boolean[][] adjMatrix;
	private int length;
	
	/**
	 * constructor
	 */
	public Graph() {
		vertices = new int[MAX];
		adjMatrix = new boolean[MAX][MAX];
		length = 0;
	}
	
	/**
	 * links i and j <br>
	 * pre-condition: i >= 0 && i < MAX && j >= 0 && j < MAX <br>
	 * post-condition: adjMatrix[i][j] becomes true, length is updated <br>
	 * time complexity: Big Theta(1)
	 * @param i course 1
	 * @param j course 2
	 */
	public void setAdj(int i, int j) {
		adjMatrix[i][j] = true;
		adjMatrix[j][i] = true;
		length = Math.max(length, Math.max(i, j));
	}
	
	/**
	 * check whether i and j are connected <br>
	 * pre-condition: i >= 0 && i < MAX && j >= 0 && j < MAX <br>
	 * post-condition: return adjMatrix[i][j] <br>
	 * time complexity: Big Theta(1)
	 * @param i course 1
	 * @param j course 2
	 * @return true if i and j are connected false otherwise
	 */
	public boolean getAdj(int i, int j) {
		return adjMatrix[i][j];
	}
	
	/**
	 * get the max index are used for the graph
	 * @return the max index that used
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * get the value of vertex i <br>
	 * pre-condition: i >= 0 && i < MAX <br>
	 * post-condition: vertices[i]
	 * @param i the index of the vertex
	 * @return if it is colored
	 */
	public int getV(int i) {
		return vertices[i];
	}
	
	/**
	 * set the value of vertex i <br>
	 * pre-condition: i >= 0 && i < MAX <br>
	 * post-condition: set k to vertices[i]
	 * @param i the index of the vertex
	 * @param k the value to set to the vertex
	 */
	public void setV(int i, int k) {
		vertices[i] = k;
	}
	
	/**
	 * find the first vertex uncolored from the start point <br>
	 * pre-condition: start >= 0 && start <= length <br>
	 * post-condition: return the first vertex uncolored from the start point
	 * @param start the vertex to start the searching process
	 * @return the first vertex uncolored from the start point
	 */
	public int getNextUncolored(int start) {
		for (int i = start; i <= length; i++) {
			if (vertices[i] == 0) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * to check if all vertices of the graph are colored
	 * @return true if all vertices of the graph are colored false otherwise
	 */
	public boolean isAllColored() {
		for (int i = 0; i <= length; i++) {
			if (vertices[i] == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * clear the whole graph to uncolored state
	 */
	public void clearColor() {
		for (int i = 0; i <= length; i++) {
			vertices[i] = 0;
		}
	}
	
	/**
	 * override the toString method
	 * @return a string
	 */
	public String toString() {
		String str = "    ";
		for (int i = 0; i <= length; i++) {
			str += i + " ";
		}
		str += "\n";
		for (int i = 0; i <= length; i++) {
			str += " －";
		}
		str += "\n";
		for (int i = 0; i <= length; i++) {
			str += i + " | ";
			for (int j = 0; j <= length; j++) {
				str += (adjMatrix[i][j]? 1 : 0) + " ";
			}
			str += "\n";
		}
		return str;
	}
}
