package student;

public class MyNodeImpl implements MyNode {
	private long id;
	private boolean visited;
	private long[] neighbours;
	
	MyNodeImpl(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public boolean getVisited() {
		return visited;
	}
	
	public void setVisited() {
		visited = true;
	}
	
	public void addNeighbours(long[] neighbours) {
		this.neighbours = neighbours;
	}
	
	public long getNext() {
		return neighbours[0];
	}
}
