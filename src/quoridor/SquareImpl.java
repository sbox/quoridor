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
	//protected boolean pawn = false;
	
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
	 * Constructor for SquareImpl that takes in a Square and seperates it into row and col
	 * @param s 
	 *			any given square
	 */
	public SquareImpl(Square s) {
		this.row = s.getRow();
		this.col = s.getCol();
	}
	
	/*
	 * (non-Javadoc)
	 * @see quoridor.Square#getRow()
	 */
	public int getRow() {
		return row;
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.Square#getCol()
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
	@Override
	public boolean equals(Object o) {
		Square s = (Square) o;
		return this.getRow() == s.getRow() &&
			   this.getCol() == s.getCol();
	}
	
	/**
	 * String of the row and column
	 */
	public String toString() {
		return col + ", " + row;
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
	
	/*
	 * (non-Javadoc)
	 * @see quoridor.Square#hasPawn(quoridor.Pawn)
	 */
	public boolean hasPawn(Pawn pawn) {
		if (pawn.getSquare().getRow() == row && pawn.getSquare().getCol() == col) {
			return true;
		} else {
			return false;
		}
	}
}
