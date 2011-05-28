package quoridor;

/**
 * 
 * Scans strings and returns a move at a time.
 * 
 * @author Stephen Sherratt
 *
 */
public interface MoveParser extends Iterable <GenericMove>{
	public GenericMove nextMove();
	public boolean hasNextMove();
	public String parseMoveString(String moveString, Player currentPlayer, Board board) ;
	public GenericMove parseMove(String move, Player current, Board board);
	public GenericMove scanMove(Player current, Board board);
	public GenericMove loadMove(Player current, Board board, String move);
	
}
