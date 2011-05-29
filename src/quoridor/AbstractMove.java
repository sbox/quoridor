package quoridor;

public abstract class AbstractMove implements GenericMove {

	Player owner;
	Board setting;
	
	/**
	 * 
	 * @param owner
	 * 			the current player
	 * @param setting
	 * 			the current board game state
	 */
	public AbstractMove(Player owner, Board setting) {
		this.owner = owner;
		this.setting = setting;
	}
	/*
	 * (non-Javadoc)
	 * @see quoridor.GenericMove#isValid()
	 */
	@Override
	public abstract boolean isValid();

	/*
	 * (non-Javadoc)
	 * @see quoridor.GenericMove#type()
	 */
	@Override
	public abstract boolean type();
	
	/*
	 * (non-Javadoc)
	 * @see quoridor.GenericMove#makeMove()
	 */
	@Override
	public abstract void makeMove();

	/*
	 * (non-Javadoc)
	 * @see quoridor.GenericMove#asPlaceWall()
	 */
	@Override
	public PlaceWall asPlaceWall() {
		return (PlaceWall)this;
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.GenericMove#asMovePawn()
	 */
	@Override
	public MovePawn asMovePawn() {
		return (MovePawn)this;
	}

}
