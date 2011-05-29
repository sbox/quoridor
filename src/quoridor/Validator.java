package quoridor;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Validator {


	// TODO complete this class using your project code
	// you must implement the no-arg constructor and the check method
	
	// you may add extra fields and methods to this class
	// but the ProvidedTests code will only call the specified methods
	
	Board setting;
	Player p1, p2;
	
	public Validator() {
		p1 = new PlayerImpl("herp", Player.BOTTOM);
		p2 = new PlayerImpl("derp", Player.TOP);
		
		p1.setOpponent(p2);
		p2.setOpponent(p1);
		
		Pair <Pawn> pawns = new PairImpl <Pawn> (new PawnImpl(new SquareImpl(4, 0), p1), new PawnImpl(new SquareImpl(4, 8), p2));
		
		setting = new BoardImpl(pawns);
	}

	/**
	 * Check the validity of a given sequence of moves.
	 * The sequence is valid if and only if each (space separated)
	 * move in the list is valid,
	 * starting from the initial position of the game.
	 * When the game has been won, no further moves are valid.
	 * @param moves a list of successive moves
	 * @return validity of the list of moves
	 */
	public boolean check(List <String> moves) {

		boolean valid = true;
		
		MoveParser parser = new MoveParserImpl();
		
		Player first = null;
		

		GenericMove p1Move = parser.parseMove(moves.get(0), p1, setting);
		GenericMove p2Move = parser.parseMove(moves.get(0), p2, setting);
		
		//Figure out who  moves first
		if (p1Move.isValid()) {
			first = p1;
		} else if (p2Move.isValid()) {
			first = p2;
		} else {
			valid = false;
		}
		
		Board localBoard = new BoardImpl((BoardImpl)setting);
		
		if (valid) {
			Player current = first;	
			
			Iterator <String> it = moves.iterator();
			String moveStr;
			GenericMove move;
			
			while (it.hasNext() && valid) {
				moveStr = it.next();
				
				move = parser.parseMove(moveStr, current, localBoard);
				
				if (move.isValid()) {
					move.makeMove();
					current = current.getOpponent();
				} else {
					valid = false;
				}
				
			}
			
		}
		
		return valid;
	}
	

}
