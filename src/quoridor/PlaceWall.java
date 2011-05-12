package quoridor;

/**
 * 
 * An instruction for placing a wall on the game board
 * 
 * @author Stephen Sherratt
 *
 */

public interface PlaceWall extends GenericMove {
	public Wall getTentative();
}
