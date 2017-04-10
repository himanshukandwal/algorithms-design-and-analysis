package minimum.spanning.tree.graph.algorithms.model;


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
	public Integer rank; // vertex rank
	public int name; // name of the vertex
	public boolean seen; // flag to check if the vertex has already been visited
	public Vertex parent; // parent of the vertex
	public Integer distance; // distance to the vertex from the source vertex
	public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or
									// ArrayList
	
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
	}

	/**
	 * Method to represent a vertex by its name
	 */
	public String toString() {
		return Integer.toString(name);
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