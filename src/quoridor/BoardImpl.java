package quoridor;

import java.util.HashSet;

/**
 * 
 * An implementation of a board using a hash set of walls and a
 * pair of pieces
 * 
 * @author Stephen Sherratt
 *
 */

public class BoardImpl implements Board {
    
    protected Pair <Pawn> pawns;
    protected HashSet <Wall> walls;
    
    public BoardImpl(Pair<Pawn> pawns) {
    	this.pawns = pawns;
    	walls = new HashSet<Wall>();
    }

    //bec can you write this please
    @Override 
    public String toString() {
    	String board = "  ";

	    //Adding numbers to the top of the board
		/*for (int i = 0; i < 9; i++) {
			board+= "  "+(i+1)+" "; //sbox: added +1 as per requirements
		}*/
    	board+= "  a   b   c   d   e   f   g   h   i";
    	
		board+= "\n  ";
		board = addBoarder(board);
		
		for (int i = 0; i < 9*2-1; i++) {
			//Placing numbers at the beginning of every row
			//Mod to so that the number only gets printed once
			if (i%2 == 0) {
				board+= (i/2+1)+" "; //sbox: added +1 as per requirements
			} else {
				board+="  ";
			}
			for (int j = 0; j < 9; j++) {
				boolean wallBelow = wallBetween(new SquareImpl(j, i/2), new SquareImpl(j, i/2+1));
				//Place a horizontal wall
				board = addWallHorizontal(board, i, j);
				//Placing the pawns on the board, or a space if no player is on that square
				if (i%2 == 0) {
					board = addPawn(board, i, j);
				} else {
					//checking if there is a horizontal wall between two squares
					if (wallBelow){ //sbox: replaced condition with flag
						board+= " * ";
					} else {
						board+= " - "; //sbox; changed the _'s to -'s
					}
				}
			}
			board+="|\n";
		}
		
		//Adding the bottom of the board
		board+="  ";
		board = addBoarder(board);
    	
        return board;
    }
    
     private String addBoarder(String board) {
    	for (int i = 0; i < 9; i++) {
    		board+= "| - ";
    	}
    	board+="|\n";
    	return board;
    	
    }
     
     private String addWallHorizontal(String board, int i, int j) {
    	    //checking whether there is a vertical wall between two squares
			//sbox: introducing this flags since this information is needed in multiple places now
			boolean wallBelow = wallBetween(new SquareImpl(j, i/2), new SquareImpl(j, i/2+1));
			boolean wallBelowPrev = j > 0 && wallBetween(new SquareImpl(j-1, i/2), new SquareImpl(j-1, i/2+1));
			
			boolean wallLeft = j > 0 && wallBetween(new SquareImpl(j-1, i/2), new SquareImpl(j, i/2));
			boolean wallLeftBelow = j > 0 && wallBetween(new SquareImpl(j-1, i/2+1), new SquareImpl(j, i/2+1));

			/*
			 * sbox:
			 * changed mod signs to divide signs
			 * the first square is now the square to the left, and the second square is the current square
			 * we must now include the condition j > 0 since there cannot be a wall on the left of column 0
			 */
			
			if (wallLeft || (i%2 == 1 && (wallBelow || wallBelowPrev || wallLeftBelow))) {
				board+= "*";
			} else {
			    board+= "|";
			}
			
    	 return board;
     }
    
     private String addPawn(String board, int row, int col) {
    	int pawn1Row = pawns._1().getSquare().getRow();
    	int pawn1Col = pawns._1().getSquare().getCol();
    	int pawn2Row = pawns._2().getSquare().getRow();
    	int pawn2Col = pawns._2().getSquare().getCol();
    	
    	if (pawn1Col == col && pawn1Row*2 == row) {
			board+= " X ";
		} else if (pawn2Col == col && pawn2Row*2 == row){
			board+= " O ";
		} else {
			board+= "   ";
		}
    	
    	return board;
    }
    
    /**
     * Adds a wall to the hashSet of walls
     */
    public void addWall(Wall wall) {
    	walls.add(wall);
    }
    
    /**
     * 
     * Searches for a path from a pawn to said pawn's goal.
     * 
     * @param pawn
     * the pawn from which to search for a path to a goal
     * 
     * @return True iff there is a path from the pawn to its goal
     */
    public boolean pathToGoal(Pawn pawn) {
        //make sure the pawn is on the board
        assert(pawns.contains(pawn));  
        
        //keeps track of which squares have been visited before
        HashSet <Square> seen = new HashSet <Square> (ROWS * COLS);
        
        //the row to which there is required to be a path
        int goalRow;
        
        //set the goal row to the appropriate end
        if (pawn.getOwner().goalEnd() == Player.BOTTOM) {
        	goalRow = 0;
        } else {
        	goalRow = Board.ROWS - 1;
        }
        
        //call the search method
        return localPathToGoal(pawn.getSquare(), seen, goalRow);
    }
    
    /**
     * 
     * This is a helper function used by pathToGoal.
     * It recursively checks neighbors until it finds a goal or all
     * connected squares are searched.
     * 
     * @param current
     * 			The square currently being visited
     * @param seen
     * 			A hash set of squares that have been visited
     * @param goalRow
     * 			The number of the row that we are trying to get to
     * @return True iff there is a path from current to the goal row
     */
    private boolean localPathToGoal(Square current, 
    							    HashSet <Square> seen,
    							    int goalRow) {
    	
    	//store coordinates to limit method calls
    	int row = current.getRow();
    	int col = current.getCol();
    	
    	//If the current row is the goal row then we've found a path.\
    	boolean found = (row == goalRow);
    	
    	//if we aren't at the goal yet, do more flood fill
    	if (!found) {
    	
    		//add the current square to the set of seen squares
    		seen.add(current);
    		
	    	//make some candidates for neighbors:
	    	Square left  = possibleSquare(col - 1, row);
	    	Square right = possibleSquare(col + 1, row);
	    	Square up    = possibleSquare(col, row + 1);
	    	Square down  = possibleSquare(col, row - 1);
	    	
	    	//found is false here
	    	
	    	//start checking neighbors that are on the board, not yet
	        //  visited and not separated from current by a wall
	    	if (left != null && !seen.contains(left) && 
	    			!wallBetween(current, left)) {
	    		found = localPathToGoal(left, seen, goalRow);
	    	}
	    	
	    	/*
	    	 * found will be true here if the above search was
	    	 * successful. If this is the case, we can stop
	    	 * searching.
	    	 */
	    	
	    	if (!found && right != null && !seen.contains(right) && 
	    			!wallBetween(current, right)) {
	    		
	    		found = localPathToGoal(right, seen, goalRow);
	    	}
	    	
	    	if (!found && up != null && !seen.contains(up) && 
	    			!wallBetween(current, up)) {
	    		
	    		found = localPathToGoal(up, seen, goalRow);
	    	}	
	    	
	    	if (!found && down != null && !seen.contains(down) && 
	    			!wallBetween(current, down)) {
	    		
	    		found = localPathToGoal(down, seen, goalRow);
	    	}
    	}
	    	
    	return found;
    }
   
    
    /**
     * 
     * Returns a Square with the given col an row if such a square
     * could exist on the board, otherwise null
     * 
     * @param col
     * 			the column of the requested square
     * @param row
     * 			the row of the requested square
     * @return the requested square if it's possible, otherwise null
     */
    private Square possibleSquare(int col, int row) {
    	
    	Square result = null;
    
    	if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
    			result = new SquareImpl(col, row);
    	}
    	
    	return result;
    }
    
    
    /**
     * 
     * Checks if there is a wall between two given adjacent squares
     * 
     * @param a
     * 			one of the squares
     * @param b
     * 			the other square
     * @return True if there is a wall between the two squares
     */
    public boolean wallBetween(Square a, Square b) {
    	//the squares must be in line somehow
    	assert(a.getRow() == b.getRow() || a.getCol() == b.getCol());
    	
    	//to hold the square that is the most top-left of the two
    	// that are passed in
    	Square topLeftMost;
    	
    	//to hold the other candidate for topLeft 
    	// (that isn't passed in)
    	Square other;
    	
    	//to hold the two candidates for topLeft of the 
    	// potential wall
    	Pair <Square> topLeftCandidate;
    	
    	//to hold the direction of the potential wall
    	boolean wallDirection;
    	
    	//will store the potential walls
    	Pair <Wall> candidateWalls;
    	
    	//if the squares are on the same row
    	if (a.getRow() == b.getRow()) {
    		//they must be adjacent
    		assert(Math.abs(a.getCol() - b.getCol()) == 1);

    		//store the square with the lower column
    		if (a.getCol() < b.getCol()) {
    			topLeftMost = a;
    		} else {
    			topLeftMost = b;
    		}
    		
    		wallDirection = Wall.VERTICAL;
    		
    		//the other candidate for topLeft
    		other = new SquareImpl(topLeftMost.getCol(), 
					                      topLeftMost.getRow()-1);
    		
    	} else {
    	//if the squares are in the same column
    		//they must be adjacent
    		assert(Math.abs(a.getRow() - b.getRow()) == 1);

    		//store the square with the higher row
    		if (a.getRow() < b.getRow()) {
    			topLeftMost = a;
    		} else {
    			topLeftMost = b;
    		}
    		
    		wallDirection = Wall.HORIZONTAL;
    		
    		//the other candidate for topLeft
    		other = new SquareImpl(topLeftMost.getCol()-1, 
					                      topLeftMost.getRow());
    		
    	}
    	

    	
    	//make a pair for topLeftCandidate
		topLeftCandidate = new PairImpl<Square>(topLeftMost, other);
    	
		//for (Square s : topLeftCandidate) {
		//	System.out.println(s.getCol() +", "+ s.getRow());
		//}
		
		//make a pair of walls to test
    	candidateWalls = 
    	new PairImpl<Wall>(new WallImpl(topLeftCandidate._1(),
    									wallDirection),
    					   new WallImpl(topLeftCandidate._2(),
    	    							wallDirection)
    	                   );
    	


    	//return true if either candidate is on the board
    	return walls.contains(candidateWalls._1()) || 
    		   walls.contains(candidateWalls._2());
    	
    }

}
