package quoridor;

public class MovePawnImpl extends AbstractMove implements MovePawn {

	Square destination;
	Pawn mover;
	Board current;
	
	public MovePawnImpl(int col, int row, Pawn pawn, Board board) {
		destination = new SquareImpl(col, row);
		mover = pawn;
		current = board;
	}
	
	@Override
	public Square getDestination() {
		return destination;
	}

	@Override
	public boolean isValid() {
		return false;
	}

	@Override
	public boolean type() {
		return PAWN;
	}

	@Override
	public void makeMove() {
		
		
		
	}


}
