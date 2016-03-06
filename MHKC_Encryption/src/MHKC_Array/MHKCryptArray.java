package MHKC_Array;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

import MHKC_LinkedList.MHKnapsackCrypt;

/**
 * 
 * @author Jiaqi Zhang
 *
 */
public class MHKCryptArray {
	private final int nodes = 80;
	private BigInteger[] w = new BigInteger[8*nodes];
	private BigInteger[] b = new BigInteger[8*nodes];
	private BigInteger r,q;

	/**
	 * Generate w - super-increasing sequence of integers, BigInteger r, q, and sequence b - corresponding public key.<br> 
	 * Pre-condition: none<br>
	 * Post-condition: w, r, q, b are generated<br>
	 * Best case run time complexity: Big Theta(n)<br>
	 * Worst case run time complexity: Big Theta(n)<br>
	 */
	public void generateKey(){
		BigInteger sum= new BigInteger("1");
		Random rnd = new Random();
		for(int i=1; i<=8*nodes; i++){
			w[i-1]=sum;
			sum=sum.add(sum.add(BigInteger.valueOf(1)));
		}
		r = BigInteger.probablePrime(sum.bitLength()+2, rnd);
		q = BigInteger.probablePrime(sum.bitLength()+5, rnd);
		while(q.gcd(r).intValue()!=1){
			r = BigInteger.probablePrime(sum.bitLength()+2, rnd);
		}
		for(int i=1; i<=8*nodes; i++){
			b[i-1]=w[i-1].multiply(r).mod(q);
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
		BigInteger bi = new BigInteger("0");
		for (int i=0;i<bit.length;i++){
			bi=bi.add(b[i].multiply(BigInteger.valueOf(bit[i])));
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
		s=r.modInverse(q);
		// s=PowerMod(r,-1,q)
		k = s.multiply(b).mod(q);
		for(int i=8*nodes-1;i>=0;i--){ //Greedy
			if(k.compareTo(w[i])!=-1){
				k=k.subtract(w[i]);
				decry[i]++;
			}
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
