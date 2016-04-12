package student;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

public class MyCavernTest {
	
	@Test
	public void testsAddNode() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1);
		long expected = 1;
		long output = cavern.getNode(1).getId();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsAddNodeOnlyOnce() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1);
		cavern.addNode(1);
		long expected = 1;
		long output = cavern.size();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsSetLocation() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1);
		cavern.setLocation(1);
		long expected = 1;
		long output = cavern.getLocation();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsGetNextUnvisited() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1);
		cavern.setLocation(1);
		cavern.getNode(1).addNeighbour(2);
		cavern.getNode(1).addNeighbour(3);
		cavern.addNode(2);
		cavern.getNode(2).setVisited();
		cavern.addNode(3);
		long expected = 3;
		long output = cavern.getNext();
		assertEquals(expected, output);
	}
	
	@Test public void testsGetNextRetracesSteps() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1);
		cavern.getNode(1).setVisited();
		cavern.getNode(1).addNeighbour(2);
		cavern.getNode(1).addNeighbour(4);
		cavern.setLocation(1);
		cavern.addNode(2);
		cavern.getNode(2).setVisited();
		cavern.getNode(2).addNeighbour(1);
		cavern.getNode(2).addNeighbour(3);
		cavern.setLocation(2);
		cavern.addNode(3);
		cavern.getNode(3).setVisited();
		cavern.getNode(3).addNeighbour(2);
		cavern.getNode(3).addNeighbour(4);
		cavern.getNode(3).addNeighbour(5);
		cavern.setLocation(3);
		cavern.addNode(4);
		cavern.getNode(4).setVisited();
		cavern.getNode(4).addNeighbour(3);
		cavern.getNode(4).addNeighbour(1);
		cavern.setLocation(4);
		cavern.addNode(5);
		long expected = 3;
		long output = cavern.getNext();
		assertEquals(expected, output);
	}

	@Test public void testsGetNextKeepsRetracingToUnvisited() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1);
		cavern.getNode(1).setVisited();
		cavern.getNode(1).addNeighbour(2);
		cavern.setLocation(1);
		cavern.addNode(2);
		cavern.getNode(2).setVisited();
		cavern.getNode(2).addNeighbour(1);
		cavern.getNode(2).addNeighbour(3);
		cavern.setLocation(2);
		cavern.addNode(3);
		cavern.getNode(3).setVisited();
		cavern.getNode(3).addNeighbour(2);
		cavern.getNode(3).addNeighbour(4);
		cavern.getNode(3).addNeighbour(6);
		cavern.setLocation(3);
		cavern.addNode(4);
		cavern.getNode(4).setVisited();
		cavern.getNode(4).addNeighbour(3);
		cavern.getNode(4).addNeighbour(5);
		cavern.setLocation(4);
		cavern.addNode(5);
		cavern.getNode(5).setVisited();
		cavern.getNode(5).addNeighbour(4);
		cavern.setLocation(5);
		cavern.addNode(6);
		cavern.setLocation(cavern.getNext());
		cavern.setLocation(cavern.getNext());
		long expected = 6;
		long output = cavern.getNext();
		assertEquals(expected, output);
	}
	
}
