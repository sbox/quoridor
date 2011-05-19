package quoridor;

/**
 * 
 * An instruction for moving a pawn
 * 
 * @author Stephen Sherratt
 *
 */

public interface MovePawn extends GenericMove {
	public Square getDestination();
	public void makeMove();
}
