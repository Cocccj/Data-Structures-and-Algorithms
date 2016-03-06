/**
 * red black tree node class
 * @author Jiaqi Zhang
 *
 */
public class RedBlackNode {

	public static final int RED = 0;
	public static final int BLACK = 1;
	
	private String data;
	private int color;
	RedBlackNode p;
	RedBlackNode lc;
	RedBlackNode rc;
	
	/**
	 * default constructor
	 */
	RedBlackNode(){
		this.data = "-1";
		this.color = RED;
		this.p = this;
		this.lc = this;
		this.rc = this;
	}
	
	/**
	 * construct a RedBlackNode with data, color, parent pointer, left child pointer and right child pointer
	 * @param data a simple value held in the tree
	 * @param color either RED or BLACK
	 * @param p the parent pointer
	 * @param lc the pointer to the left child (will be null only for the node that represents all external nulls
	 * @param rc the pointer to the right child (will be null only for the node that represents all external nulls
	 */
	RedBlackNode(String data, int color, RedBlackNode p, RedBlackNode lc, RedBlackNode rc){
		this.data = data;
		this.color = color;
		this.p = p;
		this.lc = lc;
		this.rc = rc;
	}
	
	/**
	 * get the color of the node<br>
	 * pre-condtion: none<br>
	 * post-condition: return the color of the node<br>
	 * time complexity: Big Theta(1)
	 * @return the color of the node
	 */
	public int getColor(){
		return color;
	}
	
	/**
	 * get the data of the node<br>
	 * pre-condtion: none<br>
	 * post-condition: return the data of the node<br>
	 * time complexity: Big Theta(1)
	 * @return the data of the node
	 */
	public String getData(){
		return data;
	}
	
	/**
	 * get the left child of the node<br>
	 * pre-condtion: none<br>
	 * post-condition: return the left child of the node<br>
	 * time complexity: Big Theta(1)
	 * @return the left child of the node
	 */
	public RedBlackNode getLc(){
		return lc;
	}
	
	/**
	 * get the parent node of the node<br>
	 * pre-condtion: none<br>
	 * post-condition: return the parent node of the node<br>
	 * time complexity: Big Theta(1)
	 * @return the parent node of the node
	 */
	public RedBlackNode getP(){
		return p;
	}	
	
	/**
	 * get the right child of the node<br>
	 * pre-condtion: none<br>
	 * post-condition: return the right child of the node<br>
	 * time complexity: Big Theta(1)
	 * @return the right child of the node
	 */
	public RedBlackNode getRc(){
		return rc;
	}
	
	/**
	 * set color of the node<br>
	 * time complexity: Big Theta(1)
	 * @param color is either RED or BLACK
	 */
	public void setColor(int color){
		this.color = color;
	}

	/**
	 * set data of the node<br>
	 * time complexity: Big Theta(1)
	 * @param data
	 */
	public void setData(String data){
		this.data = data;
	}
	
	/**
	 * set left child of the node<br>
	 * time complexity: Big Theta(1)
	 * @param lc left child
	 */
	public void setLc(RedBlackNode lc){
		this.lc = lc;
	}
	
	/**
	 * set parent of the node<br>
	 * time complexity: Big Theta(1)
	 * @param p parent
	 */
	public void setP(RedBlackNode p){
		this.p = p;
	}
	
	/**
	 * set right child of the node<br>
	 * time complexity: Big Theta(1)
	 * @param rc right child
	 */
	public void setRc(RedBlackNode rc){
		this.rc = rc;
	}
	
	/**
	 * to string method<br>
	 * time complexity: Big Theta(1)
	 * @return the string representation of a RedBlackNode
	 */
	public String toString(){
		return ("[Data: = "+data+": Color = "+(color==0? "Red" : "Black")+": Parent = "+ p.getData() +": LC = "+(lc == null? -1 : lc.getData())+": RC = "+(rc == null? -1 : rc.getData())+"]");
	}
	
	
	

}
