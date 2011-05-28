package quoridor;

/**
 *
 * A player in a game.
 *
 * @author Stephen Sherratt
 *
 */

public interface Player {
	/**
	 * The return value of goalEnd() if the player's target end of
	 * the game is the top of the board
	 */
	public static final boolean TOP = false;
	
	/**
	 * The return value of goalEnd() if the player's target end of
	 * the game is the bottom of the board
	 */
	public static final boolean BOTTOM = true;
	
	/**
	 * Returns the name of the player
	 * @return the name of the player
	 */
	public String getName();
	
	
	/**
	 * Returns the player's opponent
	 * @return the player's opponent
	 */
	public Player getOpponent();
	
	/**
	 * Sets this player's opponent
	 * @param opponent
	 * 			the player's opponent
	 */
	public void setOpponent(Player opponent);
	
	
	/**
	 * Returns the end of the board the player is trying to reach
	 * @return the end of the board the player is trying to reach
	 */
	public boolean goalEnd();
	
	/**
	 * Returns if a player has won the game
	 * @return if a player has won the game
	 */
	public boolean hasWon(Board board);
	
	/**
	 * Returns the number of walls a player has left
	 * @return the number of walls a player has left
	 */
	public int wallCount();
	
	/**
	 * decrease the wall count of a player
	 */
	public void decreaseWallCount();
	
	/**
	 * Adds a number to the wall count of a player
	 */
	public void addWallCount();
	
}
