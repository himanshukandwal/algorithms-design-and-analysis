package minimum.spanning.tree.graph.algorithms;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import minimum.spanning.tree.graph.algorithms.model.BinaryHeap;
import minimum.spanning.tree.graph.algorithms.model.Edge;
import minimum.spanning.tree.graph.algorithms.model.Graph;
import minimum.spanning.tree.graph.algorithms.model.Vertex;

/**
 * The class implementing the Kruskal's Algorithm. 
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class KruskalAlgorithm {

	private Integer minimumCost;
	private List<Edge> orderedEdgesList;

	private final Comparator<Edge> edgeComparator = new Comparator<Edge>() {
		@Override
		public int compare(Edge edge1, Edge edge2) {
			return (edge1.Weight < edge2.Weight) ? -1 : ((edge1.Weight == edge2.Weight) ? 0 : 1);
		}
	};
	
	// Kruskal's algorithm implementation.
	public void kruskalMST(Graph graph) {
		// clear previous run data
		clear();
		
		int wmst = 0;
		Edge [] edges = new Edge [graph.numEdges + 1];
		edges [0] = null; 
		
		int index = 1;
		
		// make set 
		for (Vertex vertex : graph) {
			makeSet(vertex);
			
			// populate Edges list
			for (Edge edge : vertex.Adj) {
				if (!edge.seen) {
					edge.seen = true;
					edges [index ++] = edge;
				}
			}
		}
		
		// sort edges by non-decreasing order of weight.
		BinaryHeap.heapSort(edges, edgeComparator);
		
		for (Edge edge : edges) {
			if (edge != null) {
				Vertex u = edge.To;
				Vertex v = edge.From;

				Vertex ru = find(u); // representative of u's forest
				Vertex rv = find(v); // representative of v's forest

				if (ru != rv) {
					// u and v are in different components
					getOrderedEdgesList().add(edge); // add edge in the forest set.

					wmst += edge.Weight;
					union(ru, rv);
				}
			}
		}
		
		setMinimumCost(wmst);
	}
	
	public void makeSet(Vertex vertex) {
		vertex.parent = vertex;
		vertex.rank = 0;
	}
	
	public Vertex find(Vertex vertex) {
		if (vertex != vertex.parent)
			vertex.parent = find(vertex.parent);
		return vertex.parent;
	}
	
	public void union(Vertex x, Vertex y) {
		if (x.rank > y.rank)
			y.parent = x;
		else if (y.rank > x.rank)
			x.parent = y;
		else {
			x.parent = y;
			y.rank ++;
		}	
	}

	private void clear() {
		minimumCost = null;
		getOrderedEdgesList().clear();
	}
	
	public Integer getMinimumCost() {
		return minimumCost;
	}

	public void setMinimumCost(Integer minimumCost) {
		this.minimumCost = minimumCost;
	}

	public List<Edge> getOrderedEdgesList() {
		if (orderedEdgesList == null)
			orderedEdgesList = new LinkedList<>();

		return orderedEdgesList;
	}
	
}
