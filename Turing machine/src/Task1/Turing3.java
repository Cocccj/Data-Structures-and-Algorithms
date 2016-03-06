package Task1;

import java.util.Scanner;

/**
 * Third turing machine simulation.
 * Simulate a Turing machine that reads a series of one’s and makes a copy of those one’s to the same tape.
 * @author Jiaqi Zhang
 *
 */
public class Turing3 {

	/**
	 * Main driver, define the turing machine and test it.
	 * @param args no argument
	 */
	public static void main(String[] args) {
		
		Turing machine = new Turing(5);  // This machine will have 5 states + halt state.
		Transition one = new Transition('0',Transition.RIGHT, 1);
		Transition two = new Transition('1',Transition.RIGHT, 1);
		Transition three = new Transition('B', Transition.RIGHT, 2);
		Transition four = new Transition('1', Transition.RIGHT, 2);
		Transition five = new Transition('1', Transition.LEFT, 3);
		Transition six = new Transition('B', Transition.LEFT, 3);
		Transition seven = new Transition('0', Transition.RIGHT, 0);
		Transition eight = new Transition('B', Transition.LEFT, 4);
		Transition nine = new Transition('1', Transition.LEFT, 4);
		Transition ten = new Transition('B', Transition.RIGHT, 5);
		Transition eleven = new Transition('1', Transition.LEFT, 4);
		
		machine.addTransition(0, '1', one);
		machine.addTransition(1, '1', two);
		machine.addTransition(1, 'B', three);
		machine.addTransition(2, '1', four);
		machine.addTransition(2, 'B', five);
		machine.addTransition(3, 'B', six);
		machine.addTransition(3, '1', five);
		machine.addTransition(3, '0', seven);
		machine.addTransition(0, 'B', eight);
		machine.addTransition(4, '0', nine);
		machine.addTransition(4, 'B', ten);
		machine.addTransition(4, '1', eleven);
		
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the bits string to manipulate:");
		String inTape = in.nextLine();
		
		String outTape = machine.execute(inTape);
		System.out.println(outTape);
		in.close();
	}
}
