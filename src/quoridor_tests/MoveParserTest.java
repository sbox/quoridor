package quoridor_tests;


import quoridor.GenericMove;
import quoridor.MoveParser;
import quoridor.MoveParserImpl;

import org.junit.Before; 
import static org.junit.Assert.*;
import org.junit.Test;

public class MoveParserTest {

	MoveParser parser;
	
	@Before
	public void setUp() throws Exception {
		
		parser = new MoveParserImpl();
	}
	
	@Test
	public void parse() {
		String test = parser.parseMoveString("e1 e7 a4h b5v");
		System.out.print(test);
		
		assertTrue(parser.hasNextMove());
		assertTrue(parser.nextMove().type() == GenericMove.PAWN);

		assertTrue(parser.hasNextMove());
		assertTrue(parser.nextMove().type() == GenericMove.PAWN);
		
		
		assertTrue(parser.hasNextMove());
		assertTrue(parser.nextMove().type() == GenericMove.WALL);
		
		assertTrue(parser.hasNextMove());
		assertTrue(parser.nextMove().type() == GenericMove.WALL);
	
		assertFalse(parser.hasNextMove());
	}
	
	
}
