	package quoridor;

/**
 * 
 * A single wall in quoridor
 * 
 * @author Stephen Sherratt
 *
 */

public interface Wall {
	/**
	 * Return value of getDirection() if the wall is horizontal
	 */
	public static final boolean HORIZONTAL = true;
	
	/**
	 * Return value of getDirection() if the wall is vertical
	 */
	public static final boolean VERTICAL = false;
	
	/**
	 * Returns the direction of the wall.
	 * @return the direction of the wall.
	 */
	public boolean getDirection();
	
	/**
	 * Returns the top left square of the wall
	 * @return the top left square of the wall
	 */
	public Square topLeft();

}
