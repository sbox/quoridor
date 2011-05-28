package quoridor;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import quoridor.*;

public class StateImplTest {

	StateImpl test;
	Player pl1 = new PlayerImpl("steve", Player.TOP);
	Player pl2 = new PlayerImpl("bec", Player.BOTTOM);
	

	
	Pawn pn1 = new PawnImpl(new SquareImpl(4, 4), pl1);
	Pawn pn2 = new PawnImpl(new SquareImpl(4, 5), pl2);	

	Pair <Pawn> pawns = new PairImpl<Pawn>(pn1, pn2);
	Board testBoard = new BoardImpl(pawns);
		
	@Before
	public void setUp() throws Exception {
		
		
		
		pl1.setOpponent(pl2);
		pl2.setOpponent(pl1);
		
		
		test = new StateImpl(testBoard, pl2);
		
		
		
		
	}
	
	
	

	@Test
	public void minimaxTester() {
		

		System.out.println(test.nextBestMove());
		
	}

}
