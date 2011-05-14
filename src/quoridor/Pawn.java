package quoridor;

/**
 * 
 * A single piece in quoridor
 * 
 * @author Stephen Sherratt
 *
 */

public interface Pawn {
	
	/**
	 * Returns the current square that the pawn is placed on
	 * @return the current square that the pawn is placed on
	 */
	public Square getSquare();
	
	/**
	 * Applies a move to a piece
	 * @param move
	 * 			the move to be applied to the peice
	 */
	public void makeMove(MovePawn move);
	
	/**
	 * Returns the owner of the piece
	 * @return the owner of the piece
	 */
    public Player getOwner();	

}