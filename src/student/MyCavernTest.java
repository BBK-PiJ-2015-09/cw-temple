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
	
	@Test(expected= NoSuchElementException.class)
	public void testsGetNextNotPresent() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1);
		cavern.setLocation(1);
		cavern.getNext();
	}

}
