package student;

import org.junit.*;
import static org.junit.Assert.*;

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
	public void testsGetNextUnvisited() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1);
		cavern.addNode(2);
		cavern.addNode(3);
		cavern.getNode(2).setVisited();
		long expected = 3;
		long output = cavern.getNext();
		assertEquals(expected, output);
	}
}
