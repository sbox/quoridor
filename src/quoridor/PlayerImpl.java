package quoridor;

public class PlayerImpl implements Player {

	protected String name;
	
	public PlayerImpl(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}



	@Override
	public boolean goalEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Player getOpponent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOpponent(Player opponent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasWon() {
		// TODO Auto-generated method stub
		return false;
	}

}
