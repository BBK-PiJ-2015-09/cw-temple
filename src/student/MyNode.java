package student;

import java.util.ArrayList;

/**
  * A MyNode is a location on our explorer's mental map of the Cavern.
  *
  * MyNodes have an ID (matching the ID of the actual Node),
  * and a boolean marking them as visited or unvisited.
  */
public interface MyNode {

	/**
	 * Returns the ID of the current node.
	 *
	 * @return the ID, a long.
	 */
	long getId();
	
	/**
	 * Returns whether this node has been visited.
	 *
	 * @return a boolean, true if visited, false otherwise.
	 */
	boolean getVisited();
	
	/**
	 * Set this node to visited. Cannot be undone.
	 */
	 void setVisited();

	/**
	 * Get any neighbours to the node.
	 *
	 * @return the neighbourhood MyNodes in an ArrayList
	 */
	 ArrayList<MyNode> getNeighbours();
	
	/**
	 * Add a neighbour (if it does not already exist) to the node.
	 *
	 * @param neighbour the neighbour to be checked and added if new
	 */
	 void addNeighbour(MyNode neighbour);
	 
	/**
	 * Add any neighbours (which do not already exist) to the node.
   	 *
   	 * @param neighbours the neighbours to be checked and added if new
  	 * @throws NullPointerException if neighbours is null
	 */
	 void addNeighbours(MyNode[] neighbours);

	/**
	 * Get a MyNode by its ID.
   	 *
   	 * @param id the id of the MyNode to be returned
	 * @return the MyNode if found
   	 * @throws IllegalArgumentException if MyNode is not found (I always expect to find it,
	 *			something is wrong if not)
	 */
	MyNode getNode(long id);

	/**
	 * Get the ID of the next Node to move to.
	 * This node should on the route to the next unvisited node.
   	 *
	 * @return the next node to move to.
   	 * @throws NoSuchElementException if next node is not found (I always expect to find it,
	 *			something is wrong if not)
	 */
	 MyNode getNext();
}
