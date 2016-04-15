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
	 * Returns the (straight-line) distance of the current node from the goal.
	 *
	 * @return the distance, a long.
	 */
	long getDistance();
	
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
	
	/**
	 * Returns whether this node has been searched.
	 *
	 * @return a boolean, true if searched, false otherwise.
	 */
	boolean getSearched();
	
	/**
	 * Set this node to searched/unsearched.
	 * 
	 * @param searched whether or not this has been searched.
	 */
	void setSearched(boolean searched);
	
	/**
	 * Get the path length of this node from the goal.
	 *
	 * @return an int of the pathlength of this node.
	 */
	int getPathLength();
	
	/**
	 * Set the pathlength of this node from the goal.
	 * 
	 * @param length the pathlength of this node
	 */
	void setPathLength(int length);

}
