package quoridor;

/**
 * 
 * A single wall in quoridor
 * 
 * @author Stephen Sherratt
 *
 */

public interface Wall {
	public static final boolean HORIZONTAL = true;
	public static final boolean VERTICAL = false;
	
	public boolean getDirection();
	public Square topLeft();

}
