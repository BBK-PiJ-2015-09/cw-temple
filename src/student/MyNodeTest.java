package student;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class MyNodeTest {

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
	public void testsGetNeighbours() {
		MyNode node = new MyNodeImpl(1);
		MyNode node2 = new MyNodeImpl(2);
		MyNode node3 = new MyNodeImpl(3);
		node.addNeighbour(node2);
		node.addNeighbour(node3);
		ArrayList<MyNode> expected = new ArrayList<>();
		expected.add(node2);
		expected.add(node3);
		ArrayList<MyNode> output = node.getNeighbours();
		assertEquals(expected, output);
	}
		assertEquals(expected, output);
	}
	
	@Test
	public void testsAddNeighbourDoublyLinked() {
		MyNode node = new MyNodeImpl(1);
		MyNode node2 = new MyNodeImpl(2);
		node.addNeighbour(node2);
		long expected = 1;
		long output = node2.getNext().getId();
		assertEquals(expected, output);
	}

	@Test
	public void testsAddNeighbourOnlyOnce() {
		MyNode node = new MyNodeImpl(1);
		MyNode node2 = new MyNodeImpl(2);
		node.addNeighbour(node2);
		node.addNeighbour(node2);
		long expected = 1;
		long output = node2.getNeighbours().size();
		assertEquals(expected, output);
	}
	
	@Test(expected= NullPointerException.class)
	public void testsAddNeighbourNull() {
		MyNode node = new MyNodeImpl(1);
		node.addNeighbour(null);
	}
	
	@Test
	public void testsGetNode() {
		MyNode node = new MyNodeImpl(1);
		MyNode node2 = new MyNodeImpl(2);
		node.addNeighbour(node2);
		long expected = 2;
		long output = node.getNode(2).getId();
		assertEquals(expected, output);
	}

	@Test(expected= IllegalArgumentException.class)
	public void testsGetNodeNotFound() {
		MyNode node = new MyNodeImpl(1);
		MyNode node2 = new MyNodeImpl(2);
		node.addNeighbour(node2);
		node.getNode(3);
	}
	
	@Test
	public void testsGetNext() {
		MyNode node = new MyNodeImpl(1);
		MyNode node2 = new MyNodeImpl(2);
		node.addNeighbour(node2);
		long expected = 2;
		long output = node.getNext().getId();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsGetNextUnvisited() {
		MyNode node = new MyNodeImpl(1);
		MyNode node2 = new MyNodeImpl(2);
		MyNode node3 = new MyNodeImpl(3);
		node2.setVisited();
		node.addNeighbour(node2);
		node.addNeighbour(node3);
		long expected = 3;
		long output = node.getNext().getId();
		assertEquals(expected, output);
	}
}
