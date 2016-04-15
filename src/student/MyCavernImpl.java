package student;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Stack;

public class MyCavernImpl implements MyCavern {
	private ArrayList<MyNode> nodes = new ArrayList<>();
	private long location;
	private Stack<Long> history = new Stack();
	private boolean retracing = false;
	
	public MyCavernImpl() {}
	
	@Override
	public void addNode(long id, long distance) {
		for(MyNode node : nodes) {
			if(node.getId() == id) {
				return;
			}
		}
		MyNode node = new MyNodeImpl(id, distance);
		nodes.add(node);
	}
	
	@Override
	public MyNode getNode(long id) {
		for(MyNode node : nodes) {
			if(node.getId() == id) {
				return node;
			}
		}
		return null;
	}
	
	@Override
	public void setLocation(long location) {
		if (!retracing) {
			history.push(this.location);
		}
		this.location = location;
	}
	
	@Override
	public long getLocation() {
		return location;
	}
	
	@Override
	public int size() {
		return nodes.size();
	}
	
	@Override
	public long getNext() {
		ArrayList<Long> neighbours = getNode(location).getNeighbours();
		ArrayList<MyNode> options = new ArrayList<>();
		MyNode neighbour;
		for(long id : neighbours) {
			neighbour = getNode(id);
			if(neighbour.getVisited() == false) {
				options.add(neighbour);
			}
		}
		if(!options.isEmpty()) {
			if(retracing) {
				retracing = false;
			}
			MyNode output = options.get(0);
			for(MyNode option : options) {
				if(option.getDistance() < output.getDistance()) {
					output = option;
				}
			}
			return output.getId();
		} else {
			return getLast();
		}
	}
	
	@Override
	public void setAllUnsearched() {
		for(MyNode node : nodes) {
			if(node.getSearched()) {
				node.setSearched(false);
			}
		}
	}
	
	@Override
	public void setAllPathsInfinite() {
		for(MyNode node : nodes) {
			if(node.getPathLength() != Integer.MAX_VALUE) {
				node.setPathLength(Integer.MAX_VALUE);
			}
		}
	}
	
	@Override
	public Stack<Long> getPath(long start, long end) {
		// set all Node.searched to false
		// set all Node.pathLength to Integer.MAX_VALUE
		// Node should have lastStep
		// end = unvisited MyNode with the smallest distance to orb
		// start = location;
		// start.pathLength = 0;
		// get its neighbours
		// set the pathLength of each to this pathLength +1 (0+1) if less than its current pathLength.
		// start.searched = true;
		// while (node != end) {
			// lastNode = node;
			// node = node with the shortest pathLength node from the cavern where searched == false
			// set its lastStep to lastNode;
			// get its neighbours
			// set the pathLength of each to this pathLength +1 if less than its current pathLength.
			// node.searched = true
		// }
		// work back from the end through each lastStep, pushing the id of each onto a stack, all the way to the start
		// return a stack of all the steps to the next unvisited node
	}
	
	private long getLast() {
		if(!retracing) {
			retracing = true;
		}
		return history.pop();
	}

}
