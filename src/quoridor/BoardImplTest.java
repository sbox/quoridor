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
		assertTrue(test.pathToGoal(pn2));
		
		test.addWall(new WallImpl(new SquareImpl(0, 3), Wall.HORIZONTAL));
		test.addWall(new WallImpl(new SquareImpl(2, 3), Wall.HORIZONTAL));
		test.addWall(new WallImpl(new SquareImpl(4, 3), Wall.HORIZONTAL));
		test.addWall(new WallImpl(new SquareImpl(6, 3), Wall.HORIZONTAL));
		test.addWall(new WallImpl(new SquareImpl(7, 2), Wall.HORIZONTAL));
		
		assertTrue(test.pathToGoal(pn1));
		assertTrue(test.pathToGoal(pn2));
		
		test.addWall(new WallImpl(new SquareImpl(6, 2), Wall.VERTICAL));
		
		System.out.println(test);
		
		assertFalse(test.pathToGoal(pn1));
		assertFalse(test.pathToGoal(pn2));
	}

	
	@Test
	public void testPathToGoal2() {
		
		assertTrue(test.pathToGoal(pn1));
		assertTrue(test.pathToGoal(pn2));
		
		test.addWall(new WallImpl(new SquareImpl(0, 3), Wall.HORIZONTAL));
		test.addWall(new WallImpl(new SquareImpl(2, 3), Wall.HORIZONTAL));
		test.addWall(new WallImpl(new SquareImpl(4, 3), Wall.HORIZONTAL));
		test.addWall(new WallImpl(new SquareImpl(6, 3), Wall.HORIZONTAL));
		test.addWall(new WallImpl(new SquareImpl(7, 2), Wall.HORIZONTAL));
		
		assertTrue(test.pathToGoal(pn1));
		assertTrue(test.pathToGoal(pn2));
		
		test.addWall(new WallImpl(new SquareImpl(3, 2), Wall.VERTICAL));
		test.addWall(new WallImpl(new SquareImpl(2, 2), Wall.VERTICAL));
		assertTrue(test.pathToGoal(pn1));
		assertTrue(test.pathToGoal(pn2));
		
		
		
		test.addWall(new WallImpl(new SquareImpl(2, 1), Wall.HORIZONTAL));
		
		
		System.out.println(test);
		
		assertFalse(test.pathToGoal(pn1));
		assertTrue(test.pathToGoal(pn2));
	}
	
	@Test
	public void testPathToGoal3() {
		
		Player pl1 = new PlayerImpl("steve", Player.TOP);
		Player pl2 = new PlayerImpl("bec", Player.BOTTOM);
		
		pn1 = new PawnImpl(new SquareImpl(4, 0), pl1);
		pn2 = new PawnImpl(new SquareImpl(4, 8), pl2);	
	
		Pair <Pawn> pawns = new PairImpl<Pawn>(pn1, pn2);
		
		test = new BoardImpl(pawns);
		
		
		assertTrue(test.pathToGoal(pn1));
		assertTrue(test.pathToGoal(pn2));
		
		test.addWall(new WallImpl(new SquareImpl(3, 0), Wall.VERTICAL));
		test.addWall(new WallImpl(new SquareImpl(4, 1), Wall.HORIZONTAL));
		MoveParser parser = new MoveParserImpl();
		GenericMove place = parser.loadMove(pl1, test, "f1v");
		
		assertFalse(place.isValid());
		
		//assertFalse(test.pathToGoal(pn1));
		assertTrue(test.pathToGoal(pn2));
		
		System.out.println(test);
	
	}
	
	
	
	
	
}
