package minimum.spanning.tree.graph.algorithms;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import minimum.spanning.tree.graph.algorithms.model.BinaryHeap;
import minimum.spanning.tree.graph.algorithms.model.Edge;
import minimum.spanning.tree.graph.algorithms.model.Graph;
import minimum.spanning.tree.graph.algorithms.model.Vertex;

/**
 * A prim1 algorithm implementation class. This class makes use of binary heap for priority queue.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class Prims1Algorithm {
	
	private Integer minimumCost;			// a placeholder for minimum cost.
	private List<Edge> orderedEdgesList;	// a placeholder for storing the edges.
	
	/**
	 * A edges comparator, that will be used for ordering the edges within the Binary heap (PQ).  
	 */
	private final Comparator<Edge> edgeComparator = new Comparator<Edge>() {
		@Override
		public int compare(Edge edge1, Edge edge2) {
			return (edge1.Weight < edge2.Weight) ? -1 : ((edge1.Weight == edge2.Weight) ? 0 : 1);
		}
	};
	
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
	
	/**
	 * Prims algorithm using Binary heap Priority Queue.
	 * 
	 */
	public void PrimMST(Graph graph) {
		clear();
		
		int wmst = 0;
		Vertex sourceVertex = graph.verts.get(1);
		
		BinaryHeap<Edge> priorityQueue = new BinaryHeap<Edge>(graph.numEdges, edgeComparator);

		sourceVertex.seen = true;
		for (Edge edge : sourceVertex.Adj)
			priorityQueue.add(edge);
		
		while (priorityQueue.size() > 1) {
			Edge edge = priorityQueue.deleteMin();

			if (edge == null || (edge.From.seen && edge.To.seen))
				continue;

			Vertex u = (edge.To.seen ? edge.To : edge.From);
			Vertex v = edge.otherEnd(u);

			getOrderedEdgesList().add(edge);
			v.parent = u;
			wmst += edge.Weight;
			v.seen = true;

			for (Edge nextEdge : v.Adj) {
				Vertex w = nextEdge.otherEnd(v);
				if (!w.seen)
					priorityQueue.add(nextEdge);
			}

		}
	
		setMinimumCost(wmst);
	}
	
	/**
	 * method for resetting the algorithm result information.
	 */
	private void clear() {
		minimumCost = null;
		getOrderedEdgesList().clear();
	}
}
