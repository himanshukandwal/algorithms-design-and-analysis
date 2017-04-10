package maximal.matching.graph.algorithms.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class to represent a vertex of a graph
 * 
 * @author rbk
 * 
 */
public class Vertex {
	
	public int name; 							// name of the vertex
	public boolean seen;		 				// flag to check if the vertex has already been visited
	public Vertex parent; 						// parent of the vertex
	public int distance; 						// distance to the vertex from the source vertex
	public List<Edge> Adj, revAdj; 				// adjacency list; use LinkedList or ArrayList.
	public Layer layer; 						// matching layer to which this vertex belong to.
	public Vertex mate;							// matched mate with this vertex.
	public boolean isFrozen;					// a variable added to avoid augmenting path from getting used again.
	public Matching matching;
	
	/**
	 * Constructor for the vertex
	 * 
	 * @param n : int - name of the vertex
	 */
	public Vertex(int n) {
		name = n;
		seen = false;
		parent = null;
		Adj = new ArrayList<Edge>();
		revAdj = new ArrayList<Edge>(); /* only for directed graphs */
		isFrozen = false;
	}

	/**
	 * Method to represent a vertex by its name
	 */
	public String toString() {
		return Integer.toString(name);
	}
	
	/**
	 * method to reverse sort the edges as per the weights.
	 */
	public void sortEdgesByWeight() {
		Collections.sort(Adj, new Comparator<Edge>() {
			
			@Override
			public int compare(Edge o1, Edge o2) {
				return (o2.Weight - o1.Weight);
			}
			
		});
	}
	
}