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
    
    }

}
