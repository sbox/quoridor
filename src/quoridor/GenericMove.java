package quoridor;

/**
 * 
 * This is a general move with no specificity 
 * towards either type of Quoridor move.
 * Use only in cases when any type of move is allowed.
 * 
 * @author Stephen Sherratt
 *
 */

public interface GenericMove {
	
	/**
	 * Checks if a move is valid.
	 * @return true iff a move is valid.
	 */
	public boolean isValid();
	
}
