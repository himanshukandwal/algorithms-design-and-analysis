package shortest.path.graph.algorithms;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

import shortest.path.graph.algorithms.model.Edge;
import shortest.path.graph.algorithms.model.Graph;
import shortest.path.graph.algorithms.model.Vertex;

/**
 * Main algorithm calculating the possible shortest paths for each vertex in a graph.
 * This algorithm will work with the assumption that we have supplied a DAG.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class CalculatePathsAlgorithm extends AbstractGraphShortestPath {

	public CalculatePathsAlgorithm() {
		super();
	}
	
	public CalculatePathsAlgorithm(Graph graph) {
		super(graph);
	}
	
	@Override
	public void arrangeShortestPath() {
		
		getInnerGraph().source.shortestPathCount = 1;
		Queue<Vertex> waitingQueue = new ArrayDeque<Vertex>();
		waitingQueue.add(getInnerGraph().source);
		
		while (!waitingQueue.isEmpty()) {
			Vertex vertex = waitingQueue.poll();
			
			for (Edge edge : vertex.Adj) {
				Vertex childVertex = edge.otherEnd(vertex);
				
				if (vertex.distance != null) {
					
					if (vertex.distance + edge.Weight == childVertex.distance) {
						childVertex.shortestPathCount = vertex.shortestPathCount;
						
						for (Edge incomingChildEdge : childVertex.revAdj) {
							Vertex otherChildParent = incomingChildEdge.otherEnd(childVertex);
							
							if (otherChildParent != vertex && otherChildParent.distance != null 
									&& (otherChildParent.distance + incomingChildEdge.Weight == childVertex.distance)) {
								childVertex.shortestPathCount += (otherChildParent.shortestPathCount == null ? 0 : otherChildParent.shortestPathCount);
							}
						}
						
						waitingQueue.add(childVertex);
					}
				}
			}
		}
	}
	
	/**
	 * method to create a regular graph to a DAG (if possible). It filters (removes) out the branches, which are not part of the 
	 * shortest path possible, hence not needed at all. 
	 * 
	 * This will leave us a sub-graph, which will contain branches from where shortest path is feasible to a vertex.
	 */
	@Override
	public void optimizeGraph() {
		
		for (Vertex vertex : getInnerGraph()) {
			// partial re-setting of the graph
			vertex.topologicallyOrderable = true;
			vertex.rank = null;
			vertex.seen = false;
			vertex.parent = null;
			
			if (vertex.distance != null) {
				for (Iterator<Edge> edgeIterator = vertex.Adj.iterator(); edgeIterator.hasNext();) {
					Edge edge = edgeIterator.next();
					
					Vertex childVertex = edge.otherEnd(vertex);
					if (childVertex.distance == null || (childVertex.distance != null && (childVertex.distance != edge.Weight + vertex.distance )))
						edgeIterator.remove();
				}
			}
		}
	}
	
}
