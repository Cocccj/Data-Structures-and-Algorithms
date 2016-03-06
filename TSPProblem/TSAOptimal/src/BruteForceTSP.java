import java.util.LinkedList;

/**
 * Search for the hamilton cycle with minimal length using brute force method
 * @author Jiaqi Zhang
 *
 */
public class BruteForceTSP {
	
	private double minLength = Integer.MAX_VALUE;
	private LinkedList<Integer> optimalL;
	
	/**
	 * brute force - search for the hamilton cycle with minimal length<br>
	 * pre-condition: g is not empty<br>
	 * post-condition: optimalL and minLength are updated<br>
	 * any-case time complexity: Big Theta(N!)
	 * @param g graph
	 */
	public LinkedList<Integer> bruteForceTSP(Graph g) {
		LinkedList<Integer> l = new LinkedList<Integer>();
		for (int i = 0; i < g.getSize(); i++) {
			l.add(i);
			bruteForceTSPHelper(g, l, i, 0);
			l.removeLast();
		}
		resultsPrinter();
		return optimalL;
	}
	
	/**
	 * bruteForceTSPHelper - DFS<br>
	 * pre-condition: g is not empty, 0 <= vStart < g.size() <br>
	 * post-condition: optimalL and minLength are updated<br>
	 * any-case time complexity: Big Theta((N-1)!)
	 * @param g graph
	 * @param l linkedlist(helper)
	 * @param vStart previous index to compute distance
	 * @param len length
	 */
	private void bruteForceTSPHelper(Graph g, LinkedList<Integer> l, int vStart, double len) {
		if(l.size() == g.getSize()) {
			len += g.getDist(vStart, l.getFirst());
			if (len < minLength) {
				optimalL = new LinkedList<Integer>(l);
				optimalL.add(l.getFirst());
				minLength = len;
			}
			return;
		}
		for (int i = 0; i < g.getSize(); i++) {
			if(l.contains(i))	continue;
			l.add(i);
			len += g.getDist(vStart, i);
			bruteForceTSPHelper(g, l, i, len);
			l.removeLast();
			len -= g.getDist(vStart, i);
		}
	}
	
	/**
	 * print the results
	 */
	private void resultsPrinter() {
		String str = "Hamiltonan Cycle (minimal): ";
		str += optimalL.get(0);
		for (int i = 1; i < optimalL.size(); i++) {
			str += ", " + optimalL.get(i);
		}
		System.out.println(str);
		System.out.println("Length of Cycle : " + minLength +" miles");
	}
	
}
