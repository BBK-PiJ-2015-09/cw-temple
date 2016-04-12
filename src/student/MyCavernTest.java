package student;

import org.junit.*;
import static org.junit.Assert.*;

public class MyCavernTest {
	
	@Test
	public void testsaddNode() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1);
		long expected = 1;
		long output = cavern.getNode(1).getId();
		assertEquals(expected, output);
	}
	
}
