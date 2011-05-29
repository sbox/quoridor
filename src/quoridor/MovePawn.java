package quoridor;

/**
 * 
 * An instruction for moving a pawn
 * 
 * @author Stephen Sherratt
 *
 */

public interface MovePawn extends GenericMove {
	/**
	 * Returns a pawns square destination
	 * @return the pawns square destination
	 */
	public Square getDestination();
}
