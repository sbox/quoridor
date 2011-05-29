package quoridor;

public class PawnImpl implements Pawn {
	Square pawnSquare;
	Player owner;
	
	
	public PawnImpl(Square pawnSquare, Player owner) {
		this.pawnSquare = pawnSquare;
		this.owner = owner;
	}
	

	
	public PawnImpl(Pawn p) {
		this.pawnSquare = new SquareImpl(p.getSquare());
		this.owner = new PlayerImpl(p.getOwner());
	}

	@Override
	public Square getSquare() {
		return pawnSquare;
	}


	@Override
	public Player getOwner() {
		return owner;
	}
	
	public void setSquare(Square newSquare) {
		pawnSquare = newSquare;
	}
	
}
