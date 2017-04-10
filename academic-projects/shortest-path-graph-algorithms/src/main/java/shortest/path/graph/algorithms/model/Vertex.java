package shortest.path.graph.algorithms.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a vertex of a graph
 * 
 * @author rbk
 * @modified by G31 (Himanshu Kandwal and Dharmam Buch)
 */
public class Vertex implements Index {
	
	public int index;
	public Integer rank; // rank of the vertex (to be used while topological ordering and for Kruskal's algorithm)
	public int name; // name of the vertex
	public boolean seen; // flag to check if the vertex has already been visited
	public Vertex parent; // parent of the vertex
	public Integer distance; // distance to the vertex from the source vertex
	public boolean topologicallyOrderable; // flag to mark whether a vertex is topologically orderable or not.
	public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or ArrayList
	public int count; 	// needed for take-3 Bellman ford algorithm
	public Integer shortestPathCount;
	public Integer [] shortestPathDistanceArr; // needed for take-1 and take-2 Bellman ford algorithm
	public boolean bellmanVariant; // needed to track whether the vertex was changing while last iteration ran. (for negative cycle detection only) 
	
	/**
	 * Constructor for the vertex
	 * 
	 * @param n
	 *            : int - name of the vertex
	 */
	Vertex(int n) {
		name = n;
		seen = false;
		parent = null;
		Adj = new ArrayList<Edge>();
		revAdj = new ArrayList<Edge>(); /* only for directed graphs */
		topologicallyOrderable = true;
	}
	
	public void setShortestPathDistanceArr(int numNodes) {
		this.shortestPathDistanceArr = new Integer [numNodes];
	}
	
	public Integer[] getShortestPathDistanceArr() {
		return shortestPathDistanceArr;
	}
	
	/**
	 * Method to represent a vertex by its name
	 */
	public String toString() {
		return Integer.toString(name);
	}
	
	public boolean isTopologicallyOrderable() {
		return topologicallyOrderable;
	}
	
	public void setTopologicallyOrderable(boolean topologicallyOrderable) {
		this.topologicallyOrderable = topologicallyOrderable;
	}

	@Override
	public void putIndex(int index) {
		this.index = index;
	}

	@Override
	public int getIndex() {
		return index;
	}

}