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
	public String parseMoveString(String moveString);
	
	
	
}
