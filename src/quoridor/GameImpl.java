package quoridor;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GameImpl implements Game {

	public static final short TOKEN_VOID = -1;
	public static final short TOKEN_QUIT = 0;
	public static final short TOKEN_MOVE = 1;
	public static final short TOKEN_LOAD = 2;
	public static final short TOKEN_SAVE = 3;
	public static final short TOKEN_UNDO = 4;
	public static final short TOKEN_REDO = 5;
	
	Pair <Player> players;
	List<GenericMove> undoMoves;
	List<GenericMove> redoMoves;
	Board gameBoard;
	Player current;
	Parser inputParser;
	

	public GameImpl(Pair <Player> players) {
		this.players = players;
		undoMoves = new LinkedList<GenericMove>();
		redoMoves = new LinkedList<GenericMove>();
		current = players._1();
		
		Pair <Pawn> pawns = new PairImpl<Pawn>(new PawnImpl(
				new SquareImpl(4, 8), players._1()),
				new PawnImpl(new SquareImpl(4, 0), players._2()));		
		gameBoard = new BoardImpl(pawns);
		
		undoMoves.add(new MovePawnImpl(4, 8, current, gameBoard));
		undoMoves.add(new MovePawnImpl(4, 0, current.getOpponent(), gameBoard));		
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.Game#play()
	 */
	@Override
	public void play() {
		String[] commands = { "savegame", "undo", "redo", "quit"};
		short[] tokens = { TOKEN_SAVE, TOKEN_UNDO, TOKEN_REDO, TOKEN_QUIT};
		short[] argCount = { 0, 0, 0, 0 };
		short invalid = -1;
		inputParser = new ParserImpl(commands, tokens, argCount, invalid);
		
		MoveParser parser = new MoveParserImpl();
		Scanner s = new Scanner(System.in);
		short token; // holds the token of the command just read
		// read the next token
		
		GenericMove nextMove;
		printBoard();
		
		while (!isOver()) {
			if (current.equals(players._1())) {
				System.out.println("Enter move " +current.getName()+ " (X): ");
			} else {
				System.out.println("Enter move "+current.getName()+ " (O): ");
			}
			String command;
			if (current.isHuman()) {
				command = s.next();
			} else {
				State thinker = new StateImpl(gameBoard, current);
				command = thinker.nextBestMove().toString();
				System.out.println(command);
			}
			token = inputParser.ensureString(command);
			if (token == TOKEN_SAVE) {
				try {
					Manager.saveGame();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (token == TOKEN_UNDO) {
				undoMove();
				printBoard();
				current = current.getOpponent();
			} else if (token == TOKEN_REDO) {
				redoMove();
				printBoard();
				current = current.getOpponent();
			} else {	
				nextMove = parser.loadMove(current, gameBoard, command);
				if (nextMove == null) {
					System.out.println("Invalid input");
				} else {
					if (nextMove.isValid()) {
						 nextMove.makeMove();
						 printBoard();
						 undoMoves.add(nextMove);
						 redoMoves = new LinkedList<GenericMove>();
						 current = current.getOpponent();
					 } else {
						 System.out.println("Invalid Move");
					 }
				}
			}
		}
		System.out.println("Player " +current.getOpponent().getName()+ "has won. Congratulations!");
		System.out.println("newgame or savegame?");
	}
	
	/*
	 * (non-Javadoc)
	 * @see quoridor.Game#loadGamePlay(java.lang.String[])
	 */
	@Override
	public void loadGamePlay(String[] savedMoves) {
		MoveParser parser = new MoveParserImpl();
		GenericMove nextMove;
		for (int i = 0; i < savedMoves.length; i++) {
			nextMove = parser.loadMove(current, gameBoard, savedMoves[i]);
			nextMove.makeMove();
			undoMoves.add(nextMove);
			current = current.getOpponent();
		}
		play();
	}
	
	/**
	 * Prints the given board with how many walls each player has left underneath
	 */
	private void printBoard() {
		 System.out.println(gameBoard.toString());
		 System.out.println(current.getName() +" walls left: " +current.wallCount());
		 System.out.println(current.getOpponent().getName() +" walls left: " 
				 				+current.getOpponent().wallCount());
	}
	
	/**
	 * Returns if the current game is over
	 * @return if the game is over
	 */
	private boolean isOver() {
		return players._1().hasWon(gameBoard) || players._2().hasWon(gameBoard);
	}
	
	/*
	 * (non-Javadoc)
	 * @see quoridor.Game#formatFile()
	 */
	public String formatFile() {
		String retVal = "";
		String ai;
		if (current.getOpponent().isHuman() == true && current.isHuman() == true) {
			ai = "HH";
		} else {
			ai = "HA";
		}
		retVal+= ai + " " + players._1().getName() + " " +players._2().getName();
		int i = 0;
		while(i < undoMoves.size()) {
			retVal+= " ";
			retVal+= undoMoves.get(i);
			i++;
		}
		System.out.println("string of moves"+retVal);	
		return retVal;
	}

	/*
	 * (non-Javadoc)
	 * @see quoridor.Game#undoMove()
	 */
	@Override
	public void undoMove() {
		if (undoMoves.size() >= 1) {
			if (current.getOpponent().isHuman() == false) {
				undoOnce();
				current = current.getOpponent();
				undoOnce();
			} else {
				undoOnce();
			}
		} else {
			System.out.println("No moves left to undo!");
		}
	}

	/**
	 * Private method to undo one move
	 */
	private void undoOnce() {
		GenericMove lastMove = undoMoves.remove(undoMoves.size() -1);
		if (lastMove.toString().length() == 3) {
			gameBoard.removeWall(lastMove.asPlaceWall().getTentative());
			current.getOpponent().addWallCount();
		} else {
			GenericMove playerMoveBefore = undoMoves.get(undoMoves.size() -2);
			playerMoveBefore.makeMove();
		}
		redoMoves.add(lastMove);
	}
	
	/*
	 * (non-Javadoc)
	 * @see quoridor.Game#redoMove()
	 */
	@Override
	public void redoMove() {
		if (redoMoves.size() >= 0) {
			if (current.getOpponent().isHuman() == false) {
				redoOnce();
				current = current.getOpponent();
				redoOnce();
			} else {
				redoOnce();
			}
		} else {
			System.out.println("No moves to redo!");
		}
	}

	/**
	 * private method to redo two moves
	 */
	private void redoOnce() {
		GenericMove lastMove = redoMoves.remove(redoMoves.size()-1);
		if (lastMove.toString().length() == 3) {
			gameBoard.addWall(lastMove.asPlaceWall().getTentative());
			current.decreaseWallCount();
		} else {
			lastMove.makeMove();
		}
		undoMoves.add(lastMove);
	}
}

