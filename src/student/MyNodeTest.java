package student;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class MyNodeTest {

	@Test
	public void testsGetId() {
		MyNode node = new MyNodeImpl(1, 1);
		long expected = 1;
		long output = node.getId();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsGetVisited() {
		MyNode node = new MyNodeImpl(1, 1);
		boolean expected = false;
		boolean output = node.getVisited();
		assertEquals(expected, output);
	}

	@Test
	public void testsSetVisited() {
		MyNode node = new MyNodeImpl(1, 1);
		node.setVisited();
		boolean expected = true;
		boolean output = node.getVisited();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsGetDistance() {
		MyNode node = new MyNodeImpl(1, 2);
		long expected = 2;
		long output = node.getDistance();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsGetNeighbours() {
		MyNode node = new MyNodeImpl(1, 1);
		node.addNeighbour(2);
		node.addNeighbour(3);
		ArrayList<Long> expected = new ArrayList<>();
		expected.add(2L);
		expected.add(3L);
		ArrayList<Long> output = node.getNeighbours();
		assertEquals(expected, output);
	}

	@Test
	public void testsAddNeighbourOnlyOnce() {
		MyNode node = new MyNodeImpl(1, 1);
		node.addNeighbour(2);
		node.addNeighbour(2);
		long expected = 1;
		long output = node.getNeighbours().size();
		assertEquals(expected, output);
	}

}
