package student;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Stack;

public class MyCavernImpl implements MyCavern {
	private ArrayList<MyNode> nodes = new ArrayList<>();
	private long location;
	private Stack<Long> history = new Stack();
	
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
		history.push(this.location);
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
		return getLast();
	}
	
	private long getLast() {
		return history.pop();
	}

}
