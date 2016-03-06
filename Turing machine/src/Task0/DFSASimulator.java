package Task0;

/**
 * Simulates a DFSA (Cited the codes from the lecture slides, and made some changes).
 * @author Jiaqi Zhang
 */
public class DFSASimulator {

	/**
	 * Records final states.
	 */
	private boolean[] finalStates;

	/**
	 * Transition function.
	 */
	private char[][] delta;

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
	 * Constructor, states are numbered from 1.
	 * @param n number of total states
	 * @param start start state
	 */
    public DFSASimulator(int n, int start) {
    	if (n <= 0 || start < 0 || n < start) {
    		throw new RuntimeException();
    	}
    	totalStates = n;
    	finalStates = new boolean[totalStates];
    	delta = new char[totalStates][totalStates];
    	startState = start;
    }

    /**
     * Check if the current state is final state or not.
     * @return true if current state is final, false otherwise
     */
    private boolean isFinal() {
       return finalStates[currentState];
    }

    /**
     * Add transition function.
     * @param fromState state transit from
     * @param toState state transit to
     * @param letter the letter to specify the transition
     */
    public void addTransition(int fromState, int toState, char letter) {
       delta[fromState][toState] = letter;
    }
    
    /**
     * Add final state.
     * @param q final state
     */
    public void addFinalState(int q) {
        finalStates[q] = true;
    }

    /**
     * Determine whether a string is accepted or not.
     * @param s string to judge
     * @return true if s is accepted false otherwise
     */
    public boolean isAccepted (String s) {
        currentState = startState;
        for (int i = 0; i < s.length(); i++) {
        	boolean found = false;
        	for (int j = 0; j < totalStates; j++) {
        		if (delta[currentState][j] == s.charAt(i)) {
        			currentState = j;
        			found = true;
        			break;
        		}
        	}
        	if (!found) {
        		System.out.println(" d("+currentState+","+s.charAt(i)+") is undefined.");
        		return false;
        	} 
        }
        return isFinal();
    }
    

	/**
	 * Main driver, define the two-state DFSA and test it.
	 * @param args no arguments
	 */
	public static void main(String[] args) {
		
		String str = "0010010101001100111111101111";		
		System.out.println(str);

		// At least one 0 that specifies the number of states
		int indexDel = str.indexOf('1');
		int numStates = indexDel;
		if (numStates == 0) throw new RuntimeException();
		DFSASimulator simu = new DFSASimulator(numStates, 0);
		str = str.substring(indexDel + 1);
		int cnt = 0;
		while (str.length() != 0 && cnt < numStates) {
			// Define the transition functions
			indexDel = str.indexOf('1');
			if(indexDel == 0) break;
			simu.addTransition(cnt, indexDel - 1, '0');
			str = str.substring(indexDel + 1);
			indexDel = str.indexOf('1');
			if(indexDel == 0) break;
			simu.addTransition(cnt, indexDel - 1, '1');
			str = str.substring(indexDel + 1);
			cnt++;
		}
		str = str.substring(1);
		// Number of 0's specify an accepting state
		while (str.length() != 0) {
			indexDel = str.indexOf('1');
			if(indexDel == 0) break;
			simu.addFinalState(indexDel - 1);
			str = str.substring(indexDel + 1);
		}
		str = str.substring(1);
		// For the remaining string, check if it can be accepted.
        if (simu.isAccepted(str)) {
            System.out.println("Accepted");
        } else {
            System.out.println("Rejected");
        }
	}
}
