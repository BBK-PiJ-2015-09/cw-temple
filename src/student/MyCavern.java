package student;

import java.util.Stack;

/**
  * A MyCavern is our explorer's mental map of the Cavern.
  *
  * MyCaverns have a list of the MyNodes which they contain.
  */
public interface MyCavern {

	/**
	 * Adds a node to the cavern, if it is not already present.
	 *
	 * @param id, the id of the node to add.
	 */
	void addNode(long id);

	/**
	 * Adds a node to the cavern, if it is not already present.
	 *
	 * @param id, the id of the node to add.
	 * @param distance, the (straight-line) distance of the node from the goal.
	 */
	void addNode(long id, long distance);

	/**
	 * Returns a node by its ID.
	 *
	 * @param id, the id of the node to return.
	 * @return the MyNode.
	 * @throws IllegalArgumentException if MyNode is not found (I always expect to find it,
	 * something is wrong if not)
	 */
	MyNode getNode(long id);

	/**
	 * Set the current location of the explorer.
	 *
	 * @param id, the id of the location.
	 * @throws IllegalArgumentException if location is not found (I always expect to find it,
	 * something is wrong if not)
	 */
	void setLocation(long id);

	/**
	 * Get the current location of the explorer.
	 *
	 * @return the id of the location.
	 */
	long getLocation();

	/**
	 * Returns the size of the cavern.
	 *
	 * @return the number of nodes in the cavern.
	 */
	int size();

	/**
	 * Returns the id of the next neighbouring node to move to according to lowest distance. 
	 * Will retrace through its history if it hits a dead end.
	 *
	 * @return the next id to move to.
	 * @throws NoSuchElementException if id is not found (I always expect to find it,
	 * something is wrong if not)
	 */
	long getNext();

	/**
	 * Set all pathlengths to infinity.
	 */
	void setAllPathsInfinite();

	/**
	 * Return a stack containing the shortest path between two nodes.
	 *
	 * @param start the id of the starting node
	 * @param end the id of the ending node
	 * @return a stack of the next neighbouring id to move to in order
	 * to reach the end node in the shortest number of steps
	 */
	Stack<Long> getPath(long start, long end);

	/**
	 * Return the best node - ie., the node closest to the goal.
	 *
	 * @return the node with the lowest value for distance
	 */
	long getBestNode();

	/**
	 * Find the next step from each node on the shortest path to the given destination.
	 * Store it in the lastNode variable.
	 *
	 * @param goal the id of the node all lastNodes should work towards
	 */
	void setAllPathsTo(long goal);

	/**
	 * Return the size of the cavern.
	 *
	 * @return an int of the number of nodes in the cavern.
	 */
	int getSize();

}
