package student;

import student.MyNode;

import java.util.Stack;

import game.EscapeState;
import game.ExplorationState;
import game.Node;

public class Explorer {

    /**
     * Explore the cavern, trying to find the orb in as few steps as possible.
     * Once you find the orb, you must return from the function in order to pick
     * it up. If you continue to move after finding the orb rather
     * than returning, it will not count.
     * If you return from this function while not standing on top of the orb,
     * it will count as a failure.
     * <p>
     * There is no limit to how many steps you can take, but you will receive
     * a score bonus multiplier for finding the orb in fewer steps.
     * <p>
     * At every step, you only know your current tile's ID and the ID of all
     * open neighbor tiles, as well as the distance to the orb at each of these tiles
     * (ignoring walls and obstacles).
     * <p>
     * To get information about the current state, use functions
     * getCurrentLocation(),
     * getNeighbours(), and
     * getDistanceToTarget()
     * in ExplorationState.
     * You know you are standing on the orb when getDistanceToTarget() is 0.
     * <p>
     * Use function moveTo(long id) in ExplorationState to move to a neighboring
     * tile by its ID. Doing this will change state to reflect your new position.
     * <p>
     * A suggested first implementation that will always find the orb, but likely won't
     * receive a large bonus multiplier, is a depth-first search.
     *
     * @param state the information available at the current state
     */
    public void explore(ExplorationState state) {
		MyCavern cavern = buildExploreCavern(state);
		MyNode node = cavern.getNode(state.getCurrentLocation());
		Stack<Long> path = new Stack<Long>();

    	while(state.getDistanceToTarget() > 0)  {
    		visit(cavern, node);
    		addNeighbours(state, cavern, node);
        	if(path.isEmpty()) {
        		path = cavern.getPath(cavern.getLocation(), cavern.getBestNode());
        	}
        	node = cavern.getNode(path.peek());
        	state.moveTo(path.pop());
    	}
    }
    
    /**
     * Escape from the cavern before the ceiling collapses, trying to collect as much
     * gold as possible along the way. Your solution must ALWAYS escape before time runs
     * out, and this should be prioritized above collecting gold.
     * <p>
     * You now have access to the entire underlying graph, which can be accessed through EscapeState.
     * getCurrentNode() and getExit() will return you Node objects of interest, and getVertices()
     * will return a collection of all nodes on the graph.
     * <p>
     * Note that time is measured entirely in the number of steps taken, and for each step
     * the time remaining is decremented by the weight of the edge taken. You can use
     * getTimeRemaining() to get the time still remaining, pickUpGold() to pick up any gold
     * on your current tile (this will fail if no such gold exists), and moveTo() to move
     * to a destination node adjacent to your current node.
     * <p>
     * You must return from this function while standing at the exit. Failing to do so before time
     * runs out or returning from the wrong location will be considered a failed run.
     * <p>
     * You will always have enough time to escape using the shortest path from the starting
     * position to the exit, although this will not collect much gold.
     *
     * @param state the information available at the current state
     */
    public void escape(EscapeState state) {
    	MyCavern cavern = buildEscapeCavern(state);
    	MyNode node = cavern.getNode(state.getCurrentNode().getId());
    	cavern.setAllPathsTo(state.getExit().getId());
    	searchForGold(state, cavern, node);
    	goToExit(state, cavern);
    }
    
    private void searchForGold(EscapeState state, MyCavern cavern, MyNode node) {
    	if(cavern.getSize() > 20) {
	       	while((state.getTimeRemaining()/19) > node.getPathLength())  {
	        	visit(cavern, node);
	        	node = cavern.getNode(cavern.getNext());
	        	makeEscapeMove(state, node.getId());
	    	}
    	}
    }
    
    private void goToExit(EscapeState state, MyCavern cavern) {
    	while(!(state.getCurrentNode() == state.getExit())) {
    		long currentId = state.getCurrentNode().getId();
    		long nextId = cavern.getNode(currentId).getLastNode().getId();
    		makeEscapeMove(state, nextId);
    	}
    }

    private void addNeighbours(ExplorationState state, MyCavern cavern, MyNode node) {
    	for (game.NodeStatus neighbour : state.getNeighbours()) {
    		cavern.addNode(neighbour.getId(), neighbour.getDistanceToTarget());
    		node.addNeighbour(neighbour.getId());
    	}
    }
    
    private void visit(MyCavern cavern, MyNode node) {
		cavern.setLocation(node.getId());
    	node.setVisited();
    }
    
    private void makeEscapeMove(EscapeState state, long id) {
		for (Node nextNode : state.getVertices()) {
			if(nextNode.getId() == id) {
				state.moveTo(nextNode);
			}
			getGold(state);
		}
    }
    
    private void getGold(EscapeState state) {
		if (state.getCurrentNode().getTile().getGold() > 0) {
			state.pickUpGold();
		}
    }
    
    private MyCavern buildExploreCavern(ExplorationState state) {
    	MyCavern cavern = new MyCavernImpl();
		cavern.addNode(state.getCurrentLocation(), state.getDistanceToTarget());
		return cavern;
    }
    
    private MyCavern buildEscapeCavern(EscapeState state) {
    	MyCavern cavern = new MyCavernImpl();
    	for(Node node : state.getVertices()) {
    		cavern.addNode(node.getId());
    		for(Node neighbour : node.getNeighbours()) {
    			cavern.getNode(node.getId()).addNeighbour(neighbour.getId());
    		}
    	}
    	return cavern;
    }
}
