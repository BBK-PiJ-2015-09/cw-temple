package student;

import java.util.ArrayList;

public class MyCavernImpl implements MyCavern {
	private ArrayList<MyNode> nodes = new ArrayList<>();
	
	MyCavernImpl() {
		
	}
	
	public void addNode(long id) {
		MyNode node = new MyNodeImpl(id);
		nodes.add(node);
	}
	
	public MyNode getNode(long id) {
		for(MyNode node : nodes) {
			if(node.getId() == id) {
				return node;
			}
		}
		return null;
	}
	
	public int size() {
		return nodes.size();
	}
	
}
