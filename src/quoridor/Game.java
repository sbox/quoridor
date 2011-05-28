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
	 * Plays a game from a saved state
	 */
	public void loadGamePlay(String[] savedMoves);
	
	/**
	 * Return the string of moves made in the current game
	 * @return string of moves made in the current game
	 */
	public String formatFile();
	
	/**
	 * Undo a move of the current player
	 */
	public void undoMove();
	
	/**
	 * Redo the move of the current player
	 */
	public void redoMove();
	
}
