package quoridor;

/**
 *
 * A player in a game.
 *
 * @author Stephen Sherratt
 *
 */

public interface Player {
	public static final boolean TOP = false;
	public static final boolean BOTTOM = true;
	
	public String getName();
	public Player getOponent();
	public boolean goalEnd();
	
	
}
