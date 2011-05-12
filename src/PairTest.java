
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

import quoridor.*;

public class PairTest {

	Pair <String> pair;
	
	@Before
	public void setUp() throws Exception {
		pair = new PairImpl<String>("hello", "world");
	}
	
	@Test
	public void test_1() {
		assertEquals(pair._1(), "hello");
	}
	
	@Test
	public void test_2() {
		assertEquals(pair._2(), "world");
	}
	
	@Test
	public void testOther() {
		assertEquals(pair.other("hello"), "world");
		assertEquals(pair.other("world"), "hello");
	}
	
	@Test 
	public void testContains() {
		assertTrue(pair.contains("hello"));
		assertTrue(pair.contains("world"));
		assertFalse(pair.contains("helloworld"));
	}
	
	@Test
	public void testIterator() {
		
		int count = 0;
		
		for (String s : pair) {
			
			if (count == 0) {
				assertEquals(s, "hello");
			} else {
				assertEquals(s, "world");
			}
			
			count ++;
		}
		
		assertEquals(count, 2);
		
		
	}
	
	
}
