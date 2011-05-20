package quoridor;

public abstract class AbstractMove implements GenericMove {

	Player owner;
	Board setting;
	
	
	
	public AbstractMove(Player owner, Board setting) {
		this.owner = owner;
		this.setting = setting;
	}
	
	@Override
	public abstract boolean isValid();

	@Override
	public abstract boolean type();

	@Override
	public PlaceWall asPlaceWall() {
		return (PlaceWall)this;
	}

	@Override
	public MovePawn asMovePawn() {
		return (MovePawn)this;
	}

}
