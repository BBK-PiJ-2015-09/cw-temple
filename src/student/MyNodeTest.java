package student;

import org.junit.*;
import static org.junit.Assert.*;

public class MyNodeTest {

	@Test
	public void testsConstructor() {
		MyNode node = new MyNodeImpl(1);
	}

	@Test
	public void testsGetVisited() {
		MyNode node = new MyNodeImpl(1);
		boolean expected = false;
		boolean output = node.getVisited();
		assertEquals(expected, output);
	}

	@Test
	public void testsSetVisited() {
		MyNode node = new MyNodeImpl(1);
		node.setVisited();
		boolean expected = true;
		boolean output = node.getVisited();
		assertEquals(expected, output);
	}

}
