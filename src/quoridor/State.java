package quoridor;


/**
 * This is the interface to be implemented by any AI player.
 * 
 * @author Stephen Sherratt
 *
 */
public interface State extends Iterable <StateImpl> {
	
	/**
	 * 
	 * @return
	 * 		the next best move to make
	 */
	GenericMove nextBestMove();
	
	
	
}
