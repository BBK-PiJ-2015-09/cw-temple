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
	 * Get any neighbour IDs to the node.
	 *
	 * @return the neighbourhood MyNode IDs in an ArrayList
	 */
	 ArrayList<Long> getNeighbours();
	 
	/**
	 * Add the id of a neighbour (if it does not already exist) to the node.
	 *
	 * @param id the id of the neighbour to be checked and added if new
	 */
	void addNeighbour(long id);

}
