package quoridor;

/**
 * A single game of quoridor
 * @author Stephen Sherratt
 *
 */

public interface Game {
	
	/**
	 * Begin the game.
	 */
	public void play();
	
	/**
	 * Return the string of moves made in the current game
	 * @return string of moves made in the current game
	 */
	public String formatFile();
	
}
