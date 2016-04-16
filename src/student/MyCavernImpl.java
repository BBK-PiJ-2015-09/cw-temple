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
	
	public void printState() {
		for(MyNode node : nodes) {
			node.printState();
			System.out.println("-------");
		}
		System.out.println("**********************");

	}
	
	@Override
	public Stack<Long> getPath(long start, long end) {
		MyNode lastStep;
		MyNode minimumNode;
		ArrayList<MyNode> unsearchedNodes = new ArrayList<>();
		ArrayList<MyNode> neighbours = new ArrayList<>();

		for(MyNode newNode : nodes) {
			unsearchedNodes.add(newNode);
		}
		
		setAllPathsInfinite();
		MyNode node = getNode(start);
		node.setPathLength(0);
		int loop = 3;
		while(node.getId() != end && loop > 0) {
			System.out.println("Current Node:" + node.getId());
			printState();
			lastStep = node;
			unsearchedNodes.remove(node);
			
			minimumNode = unsearchedNodes.get(0);
			for(MyNode newNode : unsearchedNodes) {
				if(newNode.getPathLength() < minimumNode.getPathLength()) {
					minimumNode = newNode;
				}
			}
			node = minimumNode;

			node.setLastNode(lastStep);
			
			// all good up to here
			
			for(long id : node.getNeighbours()) {
				neighbours.add(getNode(id));
			}

			for(MyNode neighbour : neighbours) {
				if(!unsearchedNodes.contains(neighbour)) {
					neighbours.remove(neighbour);
				}
			}
			
			for(MyNode neighbour : neighbours) {
				if((node.getPathLength() + 1) < neighbour.getPathLength()) {
					neighbour.setPathLength(node.getPathLength() + 1);
				}
			}
			
			neighbours.clear();
			loop--;
		}

		Stack<Long> output = new Stack<Long>();
		while(node.getId() != start) {
			output.push(node.getId());
			node = node.getLastNode();
		}
		return output;
	}
	
	private long getLast() {
		if(!retracing) {
			retracing = true;
		}
		return history.pop();
	}

}
