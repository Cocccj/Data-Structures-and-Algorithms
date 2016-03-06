package approximateTSP;
/**
 * Heap Node - with information of the node-index, previous-node-index, and distance of the node with its previous node 
 * @author Jiaqi Zhang
 *
 */
public class HeapNode {
	
	private int nodeNum;
	private int prevNode;
	private double dist;
	
	/**
	 * constructor
	 * @param n node index in the graph
	 * @param p index of the previous node
	 * @param d distance
	 */
	public HeapNode(int n, int p, double d) {
		nodeNum = n;
		prevNode = p;
		dist = d;
	}
	
	/**
	 * get node index
	 * @return node index
	 */
	public int getNodeNum() {
		return nodeNum;
	}
	
	/**
	 * get index of the previous node
	 * @return index of the previous node
	 */
	public int getPrevNode() {
		return prevNode;
	}
	
	/**
	 * get distance
	 * @return distance with the previous node
	 */
	public double getDist() {
		return dist;
	}
	
	/**
	 * set distance
	 * @param d distance with the previous node
	 */
	public void setDist(double d) {
		dist = d;
	}
	
	/**
	 * set previous node
	 * @param p new node to be set as the previous node
	 */
	public void setPrevNode(int p) {
		prevNode = p;
	}
}
