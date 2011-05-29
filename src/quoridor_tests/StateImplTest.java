package quoridor_tests;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import quoridor.*;

public class StateImplTest {

	StateImpl test;
	Player pl1 = new PlayerImpl("steve", Player.BOTTOM);
	Player pl2 = new PlayerImpl("bec", Player.TOP);
	

	
	Pawn pn1 = new PawnImpl(new SquareImpl(0, 0), pl1);
	Pawn pn2 = new PawnImpl(new SquareImpl(8, 8), pl2);	

	Pair <Pawn> pawns = new PairImpl<Pawn>(pn1, pn2);
	Board testBoard = new BoardImpl(pawns);
		
	@Before
	public void setUp() throws Exception {
		
		
		
		pl1.setOpponent(pl2);
		pl2.setOpponent(pl1);
		
		
		test = new StateImpl(testBoard, pl1);
		
		
		
		
	}
	
	
	//@Test
	public void validTest() {
		
		MovePawn a = new MovePawnImpl(new SquareImpl(4, 4), new SquareImpl(4, 5), testBoard);
		assertTrue(a.isValid());
	}
	
	//@Test
	public void scoreTest() {
		testBoard.addWall(new WallImpl(new SquareImpl(1, 7), Wall.HORIZONTAL));
		testBoard.addWall(new WallImpl(new SquareImpl(3, 7), Wall.HORIZONTAL));
		testBoard.addWall(new WallImpl(new SquareImpl(5, 7), Wall.HORIZONTAL));
		testBoard.addWall(new WallImpl(new SquareImpl(7, 7), Wall.HORIZONTAL));
		
		System.out.println(testBoard);
		System.out.println(test.pathLength(pl2));
		System.out.println(test.bfs(pl2));
		test.determineScore();
		System.out.println(test.score);
	}

	@Test
	public void minimaxTester() {
		testBoard.addWall(new WallImpl(new SquareImpl(1, 7), Wall.HORIZONTAL));
		testBoard.addWall(new WallImpl(new SquareImpl(3, 7), Wall.HORIZONTAL));
		testBoard.addWall(new WallImpl(new SquareImpl(5, 7), Wall.HORIZONTAL));
		
		System.out.println(test.nextBestMove());
		
	}

}
