package quoridor;

/**
 * 
 * An instruction for placing a wall on the game board
 * 
 * @author Stephen Sherratt
 *
 */

public interface PlaceWall extends GenericMove {
	/**
	 * Returns the proposed wall to be added to the board
	 * @return the proposed wall to be added to the board
	 */
	public Wall getTentative();
}
