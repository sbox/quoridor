package quoridor;

public interface Board {
	public static final int ROWS = 9;
	public static final int COLS = 9;
	
    public boolean pathToGoal(Pawn pawn);
}
