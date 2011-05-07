package quoridor;

/**
 * 
 * An instruction for placing a wall on the game board
 * 
 * @author Stephen Sherratt
 *
 */

public interface PlaceWall {
	public Wall getTentative();
}
