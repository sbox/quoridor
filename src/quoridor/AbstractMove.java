package quoridor;

public abstract class AbstractMove implements GenericMove {

	@Override
	public abstract boolean isValid(Board board);

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
