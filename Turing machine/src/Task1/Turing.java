package Task1;

/**
 * Turing machine simulation.
 * @author Jiaqi Zhang
 *
 */
public class Turing {

	/**
	 * Array Length for tape is 40.
	 */
	private static final int ARRAY_LENGTH = 40;
	
	/**
	 * Start point for head is 20.
	 */
	private static final int START_POS = 20;
	
	/**
	 * Alphabet for the turing machine.
	 */
	private static final char[] ALPHABET = {'0', '1', 'B'};
	
	/**
	 * Read and write head.
	 */
	private int head;
	
	/**
	 * Tape array.
	 */
	private char[] tape;

	/**
	 * Transition function.
	 */
	private Transition[][] delta;

	/**
	 * Start state.
	 */
	private int startState;

	/**
	 * Current state.
	 */
	private int currentState;

	/**
	 * Number of total states.
	 */
	private int totalStates;
	
	/**
	 * Halt state.
	 */
	private int haltState;

	/**
	 * Constructor.
	 * @param n number of total states
	 */
    public Turing(int n) {
    	if (n <= 0) {
    		throw new RuntimeException();
    	}
    	totalStates = n;
    	delta = new Transition[totalStates][ALPHABET.length];
    	startState = 0;
    	haltState = totalStates; // There is an additional halt state.
    	head = START_POS - 1; // position is 20, index is 20 - 1 = 19
    	tape = new char[ARRAY_LENGTH];
    	for (int i = 0; i < tape.length; i++) {
    		// The values on the input tape are set to all B’s.
    		tape[i] = 'B';
    	}
    }

    /**
     * Add transition function.
     * @param fromState state transit from
     * @param read character read
     * @param transition transition to add
     */
    public void addTransition(int fromState, char read, Transition transition) {
       for (int i = 0; i < ALPHABET.length; i++) {
    	   if (read == ALPHABET[i]) {
    		   delta[fromState][i] = transition;
    	   }
       }
    }

    /**
     * Execute the turing machine.
     * @param input the input string
     * @return the output string after execution
     */
    public String execute(String input) {
    	if (input == null) {
    		throw new RuntimeException();
    	}
    	for (int i = 0; i < input.length(); i++) {
    		// The leftmost value of inTape will be placed under the read/write head.
    		if (i + head >= ARRAY_LENGTH) {
    			throw new RuntimeException();
    		}
    		tape[i + head] = input.charAt(i); 
    	}
        currentState = startState;
        while (currentState != haltState) {
        	for (int i = 0; i < ALPHABET.length; i++) {
        		if (tape[head] == ALPHABET[i]) {
        			transit(delta[currentState][i]);
        			break;
        		}
        	}
        }
        return new String(tape);
    }

    /**
     * Make the transition.
     * @param transition the defined transition
     */
    private void transit(Transition transition) {
    	currentState = transition.getState();
    	tape[head] = transition.getWrite();
    	head += transition.getMove();
    }
}
