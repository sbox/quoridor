package quoridor;

public class PawnImpl implements Pawn {
	Square pawnSquare;
	Player owner;
	
	/**
	 * Constructor to create a pawn given a square and owner
	 * @param pawnSquare
	 * 				square which the pawn is on the board
	 * @param owner
	 * 			of the pawn
	 */
	public PawnImpl(Square pawnSquare, Player owner) {
		this.pawnSquare = pawnSquare;
		this.owner = owner;
	}
	
	/**
	 * Constructor to create a pawn given a player
	 * @param p
	 * 			player who owns the pawn
	 */	
	public PawnImpl(Pawn p) {
		this.pawnSquare = new SquareImpl(p.getSquare());
		this.owner = new PlayerImpl(p.getOwner());
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.Pawn#getSquare()
	 */
	@Override
	public Square getSquare() {
		return pawnSquare;
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.Pawn#getOwner()
	 */
	@Override
	public Player getOwner() {
		return owner;
	}
	
	/*
	 * (non-Javadoc)
	 * @see quoridor.Pawn#setSquare(quoridor.Square)
	 */
	public void setSquare(Square newSquare) {
		pawnSquare = newSquare;
	}
	
}
