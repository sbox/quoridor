package quoridor;



public interface State extends Iterable <StateImpl> {
	
	GenericMove nextBestMove();
	
	
	
}
