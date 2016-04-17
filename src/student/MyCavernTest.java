package student;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Stack;

public class MyCavernTest {
	
	@Test
	public void testsAddNodeById() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1);
		long expected = 1;
		long output = cavern.getNode(1).getId();
		assertEquals(expected, output);
	}
	
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
	public void testsGetPathSecondStep() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(0, 2);
		cavern.getNode(0).addNeighbour(1);
		cavern.getNode(0).setVisited();
		cavern.setLocation(0);
		cavern.addNode(1, 1);
		cavern.getNode(1).addNeighbour(0);
		cavern.getNode(1).addNeighbour(2);
		cavern.addNode(2, 1);
		cavern.getNode(2).addNeighbour(1);
		cavern.getNode(2).addNeighbour(3);

		cavern.getPath(0L,1L);		
		cavern.getNode(1).setVisited();
		cavern.setLocation(1);
		
		Stack<Long> expected = new Stack<Long>(); 
		expected.push(2L);
		Stack<Long> output = output = cavern.getPath(1L,2L);
		assertEquals(expected, output);
	}
	
	@Test
	public void testsGetBestNode() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1, 1);
		cavern.getNode(1).setVisited();
		cavern.addNode(2, 2);
		cavern.getNode(2).setVisited();
		cavern.addNode(3, 2);
		cavern.addNode(4, 3);
		long expected = 3;
		long output = cavern.getBestNode();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsSetAllPaths() {
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
		
		cavern.setAllPathsTo(6);
		cavern.printState();
		
		long expected = 2;
		long output = cavern.getNode(1).getLastNode().getId();
		assertEquals(expected, output);
		
		expected = 3;
		output = cavern.getNode(7).getLastNode().getId();
		assertEquals(expected, output);
		
		expected = 7;
		output = cavern.getNode(8).getLastNode().getId();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsSetAllPathsFromMiddle() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(5);
		cavern.getNode(5).addNeighbour(3);
		cavern.addNode(1);
		cavern.getNode(1).addNeighbour(2);
		cavern.addNode(2);
		cavern.getNode(2).addNeighbour(1);
		cavern.getNode(2).addNeighbour(3);
		cavern.addNode(3);
		cavern.getNode(3).addNeighbour(2);
		cavern.getNode(3).addNeighbour(4);
		cavern.getNode(3).addNeighbour(5);
		cavern.addNode(4);
		cavern.getNode(4).addNeighbour(3);
		
		cavern.setAllPathsTo(4);
		
		long expected = 2;
		long output = cavern.getNode(1).getLastNode().getId();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsReset() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1);
		cavern.getNode(1).setVisited();
		cavern.setLocation(cavern.getNode(1).getId());
		cavern.setRetracing(true);
		
		cavern.reset();
		
		boolean expected = false;
		boolean output;
		
		output = cavern.getNode(1).getVisited();
		assertEquals(expected, output);

		output = cavern.getRetracing();
		assertEquals(expected, output);

		output = cavern.anyHistory();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsSetRetracing() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1, 1);
		cavern.setRetracing(false);
		boolean expected = false;
		boolean output = cavern.getRetracing();
		assertEquals(expected, output);
	}
	
	@Test
	public void testsGetMostGold() {
		MyCavern cavern = new MyCavernImpl();
		cavern.addNode(1, 1);
		cavern.getNode(1).setGold(23);
		cavern.addNode(2, 1);
		cavern.getNode(2).setGold(15);
		cavern.addNode(3, 1);
		cavern.getNode(3).setGold(5);
		cavern.setRetracing(false);
		boolean expected = 1;
		boolean output = cavern.getMostGold(0);
		assertEquals(expected, output);
		
		expected = 3;
		output = cavern.getMostGold(2);
		assertEquals(expected, output);
	}
}
