package quoridor;

public class PawnImpl implements Pawn {
	Square pawnSquare;
	Player owner;
	
	public PawnImpl(Square pawnSquare, Player owner) {
		this.pawnSquare = pawnSquare;
		this.owner = owner;
	}

	@Override
	public Square getSquare() {
		
		return pawnSquare;
	}

	@Override
	public void makeMove(MovePawn move) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player getOwner() {

		return owner;
	}
}
