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
	 * Return value of type() if moving a pawn.
	 */
	public static final boolean PAWN = true;
	/**
	 * Return value of type() if placing a wall.
	 */
	public static final boolean WALL = false;
	
	/**
	 * Checks if a move is valid.
	 * @return true iff a move is valid.
	 */
	public boolean isValid();
	
	/**
	 * Returns the type of the move.
	 * @return the type of the move.
	 */
	public boolean type();
	
	/**
	 * Returns the move cast as a PlaceWall.
	 * @return the move cast as a PlaceWall.
	 */
	public PlaceWall asPlaceWall();
	
	/**
	 * Returns the move cast as a MovePiece.
	 * @return the move cast as a MovePiece.
	 */
	public MovePawn asMovePawn();
	
	/**
	 * Applies the move to the board passed to the constructor
	 */
	public void makeMove();
	
	/**
	 * Returns a error message
	 * @return
	 */
	public String getMessage();
	
	public Player getPlayer();
	
}
