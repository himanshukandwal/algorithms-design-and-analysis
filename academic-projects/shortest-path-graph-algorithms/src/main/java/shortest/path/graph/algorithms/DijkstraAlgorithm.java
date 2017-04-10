package shortest.path.graph.algorithms;

import java.util.Comparator;

import shortest.path.graph.algorithms.model.Edge;
import shortest.path.graph.algorithms.model.Graph;
import shortest.path.graph.algorithms.model.IndexedHeap;
import shortest.path.graph.algorithms.model.Vertex;

/**
 * A class implementing the Dijstra's Algorithm.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class DijkstraAlgorithm extends AbstractGraphShortestPath {

	/**
	 * A vertex comparator, that will be used for ordering the vertices within the Indexed heap (PQ). 
	 */
	private final Comparator<Vertex> vertexComparator = new Comparator<Vertex>() {
		@Override
		public int compare(Vertex vertex1, Vertex vertex2) {
			int result;

			if (vertex1 != null && vertex2 != null) {
				if (vertex1.distance != null && vertex2.distance != null) {
					result = (vertex1.distance > vertex2.distance) ? 1
							: ((vertex1.distance == vertex2.distance) ? 0 : -1);
				} else {
					if (vertex1.distance == null && vertex2.distance != null)
						result = 1;
					else if (vertex1.distance != null && vertex2.distance == null)
						result = -1;
					else
						result = 0;
				}
			} else {
				if (vertex1 == null && vertex2 != null)
					result = 1;
				else if (vertex1 != null && vertex2 == null)
					result = -1;
				else
					result = 0;
			}

			return result;
		}
	};
	
	public DijkstraAlgorithm() {
		super();
	}
	
	public DijkstraAlgorithm(Graph graph) {
		super(graph);
	}
	
	@Override
	public void arrangeShortestPath() {
		// sets parent, distance to null. seen to false.
		getInnerGraph().resetGraph();
		
		Vertex sourceVertex = getInnerGraph().source;
		sourceVertex.seen = true;
		sourceVertex.distance = 0;
		
		IndexedHeap<Vertex> priorityQueue = new IndexedHeap<Vertex>(getInnerGraph().numNodes, vertexComparator);

		for (Vertex vertex : getInnerGraph())
			if (vertex != null)
				priorityQueue.add(vertex);
		
		while (priorityQueue.size() > 1) {
			Vertex u = priorityQueue.remove();
			u.seen = true;
			
			if (u.distance != null) {
				// relax edges out of u
				for (Edge edge : u.Adj) {
					Vertex v = edge.otherEnd(u);
					
					if (!v.seen && (v.distance == null ? true : (v.distance > u.distance + edge.Weight))) {
						v.distance = u.distance + edge.Weight;
						v.parent = u;
						
						priorityQueue.decreaseKey(v);
					}
				}
			}
		}
	}
	
}
