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
		// TODO Auto-generated method stub
		
	}
	
}
