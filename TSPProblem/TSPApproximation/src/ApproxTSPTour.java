import java.util.LinkedList;

/**
 * Get the approximate solution of TSP by Prim
 * @author Jiaqi Zhang
 *
 */
public class ApproxTSPTour {
	
	/**
	 * get the approximate solution of TSP by Prim
	 * @param g graph
	 * @param vStart start vertex
	 */
	public LinkedList<Integer> mstPrim(Graph g, int vStart) {
		MinHeap heap = new MinHeap(g.getSize());
		for (int i = 0; i < g.getSize(); i++) {
			heap.insert(i, vStart, g.getDist(i, vStart));
		}
		LinkedList<LinkedList<Integer>> mst = new LinkedList<LinkedList<Integer>> ();
		for (int i = 0; i < g.getSize(); i++) {
			LinkedList<Integer> l = new LinkedList<Integer>();
			mst.add(l);
		}
		while(!heap.isEmpty()) {
			HeapNode h = heap.deleteMin();
			mst.get(h.getPrevNode()).add(h.getNodeNum());
			updateDist(g, heap, h.getNodeNum());
		}
		LinkedList<Integer> hCycle = new LinkedList<Integer>();
		preTraverse(mst, hCycle, vStart);
		hCycle.add(vStart);
		resultsPrinter(g, hCycle);
		return hCycle;
	}
	
	/**
	 * pre-order traverse
	 * @param mst minimal spanning tree
	 * @param h hamilton cycle
	 * @param index
	 */
	private void preTraverse(LinkedList<LinkedList<Integer>> mst, LinkedList<Integer> h, int index) {
		h.add(index);
		LinkedList<Integer> l = mst.get(index);
		for (int i = 0; i < mst.size(); i++) {
			if (l.contains(i) && !h.contains(i))
				preTraverse(mst, h, i);
		}
	}
	
	/**
	 * update the distance of the heap
	 * @param g graph
	 * @param heap
	 * @param index
	 */
	private void updateDist(Graph g, MinHeap heap, int index) {
		for (int i = 0; i < heap.lastIndex(); i++) {
			HeapNode h = heap.getNode(i);
			if (h.getDist() > g.getDist(h.getNodeNum(), index)) {
				heap.update(i, index, g.getDist(h.getNodeNum(), index));
			}
		}
	}
	
	/**
	 * get the length of the hamilton cycle
	 * @param g graph
	 * @param h hamilton cycle
	 * @return length
	 */
	private double getCycleLength(Graph g, LinkedList<Integer> h) {
		double length = 0;
		for (int i = 0; i < h.size(); i++) {
			length += g.getDist(h.get(i), h.get((i + 1) % h.size()));
		}
		return length;
	}
	
	/**
	 * print the results
	 * @param g graph
	 * @param h suboptimal hamilton cycle
	 */
	private void resultsPrinter(Graph g, LinkedList<Integer> h) {
		double length = getCycleLength(g, h);
		String str = "Hamiltonan Cycle (not necessarily optimum): ";
		str += h.get(0);
		for (int i = 1; i < h.size(); i++) {
			str += ", " + h.get(i);
		}
		System.out.println(str);
		System.out.println("Length of Cycle : " + length +" miles");
	}
}
