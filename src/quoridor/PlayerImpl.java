package quoridor;

public class PlayerImpl implements Player {

	protected String name;
	protected Player opponent;
	protected int wallCount;
	protected boolean goalEnd;
	
	public PlayerImpl(String name, boolean goalEnd) {
		this.name = name;
		wallCount = 10;
		this.goalEnd = goalEnd;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean goalEnd() {
		return goalEnd;
	}

	@Override
	public Player getOpponent() {
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

	@Override
	public int wallCount() {
		return wallCount;
	}

	@Override
	public void decreaseWallCount() {
		wallCount--;
	}

	@Override
	public void addWallCount() {
		wallCount++;
	}
	

}
