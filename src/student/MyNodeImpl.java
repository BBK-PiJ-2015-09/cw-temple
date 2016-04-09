package student;

public class MyNodeImpl implements MyNode {
	private boolean visited;
	
	MyNodeImpl(long id) {

	}
	
	public boolean getVisited() {
		return visited;
	}
	
	public void setVisited() {
		visited = true;
	}
}
