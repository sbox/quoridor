package quoridor;

/**
 * 
 * A single piece in quoridor
 * 
 * @author Stephen Sherratt
 *
 */

public interface Pawn {
	
	public Square getSquare();
	public void makeMove(MovePawn move);
    public Player getOwner();	

}