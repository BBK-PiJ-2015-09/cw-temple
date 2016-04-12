package student;

import java.util.ArrayList;

public class MyNodeImpl implements MyNode {
	private long id;
	private boolean visited;
	private ArrayList<MyNode> neighbours = new ArrayList<>();
	private ArrayList<Long> neighbour_ids = new ArrayList<>();
	
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
	
	public ArrayList<MyNode> getNeighbours() {
		return neighbours;
	}
	
	public ArrayList<Long> getNeighbourIds() {
		return neighbour_ids;
	}
	
	public void addNeighbour(MyNode neighbour) {
		if (neighbour == null) {
			throw new NullPointerException();
		} else {
			if( !this.neighbour_ids.contains(neighbour.getId()) ) {
				this.neighbours.add(neighbour);
				this.neighbour_ids.add(neighbour.getId());
			}
			if( !neighbour.getNeighbourIds().contains(this.getId()) ) {
				neighbour.addNeighbour(this);
			}
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
		return neighbours.get(0);
	}
}
