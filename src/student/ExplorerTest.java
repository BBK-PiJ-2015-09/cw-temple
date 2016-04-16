package student;

import org.junit.*;
import static org.junit.Assert.*;

import game.GameState;

public class ExplorerTest {
	
	@Test
	public void benchmark() {
		// Based on the code from main.TXTmain.java
		int numTimesToRun = 1000;
		int totalScore = 0;
    
		for (int i = 0; i < numTimesToRun; i++) {
			totalScore += GameState.runNewGame(0, false);
		}
		
		int expected = 20000;
		int avgScore = totalScore / numTimesToRun;
		assertEquals(expected, avgScore);
	}
	
}