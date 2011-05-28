package quoridor;



public interface State extends Iterable <State> {
	
	GenericMove nextBestMove();
	
	
	
}
