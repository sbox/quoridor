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
		Wall test = new WallImpl(new SquareImpl(tentative.topLeft().getCol(), tentative.topLeft().getRow()),
									tentative.getDirection());
		boolean direction = tentative.getDirection();
		if (direction == Wall.HORIZONTAL) {
			direction = Wall.VERTICAL;
		} else {
			direction = Wall.HORIZONTAL;
		}
		Wall opposite = new WallImpl(new SquareImpl(tentative.topLeft().getCol(), tentative.topLeft().getRow()), direction);		
		
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
		} else if (setting.containsWall(test) == true) {
				valid = false;
		} else if (setting.containsWall(opposite) == true) {
				valid = false;
		} else {
			setting.addWall(tentative);
			if (setting.pathToGoal(setting.getPawn(owner, setting)) == false) {
				valid = false;
			} else if (setting.pathToGoal(setting.getPawn(owner.getOpponent(), setting)) == false) {
				System.out.println("op name" +owner.getOpponent().getName());
				System.out.println("place a wall by op is not val");
				valid = false;
			}
			setting.removeWall(tentative);
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
