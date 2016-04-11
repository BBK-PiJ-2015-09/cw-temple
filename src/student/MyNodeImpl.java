package student;

public class MyNodeImpl implements MyNode {
	private long id;
	private boolean visited;
	private MyNode[] neighbours;
	
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
	
	public void addNeighbours(MyNode[] neighbours) {
		if (neighbours == null) {
			throw new NullPointerException();
		} else {
			this.neighbours = neighbours;
		}
	}
	
	public MyNode getNode(long id) {
		for(MyNode neighbour : neighbours) {
			if (neighbour.getId() == id) {
				return neighbour;
			}
		}
		throw new IllegalArgumentException();
	}
	
	public MyNode getNext() {
		for(MyNode neighbour : neighbours) {
			if (neighbour.getVisited() == false) {
				return neighbour;
			}
		}
		return null;
	}
}
