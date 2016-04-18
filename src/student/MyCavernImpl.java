package student;

import java.util.ArrayList;
import java.util.Stack;

public class MyCavernImpl implements MyCavern {
	private ArrayList<MyNode> nodes = new ArrayList<>();
	private long location;
	private Stack<Long> history = new Stack<Long>();
	private boolean retracing = false;

	public MyCavernImpl() {}

	@Override
	public void addNode(long id) {
		for(MyNode node : nodes) {
			if(node.getId() == id) {
				return;
			}
		}
		MyNode node = new MyNodeImpl(id);
		nodes.add(node);
	}

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
		ArrayList<MyNode> options = addNewNeighbours();
		if(!options.isEmpty()) {
			stopRetracing();
			return getNearest(options);
		} else {
			startRetracing();
			return getLast();
		}
	}

	private long getNearest(ArrayList<MyNode> nodes) {
		MyNode nearest = nodes.get(0);
		for(MyNode node : nodes) {
			if(node.getDistance() < nearest.getDistance()) {
				nearest = node;
			}
		}
		return nearest.getId();
	}

	private void startRetracing() {
		if(!retracing) {
			retracing = true;
		}
	}

	private void stopRetracing() {
		if(retracing) {
			retracing = false;
		}
	}

	private ArrayList<MyNode> addNewNeighbours() {
		ArrayList<MyNode> options = new ArrayList<>();
		MyNode neighbour;
		for(long id : getNode(location).getNeighbours()) {
			neighbour = getNode(id);
			if(neighbour.getVisited() == false) {
				options.add(neighbour);
			}
		}
		return options;
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
		System.out.println("**********");

	}

	@Override
	public Stack<Long> getPath(long start, long end) {
		MyNode minimumNode;
		MyNode currentNode;
		MyNode endNode = nodes.get(0);

		ArrayList<MyNode> unsearchedNodes = new ArrayList<>();
		ArrayList<MyNode> nodesToRemove = new ArrayList<>();
		ArrayList<MyNode> neighbours = new ArrayList<>();

		// add all nodes to unsearched set
		for(MyNode newNode : nodes) {
			unsearchedNodes.add(newNode);
		}

		// set all node paths to max
		setAllPathsInfinite();

		// set currentNode to start
		currentNode = getNode(start);

		// set currentNode pathlength to zero (it's the start)
		currentNode.setPathLength(0);

		// loop until currentNode is the end
		while(!unsearchedNodes.isEmpty()) {

			// get the neighbours of this node
			for(long id : currentNode.getNeighbours()) {
				neighbours.add(getNode(id));
			}

			// remove any not in the unsearched set
			for(MyNode neighbour : neighbours) {
				if(!unsearchedNodes.contains(neighbour)) {
					nodesToRemove.add(neighbour);
				}
			}

			for(MyNode node : nodesToRemove) {
				neighbours.remove(node);
			}

			// if the neighbour's pathlength is greater than this node's pathlength + 1:
				// set its pathlength to pathlength + 1
				// set the neighbour's lastNode to this node
			for(MyNode neighbour : neighbours) {
				if(neighbour.getPathLength() > (currentNode.getPathLength() + 1)) {
					neighbour.setPathLength(currentNode.getPathLength() + 1);
					neighbour.setLastNode(currentNode);
				}
			}

			neighbours.clear();

			// remove currentNode from the set of unsearched nodes
			unsearchedNodes.remove(currentNode);

			// get the first of the unsearched nodes as the initial minimum node
			if(!unsearchedNodes.isEmpty()) {
				minimumNode = unsearchedNodes.get(0);
			} else {
				minimumNode = new MyNodeImpl(999);
			}

			// find the unsearched node with the minimum pathlength
			for(MyNode node : unsearchedNodes) {
				if(node.getPathLength() < minimumNode.getPathLength()) {
					minimumNode = node;
				}
			}

			// set currentNode to the node with the shortest pathlength
			currentNode = minimumNode;

			if(currentNode.getId() == end) {
				endNode = currentNode;
			}

		}

		currentNode = endNode;

		Stack<Long> output = new Stack<Long>();
		while(currentNode.getId() != start) {
			output.push(currentNode.getId());
			currentNode = currentNode.getLastNode();
		}

		return output;
	}

	@Override
	public long getBestNode() {
		ArrayList<MyNode> unvisitedNodes = new ArrayList<MyNode>();
		for(MyNode node : nodes) {
			if(!node.getVisited()) {
				unvisitedNodes.add(node);
			}
		}
		return getNearest(unvisitedNodes);
	}

	@Override
	public void setAllPathsTo(long goal) {
		getPath(goal, nodes.get(0).getId());
	}

	@Override
	public int getSize() {
		return nodes.size();
	}

	private long getLast() {
		return history.pop();
	}

}
