import java.io.*;
import java.util.*;
import RedBlackTree.*;

/**
 * Find the final schedule through greedy algorithm (not necessarily optimal), 
 * set the first vertex as the start point to apply the greedy algorithm, to find an applicable schedule
 * @author Jiaqi Zhang
 *
 */
public class FinalSchedule {

	/**
	 * 
	 * @param args file name
	 */
	public static void main(String args[]) {
		RedBlackTree rbt = new RedBlackTree();
		Graph g = new Graph();
		LinkedList newclr = new LinkedList();
		String[] courses = new String[40];
		FinalSchedule fs = new FinalSchedule();
		try{
			BufferedReader in  = new BufferedReader(new FileReader(args[0]));
	        String line = in.readLine();
	        while(line != null) {
	 	       StringTokenizer st;
		       st = new StringTokenizer(line, " \t");
		       st.nextToken();
		       int nums = Integer.parseInt(st.nextToken());
		       int[] courseindices = new int[nums];
		       for (int i = 0; i < nums; i++) {
		    	   String s = st.nextToken();
		    	   courseindices[i] = rbt.check(s);
		    	   if (courses[courseindices[i]] == null) {
		    		   courses[courseindices[i]] = s;
		    	   }
		       }
		       for (int i = 0; i < nums; i++) {
		    	   for (int j = i + 1; j < nums; j++) {
		    		   g.setAdj(courseindices[i], courseindices[j]);
		    	   }
		       }
		       line = in.readLine();
	        }
	        in.close();
	        System.out.println("Mapping results:\n");
	        rbt.inOrderTraversal();
	        System.out.println("\nAdjacency Matrix:\n\n"+g);
	        
	        System.out.println("RECOMMENDED SCHEDULE OF FINAL EXAMS (NOT NECESSARILY OPTIMAL)\n");
	        int sche = 1;
	        while(!g.isAllColored()) {
	        	newclr = fs.greedyColoring(g, sche);
	        	ListNode h = newclr.getHead();
	        	String str = "Final Exam Period " + sche++ +" => ";
	        	while (h != null) {
	        		str += courses[h.getInt()]+" ";
	        		h = h.getNext();
	        	}
	        	System.out.println(str);
	        }
	     }catch(IOException e) {
	        System.out.println(e.getStackTrace());
	     }
	}
	
	
	/**
	 * Use greedy algorithm to color the graph from vertex 0 <br>
	 * @param g the graph
	 * @param c the color to set
	 * @return a linked list contains the vertices can be colored the same
	 */
	public LinkedList greedyColoring(Graph g, int c) {
		LinkedList newclr = new LinkedList();
		boolean found;
		int v = g.getNextUncolored(0);
		while (v > -1) {
			found = false;
			ListNode h = newclr.getHead();
			while (h != null) {
				if (g.getAdj(v, h.getInt())) {
					found = true;
				}
				h = h.getNext();
			}
			if (!found) {
				g.setV(v, c);
				newclr.addAtEnd(v);
			}
			v = g.getNextUncolored(v + 1);
		}
		return newclr;
	}
}
