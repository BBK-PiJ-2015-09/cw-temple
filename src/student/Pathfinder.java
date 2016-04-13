package student;

import game.Node;

/**
  * The Pathfinder finds the shortest route between two nodes.
  *
  * It is constructed with a set of the nodes representing the graph, and the start and 
  * endpoints.
  */
public interface Pathfinder {

	/**
	 * Sets the starting node.
	 *
	 * @param start, the starting node.
	 */
	void setStart(Node start);
	
	/**
	 * Sets the ending node.
	 *
	 * @param end, the ending node.
	 */
	void setEnd(Node end);
	
	/**
	 * Returns the next neighbouring node to move to on the shortest path between the
	 * start and endpoints.
	 *
	 * @return the next node to move to.
	 * @throws NoSuchElementException if id is not found (I always expect to find it,
	 * something is wrong if not)
	 */
	Node getNext();
	
}
