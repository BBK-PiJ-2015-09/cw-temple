// package student;

/**
  * A MyNode is a location on our explorer's mental map of the Cavern.
  *
  * MyNodes have an ID (matching the ID of the actual Node),
  * and a boolean marking them as visited or unvisited.
  */
public interface MyNode {

	/**
	 * Set this node to visited. Cannot be undone.
	 */
	 void setVisited();

	/**
	 * Returns whether this node has been visited.
	 *
	 * @return a boolean, true if visited, false otherwise.
	 */
	boolean getVisited();

	/**
	 * Add any neighbours (which do not already exist) to the tree.
   	 *
   	 * @param neighbours the neighbours to be checked and added if new
  	 * @throws NullPointerException if neighbours is null
	 */
	// void addNeighbours(Collection<NodeStatus> neighbours);

	/**
	 * Get a MyNode by its ID.
   	 *
   	 * @param id the id of the MyNode to be returned
	 * @return the MyNode if found
	 * @throws NullPointerException if id is null
   	 * @throws IllegalArgumentException if MyNode is not found (I always expect to find it,
	 *			something is wrong if not)
	 */
	// MyNode getNode(long id);

	/**
	 * Get the ID of the next Node to move to.
	 * This node should on the route to the next unvisited node.
   	 *
	 * @return the id of the next node to move to.
   	 * @throws NoSuchElementException if next node is not found (I always expect to find it,
	 *			something is wrong if not)
	 */
	// long getNext();
}
