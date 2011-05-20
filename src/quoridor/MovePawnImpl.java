package quoridor;

public class MovePawnImpl extends AbstractMove implements MovePawn {

	Square destination;
	
	
	public MovePawnImpl(int col, int row, Player owner, Board setting) {
		destination = new SquareImpl(col, row);
	}
	
	@Override
	public Square getDestination() {
		return destination;
	}

	@Override
	public boolean isValid(Board board) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean type() {
		return PAWN;
	}


}
