package quoridor_tests;

import org.junit.Before;   

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import org.junit.Test;

import quoridor.*;

public class ValidatorTest {

	Validator test;
	
	@Before
	public void setUp() throws Exception {
		test = new Validator();
	}
	
	@Test
	public void checkTest() {
		//assertTrue(new WallImpl(new SquareImpl(7, 3), Wall.HORIZONTAL).equals(new WallImpl(new SquareImpl(0, 4), Wall.HORIZONTAL)));
		assertTrue(test.check(asList ("a5h", "e2", "c5h", "e3", "e5h", "e4", "e4v", "h4h")));
	}
	
	
}
