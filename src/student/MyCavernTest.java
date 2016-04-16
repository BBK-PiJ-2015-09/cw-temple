package student;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.Stack;

public class MyCavernTest {
	
	@Test
	public void testsAddNode() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1, 1);
		long expected = 1;
		long output = cavern.getNode(1).getId();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsAddNodeOnlyOnce() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1, 1);
		cavern.addNode(1, 1);
		long expected = 1;
		long output = cavern.size();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsSetLocation() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1, 1);
		cavern.setLocation(1);
		long expected = 1;
		long output = cavern.getLocation();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsGetNextUnvisited() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1, 1);
		cavern.setLocation(1);
		cavern.getNode(1).addNeighbour(2);
		cavern.getNode(1).addNeighbour(3);
		cavern.addNode(2, 1);
		cavern.getNode(2).setVisited();
		cavern.addNode(3, 1);
		long expected = 3;
		long output = cavern.getNext();
		assertEquals(expected, output);
	}
	
	@Test 
	public void testsGetNextRetracesSteps() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1, 1);
		cavern.getNode(1).setVisited();
		cavern.getNode(1).addNeighbour(2);
		cavern.getNode(1).addNeighbour(4);
		cavern.setLocation(1);
		cavern.addNode(2, 1);
		cavern.getNode(2).setVisited();
		cavern.getNode(2).addNeighbour(1);
		cavern.getNode(2).addNeighbour(3);
		cavern.setLocation(2);
		cavern.addNode(3, 1);
		cavern.getNode(3).setVisited();
		cavern.getNode(3).addNeighbour(2);
		cavern.getNode(3).addNeighbour(4);
		cavern.getNode(3).addNeighbour(5);
		cavern.setLocation(3);
		cavern.addNode(4, 1);
		cavern.getNode(4).setVisited();
		cavern.getNode(4).addNeighbour(3);
		cavern.getNode(4).addNeighbour(1);
		cavern.setLocation(4);
		cavern.addNode(5, 1);
		long expected = 3;
		long output = cavern.getNext();
		assertEquals(expected, output);
	}

	@Test 
	public void testsGetNextKeepsRetracingToUnvisited() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1, 1);
		cavern.getNode(1).setVisited();
		cavern.getNode(1).addNeighbour(2);
		cavern.setLocation(1);
		cavern.addNode(2, 1);
		cavern.getNode(2).setVisited();
		cavern.getNode(2).addNeighbour(1);
		cavern.getNode(2).addNeighbour(3);
		cavern.setLocation(2);
		cavern.addNode(3, 1);
		cavern.getNode(3).setVisited();
		cavern.getNode(3).addNeighbour(2);
		cavern.getNode(3).addNeighbour(4);
		cavern.getNode(3).addNeighbour(6);
		cavern.setLocation(3);
		cavern.addNode(4, 1);
		cavern.getNode(4).setVisited();
		cavern.getNode(4).addNeighbour(3);
		cavern.getNode(4).addNeighbour(5);
		cavern.setLocation(4);
		cavern.addNode(5, 1);
		cavern.getNode(5).setVisited();
		cavern.getNode(5).addNeighbour(4);
		cavern.setLocation(5);
		cavern.addNode(6, 1);
		cavern.setLocation(cavern.getNext());
		cavern.setLocation(cavern.getNext());
		long expected = 6;
		long output = cavern.getNext();
		assertEquals(expected, output);
	}
	
	@Test 
	public void testsGetNextChoosesLeastDistance() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1, 3);
		cavern.getNode(1).setVisited();
		cavern.getNode(1).addNeighbour(2);
		cavern.getNode(1).addNeighbour(3);
		cavern.getNode(1).addNeighbour(4);
		cavern.setLocation(1);
		cavern.addNode(2, 2);
		cavern.getNode(2).addNeighbour(1);
		cavern.addNode(3, 2);
		cavern.getNode(3).addNeighbour(1);
		cavern.addNode(4, 1);
		cavern.getNode(4).addNeighbour(1);
		long expected = 4;
		long output = cavern.getNext();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsSetAllPathsInfinite() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1, 1);
		cavern.setAllPathsInfinite();		
		int expected = Integer.MAX_VALUE;
		int output = cavern.getNode(1).getPathLength();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsGetPath() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1, 1);
		cavern.getNode(1).addNeighbour(2);
		cavern.addNode(2, 1);
		cavern.getNode(2).addNeighbour(1);
		cavern.getNode(2).addNeighbour(3);
		cavern.addNode(3, 1);
		cavern.getNode(3).addNeighbour(2);
		cavern.getNode(3).addNeighbour(4);
		cavern.getNode(3).addNeighbour(7);
		cavern.addNode(4, 1);
		cavern.getNode(4).addNeighbour(3);
		cavern.getNode(4).addNeighbour(5);
		cavern.addNode(5, 1);
		cavern.getNode(5).addNeighbour(4);
		cavern.getNode(5).addNeighbour(6);
		cavern.getNode(5).addNeighbour(11);
		cavern.addNode(6, 1);
		cavern.getNode(6).addNeighbour(5);
		cavern.addNode(7, 1);
		cavern.getNode(7).addNeighbour(3);
		cavern.getNode(7).addNeighbour(8);
		cavern.addNode(8, 1);
		cavern.getNode(8).addNeighbour(7);
		cavern.getNode(8).addNeighbour(9);
		cavern.addNode(9, 1);
		cavern.getNode(9).addNeighbour(8);
		cavern.getNode(9).addNeighbour(10);
		cavern.addNode(10, 1);
		cavern.getNode(10).addNeighbour(9);
		cavern.getNode(10).addNeighbour(11);
		cavern.addNode(11, 1);
		cavern.getNode(11).addNeighbour(5);
		cavern.getNode(11).addNeighbour(10);

		Stack<Long> expected = new Stack<Long>(); 
		expected.push(6L);
		expected.push(5L);
		expected.push(4L);
		expected.push(3L);
		expected.push(2L);
		Stack<Long> output = cavern.getPath(1L,6L);
		assertEquals(expected, output);
	}
	
	@Test
	public void testsGetBestNode() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1, 2);
		cavern.addNode(2, 2);
		cavern.addNode(3, 1);
		cavern.addNode(4, 2);
		long expected = 3;
		long output = cavern.getBestNode().getId();
		assertEquals(expected, output);
	}
}
