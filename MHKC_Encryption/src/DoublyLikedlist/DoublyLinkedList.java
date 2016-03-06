package DoublyLikedlist;
/**
 * 
 * @author Jiaqi Zhang
 *
 */
public class DoublyLinkedList {
	private DoubleNode head;
	private DoubleNode tail;
	
	/**
	 * Constructs a new DoublyLinkedList object with head and tail as null.
	 */
	public DoublyLinkedList(){
		head=null;
		tail=null;
	}
	
	/**
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
	 * Add a character node containing the character c to the end of the linked list.<br>
	 * Pre-condition: none<br>
	 * Post-condition: add character c to the end<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)
	 * @param c the character to set at the end of the linked list.
	 */
	public void addCharAtEnd(char c){
		if(isEmpty()){
			DoubleNode n = new DoubleNode();
			n.setC(c);
			head=n;
			tail=n;
		}
		else{
			DoubleNode d = new DoubleNode(tail,c,null);
			tail.setNext(d);
			tail=d;
		}
	}
	
	/**
	 * Add a character node containing the character c to the front of the linked list.<br>
	 * Pre-condition: none<br>
	 * Post-condition: add character c to the front<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)
	 * @param c the character to set at the front of the linked list.
	 */
	public void addCharAtFront(char c){
		if(isEmpty()){
			DoubleNode n = new DoubleNode();
			n.setC(c);
			head=n;
			tail=n;
		}
		else{
			DoubleNode f = new DoubleNode(null,c,head);
			head.setPrev(f);
			head=f;
		}
	}
	
	/**
	 * Remove and return the character at the front of the doubly linked list.<br>
	 * Pre-condition: the list is not empty<br>
	 * Post-condition: return the character from the front which is deleted<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)<br>
	 * @return the character at the front of the doubly linked list.
	 */
	public char removeCharFromFront(){
		char c = head.getC();
		DoubleNode t;
		if(head.getNext()!=null){
			t=head.getNext();
			t.setPrev(null);
			head.setNext(null);
			head=t;
		}
		else{
			head=null;
			tail=null;
		}
		
		return c;
	}
	
	/**
	 * Remove and return the character at the end of the doubly linked list.<br>
	 * Pre-condition: the list is not empty<br>
	 * Post-condition: return the character at the end which is deleted<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(1)
	 * @return the character at the end of the doubly linked list.
	 */
	public char removeCharAtEnd(){
		char c = tail.getC();
		DoubleNode t;
		if(tail.getPrev()!=null){
			t=tail.getPrev();
			t.setNext(null);
			tail.setPrev(null);
			tail=t;
		}
		else{
			tail=null;
			head=null;
		}
		return c;
	}
	
	/**
	 * Counts the number of nodes in the list.<br>
	 * Pre-condition: none<br>
	 * Post-condition: return the number of nodes in the list<br>
	 * Best case run time complexity: Big Theta(n)<br>
	 * Worst case run time complexity: Big Theta(n)
	 * @return the number of nodes in the list
	 */
	public int countNodes(){
		if(isEmpty()){
			return 0;
		}
		int cnt=0;
		DoubleNode n = head;
		while(n!=null){
			n=n.getNext();
			cnt++;
		}
		return cnt;
	}
	
	/**
	 * Deletes the first occurence of the character c from the list.<br>
	 * Pre-condition: the list is not empty<br>
	 * Post-condition: return true if c exists ,and first occurence of the c is deleted from the list, or return false if c does not exist.<br>
	 * Best case run time complexity: Big Theta(1)<br>
	 * Worst case run time complexity: Big Theta(n)<br>
	 * @param c the character to delete
	 * @return return true if c exists and false otherwise
	 */
	public boolean deleteChar(char c){
		if(isEmpty()){
			return false;
		}
		DoubleNode t,n = head;
		while(n!=null){
			if(n.getC() == c){
				if(n==head){
					t=head.getNext();
					if(t!=null){
						t.setPrev(null);
						head.setNext(null);
					}
					head=t;
				}
				else if(n==tail){
					t=tail.getPrev();
					if(t!=null){
						t.setNext(null);
						tail.setPrev(null);
					}
					tail=t;
				}
				else{
					n.getPrev().setNext(n.getNext());
					n.getNext().setPrev(n.getPrev());
				}
				return true;
			}
			n=n.getNext();
		}
		return false;
	}
	
	/**
	 * Returns the list as a String, null pointers are different from non-null pointers.<br>
	 * Pre-condition: none<br>
	 * Post-condition: returns the list as a string.<br>
	 * Best case run time complexity: Big Theta(n)<br>
	 * Worst case run time complexity: Big Theta(n)
	 */
	public java.lang.String toString(){
		if(isEmpty()){
			return null;
		}
		String s = new String();
		DoubleNode n = head;
		while(n!=null){
			if(n.getNext()!=null){
				s+=n.toString()+"-->";
			}
			else{
				s+=n.toString()+"--||";
			}
			n=n.getNext();
		}
		return s;
	}
	
	/**
	 * Reverse the list.<br>
	 * Pre-condition: none<br>
	 * Post-condition: reverse the list.<br>
	 * Best case run time complexity: Big Theta(n)<br>
	 * Worst case run time complexity: Big Theta(n)<br>
	 */
	public void reverse(){
		if(!isEmpty() && head != tail){
			DoubleNode temp,n = head;
			temp = tail;
			tail = head;
			head = temp;
			while(n!=null){
				temp=n.getPrev();
				n.setPrev(n.getNext());
				n.setNext(temp);
				n=n.getPrev();
			}
		}
	}

/**
 * Test driver for DoublyLinkedList
 * @param a
 */
public static void main(String a[]) {
		
		DoublyLinkedList list = new DoublyLinkedList();
		list.addCharAtEnd('H');
		list.addCharAtEnd('e');
		list.addCharAtEnd('l');
		list.addCharAtEnd('l');
		list.addCharAtEnd('o');
		System.out.println(list);
		System.out.println("Deleting l");
		list.deleteChar('l');
		System.out.println(list);
		System.out.println("Deleting H");
		list.deleteChar('H');
		System.out.println(list);
		System.out.println("Deleting o");
		list.deleteChar('o');
		System.out.println(list);
		System.out.println("Deleting e");
		list.deleteChar('e');
		System.out.println(list);
		System.out.println("Deleting l");
		list.deleteChar('l');
		System.out.println(list);
		list.addCharAtFront('o');
		list.addCharAtFront('l');
		list.addCharAtFront('l');
		list.addCharAtFront('e');
		list.addCharAtFront('H');
		System.out.println(list);
		
		System.out.println(list.countNodes());
		
		System.out.println("Popping everything");
		while(!list.isEmpty()){
			System.out.println(list.removeCharFromFront());
		}
		
		list.addCharAtFront('o');
		list.addCharAtFront('l');
		list.addCharAtFront('l');
		list.addCharAtFront('e');
		list.addCharAtFront('H');
		
		System.out.println("Popping everything from the end");
		while(!list.isEmpty()){
			System.out.println(list.removeCharAtEnd());
		}
		System.out.println(list.countNodes());
		
		list.addCharAtEnd('o');
		list.addCharAtEnd('l');
		list.addCharAtEnd('l');
		list.addCharAtEnd('e');
		list.addCharAtEnd('H');
	
		list.reverse();
		System.out.println(list);
		
		list.reverse();
		System.out.println(list);
		
	}
}
