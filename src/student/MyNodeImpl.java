package student;

import java.util.ArrayList;

public class MyNodeImpl implements MyNode {
	private long id;
	private boolean visited;
	private ArrayList<Long> neighbours = new ArrayList<>();
	private long distance;
	
	MyNodeImpl(long id, long distance) {
		this.id = id;
		this.distance = distance;
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
	
	public long getDistance() {
		return distance;
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
