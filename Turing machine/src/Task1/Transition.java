package Task1;

/**
 * Transition for turing machine.
 * @author Jiaqi Zhang
 *
 */
public class Transition {
	
	/**
	 * Move left.
	 */
	public static final int LEFT = -1;
	
	/**
	 * Move right.
	 */
	public static final int RIGHT = 1;
	
	/**
	 * Character to write.
	 */
	private char toWrite;
	
	/**
	 * Move direction.
	 */
	private int move;
	
	/**
	 * State transit to.
	 */
	private int toState;
	
	/**
	 * Constructor.
	 * @param toWrite character to write
	 * @param move move direction
	 * @param toState state transit to
	 */
	public Transition (char toWrite, int move, int toState) {
		this.toWrite = toWrite;
		this.move = move;
		this.toState = toState;
	}
	
	/**
	 * Return the character to write.
	 * @return character to write
	 */
	public char getWrite() {
		return toWrite;
	}
	
	/**
	 * Return move direction.
	 * @return move direction
	 */
	public int getMove() {
		return move;
	}
	
	/**
	 * Return the state transit to.
	 * @return state transit to
	 */
	public int getState() {
		return toState;
	}
	
}
