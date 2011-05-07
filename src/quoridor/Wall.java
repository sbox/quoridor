package quoridor;

/**
 * 
 * A single wall in quoridor
 * 
 * @author Stephen Sherratt
 *
 */

public interface Wall {
	public boolean getDirection();
	public Square topLeft();
}
