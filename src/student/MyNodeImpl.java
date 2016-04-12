package student;

import java.util.ArrayList;

public class MyNodeImpl implements MyNode {
	private long id;
	private boolean visited;
	private ArrayList<MyNode> neighbours = new ArrayList<>();
	
	MyNodeImpl(long id) {
		this.id = id;
	}
	
	MyNodeImpl(long id, MyNode parent) {
		this.id = id;
		neighbours.add(parent);
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
	
	public void addNeighbour(MyNode neighbour) {
		if (neighbour == null) {
			throw new NullPointerException();
		} else {
			if( !this.neighbours.contains(neighbour) ) {
				this.neighbours.add(neighbour);
			}
			if( !neighbour.getNeighbours().contains(this) ) {
				neighbour.addNeighbour(this);
			}
		}
	}
	
	public void addNeighbours(MyNode[] neighbours) {
		if (neighbours == null) {
			throw new NullPointerException();
		} else {
			for(MyNode neighbour : neighbours) {
				if( !this.neighbours.contains(neighbour) ) {
					this.neighbours.add(neighbour);
				}
				if( !neighbour.getNeighbours().contains(this) ) {
					neighbour.addNeighbour(this);
				}
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
