/**
 * Min-heap
 * @author Jiaqi Zhang
 *
 */
public class MinHeap {

	private HeapNode[] minHeap;
	private int last;

	/**
	 * constructor
	 * @param maxSize
	 */
	public MinHeap(int maxSize) {
		minHeap = new HeapNode[maxSize];
		last = 0;
	}
	
	/**
	 * return true if the heap is empty, false otherwise
	 * @return true if the heap is empty, false otherwise
	 */
	public boolean isEmpty() {
		return last == 0;
	}
	
	/**
	 * return the last index of the heap
	 * @return last index
	 */
	public int lastIndex() {
		return last;
	}
	
	/**
	 * insert a node into heap
	 * @param nodeNum node index in the graph
	 * @param prev index of the previous node
	 * @param d distance of the node and its previous node
	 */
	public void insert(int nodeNum, int prev, double d) {
		minHeap[last] = new HeapNode(nodeNum, prev, d);
		fixUp(last);
		last++;
	}
	
	/**
	 * delete minimum from the heap
	 * @return minHeap[0]
	 */
	public HeapNode deleteMin() {
		HeapNode result = minHeap[0];
		minHeap[0] = minHeap[--last];
		fixDown(0);
		return result;
	}
	
	/**
	 * get node i <br>
	 * pre-condition: i >= 0 && i < size
	 * @param i index
	 * @return minHeap[i]
	 */
	public HeapNode getNode(int i) {
		return minHeap[i];
	}
	
	/**
	 * update the information of the node
	 * @param index index in the heap
	 * @param prev index of the previous node
	 * @param d distance of the node and its previous node
	 */
	public void update(int index, int prev, double d) {
		double prevDist = minHeap[index].getDist();
		minHeap[index].setDist(d);
		minHeap[index].setPrevNode(prev);;
		if (prevDist > d) {
			fixUp(index);
		}
		else {
			fixDown(index);
		}
	}
	
	/**
	 * fix the heap (upward)<br>
	 * pre-condition: index >= 0 && index < size<br>
	 * post-condition: the heap is fixed<br>
	 * best-case time complexity: Big Theta(1)<br>
	 * worst-case time complexity: Big Theta(LogN)
	 * @param index
	 */
	private void fixUp(int index) {
		if(index == 0)	return;
		if (minHeap[index].getDist() < minHeap[(index-1)/2].getDist()) {
			HeapNode temp = minHeap[index];
			minHeap[index] = minHeap[(index-1)/2];
			minHeap[(index-1)/2] = temp;
		}
		fixUp((index-1)/2);
	}
	
	/**
	 * fix the heap (downward)<br>
	 * pre-condition: index >= 0 && index < size<br>
	 * post-condition: the heap is fixed<br>
	 * best-case time complexity: Big Theta(1)<br>
	 * worst-case time complexity: Big Theta(LogN)
	 * @param index
	 */
	private void fixDown(int index) {
		if(2 * index + 1 > last)	return;
		int swapIndex;
		if (2 * index + 2 > last) {
			swapIndex = 2 * index + 1;
		}
		else {
			swapIndex = minHeap[2 * index + 1].getDist() < minHeap[2 * index + 2].getDist() ? 2 * index + 1 : 2 * index + 2;
		}
		if (minHeap[index].getDist() > minHeap[swapIndex].getDist()) {
			HeapNode temp = minHeap[index];
			minHeap[index] = minHeap[swapIndex];
			minHeap[swapIndex] = temp;
		}
		fixDown(swapIndex);
	}
}


