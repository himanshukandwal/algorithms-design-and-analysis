package shortest.path.graph.algorithms.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


/**
 * Class to represent a graph
 * 
 * @author rbk
 * @modified by G31 (Himanshu Kandwal and Dharmam Buch)
 * 
 */
public class Graph implements Iterable<Vertex> {

	public List<Vertex> verts; // array of vertices
	public int numNodes; // number of verices in the graph
	public boolean uniformWeighted;
	public boolean nonNegativelyWeighted;
	public Vertex source;

	/**
	 * Constructor for Graph
	 * 
	 * @param size
	 *            : int - number of vertices
	 */
	public Graph(int size) {
		numNodes = size;
		verts = new ArrayList<>(size + 1);
		verts.add(0, null);
		
		// create an array of Vertex objects
		for (int i = 1; i <= size; i++)
			verts.add(i, new Vertex(i));
		
		if (size > 0) {
			source = verts.get(1);
			source.distance = 0;
		}
	}

	/**
	 * Method to add an edge to the graph
	 * 
	 * @param a
	 *            : int - one end of edge
	 * @param b
	 *            : int - other end of edge
	 * @param weight
	 *            : int - the weight of the edge
	 */
	public void addEdge(int a, int b, int weight) {
		Vertex u = verts.get(a);
		Vertex v = verts.get(b);
		Edge e = new Edge(u, v, weight);
		u.Adj.add(e);
		v.Adj.add(e);
	}

	/**
	 * Method to add an arc (directed edge) to the graph
	 * 
	 * @param a
	 *            : int - the head of the arc
	 * @param b
	 *            : int - the tail of the arc
	 * @param weight
	 *            : int - the weight of the arc
	 */
	public void addDirectedEdge(int a, int b, int weight) {
		Vertex head = verts.get(a);
		Vertex tail = verts.get(b);
		Edge e = new Edge(head, tail, weight);
		head.Adj.add(e);
		tail.revAdj.add(e);
	}

	/**
	 * Method to create an instance of VertexIterator
	 */
	public Iterator<Vertex> iterator() {
		return new VertexIterator();
	}

	/**
	 * A Custom Iterator Class for iterating through the vertices in a graph
	 *
	 * @param <Vertex>
	 */
	private class VertexIterator implements Iterator<Vertex> {
		private Iterator<Vertex> it;

		/**
		 * Constructor for VertexIterator
		 * 
		 * @param v
		 *            : Array of vertices
		 * @param n
		 *            : int - Size of the graph
		 */
		private VertexIterator() {
			it = verts.iterator();
			it.next(); // Index 0 is not used. Skip it.
		}

		/**
		 * Method to check if there is any vertex left in the iteration
		 * Overrides the default hasNext() method of Iterator Class
		 */
		public boolean hasNext() {
			return it.hasNext();
		}

		/**
		 * Method to return the next Vertex object in the iteration Overrides
		 * the default next() method of Iterator Class
		 */
		public Vertex next() {
			return it.next();
		}

		/**
		 * Throws an error if a vertex is attempted to be removed
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public boolean isUniformWeighted() {
		return uniformWeighted;
	}

	public void setUniformWeighted(boolean uniformWeighted) {
		this.uniformWeighted = uniformWeighted;
	}

	public boolean isNonNegativelyWeighted() {
		return nonNegativelyWeighted;
	}

	public void setNonNegativelyWeighted(boolean nonNegativelyWeighted) {
		this.nonNegativelyWeighted = nonNegativelyWeighted;
	}
	
	public void resetGraph() {
		for (Vertex graphVertex : this) {
			graphVertex.parent = null;
			graphVertex.seen = false;
			graphVertex.distance = null;
			graphVertex.count = 0;
			graphVertex.rank = null;
			graphVertex.topologicallyOrderable = true;
		}
	}

	public static Graph readGraph(Scanner in, boolean directed) {
		// read the graph related parameters
		int n = in.nextInt(); // number of vertices in the graph
		int m = in.nextInt(); // number of edges in the graph

		Integer initialWeight = null;
		boolean uniformWeighted = true;
		boolean nonNegativelyWeighted = true;

		// create a graph instance
		Graph g = new Graph(n);
		for (int i = 0; i < m; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			int w = in.nextInt();

			nonNegativelyWeighted = nonNegativelyWeighted && (w >= 0);

			if (initialWeight == null) {
				uniformWeighted = uniformWeighted && (w > 0);

				if (w > 0)
					initialWeight = w;
			} else
				uniformWeighted = uniformWeighted && (initialWeight == w);

			if (directed) {
				g.addDirectedEdge(u, v, w);
			} else {
				g.addEdge(u, v, w);
			}
		}
		g.setUniformWeighted(uniformWeighted);
		g.setNonNegativelyWeighted(nonNegativelyWeighted);

		in.close();
		return g;
	}
}
