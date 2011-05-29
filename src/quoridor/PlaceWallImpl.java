package quoridor;

public class PlaceWallImpl extends AbstractMove implements PlaceWall{

	Wall tentative;
	
	/**
	 * Constructor for placing a wall on the board
	 * @param col
	 * 			column to place wall
	 * @param row
	 * 			row to place wall
	 * @param direction
	 * 			of the wall
	 * @param owner
	 * 			of the wall
	 * @param setting
	 * 			current board state
	 */
	public PlaceWallImpl(int col, int row, boolean direction, Player owner, Board setting) {
		super(owner, setting);
		tentative = new WallImpl(new SquareImpl(col, row), direction);
	}
	
	/*
	 * (non-Javadoc)
	 * @see quoridor.AbstractMove#isValid()
	 */
	@Override
	public boolean isValid() {
		boolean valid = true;
		Wall test = new WallImpl(new SquareImpl(tentative.topLeft().getCol(), tentative.topLeft().getRow()),
									tentative.getDirection());
		Wall left = new WallImpl(new SquareImpl(tentative.topLeft().getCol()-1, tentative.topLeft().getRow()),
									tentative.getDirection());
		Wall right = new WallImpl(new SquareImpl(tentative.topLeft().getCol()+1, tentative.topLeft().getRow()),
									tentative.getDirection());
		
		Wall up = new WallImpl(new SquareImpl(tentative.topLeft().getCol(), tentative.topLeft().getRow()-1),
				tentative.getDirection());
		Wall down = new WallImpl(new SquareImpl(tentative.topLeft().getCol(), tentative.topLeft().getRow()+1),
				tentative.getDirection());
		
		//Wall opWall
		boolean opDirection = tentative.getDirection();
		if (opDirection == Wall.HORIZONTAL) {
			opDirection = Wall.VERTICAL;
		} else {
			opDirection = Wall.HORIZONTAL;
		}
		Wall opposite = new WallImpl(new SquareImpl(tentative.topLeft().getCol(), tentative.topLeft().getRow()), opDirection);		
		
		if (setting.getPawn(owner, setting).getOwner().wallCount() <= 0) {
			valid = false;
		}
		if(tentative.topLeft().getCol() < 0) {
			valid = false;
		} else if(tentative.topLeft().getRow() < 0) {
			valid = false;
		} else if(tentative.topLeft().getCol() > 7) {
			valid = false;
		} else if(tentative.topLeft().getRow() > 7) {
			valid = false;
		} else if (setting.containsWall(test) == true) {
			valid = false;
		} else if (setting.containsWall(opposite) == true) {
			valid = false;
		} else if (setting.containsWall(right) == true && tentative.getDirection() == Wall.HORIZONTAL && right.topLeft().getCol() < 8) {
			valid = false;
		} else if(setting.containsWall(left) == true && tentative.getDirection() == Wall.HORIZONTAL && left.topLeft().getCol() > 0) {
			valid = false;
		} else if(setting.containsWall(up) == true && tentative.getDirection() == Wall.VERTICAL && up .topLeft().getRow () > 0) {
			valid = false;
		} else if (setting.containsWall(down) == true && tentative.getDirection() == Wall.VERTICAL && down.topLeft().getRow() < 8) {
			valid = false;
		} else {
			setting.addWall(tentative);
			if (setting.pathToGoal(setting.getPawn(owner, setting)) == false) {
				valid = false;
			} else if (setting.pathToGoal(setting.getPawn(owner.getOpponent(), setting)) == false) {
				valid = false;
			}
			setting.removeWall(tentative);
		}

		if (owner.hasWon(setting) || owner.getOpponent().hasWon(setting)) {
			valid = false;
		}
		
		return valid;
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.PlaceWall#getTentative()
	 */
	@Override
	public Wall getTentative() {
		return tentative;
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.AbstractMove#type()
	 */
	@Override
	public boolean type() {
		return WALL;
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.AbstractMove#makeMove()
	 */
    @Override
    public void makeMove() {
    	setting.addWall(tentative);
    	setting.getPawn(owner, setting).getOwner().decreaseWallCount();
    }

    /*
     * (non-Javadoc)
     * @see quoridor.GenericMove#getMessage()
     */
	@Override
	public String getMessage() {
		String retVal;
		if (setting.getPawn(owner, setting).getOwner().wallCount() <= 0) {
			retVal = "Invalid move: Player has no walls left";
		} else {
			retVal = "Invalid wall placement";
		}
		
		return retVal;
	}
	
	/**
	 * Returns the string verson of a wall placement
	 */
	public String toString() {
		String retVal = "";
		
		retVal+= (char)(tentative.topLeft().getCol() + (int) 'a');
		retVal+= tentative.topLeft().getRow()+1;
		if (tentative.getDirection() == Wall.HORIZONTAL) {
			retVal += "h";
		} else {
			retVal+= "v";
		}
		
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
