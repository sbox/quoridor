package quoridor;

public class PlayerImpl implements Player {

	protected String name;
	protected Player opponent;
	protected int wallCount;
	protected boolean goalEnd;
	protected String strategy;
	protected boolean human;
	
	public PlayerImpl(String name, boolean goalEnd) {
		this.name = name;
		wallCount = 10;
		this.goalEnd = goalEnd;
		human = true;
		strategy = "human";
	}
	
	public PlayerImpl(String name, boolean goalEnd, String strategy) {
		this.name = name;
		wallCount = 10;
		this.goalEnd = goalEnd;
		human = false;
		this.strategy = strategy;	
	}
	
	public PlayerImpl(Player p) {
		this.name = p.getName();
		this.opponent = p.getOpponent();
		this.wallCount = p.wallCount();
		this.goalEnd = p.goalEnd();
	}
	
	
	public boolean isHuman() {
		return human;
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
	public boolean hasWon(Board board) {
		int curRow = board.getPawn(this, board).getSquare().getRow();
		boolean retVal = false;
		if (goalEnd == TOP) {
			if (curRow == 0) {
				retVal = true;
			}
		} else if (goalEnd == BOTTOM) {
			if (curRow == 8) {
				retVal = true;
			}
		}
		
		return retVal;
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
	
	@Override
	public boolean equals(Object o) {
		Player p = (Player) o;
		return p.goalEnd() == goalEnd();
	}

}
