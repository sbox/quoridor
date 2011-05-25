 package quoridor;

 /**
  * 
  * This a the representation of the game board.
  * 
  * @author Stephen Sherratt
  *
  */
 
public interface Board {
	/**
	 * The number of rows on the board
	 */
	public static final int ROWS = 9;
	
	/**
	 * The number of columns on the board
	 */
	public static final int COLS = 9;
	
	/**
	 * Checks if there is a path from a pawn to its goal end of the
	 * board (not obstructed by walls)
	 * @param pawn
	 * 			The pawn whose ability to reach the goal end is being
	 * 			checked
	 * @return true iff there is a path from the pawn to its goal
	 */
    public boolean pathToGoal(Pawn pawn);
    
    /**
     * Checks if two adjacent squares are seperated by a wall
     * @param a
     * 			One of the squares being checked
     * @param b
     * 			The other square being checked
     * @return true iff the squares are seperated by a wall
     */
    public boolean wallBetween(Square a, Square b);
    
    /**
     * Adds a wall to the board
     * @param wall
     * 			the wall being added
     */
    public void addWall(Wall wall);
    
    /**
     * Finds a players pawn and returns it
     * @param subject
     * 			the player who's pawn is being accessed
     * @param setting
     * 			the board currently being used
     * @return
     * 			the pawn being accessed
     */
    public Pawn getPawn(Player subject, Board setting); 
 
}
