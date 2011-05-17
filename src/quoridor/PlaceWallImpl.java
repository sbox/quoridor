package quoridor;

public class PlaceWallImpl extends AbstractMove implements PlaceWall{

	Wall tentative;
	
	public PlaceWallImpl(int col, int row, boolean direction) {
		tentative = new WallImpl(new SquareImpl(col, row), direction);
	}
	
	@Override
	public boolean isValid(Board board) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Wall getTentative() {
		return tentative;
	}

	@Override
	public boolean type() {
		return WALL;
	}

}
