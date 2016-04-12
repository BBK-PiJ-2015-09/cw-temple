package student;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyCavernImpl implements MyCavern {
	private ArrayList<MyNode> nodes = new ArrayList<>();
	private long location;
	
	MyCavernImpl() {}
	
	public void addNode(long id) {
		for(MyNode node : nodes) {
			if(node.getId() == id) {
				return;
			}
		}
		MyNode node = new MyNodeImpl(id);
		nodes.add(node);
	}
	
	public MyNode getNode(long id) {
		for(MyNode node : nodes) {
			if(node.getId() == id) {
				return node;
			}
		}
		return null;
	}
	
	public void setLocation(long location) {
		this.location = location;
	}
	
	public long getLocation() {
		return location;
	}
	
	public int size() {
		return nodes.size();
	}
	
	public long getNext() {
		ArrayList<Long> neighbours = getNode(location).getNeighbours();
		MyNode neighbour;
		for(long id : neighbours) {
			neighbour = getNode(id);
			if(neighbour.getVisited() == false) {
				return id;
			}
		}
		throw new NoSuchElementException();
	}
}
