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
	
		Wall w0 = new WallImpl(new SquareImpl(1, 0), Wall.HORIZONTAL);	
		Wall w1 = new WallImpl(new SquareImpl(1, 2), Wall.HORIZONTAL);
		Wall w2 = new WallImpl(new SquareImpl(6, 0), Wall.HORIZONTAL);
		Wall w3 = new WallImpl(new SquareImpl(6, 2), Wall.HORIZONTAL);
		Wall w4 = new WallImpl(new SquareImpl(0, 1), Wall.VERTICAL);
		Wall w5 = new WallImpl(new SquareImpl(2, 1), Wall.VERTICAL);
		Wall w6 = new WallImpl(new SquareImpl(5, 1), Wall.VERTICAL);
		Wall w7 = new WallImpl(new SquareImpl(7, 1), Wall.VERTICAL);
		Wall w8 = new WallImpl(new SquareImpl(5, 6), Wall.VERTICAL);
		Wall w9 = new WallImpl(new SquareImpl(7, 6), Wall.VERTICAL);
		Wall w10 = new WallImpl(new SquareImpl(6, 7), Wall.HORIZONTAL);
		Wall w11 = new WallImpl(new SquareImpl(6, 5), Wall.HORIZONTAL);
		Wall w12 = new WallImpl(new SquareImpl(4, 5), Wall.HORIZONTAL);
		Wall w13 = new WallImpl(new SquareImpl(2, 5), Wall.HORIZONTAL);
		
		test.addWall(w0);
    	test.addWall(w1);
		test.addWall(w2);
		test.addWall(w3);
		test.addWall(w4);
		test.addWall(w5);
		test.addWall(w6);
		test.addWall(w7);
		test.addWall(w8);
		test.addWall(w9);
		test.addWall(w10);
		test.addWall(w11);
		test.addWall(w12);
		test.addWall(w13);
		
		System.out.println(test);
		
	}

}
