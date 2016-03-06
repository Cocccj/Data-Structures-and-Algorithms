
/**
 * Singly Linked List
 * @author Jiaqi Zhang
 *
 */
public class LinkedList {
	private ListNode head;
	private ListNode tail;
	
	/**
	 * Constructs a new SinglyLinkedList object with head and tail as null.
	 */
	public LinkedList(){
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
	public ListNode getHead(){
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
	public ListNode getTail(){
		return tail;
	}
	
	/**
	 * Add a node containing  i to the end of the linked list.<br>
	 * Pre-condition: none<br>
	 * Post-condition: add i to the end<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * @param i value of node to be added at end of the list
	 */
	public void addAtEnd(int i){
		if(isEmpty()){
			ListNode n = new ListNode();
			n.setInt(i);
			head=n;
			tail=n;
		}
		else{
			ListNode d = new ListNode(i,null);
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
	public void addAtFront(int i){
		if(isEmpty()){
			ListNode n = new ListNode();
			n.setInt(i);
			head=n;
			tail=n;
		}
		else{
			ListNode f = new ListNode(i,head);
			head=f;
		}
	}
	
	/**
	 * Remove and return the value at the front of the singly linked list.<br>
	 * Pre-condition: the list is not empty<br>
	 * Post-condition: return the value at the front <br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * @return value of node removed from front
	 */
	public int removeFromFront(){
		int i = head.getInt();
		ListNode t;
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
	 * Remove and return the value at the end of the singly linked list.<br>
	 * Pre-condition: the list is not empty<br>
	 * Post-condition: return the value at the end <br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * @return value of the node removed from the end
	 */
	public int removeAtEnd(){
		int i= tail.getInt();
		if(head!=tail){
			ListNode t = head;
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
		ListNode n = head;
		while(n!=null){
			n=n.getNext();
			cnt++;
		}
		return cnt;
	}
	
	/**
	 * Deletes the first occurence of i from the list.<br>
	 * Pre-condition: the list is not empty<br>
	 * Post-condition: return true if i exists ,and first occurence of the i is deleted from the list, or return false if i does not exist.<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(n)<br>
	 * @param i the value of the node to delete
	 * @return return true if i exists and false otherwise
	 */
	public boolean deleteNode(int i) {
		if (isEmpty()) {
			return false;
		}
		if (head.getInt() == i) {
			head = head.getNext();
			return true;
		}
		ListNode t,n = head;
		while (n.getNext() != null) {
			t = n.getNext();
			if (t.getInt() == i) {
				if (t == tail) {
					n.setNext(null);;
					tail = n;
				}
				else {
					n.setNext(t.getNext());
					t.setNext(null);
				}
				return true;
			}
			n = n.getNext();
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
        if (head == null || head.getNext() == null) {
            return;
        }
        ListNode h,p;
        p = head.getNext();
        head.getNext().setNext(null);
        while (p != null) {
            h = head;
            head = p;
            if(head.getNext() != null) {
                p = head.getNext();
            }
            else {
                p = null;
            }
            head.getNext().setNext(h);;
        }
    }
}

/**
 * 
 * @author Jiaqi Zhang
 *
 */
class ListNode {
	private int i;
	private ListNode next;
	
	/**
	 * Constructor with no arguments. 
	 */
	public ListNode(){
		i = 0;
		next = null;
	}
	
	/**
	 * Constructor
	 * @param str is a string for this node.
	 * @param next is a pointer to a next node.
	 */
	public ListNode(int i,ListNode next){
		this.i = i;
		this.next = next;
	}
	
	/**
	 * Get next node.<br>
	 * Pre-condition: none<br>
	 * Post-condition: returns a pointer to the next node or null if none exists<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * @return a pointer to the next node or null if none exists
	 */
	public ListNode getNext(){
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
	public void setNext(ListNode n){
		next = n;
	}
	
	/**
	 * Returns the value of the node.<br>
	 * Pre-condition: none<br>
	 * Post-condition: returns the string of the node<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * @return the value of the node
	 */
	public int getInt(){
		return i;
	}
	
	/**
	 * Set i to the node.<br>
	 * Pre-condition: none<br>
	 * Post-condition: i is set to the node<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * @param i is assigned to this node
	 */
	public void setInt(int i){
		this.i = i;
	}
	
}
