package quoridor;

public class GameImpl implements Game {

	public static final short TOKEN_VOID = -1;
	public static final short TOKEN_QUIT = 0;
	public static final short TOKEN_MOVE = 1;
	public static final short TOKEN_LOAD = 2;
	public static final short TOKEN_SAVE = 3;
	
	Pair <Player> players;
	
	public GameImpl(Pair <Player> players) {
		this.players = players;
		
	}

	@Override
	public void play() {
		
		Player current = players._1();
		Pair <Pawn> pawns = new PairImpl<Pawn>(new PawnImpl(
				new SquareImpl(4, 8), players._1()),
				new PawnImpl(new SquareImpl(4, 8), players._1()));
		
		Board gameBoard = new BoardImpl(pawns);
		
		MoveParser parser = new MoveParserImpl();
		
		while (!isOver()) {
			
			 GenericMove nextMove =  parser.scanMove(current, gameBoard);
			
		}
		
	}
	
	public boolean isOver() {
		return players._1().hasWon() || players._2().hasWon();
	}
	
}
