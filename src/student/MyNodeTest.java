package student;

import org.junit.*;
import static org.junit.Assert.*;

public class MyNodeTest {

	@Test
	public void testsConstructor() {
		MyNode node = new MyNodeImpl(1);
	}

	@Test
	public void testsGetId() {
		MyNode node = new MyNodeImpl(1);
		long expected = 1;
		long output = node.getId();
		assertEquals(expected, output);
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

	@Test
	public void testsAddNeighbours() {
		MyNode node = new MyNodeImpl(1);
		MyNode node2 = new MyNodeImpl(2);
		MyNode node3 = new MyNodeImpl(3);
		MyNode node4 = new MyNodeImpl(4);
		MyNode[] neighbours = {node2, node3, node4};
		node.addNeighbours(neighbours);
	}
	
	@Test
	public void testsGetNode() {
		MyNode node = new MyNodeImpl(1);
		MyNode node2 = new MyNodeImpl(2);
		MyNode[] neighbours = {node2};
		node.addNeighbours(neighbours);
		long expected = 2;
		long output = node.getNode(2).getId();
		assertEquals(expected, output);
	}

	@Test
	public void testsGetNext() {
		MyNode node = new MyNodeImpl(1);
		MyNode node2 = new MyNodeImpl(2);
		MyNode[] neighbours = {node2};
		node.addNeighbours(neighbours);
		long expected = 2;
		long output = node.getNext();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsGetNextUnvisited() {
		MyNode node = new MyNodeImpl(1);
		long[] neighbours = {2, 3};
		node.getNode(2).setVisited();
		node.addNeighbours(neighbours);
		long expected = 3;
		long output = node.getNext().getId();
		assertEquals(expected, output);
	}
}
