package quoridor;

/**
 * 
 * An implementation of a Square using a row and a column
 * 
 * @author Stephen Sherratt
 *
 */

public class SquareImpl implements Square {

	protected int row; // the row of this square
	protected int col; // the column of this square
	
	/**
	 * 
	 * Constructor for a SquareImpl from a row and column
	 * 
	 * @param row
	 * 			the row of the square
	 * @param col
	 * 			the column of the square
	 */
	public SquareImpl(int col, int row) {
		this.row = row;
		this.col = col;
	}
	
	/**
	 * returns the row of the square
	 * @return the row of the square
	 */
	public int getRow() {
		return row;
	}

	/**
	 * returns the column of the square
	 * @return the column of the square
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Two squares are equal if they have the same row and column
	 * @param s
	 * 			the Square being compared with this square
	 * @return
	 * True iff the two squares are equal
	 */
	public boolean equals(Square s) {
		return this.getRow() == s.getRow() &&
			   this.getCol() == s.getCol();
	}
	
	/**
	 * Assigns a key to a square that is unique from all other keys
	 * of squares that can go on the board.
	 * 
	 * @return a unique key for this square
	 */
	public int hashCode() {
		int key;
		key = row * Board.COLS + col; 
		return key;
	}
}
