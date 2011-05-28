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
	List<GenericMove> undoMoves;
	List<GenericMove> redoMoves;
	Board gameBoard;
	
	public GameImpl(Pair <Player> players) {
		this.players = players;
		undoMoves = new LinkedList<GenericMove>();
		redoMoves = new LinkedList<GenericMove>();
		
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
		
		//GenericMove beg = new MovePawnImpl(8, 4, current, gameBoard);
		
		undoMoves.add(new MovePawnImpl(4, 8, current, gameBoard));
		undoMoves.add(new MovePawnImpl(4, 0, current, gameBoard));
		
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
					 System.out.println(gameBoard.toString());
					 System.out.println(current.getName() +" walls left: " +current.wallCount());
					 System.out.println(current.getOpponent().getName() +" walls left: " +current.getOpponent().wallCount());
					 System.out.println(current.goalEnd());
					 current = current.getOpponent();
					 //System.out.println(current.goalEnd());
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
		while(i < undoMoves.size()) {
			retVal+= " ";
			retVal+= undoMoves.get(i);
			i++;
		}
		System.out.println("string of moves"+retVal);	
		return retVal;
	}

	@Override
	public void undoMove(Player current) {
		if (undoMoves.size() >= 2) {
			GenericMove lastMove = undoMoves.remove(undoMoves.size()-1);
			if (lastMove.toString().length() == 3) {
				gameBoard.removeWall(lastMove.asPlaceWall().getTentative());
				current.addWallCount();
			} else {
				GenericMove playerMoveBefore = undoMoves.remove(undoMoves.size()-2);
				playerMoveBefore.makeMove();
			}
			redoMoves.add(lastMove);
		} else {
			System.out.println("No moves left to undo!");
		}
	}

	@Override
	public void redoMove(Player current) {
		if (redoMoves.size() > 0) {
			GenericMove lastMove = redoMoves.remove(undoMoves.size()-2);
			if (lastMove.toString().length() == 3) {
				gameBoard.addWall(lastMove.asPlaceWall().getTentative());
				current.decreaseWallCount();
			} else {
				lastMove.makeMove();
			}
			undoMoves.add(lastMove);
		} else {
			System.out.println("No moves to redo!");
		}
	}
}

