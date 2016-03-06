package MHKC_LinkedList;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Jiaqi Zhang
 *
 */
public class MHKnapsackCrypt {
	private SinglyLinkedList w = new SinglyLinkedList();
	private SinglyLinkedList b = new SinglyLinkedList();
	private BigInteger r,q;
	
	private final int nodes = 80;
	
	/**
	 * Generate w - superincreasing sequence of integers, BigInteger r, q, and sequence b - corresponding public key.<br> 
	 * Pre-condition: none<br>
	 * Post-condition: w, r, q, b are generated<br>
	 * Best case run time complexity: Big Theta(n)<br>
	 * Worst case run time complexity: Big Theta(n)<br>
	 */
	public void generateKey(){
		BigInteger sum= new BigInteger("1");
		Random rnd = new Random();
		SingleNodeBI wh;
		for(int i=1; i<=8*nodes; i++){
			w.addBIAtEnd(sum);
			sum=sum.add(sum.add(BigInteger.valueOf(1)));
		}
		wh = w.getHead();
		r = BigInteger.probablePrime(sum.bitLength()+2, rnd);
		q = BigInteger.probablePrime(sum.bitLength()+5, rnd);
		while(q.gcd(r).intValue()!=1){
			r = BigInteger.probablePrime(sum.bitLength()+2, rnd);
		}
		for(int i=1; i<=8*nodes; i++){
			b.addBIAtEnd(wh.getB().multiply(r).mod(q));
			wh=wh.getNext();
		}
	}
	
	/**
	 * Encrypt String s to a BigInteger<br>
	 * Pre-condition: s is not null<br>
	 * Post-condition: return a BigInteger as encrypted s<br>
	 * Best case run time complexity: Big Theta(n)<br>
	 * Worst case run time complexity: Big Theta(n)<br>
	 * @param s String to be encrypted
	 * @return encrypted big integer
	 */
	public BigInteger encrypt(String s){
		byte[] bt = s.getBytes();
		byte[] bit = new byte[8*bt.length];
		for(int i=0;i<bt.length;i++){
			for (int j=0;j<8;j++){
				bit[8*i+j] = (byte)(((1<<(7-j) & bt[i])==0)? 0:1);
			}
		}
		SingleNodeBI bint = b.getHead();
		BigInteger bi = new BigInteger("0");
		for (int i=0;i<bit.length;i++){
			bi=bi.add(bint.getB().multiply(BigInteger.valueOf(bit[i])));
			bint=bint.getNext();
		}
		return bi;
	}
	
	/**
	 * Decrypt BigInteger b to a string<br>
	 * Pre-condition: b is not null<br>
	 * Post-condition: return a string as decrypted b<br>
	 * Best case run time complexity: Big Theta(n)<br>
	 * Worst case run time complexity: Big Theta(n)<br>
	 * @param b BigInteger to be decrpted
	 * @return decrpted string
	 */
	public String decrypt(BigInteger b){
		BigInteger k,s;
		byte[] decry = new byte[8*nodes];
		byte[] bt = new byte[nodes];
		w.reverse();
		SingleNodeBI wt = w.getHead();
		s=r.modInverse(q);
		k = s.multiply(b).mod(q);
		for(int i=8*nodes-1;i>=0;i--){ 
			//Greedy Algorithm
			if(k.compareTo(wt.getB())!=-1){
				k=k.subtract(wt.getB());
				decry[i]++;
			}
			wt=wt.getNext();
		}
		for(int i=0;i<nodes;i++){
			for (int j=0;j<8;j++){
				bt[i]+=decry[8*i+j]<<(7-j);
			}
		}
		String str=new String(bt);
		return str;
	}
	
	public static void main(String[] args){
		
		MHKnapsackCrypt mhc = new MHKnapsackCrypt();
		BigInteger bint;
		mhc.generateKey();
		System.out.println("Enter a string and I will encrypt it as single large integer:");
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		System.out.println("Clear text:\n"+s);
		System.out.println("Number of clear text bytes = "+s.length());
		while(s.length()>80){
			System.out.println("The string entered is too long, please try again:");
			s = in.nextLine();
			System.out.println("Clear text:\n"+s);
			System.out.println("Number of clear text bytes ="+s.length());
		}
		in.close();
		bint=mhc.encrypt(s);
		System.out.println(s+" is encrypted as:");
		System.out.println(bint);
		System.out.println("Result of decryption: "+mhc.decrypt(bint));

	}
}
