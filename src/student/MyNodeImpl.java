package student;

import java.util.ArrayList;

public class MyNodeImpl implements MyNode {
	private long id;
	private boolean visited;
	private ArrayList<Long> neighbours = new ArrayList<>();
	private long distance;
	private boolean searched;
	
	public MyNodeImpl(long id, long distance) {
		this.id = id;
		this.distance = distance;
	}
	
	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public boolean getVisited() {
		return visited;
	}
	
	@Override
	public void setVisited() {
		visited = true;
	}
	
	@Override
	public long getDistance() {
		return distance;
	}
	
	@Override
	public ArrayList<Long> getNeighbours() {
		return neighbours;
	}
	
	@Override
	public void addNeighbour(long neighbour) {
		if(!this.neighbours.contains(neighbour)) {
			this.neighbours.add(neighbour);
		}
	}

	@Override
	public boolean getSearched() {
		return searched;
	}
	
	@Override
	public void setSearched(boolean searched) {
		this.searched = searched;
	}
	
}
