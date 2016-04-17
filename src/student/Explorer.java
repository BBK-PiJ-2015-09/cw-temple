package student;

import student.MyNode;

import java.util.ArrayList;
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

    	// create the cavern
    	MyCavern cavern = new MyCavernImpl();
    	
		// add the node to the cavern
		cavern.addNode(state.getCurrentLocation(), state.getDistanceToTarget());
		MyNode node = cavern.getNode(state.getCurrentLocation());
		
		Stack<Long> path = new Stack<Long>();
		
    	while(state.getDistanceToTarget() != 0)  {
        	
    		// set location
    		cavern.setLocation(node.getId());
     		
        	// mark as visited
        	node.setVisited();
        	
        	// add any new neighbours to the cavern and the node
        	for (game.NodeStatus neighbour : state.getNeighbours()) {
        		cavern.addNode(neighbour.getId(), neighbour.getDistanceToTarget());
        		node.addNeighbour(neighbour.getId());
        	}
        	
        	if(path.isEmpty()) {
        		// get the path to the next best node
        		path = cavern.getPath(cavern.getLocation(), cavern.getBestNode());
        	}

            // move towards the next unvisited node on the board
        	node = cavern.getNode(path.peek());
        	state.moveTo(path.pop());

    	}
 
        return;

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

    	// build a cavern out of the vertices
    	MyCavern cavern = new MyCavernImpl();
    	for(Node node : state.getVertices()) {
    		cavern.addNode(node.getId());
    		cavern.getNode(node.getId()).setGold(node.getTile().getGold());
    		for(Node neighbour : node.getNeighbours()) {
    			cavern.getNode(node.getId()).addNeighbour(neighbour.getId());
    		}
    	}

    	// set the next step on the shortest path to the exit on each node
    	cavern.setAllPathsTo(state.getExit().getId());
    	
    	// move through the bestPath
    	for(long nextId : bestPath(state, cavern)) {
     		for (Node nextNode : state.getVertices()) {
    			if(nextNode.getId() == nextId) {
    				state.moveTo(nextNode);
    			}
    			if (state.getCurrentNode().getTile().getGold() > 0) {
    				state.pickUpGold();
    			}
    		}
    	}
    	
    	// if there's time, walk around a bit more    	    	
    	MyNode node = cavern.getNode(state.getCurrentNode().getId());
    	
       	while((state.getTimeRemaining()/14) > node.getPathLength())  {
        	// set location
    		cavern.setLocation(node.getId());
     		
        	// mark as visited
        	node.setVisited();
 
        	// get the next move towards the next unvisited node on the board
        	node = cavern.getNode(cavern.getNext());
        	
            // move towards the next unvisited node on the board
    		for (Node nextNode : state.getVertices()) {
    			if(nextNode.getId() == node.getId()) {
    				state.moveTo(nextNode);
    			}
    			if (state.getCurrentNode().getTile().getGold() > 0) {
    				state.pickUpGold();
    			}
    		}
    	}
    	
    	// move to the exit
    	while(state.getCurrentNode() != state.getExit()) {
    		long nextId = cavern.getNode(state.getCurrentNode().getId()).getLastNode().getId();
    		for (Node nextNode : state.getVertices()) {
    			if(nextNode.getId() == nextId) {
    				state.moveTo(nextNode);
    			}
    			if (state.getCurrentNode().getTile().getGold() > 0) {
    				state.pickUpGold();
    			}
    		}
    	}  	
    	
    	return;
    }
    
    private ArrayList<Long> bestPath(EscapeState state, MyCavern cavern) {
    	ArrayList<Long> best = new ArrayList<Long>();
    	int loop = 0;
    	int bestGold = 0;
    	while(loop < 10) {
    		if(tryPathGetGold(state, cavern, loop) > bestGold) {
    			best = tryPath(state, cavern, loop);
    			bestGold = tryPathGetGold(state, cavern, loop);
    		}
    		loop++;
    	}
    	return best;
    }
    
    // test run - non-destructive. Make sure to mark gold as picked up in the cavern or it will double-count!
    private ArrayList<Long> tryPath(EscapeState state, MyCavern cavern, int maxGoldIndex) {
    	
    	Stack<Long> backwardsPath = new Stack<Long>();
   	 
    	MyNode node = cavern.getNode(state.getCurrentNode().getId());
    	long id = 0;
    	
    	int timeRemaining = state.getTimeRemaining();
    	int gold = 0;
    	
    	Stack<Long> path = cavern.getPath(node.getId(), cavern.getMostGold(maxGoldIndex));
    	System.out.println(path);
       	while(timeRemaining / 55 > node.getPathLength())  {
        	// set location
    		cavern.setLocation(node.getId());

        	// mark as visited
        	node.setVisited();
       		
       		// first move to the maxGold index
       		if(!path.isEmpty()) {
            	id = path.pop();
       		} else {
       		// get the next move towards the next unvisited node on the board
            	id = cavern.getNext();
       		}
       		
        	node = cavern.getNode(id);
        	backwardsPath.push(id);
       		
        	// approximate the time remaining and stop after that point - the actual method will
        	// to watch the actual time, and head for the exit early if this makes a path that's too long
        	timeRemaining = timeRemaining - 10;

        	if(!node.getVisited()) {
        		gold = gold + node.getGold();
        	}
    	}
       	
       	cavern.reset();
       	
    	return new ArrayList<Long>(backwardsPath);
    }
    
    // test run - non-destructive. Make sure to mark gold as picked up in the cavern or it will double-count!
    private int tryPathGetGold(EscapeState state, MyCavern cavern, int maxGoldIndex) {
    	Stack<Long> backwardsPath = new Stack<Long>();
   	 
    	MyNode node = cavern.getNode(state.getCurrentNode().getId());
    	long id = 0;
    	
    	int timeRemaining = state.getTimeRemaining();
    	int gold = 0;
    	
       	while(timeRemaining / 16 > node.getPathLength())  {
        	// set location
    		cavern.setLocation(node.getId());

        	// mark as visited
        	node.setVisited();
 
        	// get the next move towards the next unvisited node on the board
        	id = cavern.getNext();

        	node = cavern.getNode(id);
        	backwardsPath.push(id);

        	// approximate the time remaining and stop after that point - the actual method will
        	// to watch the actual time, and head for the exit early if this makes a path that's too long
        	timeRemaining = timeRemaining - 10;

        	if(!node.getVisited()) {
        		gold = gold + node.getGold();
        	}
    	}
       	
       	cavern.reset();
       	
    	return gold;
    }
}

