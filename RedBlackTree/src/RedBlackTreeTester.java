import java.io.*;
import java.util.*;
/**
 * tester for the red black tree
 * @author Jiaqi Zhang
 *
 */
public class RedBlackTreeTester {

	/**
	 * main program to test the red black tree.
	 * @param args The file name of the word list (to be used as a dictionary)
	 */
	public static void main(String[] args){
        File file = new File(args[0]);
        FileReader freader = null;
        RedBlackTree rbt = new RedBlackTree();
        String s="", c="";
		try {
			freader = new FileReader(file);
			BufferedReader breader = new BufferedReader(freader);
			while ((s = breader.readLine()) != null){
				rbt.insert(s);
			};
			breader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("run:");
		System.out.println("Red Black Tree is loaded with "+rbt.getSize()+" words.");
		System.out.println("The height of the tree is "+rbt.height());
		System.out.println("2*Lg(n+1)="+2*Math.log(rbt.getSize()+1)/Math.log(2));
		System.out.println("Legal commands are: "
				+ "\n<p>   display the entire word tree with a level order traversal."
				+ "\n<s>   print the words of the tree in sorted order (use an inorder traversal)."
				+ "\n<r>   print the words of the tree in reverse sorted order."
				+ "\n<!>   to quit."
				+ "\n<c>   <word> to spell check this word"
				+ "\n<a>   <word> add word to tree."
				+ "\n<f>   <fileName> to check a text file for spelling errors.");
		Scanner in = new Scanner(System.in);
		c = in.next();
		while(!c.equals("!")){
			c.toLowerCase();
			switch(c){
			case ("p") :	rbt.levelOrderTraversal();	break;
			case ("s") :	rbt.inOrderTraversal();	break;
			case ("r") :	rbt.reverseOrderTraversal();	break;
			case ("!") :	break;
			case ("c") :	{
				String t = in.next();
				if(rbt.contains(t) == true){
					System.out.println("Found "+t+" after "+rbt.getRecentCompares()+" comparisons");
				}else{
					System.out.println(t +" Not in dictionary. Perhaps you mean");
					System.out.println(rbt.closeBy(t));
				}
				break;
			}
			case ("a") :	{
				String t = in.next();
				if(rbt.contains(t) == true){
					System.out.println(t +" was already in dictionary.");
				}
				else{
					rbt.insert(t);
					System.out.println(t +" was added to dictionary.");
				}
				break;
			}
			case ("f") :{
				int flag = 0;
				String t = in.next();
				String reg = "(?i)[^a-z]";
				try {
					Scanner scanner = new Scanner(new File(t));
					while ((scanner.hasNext()) ==true ){
						s = scanner.next().replaceAll(reg, "");
						if(!rbt.contains(s)){
							System.out.println("\'"+s+"\' was not found in dictionary.");
							flag = 1;
						};
					};
					if(flag == 0)	System.out.println("No spelling errors found.");
					scanner.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
			default :{
				System.out.println("Input error!");
				System.out.println("Legal commands are: "
						+ "\n<p>  display the entire word tree with a level order traversal."
						+ "\n<s>   print the words of the tree in sorted order (use an inorder traversal)."
						+ "\n<r>   print the words of the tree in reverse sorted order."
						+ "\n<!>   to quit."
						+ "\n<c>   <word> to spell check this word"
						+ "\n<a>   <word> add word to tree."
						+ "\n<f>   <fileName> to check a text file for spelling errors.");
			}
			}
			c = in.next();
		}
		in.close();
		System.out.println("Bye");
		
	}
}
