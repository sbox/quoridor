package quoridor_tests;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

import quoridor.*;


public class BoardImplTest {

	Board test;
	Pair <Pawn> pawns;
	
	@Before
	public void setUp() throws Exception {
		
		pawns = new PairImpl <Pawn> (new PawnImpl(new SquareImpl(4, 0), null), new PawnImpl(new SquareImpl(4, 8), null));
		test = new BoardImpl(pawns);
	}
	
	@Test
	public void testWallBetween() {
		Square s1 = new SquareImpl(1, 1);
		Square s2 = new SquareImpl(2, 1);
		Square s3 = new SquareImpl(1, 2);
		
		assertFalse(test.wallBetween(s1, s2));
		assertFalse(test.wallBetween(s1, s3));
		
		Wall w1 = new WallImpl(s1, Wall.VERTICAL);
		
		test.addWall(w1);
		
		assertTrue(test.wallBetween(s1, s2));
		assertFalse(test.wallBetween(s1, s3));
		
		Wall w2 = new WallImpl(s1, Wall.HORIZONTAL);
		
		test.addWall(w2);
		
		assertTrue(test.wallBetween(s1, s2));
		assertTrue(test.wallBetween(s1, s3));
	}
	
	@Test
	public void testPrint() {
		Square s1 = new SquareImpl(1, 1);
		
		Wall w1 = new WallImpl(s1, Wall.VERTICAL);
		Wall w2 = new WallImpl(s1, Wall.HORIZONTAL);
		
		test.addWall(w1);
		test.addWall(w2);
		
		System.out.println(test);
		
	}

}
