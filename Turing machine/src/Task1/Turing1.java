package Task1;

/**
 * First turing machine simulation.
 * This machine reads the tape from left to right and replaces any 1 with 0 and any 0 with 1.
 * @author Jiaqi Zhang
 *
 */
public class Turing1 {
	/**
	 * Main driver, define the one-state turing machine and test it.
	 * @param args no argument
	 */
	public static void main(String[] args) {
		Turing machine = new Turing(1);  // This machine will have one state.
		Transition one = new Transition('0',Transition.RIGHT, 0);
		Transition two = new Transition('1',Transition.RIGHT, 0);
		Transition three = new Transition('B', Transition.LEFT, 1);
		
		machine.addTransition(0, '0', two);
		machine.addTransition(0, '1', one);
		machine.addTransition(0, 'B', three);
		
		String inTape = "11111100010101";
		System.out.println(inTape);
		
		String outTape = machine.execute(inTape);
		System.out.println(outTape);
	}
}
