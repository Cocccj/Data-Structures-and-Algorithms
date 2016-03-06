package RedBlackTree;
/**
 * queue class
 * @author Jiaqi Zhang
 * 
 */
public class Queue {
	
	private java.lang.Object[] q;
	private int front, rear, size, nums;
	
	/**
	 * Constructor, create an empty queue that lives in a small array<br>
	 * pre-condition: memory is available<br>
	 * post-condition: array created and indexes established
	 */
	public Queue(){
		size = 10;
		q = new java.lang.Object[size];
		front = 0;
		rear = 0;
		nums = 0;
	}
	
	/**
	 * determine whether the queue is empty<br>
	 * time complexity : Big Theta(1)<br>
	 * pre-condition: none<br>
	 * post-condition: return true if the queue is empty false otherwise
	 * @return true if it is empty false otherwise
	 */
	public boolean isEmpty(){
		if(nums == 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * determine whether the queue is full<br>
	 * time complexity : Big Theta(1)<br>
	 * pre-condition: none <br>
	 * post-condition: return true if the queue is full false otherwise
	 * @return true if it is full false otherwise
	 */
	public boolean isFull(){
		if(nums == size){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * dequeue first element from the queue<br>
	 * pre-condition: the queue is not empty<br>
	 * post-condition: first element dequeued and returned<br>
	 * time complexity: Big Theta(1)
	 * @return the element dequeued
	 */
	public java.lang.Object deQueue(){
		java.lang.Object answer = q[front];
		front = (front+1)%size;
		nums--;
		return answer;
	}
	
	/**
	 * enqueue an element to the rear of the queue<br>
	 * pre-condition: there is enough memory<br>
	 * post-condition: an element added to the rear of the queue<br>
	 * best-case time complexity: Big Theta(1)<br>
	 * worst-case time complexity: Big Theta(n)
	 */
	public void enQueue(java.lang.Object x){
		if(isFull()){
			Object[] newq = new Object[size*2];
			for(int i=0; i<size; i++){
				newq[front+i] = q[(front+i)%size];
			}
			q = newq;
			rear = front+size-1;
			size *= 2;
		}
		if(isEmpty()){
			front = 0;
			rear = 0;
		}
		else{
			rear = (rear+1)%size;
		}
		q[rear] = x;
		nums++;
	}
	
	/**
	 * get the front element of the queue<br>
	 * pre-condition: the queue is not empty<br>
	 * post-condition: return the first element of the queue<br>
	 * time complexity: Big Theta(1)
	 * @return the queue front without removal
	 */
	public java.lang.Object getFront(){
		return q[front];
	}
	
	/**
	 * get the number of elements in the queue<br>
	 * pre-condition: none<br>
	 * post-condition: return the number of elements in the queue<br>
	 * time complexity: Big Theta(1)
	 * @return the number of elements in the queue
	 */
	public int getNums(){
		return nums;
	}
	
	/**
	 * to string method<br>
	 * pre-condition: none<br>
	 * post-condition: return a string of the information of this queue<br>
	 * time complexity: Big Theta(n)
	 * @return a string representation of the queue.
	 */
	public java.lang.String toString(){
		String s = "Queue size: "+size+", Number of objects: "+nums+", front index: "+front+", rear index: "+rear+"\nContent: ";
		int f=front, r=rear;
		while(f!=r){
			s+=(q[f]+" ");
			f=(f+1)%size;
		}
		s+=(q[f]+" ");
		return s;
	}
	
	/**
	 * main is for testing the queue routines<br>
	 * @param a Command line parameters are not used
	 */
	public static void main(java.lang.String[] a){
		Queue q = new Queue();
		System.out.println("isEmpty: "+q.isEmpty());
		q.enQueue("s1");
		System.out.println("isEmpty: "+q.isEmpty());
		System.out.println(q);
		q.enQueue("s2");
		System.out.println("isFull: "+q.isFull());
		System.out.println(q);
		q.deQueue();
		System.out.println(q);
		for(int i=2; i<=10; i++){
			q.enQueue("s3");
		}
		System.out.println(q);
		System.out.println("isFull: "+q.isFull());
		q.enQueue("s4");
		System.out.println(q);
		System.out.println("getFront: "+q.getFront());
			
	}
	
}
