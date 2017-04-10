package maximal.matching.graph.algorithms.model;

import java.util.ArrayList;
import java.util.List;

/**
 * a class depicting the behavior of matching.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 */
public class Matching {
	
	private int id;								// matching clas id.
	private Vertex startingNode;				// starting point of the matching.
	private Vertex tailNode;					// last attached point of the matching.
	private List<Edge> matchedEdges; 			// list of matched edges.	
	private int weight;							// current size of the matching.
	private boolean isAugmenting; 				// flag to hold information whether this matching is augmenting or not.
	private boolean active;       				// flag to hold information whether Matching is currently actively building or not.
	private int cardinality;					// variable to store the current cardinality of the matching.
	
	/**
	 * constructor.
	 * 
	 * @param startingNode
	 */
	public Matching (Vertex startingNode) {
		this.id = startingNode.name;
		this.active = (startingNode.matching == null);
		startingNode.matching = this;
		this.matchedEdges = new ArrayList<>();
		this.startingNode = startingNode;
		this.tailNode = startingNode;
	}

	/**
	 * 'id' getter method
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 'startingNode' getter method
	 * 
	 * @return
	 */
	public Vertex getStartingNode() {
		return startingNode;
	}

	/**
	 * 'tailNode' getter method
	 * 
	 * @return
	 */
	public Vertex getTailNode() {
		return tailNode;
	}

	/**
	 * 'length' getter method
	 * 
	 * @return
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * 'active' setter method
	 * 
	 * @return
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * 'active' getter method
	 * 
	 * @return
	 */
	public void setActive(boolean isActive) {
		this.active = isActive;
	}
	
	/**
	 * 'cardinality' getter method
	 * 
	 * @return
	 */
	public int getCardinality() {
		return cardinality;
	}
	
	/**
	 * 'matchedEdges' getter method
	 * 
	 * @return
	 */
	public List<Edge> getMatchedEdges() {
		return matchedEdges;
	}
	
	/**
	 * method to add a vertex to the matching. 
	 * 
	 * @param vertex
	 * @return
	 */
	public boolean addMate(Vertex vertex) {
		
		// precautionary check : the incoming node should have a valid layer value.
		if (vertex.layer == null || (vertex.layer != null && vertex.layer == tailNode.layer))
			return false;
		
		System.out.println(" adding vertex : " + vertex + " tail node : " + getTailNode());
		
		Edge connectingEdge = null;
		for (Edge edge : tailNode.Adj) {
			if (edge.otherEnd(tailNode) == vertex) {
				connectingEdge = edge; 
//				weight += edge.Weight;			// increment weight of the matching.
				break;
			}
		}
		
		// make edge 'matching' if the connection is getting made from inner node to outer node.
		if (tailNode.layer.isOuter()) {	
			cardinality ++;
			isAugmenting = false;					// case : addition of an inner node, matching not augmenting at the moment.
		} else {
			connectingEdge.matched = true;
			isAugmenting = true;
		}	
		
		getMatchedEdges().add(connectingEdge);
		tailNode = vertex;							// update the tail node.
		
		return true;
	}
	

	@Override
	public String toString() {
		return "[" + id + " : " + cardinality + "]";
	}
	
	/**
	 * method to print the matching sequence.
	 */
	public void print() {
		for (Edge edge : getMatchedEdges()) {
			// print only the visible edges
			if (!edge.matched) 
				System.out.println(edge);
		}
	}
	
	/**
	 * function to make the current matching augmenting by reversing the layering information.
	 */
	public void build() {
		Vertex vertex = tailNode;
		while (tailNode != null) {
			vertex.layer = vertex.layer.other();
			vertex = vertex.parent;
		}
	}
	
	public void buildRecursively (Vertex vertex) {
		if (vertex.Adj.size() == 0) {
			active = false;
		}
	}
	
}
