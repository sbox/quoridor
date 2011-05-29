package quoridor;

/**
 * 
 * An implementation of a wall using the top-left most adjacent
 * square and direction
 * 
 * @author Stephen Sherratt
 * 
 */

public class WallImpl implements Wall {
	/*
	 * These constants are the max row and column for the topLeft
	 * square. The bottom row and right-most column cannot
	 * be the topLeft square for any possible wall.
	 */
	protected final int MAX_ROW = Board.ROWS - 1;
	protected final int MAX_COL = Board.COLS - 1;
	
	protected boolean direction;
	protected Square topLeft;
	
	/**
	 * 
	 * Constructor that takes the topLeft square and a direction
	 * 
	 * @param topLeft
	 * 			The top-left most square that the line passes.
	 * @param direction
	 * 			The direction of the line.
	 */
	public WallImpl(Square topLeft, boolean direction) {
		this.topLeft = topLeft;
		this.direction = direction;
	}
	
	public WallImpl(Wall w) {
		this.topLeft = new SquareImpl(w.topLeft());
		this.direction = w.getDirection();
	}
	
	/*
	 * (non-Javadoc)
	 * @see quoridor.Wall#getDirection()
	 */
	public boolean getDirection() {
		return direction;
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.Wall#topLeft()
	 */
	public Square topLeft() {
		return topLeft;
	}
	
	/**
	 * Creates a hash code for a wall based on its topLeft square
	 * and direction. This is required so it can be determined
	 * in constant time whether a wall with a specified topLeft
	 * square and direction is in a hash set of walls.
	 * @return the hash code of a given wall
	 */
	@Override
	public int hashCode() {
		
		/*
		 * Each square is assigned a number.
		 * This number is then doubled to make sure it is even.
		 */
		int key = (topLeft.getRow() * MAX_COL + 
			     	topLeft.getCol()) * 2; 
		
		/*
		 * Horizontal walls are given odd codes.
		 * Vertical walls are give even codes (they stay the same).
		 */
		if (direction == Wall.HORIZONTAL) {
			key++;
		}
		
		//now return the key for this wall
		return key;
	}
	
	@Override
	public boolean equals(Object w) {
		boolean wIsWall = w instanceof Wall;
		return wIsWall && hashCode() == w.hashCode();
	}
	
	@Override
	public String toString() {
		String result = "" + this.topLeft.getCol() + ", " + topLeft.getRow();
		if (direction == HORIZONTAL) {
			result += "h";
		} else {
			result += "v";
		}
		return result;
	}
	
}
