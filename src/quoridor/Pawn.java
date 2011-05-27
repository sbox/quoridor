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
	 * Returns the owner of the piece
	 * @return the owner of the piece
	 */
    public Player getOwner();	
    
    /**
     * Moves the pawn to a give square
     * @param moveTo the square you want to move to
     */
    public void setSquare(Square newSquare);

}