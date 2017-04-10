package eular.hierholzer.graph.algorithm.model;

/**
 * Class to represent a vertex of a graph
 * 
 * @author rbk
 * 
 * @Modified by : G31
 */
import java.util.ArrayList;
import java.util.List;

public class Vertex {
	public int name; // name of the vertex
	public boolean seen; // flag to check if the vertex has already been visited
	public Vertex parent; // parent of the vertex
	public int distance; // distance to the vertex from the source vertex
	public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or
									// ArrayList
	public List<Edge> processedEdges;

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
		processedEdges = new ArrayList<Edge>();
	}
	
	/**
	 * Method to represent a vertex by its name
	 */
	public String toString() {
		return Integer.toString(name);
	}
}