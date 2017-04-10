package shortest.path.graph.algorithms;

import java.util.ArrayDeque;
import java.util.Queue;

import shortest.path.graph.algorithms.model.Edge;
import shortest.path.graph.algorithms.model.Graph;
import shortest.path.graph.algorithms.model.Vertex;

/**
 * The purpose of this class is to use Bellman Ford (BFO) take 3 algorithm in such a way that we use it to determine whether 
 * a graph has negative cycles or to provide the correct distance values to the vertices.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class BellmanFordTake3Algorithm extends AbstractGraphShortestPath {

	public BellmanFordTake3Algorithm() {
		super();
	}

	public BellmanFordTake3Algorithm(Graph graph) {
		super(graph);
	}

	@Override
	public void arrangeShortestPath() {
		// sets parent, distance to null. count to 0, seen to false.
		getInnerGraph().resetGraph();

		Queue<Vertex> toBeProcessedNodes = new ArrayDeque<>();

		Vertex sourceVertex = getInnerGraph().source;
		sourceVertex.seen = true;
		sourceVertex.distance = 0;

		toBeProcessedNodes.add(sourceVertex);

		while (!toBeProcessedNodes.isEmpty()) {
			Vertex u = toBeProcessedNodes.poll();

			u.seen = false;
			u.count += 1;

			if (u.count >= getInnerGraph().numNodes) {
				setHasNegativeCycle(true);
				return;
			}

			for (Edge edge : u.Adj) {
				Vertex v = edge.otherEnd(u);

				if (v.distance == null || (v.distance > u.distance + edge.Weight)) {
					v.distance = u.distance + edge.Weight;
					v.parent = u;

					if (!v.seen) {
						toBeProcessedNodes.add(v);
						v.seen = true;
					}
				}
			}
		}

		setHasNegativeCycle(false);
	}
	
}