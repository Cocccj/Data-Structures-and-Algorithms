import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import RedBlackTree.RedBlackTree;
/**
 * Find the optimal final schedule with brute force method, 
 * check the number of colors from 1, and use DFS to find a solution,
 * once find a solution, return and output, otherwise increase the number of colors to try again.
 * @author Jiaqi Zhang
 *
 */
public class OptimalFinalSchedule {
	/**
	 * 
	 * @param args file name
	 */
	public static void main(String args[]) {
		RedBlackTree rbt = new RedBlackTree();
		Graph g = new Graph();
		String[] courses = new String[40];
		OptimalFinalSchedule ofs = new OptimalFinalSchedule();
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
	        System.out.println("\nAdjacency Matrix:\n\n" + g);	        
	        System.out.println("OPTIMAL SCHEDULE OF FINAL EXAMS \n");
	        int c = ofs.bruteForceColoring(g);
	        for (int i = 1; i < c; i++) {
	        	String str = "Final Exam Period " + i +" => ";
	        	for (int j = 0; j <= g.getLength(); j++) {
	        		if(g.getV(j) == i) {
	        			str += courses[j]+" ";
	        		}
	        	}
	        	System.out.println(str);
	        }
	     }catch(IOException e) {
	        System.out.println(e.getStackTrace());
	     }
	}
	
	/**
	 * use the brute force method to find an optimal coloring solution
	 * @param g graph
	 * @return the next color unused
	 */
	public int bruteForceColoring(Graph g) {
		int color = 1;
		while (!foundSolution) {
			colorK(0, g.getLength(), color, g);
			color++;
		}
		return color;
	}
	
	/**
	 * try all coloring ways by DFS, until get a solution <br>
	 * pre-condition: k >= 0 && k < MAX && n >= 0 && n < MAX && m >= 1 && g != null <br>
	 * post-condition: graph g is colored if the number of colors m is feasible, otherwise g is uncolored
	 * @param k the index of the vertex
	 * @param n the maximum index of the vertices
	 * @param m the number of colors to set
	 * @param g graph
	 */
	public void colorK(int k, int n, int m, Graph g) {
		if (k > n) {
			foundSolution = true;
		}
		else {
			for (int i = 1; i <= m; i++) {
				g.setV(k, i);
				if (isColorRight(k, g)) {
					colorK(k + 1, n, m, g);
				}
				if (foundSolution) break;
				g.setV(k, 0);
			}
		}
	}
	
	/**
	 * check whether the color of k conflicts with the other vertices <br>
	 * pre-condition: k >= 0 && k < MAX && g != null <br>
	 * post-condition: return true if the color is right false otherwise
	 * @param k the index of the vertex
	 * @param g graph
	 * @return true if the color is right false otherwise
	 */
	public boolean isColorRight(int k, Graph g) {
		for (int i = 0; i < k; i++) {
			if(g.getAdj(k, i) && g.getV(i) == g.getV(k)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean foundSolution = false;
}
