package MHKC_LinkedList;
import java.math.BigInteger;

/**
 * 
 * @author Jiaqi Zhang
 *
 */
public class SinglyLinkedList {
	private SingleNodeBI head;
	private SingleNodeBI tail;
	
	/**
	 * Constructs a new SinglyLinkedList object with head and tail as null.
	 */
	public SinglyLinkedList(){
		head=null;
		tail=null;
	}
	
	/**
	 * 
	 * @return true if the list is empty false otherwise
	 */
	public boolean isEmpty(){
		if(head==null || tail==null){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * get the head of the list<br>
	 * pre-condition:none<br>
	 * post-condition: return the head of the list<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * @return the head node of the list
	 */
	public SingleNodeBI getHead(){
		return head;
	}
	
	/**
	 * get the tail of the list<br>
	 * pre-condition:none<br>
	 * post-condition: return the tail of the list<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * @return the end node of the list
	 */
	public SingleNodeBI getTail(){
		return tail;
	}
	
	/**
	 * Add a BigInteger node containing the BigInteger i to the end of the linked list.<br>
	 * Pre-condition: none<br>
	 * Post-condition: add BigInteger i to the end<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * @param i BigInteger node to be added at end of the list
	 */
	public void addBIAtEnd(BigInteger i){
		if(isEmpty()){
			SingleNodeBI n = new SingleNodeBI();
			n.setB(i);
			head=n;
			tail=n;
		}
		else{
			SingleNodeBI d = new SingleNodeBI(i,null);
			tail.setNext(d);
			tail=d;
		}
	}
	
	/**
	 * Add a BigInteger node containing the BigInteger i to the front of the linked list.<br>
	 * Pre-condition: none<br>
	 * Post-condition: add BigInteger i to the front<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * @param i BigInteger node to be added to the front of the list<br>
	 */
	public void addBIAtFront(BigInteger i){
		if(isEmpty()){
			SingleNodeBI n = new SingleNodeBI();
			n.setB(i);
			head=n;
			tail=n;
		}
		else{
			SingleNodeBI f = new SingleNodeBI(i,head);
			head=f;
		}
	}
	
	/**
	 * Remove and return the BigInteger at the front of the singly linked list.<br>
	 * Pre-condition: the list is not empty<br>
	 * Post-condition: return the BigInteger at the front <br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * @return value of BigInteger removed from front
	 */
	public BigInteger removeBIFromFront(){
		BigInteger i = head.getB();
		SingleNodeBI t;
		if(head.getNext()!=null){
			t=head.getNext();
			head.setNext(null);
			head=t;
		}
		else{
			head=null;
			tail=null;
		}
		
		return i;
	}
	
	/**
	 * Remove and return the BigInteger at the end of the singly linked list.<br>
	 * Pre-condition: the list is not empty<br>
	 * Post-condition: return the BigInteger at the end <br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * @return value of BigInteger removed from the end
	 */
	public BigInteger removeBIAtEnd(){
		BigInteger i= tail.getB();
		if(head!=tail){
			SingleNodeBI t = head;
			while(t.getNext()!=tail){
				t=t.getNext();
			}
			t.setNext(null);
		}
		else{
			tail=null;
			head=null;
		}
		return i;
	}
	
	/**
	 * Counts the number of nodes in the list.<br>
	 * Pre-condition: none<br>
	 * Post-condition: return the number of nodes in the list<br>
	 * Best case run time complexity: Big Theta(n)<br>
	 * Worst case run time complexity: Big Theta(n)<br>
	 * @return the number of nodes in the list
	 */
	public int countNodes(){
		if(isEmpty()){
			return 0;
		}
		int cnt=0;
		SingleNodeBI n = head;
		while(n!=null){
			n=n.getNext();
			cnt++;
		}
		return cnt;
	}
	
	/**
	 * Deletes the first occurence of the BigInteger i from the list.<br>
	 * Pre-condition: the list is not empty<br>
	 * Post-condition: return true if i exists ,and first occurence of the i is deleted from the list, or return false if i does not exist.<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(n)<br>
	 * @param i the value of BigInteger to delete
	 * @return return true if i exists and false otherwise
	 */
	public boolean deleteBI(BigInteger i){
		if(isEmpty()){
			return false;
		}
		if(head.getB() == i){
			head=head.getNext();
			return true;
		}
		SingleNodeBI t,n = head;
		while(n.getNext()!=null){
			t=n.getNext();
			if(t.getB() == i){
				if(t==tail){
					n.setNext(null);;
					tail=n;
				}
				else{
					n.setNext(t.getNext());
					t.setNext(null);
				}
				return true;
			}
			n=n.getNext();
		}
		return false;
	}
	
	/**
	 * Reverse the list.<br>
	 * Pre-condition: none<br>
	 * Post-condition: reverse the list.<br>
	 * Best case run time complexity: Big Theta(n)<br>
	 * Worst case run time complexity: Big Theta(n)<br>
	 */
    public void reverse() {
        if(head==null||head.next==null){
            return;
        }
        SingleNodeBI h,p;
        p=head.next;
        head.next=null;
        while(p!=null){
            h=head;
            head=p;
            if(head.next!=null){
                p=head.next;
            }
            else{
                p=null;
            }
            head.next=h;
        }
    }
}

/**
 * 
 * @author Jiaqi Zhang
 *
 */
class SingleNodeBI {
	BigInteger b;
	SingleNodeBI next;
	
	/**
	 * Constructor with no arguments. 
	 */
	public SingleNodeBI(){
		//
		b=null;
		next=null;
	}
	
	/**
	 * Constructor
	 * @param b is a BigInteger for this node.
	 * @param next is a pointer to a next node.
	 */
	public SingleNodeBI(BigInteger b,SingleNodeBI next){
		this.b=b;
		this.next=next;
	}
	
	/**
	 * Get next node.<br>
	 * Pre-condition: none<br>
	 * Post-condition: returns a pointer to the next node or null if none exists<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * @return a pointer to the next node or null if none exists
	 */
	public SingleNodeBI getNext(){
		return next;
	}
	
	/**
	 * Set a pointer to the next node.<br>
	 * Pre-condition: none<br>
	 * Post-condition: pointer to the next node is set<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * @param n SingleNode to be set
	 */
	public void setNext(SingleNodeBI n){
		next=n;
	}
	
	/**
	 * Returns the BigInteger of the node.<br>
	 * Pre-condition: none<br>
	 * Post-condition: returns the BigInteger of the node<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * @return the BigInteger of the node
	 */
	public BigInteger getB(){
		return b;
	}
	
	/**
	 * Set k to the node.<br>
	 * Pre-condition: none<br>
	 * Post-condition: k is set as the BigInteger of the node<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * @param k is assigned to this node
	 */
	public void setB(BigInteger k){
		b=k;
	}
	
}
