package student;

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
	 * Returns a node by its ID.
	 *
	 * @param id, the id of the node to return.
	 * @return a boolean, true if visited, false otherwise.
	 * @throws IllegalArgumentException if MyNode is not found (I always expect to find it,
	 * something is wrong if not)
	 */
	MyNode getNode(long id);

}
