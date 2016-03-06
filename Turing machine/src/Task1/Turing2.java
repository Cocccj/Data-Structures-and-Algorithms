package Task1;

import java.util.Scanner;

/**
 * Second turing machine simulation.
 * @author Jiaqi Zhang
 *
 */
public class Turing2 {

	/**
	 * Main driver, define the turing machine and test it.
	 * @param args no argument
	 */
	public static void main(String[] args) {
		
		Turing machine = new Turing(6);  // This machine will have 6 states + halt state.
		Transition one = new Transition('B',Transition.RIGHT, 1);
		Transition two = new Transition('0',Transition.RIGHT, 1);
		Transition three = new Transition('1', Transition.RIGHT, 2);
		Transition four = new Transition('1', Transition.LEFT, 3);
		Transition five = new Transition('0', Transition.LEFT, 3);
		Transition six = new Transition('B', Transition.RIGHT, 0);
		Transition seven = new Transition('B', Transition.LEFT, 4);
		Transition eight = new Transition('0', Transition.LEFT, 4);
		Transition nine = new Transition('0', Transition.RIGHT, 6);
		Transition ten = new Transition('B', Transition.RIGHT, 5);
		Transition eleven = new Transition('B', Transition.RIGHT, 6);
		
		machine.addTransition(0, '0', one);
		machine.addTransition(1, '0', two);
		machine.addTransition(1, '1', three);
		machine.addTransition(2, '1', three);
		machine.addTransition(2, '0', four);
		machine.addTransition(3, '0', five);
		machine.addTransition(3, '1', four);
		machine.addTransition(3, 'B', six);
		machine.addTransition(2, 'B', seven);
		machine.addTransition(4, '1', seven);
		machine.addTransition(4, '0', eight);
		machine.addTransition(4, 'B', nine);
		machine.addTransition(0, '1', ten);
		machine.addTransition(5, '0', ten);
		machine.addTransition(5, '1', ten);
		machine.addTransition(5, 'B', eleven);
		
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the bits string to manipulate:");
		String inTape = in.nextLine();
		
		String outTape = machine.execute(inTape);
		System.out.println(outTape);
		in.close();
	}
}
