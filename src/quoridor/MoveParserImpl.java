package quoridor;

import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;

public class MoveParserImpl implements MoveParser {

	Queue <GenericMove> moveBuffer;
	
	public MoveParserImpl() {
		moveBuffer = new LinkedList <GenericMove>();
	}
	
	@Override
	public GenericMove nextMove() {
		return moveBuffer.poll();
	}

	@Override
	public boolean hasNextMove() {
		return !moveBuffer.isEmpty();
	}

	@Override
	public String parseMoveString(String moveString) {
		String[] eachMove = moveString.split(" ");
		boolean stillValid = true;
		String errorMsg = null;
		
		for (int i = 0;i<eachMove.length && stillValid;i++) {
			GenericMove current = parseMove(eachMove[i]);
			if (current == null) {
				stillValid = false;
				
				errorMsg = "Parse error in ".concat(eachMove[i])
				 .concat(". Only preceding moves have been parsed.");
				
			} else {
				
				moveBuffer.add(current);
				
			}
		}
		
		return errorMsg;
		
	}
	
	private GenericMove parseMove(String move) {
		
		int len = move.length();
		boolean validInput = len == 2 || len == 3;
		int row = charNum(move.charAt(0));
		int col = -1;
		boolean type = GenericMove.PAWN;
		
		boolean dir = false;
		
		GenericMove result = null;;
		
		
		
			
			
		if (validInput && row == -1) {
			validInput = false;
		} else if (validInput) {
			
			col = parseInt(move.charAt(1));
			
		}
		
		
		
		if (validInput && col == -1) {
			validInput = false;
		} else if (validInput && len == 3){
			type = GenericMove.WALL;
			
			char dirChar = move.charAt(2);
			
			if (dirChar == 'h') {
				dir = Wall.HORIZONTAL;
			} else if (dirChar == 'v') {
				dir = Wall.VERTICAL;
			} else {
				validInput = false;
			}
		}
		
		if (validInput) {
			if (type == GenericMove.PAWN) {
				result = new MovePawnImpl(col, row);
				System.out.println(len);
			} else if (type == GenericMove.WALL){ 
				System.out.println(len + "a");
				result = new PlaceWallImpl(col, row, dir);
			}
		}
		
		return result;
	}
	

	private int parseInt(char ch) {
		int num = (int)ch - '0';
		if (num < 0 || num > 9) {
			num = -1;
		}
		return num;
	}
	
	private int charNum(char ch) {
		int num = (int)ch - 'a';
		
		if (num < 0 || num > 8) {
			num = -1;
		}
		
		
		return num;
	}

	@Override
	public Iterator<GenericMove> iterator() {
		return new MoveIterator();
	}
	
	private class MoveIterator implements Iterator <GenericMove> {

		@Override
		public boolean hasNext() {
			return hasNextMove();
		}

		@Override
		public GenericMove next() {
			return nextMove();
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
