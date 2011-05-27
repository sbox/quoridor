package quoridor;

import java.util.LinkedList;
import java.util.List;

public class GameImpl implements Game {

	public static final short TOKEN_VOID = -1;
	public static final short TOKEN_QUIT = 0;
	public static final short TOKEN_MOVE = 1;
	public static final short TOKEN_LOAD = 2;
	public static final short TOKEN_SAVE = 3;
	
	Pair <Player> players;
	List<GenericMove> moves;
	Board gameBoard;
	
	public GameImpl(Pair <Player> players) {
		this.players = players;
		moves = new LinkedList<GenericMove>();
		
	}

	@Override
	public void play() {
		
		Player current = players._1();
		Pair <Pawn> pawns = new PairImpl<Pawn>(new PawnImpl(
				new SquareImpl(4, 8), players._1()),
				new PawnImpl(new SquareImpl(4, 0), players._2()));
		
		gameBoard = new BoardImpl(pawns);
		//Board gameBoard = new BoardImpl(pawns);
		
		MoveParser parser = new MoveParserImpl();
		
		GenericMove nextMove;
		
		//moves.add();
		//moves.add();
		
		System.out.println(gameBoard.toString());
		System.out.println(current.getName() +" walls left: " +current.wallCount());
		System.out.println(current.getOpponent().getName() +" walls left: " +current.getOpponent().wallCount());
		
		while (!isOver()) {

			if (current.equals(players._1())) {
				System.out.println("Enter move " +current.getName()+ " (X): ");
			} else {
				System.out.println("Enter move "+current.getName()+ " (O): ");
			}
			
			nextMove = parser.scanMove(current, gameBoard);
			
			if (nextMove == null) {
				System.out.println("Invalid input");
			} else {
				if (nextMove.isValid()) {
					 nextMove.makeMove();
					 current = current.getOpponent();
					 System.out.println(gameBoard.toString());
					 System.out.println(current.getName() +" walls left: " +current.wallCount());
					 System.out.println(current.getOpponent().getName() +" walls left: " +current.getOpponent().wallCount());
					 moves.add(nextMove);
					 System.out.println("undoing the move");
					 undoMove();
					 System.out.println(gameBoard);
				 } else {
					 System.out.println("Invalid Move");
				 }
			}
			formatFile();
		}
	}
	
	public boolean isOver() {
		return players._1().hasWon() || players._2().hasWon();
	}
	
	/** Builds and returns a string of the players names
	 * and the current moves that have happened up to this game
	 * @return a string of player names and moves so far
	 */
	
	public String formatFile() {
		String retVal = "";
		retVal+=players._1().getName();
		retVal+= " ";
		retVal+=players._2().getName();
		int i = 0;
		while(i < moves.size()) {
			retVal+= " ";
			retVal+= moves.get(i);
			i++;
		}
		System.out.println("string of moves"+retVal);	
		return retVal;
	}

	@Override
	public void undoMove() {
		GenericMove lastMove = moves.remove(moves.size()-1);
		if (lastMove.toString().length() == 3) {
			gameBoard.removeWall(lastMove.asPlaceWall().getTentative());
		} else {
			GenericMove playerMoveBefore = moves.remove(moves.size()-3);
			playerMoveBefore.makeMove();
		}

	}

	//@Override
	/*public void undoMove() {
		String lastMove = moves.remove(moves.size()-1);
		if (lastMove.length() == 3) {
			WallImpl wall = new WallImpl(new SquareImpl(lastMove.valueOf(0), 3), lastMove.valueOf(2));
		} else if (lastMove.length() == 2) {
			
		}
	}	*/
}

