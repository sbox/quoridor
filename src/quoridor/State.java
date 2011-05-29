package quoridor;



public interface State extends Iterable <StateImpl> {
	
	/**
	 * 
	 * @return
	 * 		the next best move to make
	 */
	GenericMove nextBestMove();
	
	
	
}
