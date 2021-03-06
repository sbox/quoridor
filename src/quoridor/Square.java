package quoridor;

/**
 *
 * One of the squares that makes up the board.
 *
 * @author Stephen Sherratt
 *
 */

public interface Square {
	/**
	 * Returns the row of the square
	 * @return the row of the square
	 */
	public int getRow();
	
	/**
	 * Returns the column of the square
	 * @return the column of the square
	 */
	public int getCol();

	/**
	 * 
	 * @param pawn you are looking at
	 * @return if there is a pawn on that square
	 */
	public boolean hasPawn(Pawn pawn);
	

}
