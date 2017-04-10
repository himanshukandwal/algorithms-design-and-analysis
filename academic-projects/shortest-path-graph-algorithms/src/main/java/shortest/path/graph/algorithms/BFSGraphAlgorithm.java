package shortest.path.graph.algorithms;

import java.util.HashSet;
import java.util.Set;

import shortest.path.graph.algorithms.model.Edge;
import shortest.path.graph.algorithms.model.Graph;
import shortest.path.graph.algorithms.model.Vertex;

/**
 * a simple breadth first search algorithm, providing the shortest path.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class BFSGraphAlgorithm extends AbstractGraphShortestPath {

	public BFSGraphAlgorithm() {
		super();
	}
	
	public BFSGraphAlgorithm(Graph graph) {
		super(graph);
	}

	@Override
	public void arrangeShortestPath() {
		Set<Vertex> processedVertexQueue = new HashSet<Vertex>();
		processedVertexQueue.add(getInnerGraph().source);
		recurseShortestPath(processedVertexQueue);
	}
	
	private void recurseShortestPath(Set<Vertex> vertexQueue) {
		Set<Vertex> processedVertexQueue = new HashSet<Vertex>();
		
		for (Vertex vertex : vertexQueue) {
			for (Edge outgoingEdge : vertex.Adj) {
				Vertex childVertex = outgoingEdge.otherEnd(vertex);
				int effectiveDistance = vertex.distance + outgoingEdge.Weight;
				
				if (childVertex.distance == null) {
					childVertex.distance = effectiveDistance;
					childVertex.parent = vertex;
					
					processedVertexQueue.add(childVertex);
				} else if (childVertex.distance > effectiveDistance) {
					childVertex.distance = effectiveDistance;
					childVertex.parent = vertex;
					
					processedVertexQueue.add(childVertex);
				}
			}
		}
		
		if (processedVertexQueue.size() > 0) 
			recurseShortestPath(processedVertexQueue);
	}
	
}
