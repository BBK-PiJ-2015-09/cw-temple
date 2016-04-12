package student;

import java.util.ArrayList;

public class MyNodeImpl implements MyNode {
	private long id;
	private boolean visited;
	private ArrayList<Long> neighbours = new ArrayList<>();
	
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
	
	public ArrayList<Long> getNeighbours() {
		return neighbours;
	}
	
	public void addNeighbour(long neighbour) {
		if(!this.neighbours.contains(neighbour)) {
			this.neighbours.add(neighbour);
		}
	}

}
