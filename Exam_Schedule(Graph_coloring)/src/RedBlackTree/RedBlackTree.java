package RedBlackTree;
/**
 * red black tree
 * @author Jiaqi Zhang
 *
 */
public class RedBlackTree {

	public static final int RED = 0;
	public static final int BLACK = 1;
	private static final RedBlackNode NIL = new RedBlackNode();
	private RedBlackNode root;
	private int size, recentComp;
	
	/**
	 * constructor
	 */
	public RedBlackTree(){
		NIL.setColor(BLACK);
		root = NIL;
		size = 0;
		recentComp = 0;
	}
	
	/**
	 * constructor
	 * @param s data for root
	 */
	public RedBlackTree(String s){
		NIL.setColor(BLACK);
		root = new RedBlackNode(s, size++, RED, NIL, NIL, NIL);
		recentComp = 0;
	}
	
	/**
	 * check whether v exits in the tree, if v is found in the tree it returns the index, otherwise returns the new index after insertion<br>
	 * pre-condition: none<br>
	 * post-condition: returns index of v in the tree<br>
	 * best-case time complexity: Big Theta(1)<br>
	 * worst-case time complexity: Big Theta(log(N))
	 * @param v the value to check
	 * @return returns the index of v in the tree
	 */
	public int check(String v){
		if (root == NIL) {
			insert(v);
			return (size - 1);
		}
		RedBlackNode t = root;
		while (t != NIL) {
			if (t.getData().compareTo(v) == 0) {
				return t.getIndex();
			}
			else if (t.getData().compareTo(v) > 0) {
				if (t.getLc() != NIL) {
					t = t.getLc();
				}
				else {
					insert(v);
					return (size - 1);
				}
			}
			else {
				if (t.getRc() != NIL) {
					t = t.getRc();	
				}
				else {
					insert(v);
					return (size - 1);
				}
			}
		}
		return size - 1;
	}
	
	/**
	 * returns true if the String v is in the RedBlackTree and false otherwise<br>
	 * pre-condition: none<br>
	 * post-condition: returns true if the String v is in the RedBlackTree and false otherwise<br>
	 * best-case time complexity: Big Theta(1)<br>
	 * worst-case time complexity: Big Theta(log(N))
	 * @param v string to judge
	 * @return true if the String v is in the RedBlackTree and false otherwise.
	 */
	public boolean contains(String v){
		recentComp = 0;
		RedBlackNode t = root;
		while(t != NIL){
			recentComp++;
			if(t.getData().compareTo(v) == 0)	return true;
			else if (t.getData().compareTo(v) > 0)		t = t.getLc();
			else	t = t.getRc();
		}
		return false;
	}
	
	/**
	 * recent number of compares for the contains method
	 * @return recent number of compares
	 */
	public int getRecentCompares(){
		return recentComp;
	}
	
	/**
	 * get the size of the tree
	 * @return number of values inserted into the tree
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * get the height of the subtree of a red black tree<br>
	 * pre-condition: none<br>
	 * post-condition: return the height of the subtree<br>
	 * time complexity: Big Theta(N)
	 * @param t the root of the subtree
	 * @return height of the subtree
	 */
	private int height(RedBlackNode t){
		if(t == NIL)	return 0;
		if(t.getLc() == NIL && t.getRc()==NIL)	return 0; // root
		return (1+Math.max(height(t.getLc()), height(t.getRc())));
	}
	
	/**
	 * get the height of the red black tree
	 * pre-condition: none<br>
	 * post-condition: return the height of the tree<br>
	 * time complexity: Big Theta(N)
	 * @return height of the tree
	 */
	public int height(){
		return height(root);
	}
	
	/**
	 * Perfrom an inorder traversal of the tree, it is recursive and displays the content of the tree<br>
	 * pre-condition: none<br>
	 * post-condition: tree was traveled in inorder<br>
	 * time complexity: Big Theta(N)
	 * @param t the root of the tree on the first call
	 */
	private void inOrderTraversal(RedBlackNode t){
		if(t == NIL)	return;
		if(t.getLc() != NIL){
			inOrderTraversal(t.getLc());
		}
		System.out.println(t.getData()+"->"+t.getIndex());
		if(t.getRc() != NIL){
			inOrderTraversal(t.getRc());
		}
	}
	
	/**
	 * this no argument inOrderTraversal() method calls the recursive inOrderTraversal(RedBlackNode) - passing the root<br>
	 * pre-condition: none<br>
	 * post-condition: tree was traveled from the root in inorder<br>
	 * time complexity: Big Theta(N)
	 */
	public void inOrderTraversal(){
		inOrderTraversal(root);
	}
	
	/**
	 * Perfrom an reverseorder traversal of the tree, it is recursive and displays the content of the tree<br>
	 * pre-condition: none<br>
	 * post-condition: tree was traveled in reverseorder<br>
	 * time complexity: Big Theta(N)
	 * @param t the root of the tree on the first call
	 */
	public void reverseOrderTraversal(RedBlackNode t){
		if(t == NIL)	return;
		if(t.getRc() != NIL){
			reverseOrderTraversal(t.getRc());
		}
		System.out.println(t);
		if(t.getLc() != NIL){
			reverseOrderTraversal(t.getLc());
		}
	}
	
	/**
	 * this no argument reverseOrderTraversal() method calls the recursive reverseOrderTraversal(RedBlackNode) - passing the root<br>
	 * pre-condition: none<br>
	 * post-condition: tree was traveled from the root in reverseorder<br>
	 * time complexity: Big Theta(N)
	 */
	public void reverseOrderTraversal(){
		reverseOrderTraversal(root);
	}
	
	/**
	 * This method displays the RedBlackTree in level order. It first displays the root. Then it displays all children of the root. Then it displays all nodes on level 3 and so on. It is not recursive. It uses a queue.<br>
	 * pre-condition: none<br>
	 * post-condition: tree was traveled from in level order<br>
	 * time complexity: Big Theta(N)
	 */
	public void levelOrderTraversal(){
		Queue q = new Queue();
		q.enQueue(root);
		int i,nums;
		RedBlackNode t;
		while(!q.isEmpty()){
			nums = q.getNums();
			for(i = 0; i < nums; i++){
				t = (RedBlackNode) q.deQueue();
				System.out.println(t);
				if(t.getLc() != NIL)	q.enQueue(t.getLc());
				if(t.getRc() != NIL)	q.enQueue(t.getRc());
			}
		}
	}
	
	/**
	 * places a data item into the tree<br>
	 * pre-condition: none<br>
	 * post-condition: data item was placed into the tree<br>
	 * best-case time complexity: Big Theta(log(N))<br>
	 * worst-case time complexity: Big Theta(log(N))
	 * @param value data item
	 */
	public void insert(String value){
		RedBlackNode x = root, y = NIL, z = new RedBlackNode();
		z.setData(value);
		while(x != NIL){
			y = x;
			if(z.getData().compareTo(x.getData()) < 0){
				x = x.getLc();
			}
			else{
				x = x.getRc();
			}
		}
		z.setP(y);
		if(y == NIL){
			root = z;
		}
		else{
			if( z.getData().compareTo(y.getData()) < 0){
				y.setLc(z);
			}
			else{
				y.setRc(z);
			}
		}
		z.setLc(NIL);
		z.setRc(NIL);
		z.setColor(RED);
		z.setIndex(size++);
		RBInsertFixup(z);
	}
	
	/**
	 * performs a single left rotation
	 * pre-condition: none
	 * post-condition: the subtree of x node was left rotated
	 * time complexity: Big Theta(1)
	 * @param x the node to be left rotated
	 */
	private void leftRotate(RedBlackNode x){
		RedBlackNode y = x.getRc();
		x.setRc(y.getLc());
		if(y.getLc() != NIL)	y.getLc().setP(x);
		y.setP(x.getP());
		
		if(x.getP() == NIL){// if x is at root then y becomes new root
			root = y;
		}
		else{
			if(x == x.getP().getLc())	x.getP().setLc(y);// if x is a left child then adjust x's parent's left child
			else	x.getP().setRc(y);// if x is a right child then adjust x's parent's right child
		}
		y.setLc(x);
		x.setP(y);
		
	}

	/**
	 * performs a single right rotation
	 * pre-condition: none
	 * post-condition: the subtree of x node was right rotated
	 * time complexity: Big Theta(1)
	 * @param x the node to be right rotated
	 */
	public void rightRotate(RedBlackNode x){
		RedBlackNode y = x.getLc();
		x.setLc(y.getRc());
		y.getRc().setP(x);
		y.setP(x.getP());
		
		if(x.getP() == NIL){// if x is at root then y becomes new root
			root = y;
		}
		else{
			if(x == x.getP().getLc())	x.getP().setLc(y);// if x is a left child then adjust x's parent's left child
			else	x.getP().setRc(y);// if x is a right child then adjust x's parent's right child
		}
		y.setRc(x);
		x.setP(y);	
	}

	/**
	 * fixing up the tree so that Red Black properties are preserved
	 * pre-condition: none
	 * post-condition: the tree was fixed up so that red black properties are preserved
	 * best-case time complexity: Big Theta(1)
	 * worst-case time complexity: Big Theta(log(N))
	 * @param z the new node inserted
	 */
	 private void RBInsertFixup(RedBlackNode z){
		 RedBlackNode y;
		 while(z.getP().getColor() == RED){
			 if(z.getP() == z.getP().getP().getLc()){
				 y = z.getP().getP().getRc();
					 if(y.getColor() == RED){
						 z.getP().setColor(BLACK);
						 y.setColor(BLACK);
						 z.getP().getP().setColor(RED);
						 z = z.getP().getP();
					 }
					 else{
						 if(z == z.getP().getRc()){
							 z = z.getP();
							 leftRotate(z);
						 }
						 z.getP().setColor(BLACK);
						 z.getP().getP().setColor(RED);
						 rightRotate(z.getP().getP());
					 }
			 }
			 else{
				 y = z.getP().getP().getLc();
					 if(y.getColor() == RED){
						 z.getP().setColor(BLACK);
						 y.setColor(BLACK);
						 z.getP().getP().setColor(RED);
						 z = z.getP().getP();
					 }
					 else{
						 if(z == z.getP().getLc()){
							 z = z.getP();
							 rightRotate(z);
						 }
						 z.getP().setColor(BLACK);
						 z.getP().getP().setColor(RED);
						 leftRotate(z.getP().getP());					 
					 }
			 }
		 }
		 root.setColor(BLACK);
	 }
	 

	 /**
	  * test the red black tree
	  * @param args no command line arguments
	  */
	 public static void main(String[] args) {

		 RedBlackTree rbt = new RedBlackTree();
		 for(int j = 1; j <= 5; j++) rbt.insert(""+j);
		 System.out.println("RBT in order");
		 rbt.inOrderTraversal();
		 System.out.println("RBT reverse order");
		 rbt.reverseOrderTraversal();
		 System.out.println("RBT level order");
		 rbt.levelOrderTraversal();
		 if(rbt.contains(""+3)) System.out.println("Found 3");
		 else System.out.println("No 3 found"); 
		 System.out.println("The height is " + rbt.height());   
	 }
			  
}
