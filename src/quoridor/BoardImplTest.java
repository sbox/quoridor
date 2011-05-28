package quoridor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardImplTest {
	
	Board test;
	
	Pawn pn1;
	Pawn pn2;

	@Before
	public void setUp() throws Exception {
	
		Player pl1 = new PlayerImpl("steve", Player.TOP);
		Player pl2 = new PlayerImpl("bec", Player.BOTTOM);
		
		pn1 = new PawnImpl(new SquareImpl(3, 3), pl1);
		pn2 = new PawnImpl(new SquareImpl(3, 4), pl2);	
	
		Pair <Pawn> pawns = new PairImpl<Pawn>(pn1, pn2);
		
		test = new BoardImpl(pawns);
		
	}

	@Test
	public void testPathToGoal() {
		
		assertTrue(test.pathToGoal(pn1));
		
		test.addWall(new WallImpl(new SquareImpl(0, 3), Wall.HORIZONTAL));
		System.out.println(test);
	}

}
