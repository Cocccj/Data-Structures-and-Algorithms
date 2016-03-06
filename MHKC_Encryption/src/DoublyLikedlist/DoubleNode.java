package DoublyLikedlist;

/**
 * 
 * @author Jiaqi Zhang
 *
 */
public class DoubleNode {
	private DoubleNode p;
    private char ch;
    private DoubleNode n;
    
    /**
     * Constructor with no arguments. <br>
     * Assign null values to previous, next and the null character to c.
     */
	public DoubleNode(){
		p=null;
		ch=0;
		n=null;
	}
	
	/**
	 * Constructor
	 * @param p is a pointer to a previous node
	 * @param ch is a character for this node.
	 * @param n is a pointer to a next node.
	 */
	public DoubleNode(DoubleNode p,char ch,DoubleNode n){
		this.p=p;
		this.ch=ch;
		this.n=n;
	}
	
	/**
	 * get a pointer to the previous node or null if none exists<br>
	 * Pre-condition: none<br>
	 * Post-condition: returns a pointer to the previous node or null if none exists<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)
	 * @return a pointer to the previous node or null if none exists
	 */
	public DoubleNode getPrev(){
		return p;
	}
	
	/**
	 * Set a pointer to the previous node<br>
	 * Pre-condition: none<br>
	 * Post-condition: pointer to the previous node is set<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)
	 * @param prev a pointer to set as the previous node
	 */
	public void setPrev(DoubleNode prev){
		p=prev;
	}
	
	/**
	 * get a pointer to the next node or null if none exists<br>
	 * Pre-condition: none<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * Post-condition: returns a pointer to the next node or null if none exists
	 * @return a pointer to the next node or null if none exists
	 */
	public DoubleNode getNext(){
		return n;
	}
	
	/**
	 * set a pointer to the next node<br>
	 * Pre-condition: none<br>
	 * Post-condition: pointer to the next node is set<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)
	 * @param next a pointer to set as the next node
	 */
	public void setNext(DoubleNode next){
		n=next;
	}
	
	/**
	 * get the character of the node<br>
	 * Pre-condition: none<br>
	 * Post-condition: returns the character of the node<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)
	 * @return the character of the node
	 */
	public char getC(){
		return ch;
	}
	
	/**
	 * set the character of the node<br>
	 * Pre-condition: none<br>
	 * Post-condition: c is set as the character of the node<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)
	 * @param c is assigned to this node
	 */
	public void setC(char c){
		ch=c;
	}
	
	/**
	 * Overrides: toString in class java.lang.Object
	 */
	public java.lang.String toString(){
		String s = new String();
		s+=ch;
		return s;
	}

	/**
	 * Test driver for DoubleNode
	 * @param a
	 */
	public static void main(String a[]) {
		DoubleNode p = new DoubleNode();
		DoubleNode n = new DoubleNode(null,'n',null);
		DoubleNode m = new DoubleNode(p,'m',n);
		p.setNext(m);
		p.setPrev(null);
		p.setC('p');
		n.setPrev(m);
		DoubleNode test=p;
		while(test!=null){
			System.out.println(test.getC());
			test=test.getNext();
		}
	}
}
