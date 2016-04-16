package student;

import java.util.ArrayList;

public class MyNodeImpl implements MyNode {
	private long id;
	private boolean visited;
	private ArrayList<Long> neighbours = new ArrayList<>();
	private long distance;
	private boolean searched;
	private int pathLength;
	private MyNode lastNode;
	
	public MyNodeImpl(long id) {
		this.id = id;
	}
	
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
	public int getPathLength() {
		return pathLength;
	}
	
	@Override
	public void setPathLength(int pathLength) {
		this.pathLength = pathLength;
	}

	@Override
	public MyNode getLastNode() {
		return lastNode;
	}
	
	@Override
	public void setLastNode(MyNode lastNode) {
		this.lastNode = lastNode;
	}
	
	public void printState() {
		System.out.println("id: " + id);
		System.out.println("neighbours: ");
		for(long neighbour : neighbours) {
			System.out.println(neighbour);
		}
		System.out.println("pathlength: " + pathLength);
		if(lastNode != null) {
			System.out.println("lastNode: " + lastNode.getId());
		} else {
			System.out.println("lastNode: null");
		}

	}
}
