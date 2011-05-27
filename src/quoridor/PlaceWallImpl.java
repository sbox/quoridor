package quoridor;

public class PlaceWallImpl extends AbstractMove implements PlaceWall{

	Wall tentative;
	
	
	
	public PlaceWallImpl(int col, int row, boolean direction, Player owner, Board setting) {
		super(owner, setting);
		tentative = new WallImpl(new SquareImpl(col, row), direction);
	}
	
	@Override
	public boolean isValid() {
		boolean valid = true;
		if (setting.getPawn(owner, setting).getOwner().wallCount() <= 0) {
			valid = false;
		}
		Square right = new SquareImpl(tentative.topLeft().getCol() + 1, tentative.topLeft().getRow());
		Square bottom = new SquareImpl(tentative.topLeft().getCol(), tentative.topLeft().getRow() + 1);
		if(tentative.topLeft().getCol() < 0) {
			valid = false;
		} else if(tentative.topLeft().getRow() < 0) {
			valid = false;
		} else if(tentative.topLeft().getCol() > 8) {
			valid = false;
		} else if(tentative.topLeft().getRow() > 8) {
			valid = false;
		} else {
			//is there a wall already placed
			//is there a wall that would intersect the placement
		}
		return valid;
	}

	@Override
	public Wall getTentative() {
		return tentative;
	}

	@Override
	public boolean type() {
		return WALL;
	}

    @Override
    public void makeMove() {
    	setting.addWall(tentative);
    	setting.getPawn(owner, setting).getOwner().decreaseWallCount();
    }

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

}
