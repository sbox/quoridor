package quoridor;

public class PlayerImpl implements Player {

	protected String name;
	protected Player opponent;
	//protected String symbol;
	
	public PlayerImpl(String name) {
		this.name = name;
		
		//symbol = name.substring(0, 1);
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
		return opponent;
	}

	@Override
	public void setOpponent(Player opponent) {		
		this.opponent = opponent;		
	}

	@Override
	public boolean hasWon() {
		// TODO Auto-generated method stub
		return false;
	}

}
