package quoridor;

public class MovePawnImpl extends AbstractMove implements MovePawn {

	Square destination;
	Square start;

	/**
	 * Constructor to Move a pawn on the board
	 * @param col
	 * 			column to move the pawn to
	 * @param row
	 * 			row to move the pawn to
	 * @param owner
	 * 			of the pawn
	 * @param setting
	 * 			current gameboard state
	 */
	public MovePawnImpl(int col, int row, Player owner, Board setting) {
		super(owner, setting);
		destination = new SquareImpl(col, row);
		start = setting.getPawn(owner, setting).getSquare();

    }

	/*
	 * (non-Javadoc)
	 * @see quoridor.MovePawn#getDestination()
	 */
	@Override
	public Square getDestination() {
		return destination;
	}

	/**
	 * 
	 * Checks whether a pawn move is valid,
	 * it starts by assuming that the move is valid and eliminates invalid moves
	 * 
	 * - destination must not be the same as the start
	 * - squares that are adjacent must not have walls between them
	 * - squares that are adjacent must not have a pawn on the destination
	 * - squares two apart must not have walls between them
	 * - squares two apart must have a pawn between them
	 * - squares diagonally must have a pawn on one of the squares adjacent to them both
	 * - squares diagonally must have a wall on the other side of the opponents pawn
	 * - squares diagonally must not have walls between the opponents pawn and the start
	 * - squares diagonally must not have walls between the opponents pawn and the destination
	 * - all other moves are false
	 * 
	 * @see quoridor.AbstractMove#isValid()
	 */
	@Override
	public boolean isValid() {
		
		Square temp;
		Square temp2;
		boolean valid = true;
		int destination_col = destination.getCol();
		int destination_row = destination.getRow();
		int start_col = start.getCol();
		int start_row = start.getRow();
		
		Square between = isTwoAway(start, destination);
		
		//check whether square is on the board
		if(destination_col > 8 || destination_col < 0 ||
				   destination_row > 8 || destination_row < 0) {
			
            valid = false;
		}
		//check the destination is not the start
        else if(start == destination) {
			valid = false;
		}
		//check if square is adjacent
		else if(isAdjacent(start, destination)) {
			if(setting.wallBetween(start, destination) || destination.hasPawn(setting.getPawn(owner.getOpponent(),setting))) {
				valid = false;
			}
		}
		//check if the square is 2 away in 1 direction
		else if(between != null) {
			if(!between.hasPawn(setting.getPawn(owner.getOpponent(),setting))) {
				valid = false;
			} else if (setting.wallBetween(start, between) 
					|| setting.wallBetween(between, destination)) {
				valid = false;
			}
		}
		//check if the square is a diagonal
		else if(start_col - destination_col == 1 && start_row - destination_row == 1) {
			//temp and temp2 are the squares adjacent to both start and destination
			temp  = new SquareImpl(destination_col, start_row);
			temp2 = new SquareImpl(start_col, destination_row);
			//if the pawn is on temp
			if(temp.hasPawn(setting.getPawn(owner.getOpponent(),setting))) {
				//temp2 does not need to keep track of the other adjacent square
				//so it is the square which has the pawn between start and itself
				temp2 = new SquareImpl(destination_col - 1, start_row);
				//if there are no walls between the two pawns
				if(!setting.wallBetween(temp, temp2)) {
					valid = false;
				//if there are no walls between the opponent and the destination
				} else if(setting.wallBetween(start, temp)) {
					valid = false;
				//if there is a wall on the opposite side of the opponent
				} else if(setting.wallBetween(temp, destination)) {
					valid = false;
				}
			//if the pawn is on temp2
			} else if(temp2.hasPawn(setting.getPawn(owner.getOpponent(),setting))) {
				temp = new SquareImpl(start_col, destination_row - 1);
				if(!setting.wallBetween(temp, temp2)) {
					valid = false;
				} else if(setting.wallBetween(start, temp2)) {
					valid = false;
				} else if(setting.wallBetween(temp2, destination)) {
					valid = false;
				}
			} else {
				valid = false;
			}
		} else if(destination_col - start_col == 1 && start_row - destination_row == 1) {
			temp  = new SquareImpl(destination_col, start_row);
			temp2 = new SquareImpl(start_col, destination_row);
			if(temp.hasPawn(setting.getPawn(owner.getOpponent(),setting))) {
				temp2 = new SquareImpl(destination_col + 1, start_row);
				if(!setting.wallBetween(temp, temp2)) {
					valid = false;
				} else if(setting.wallBetween(start, temp)) {
					valid = false;
				} else if(setting.wallBetween(temp, destination)) {
					valid = false;
				}
			} else if(temp2.hasPawn(setting.getPawn(owner.getOpponent(),setting))) {
				temp = new SquareImpl(start_col, destination_row - 1);
				if(!setting.wallBetween(temp, temp2)) {
					valid = false;
				} else if(setting.wallBetween(start, temp2)) {
					valid = false;
				} else if(setting.wallBetween(temp2, destination)) {
					valid = false;
				}
			} else {
				valid = false;
			}
		} else if(destination_col - start_col == 1 && destination_row - start_row == 1) {
			temp  = new SquareImpl(destination_col, start_row);
			temp2 = new SquareImpl(start_col, destination_row);
			
			if(temp.hasPawn(setting.getPawn(owner.getOpponent(),setting))) {
				temp2 = new SquareImpl(destination_col + 1, start_row);
				if(!setting.wallBetween(temp, temp2)) {
					valid = false;
				} else if(setting.wallBetween(start, temp)) {
					valid = false;
				} else if(setting.wallBetween(temp, destination)) {
					valid = false;
				}
			} else if(temp2.hasPawn(setting.getPawn(owner.getOpponent(),setting))) {
				temp = new SquareImpl(start_col, destination_row + 1);
				if(!setting.wallBetween(temp, temp2)) {
					valid = false;
				} else if(setting.wallBetween(start, temp2)) {
					valid = false;
				} else if(setting.wallBetween(temp2, destination)) {
					valid = false;
				}
			} else {
				valid = false;
			}
		} else if(start_col - destination_col == 1 && destination_row - start_row == 1) {
			temp  = new SquareImpl(destination_col, start_row);
			temp2 = new SquareImpl(start_col, destination_row);
			
			if(temp.hasPawn(setting.getPawn(owner.getOpponent(),setting))) {
				temp2 = new SquareImpl(destination_col - 1, start_row);
				if(!setting.wallBetween(temp, temp2)) {
					valid = false;
				} else if(setting.wallBetween(start, temp)) {
					valid = false;
				} else if(setting.wallBetween(temp, destination)) {
					valid = false;
				}
			} else if(temp2.hasPawn(setting.getPawn(owner.getOpponent(),setting))) {
				temp = new SquareImpl(start_col, destination_row + 1);
				if(!setting.wallBetween(temp, temp2)) {
					valid = false;
				} else if(setting.wallBetween(start, temp2)) {
					valid = false;
				} else if(setting.wallBetween(temp2, destination)) {
					valid = false;
				}
			} else {
				valid = false;
			}
		} else {
			valid = false;
		}
		
		if (owner.hasWon(setting) || owner.getOpponent().hasWon(setting)) {
			valid = false;
		}
		
		return valid;
	}
	
	
	/**
	 * checks if two squares are adjacent
	 * 
	 * @param a
	 * 		first square
	 * @param b
	 * 		second square
	 * @return
	 * 		whether the squares are adjacent
	 */
	private boolean isAdjacent(Square a, Square b) {
		boolean adjacent = false;
		int aCol = a.getCol();
		int aRow = a.getRow();
		int bCol = b.getCol();
		int bRow = b.getRow();
		
		if(aRow == bRow && (aCol - bCol == 1 || bCol - aCol == 1)) {
			adjacent = true;
		} else if(aCol == bCol && (aRow - bRow == 1 || bRow - aRow == 1)) {
			adjacent = true;
		}
		return adjacent;
	}
	
	/**
	 * checks if two squares two apart in a straight line
	 * 
	 * @param a
	 * 		first square
	 * @param b
	 * 		second square
	 * @return
	 * 		whether the squares are two apart
	 */
	private Square isTwoAway(Square a, Square b) {
		Square between = null;
		int aCol = a.getCol();
		int aRow = a.getRow();
		int bCol = b.getCol();
		int bRow = b.getRow();
		
		if(aRow == bRow) {
			if(aCol - bCol == 2) {
				between = new SquareImpl(aCol - 1, aRow);
			} else if(bCol - aCol == 2) {
				between = new SquareImpl(bCol - 1, bRow);
			}
		} else if(aCol == bCol) {
			if (aRow - bRow == 2) {
				between = new SquareImpl(aCol, aRow - 1);
			} else if(bRow - aRow == 2) {
				between = new SquareImpl(bCol, bRow - 1);
			}
		}
		return between;
	}
	
	/*
	 * (non-Javadoc)
	 * @see quoridor.AbstractMove#type()
	 */
	@Override
	public boolean type() {
		return PAWN;
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.AbstractMove#makeMove()
	 */
    @Override
    public void makeMove() {
        setting.getPawn(owner, setting).setSquare(destination);
    }

    /*
     * (non-Javadoc)
     * @see quoridor.GenericMove#getMessage()
     */
	@Override
	public String getMessage() {
		return "Invalid move";
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String retVal = "";
		retVal+= (char)(destination.getCol() + (int) 'a');
		retVal+= destination.getRow()+1;
		return retVal;
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.GenericMove#getPlayer()
	 */
	@Override
	public Player getPlayer() {
		return owner;
	}
	

}
