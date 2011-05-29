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
	
	public PlayerImpl(boolean goalEnd, String strategy) {
		this.name = strategy;
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
	
	/*
	 * (non-Javadoc)
	 * @see quoridor.Player#isHuman()
	 */
	public boolean isHuman() {
		return human;
	}
	
	public String getStrategy() {
		return strategy;
	}
	
	/*
	 * (non-Javadoc)
	 * @see quoridor.Player#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.Player#goalEnd()
	 */
	@Override
	public boolean goalEnd() {
		return goalEnd;
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.Player#getOpponent()
	 */
	@Override
	public Player getOpponent() {
		return opponent;
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.Player#setOpponent(quoridor.Player)
	 */
	@Override
	public void setOpponent(Player opponent) {		
		this.opponent = opponent;		
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.Player#hasWon(quoridor.Board)
	 */
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

	/*
	 * (non-Javadoc)
	 * @see quoridor.Player#wallCount()
	 */
	@Override
	public int wallCount() {
		return wallCount;
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.Player#decreaseWallCount()
	 */
	@Override
	public void decreaseWallCount() {
		wallCount--;
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.Player#addWallCount()
	 */
	@Override
	public void addWallCount() {
		wallCount++;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		Player p = (Player) o;
		return p.goalEnd() == goalEnd();
	}

}
