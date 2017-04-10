package maximal.matching.graph.algorithms.model;

/**
 * Class that represents an arc in a Graph
 *
 */
public class Edge {
	
	public Vertex From;	// head vertex
	public Vertex To;	// tail vertex
	public int Weight;	// weight of the arc
	public boolean seen;// edge visited or not. 
	public boolean matched; // a boolean flag for holding the information that this edge is matched or not.

	/**
	 * Constructor for Edge
	 * 
	 * @param u
	 *            : Vertex - The head of the arc
	 * @param v
	 *            : Vertex - The tail of the arc
	 * @param w
	 *            : int - The weight associated with the arc
	 */
	Edge(Vertex u, Vertex v, int w) {
		From = u;
		To = v;
		Weight = w;
		seen = false;
		matched = false;
	}

	/**
	 * Method to find the other end end of the arc given a vertex reference
	 * 
	 * @param u
	 *            : Vertex
	 * @return
	 */
	public Vertex otherEnd(Vertex u) {
		// if the vertex u is the head of the arc, then return the tail else
		// return the head
		if (From == u) {
			return To;
		} else {
			return From;
		}
	}

	/**
	 * Method to represent the edge in the form (x,y) where x is the head of the
	 * arc and y is the tail of the arc
	 */
	public String toString() {
		return "(" + From + "," + To + ")";
	}
	
	/**
	 * a method that responds with the information pertaining to matchable edge in case 
	 * of bipartite graph.
	 * 	
	 * methods return :
	 * 	null : unable to deduce any conclusion.
	 *  true : yes, is valid edge.
	 *  false: no, invalid edge. 
	 * 	
	 * @return
	 */
	public Boolean isBipartiteCompatible() {
		Boolean result = null;
		if (To.layer != null && From.layer != null) {
			if (To.layer != From.layer)
				result = true;
			else 
				result = false;
		}
		return result;
	}
}