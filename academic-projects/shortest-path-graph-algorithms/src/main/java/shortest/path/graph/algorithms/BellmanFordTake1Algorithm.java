package shortest.path.graph.algorithms;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import shortest.path.graph.algorithms.model.Edge;
import shortest.path.graph.algorithms.model.Graph;
import shortest.path.graph.algorithms.model.Vertex;

/**
 * The purpose of this class is to use Bellman Ford (BFO) take 1 algorithm in such a way that we use it to determine the 
 * culprit nodes, which lead the negative cycles creation.
 * 
 * As we know that in BFO, the criteria is that from iterations 1 to |V| (number of vertices) we can determine the shortest 
 * path that exists and with Take 1 we assess this fact by 'no change' flag. If within two iterations, if there has been no 
 * changes (in Dk array) then we conclude that shortest path for every vertex has been set. However, if there we still 
 * encounter change then that means there is a negative cycle. 
 * 
 * We have made one change that we are iterating 1 to (|V| + 1) times to ensure that all the parent pointers are set correctly
 * and second that all the vertices with negative weights are the last ones, which will be changing. 
 * 
 * We are using a flag 'bellmanVariant', which will be set to true for those set of vertices, which were changing till the 
 * last iteration. So, this signifies that there is potential cycle head from those set of vertices and we work our way out 
 * from there onwards.
 * 
 * We are moving backwards i.e. parent to parent until we reach to the starting point. We are noting the edges while doing this
 * and computing the overall cycle weight.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class BellmanFordTake1Algorithm extends AbstractGraphShortestPath {

	public BellmanFordTake1Algorithm() {
		super();
	}

	public BellmanFordTake1Algorithm(Graph graph) {
		super(graph);
	}

	@Override
	public void arrangeShortestPath() {
		// sets parent, distance to null. seen to false.
		getInnerGraph().resetGraph();
		
		int iterations = getInnerGraph().numNodes + 1;
		
		for (Vertex vertex : getInnerGraph())
			if (vertex != null)
				vertex.setShortestPathDistanceArr(iterations);
		
		Vertex sourceVertex = getInnerGraph().source;
		sourceVertex.seen = true;
		sourceVertex.getShortestPathDistanceArr()[0] = 0;
		
		for (int vertexIndex = 1; vertexIndex < iterations; vertexIndex++) {
			boolean nochange = true;	
				
			for (Vertex vertex : getInnerGraph()) {
				if (vertex != null) {
					vertex.getShortestPathDistanceArr()[vertexIndex] = vertex.getShortestPathDistanceArr()[vertexIndex -1];
					
					for (Edge edge : vertex.revAdj) {
						Vertex parentVertex = edge.otherEnd(vertex);
						
						if ((parentVertex.getShortestPathDistanceArr() [vertexIndex -1] != null) 
								&& (vertex.getShortestPathDistanceArr()[vertexIndex] == null 
								|| vertex.getShortestPathDistanceArr()[vertexIndex] > parentVertex.getShortestPathDistanceArr() [vertexIndex -1] + edge.Weight)) {
							
							vertex.getShortestPathDistanceArr()[vertexIndex] = parentVertex.getShortestPathDistanceArr() [vertexIndex -1] + edge.Weight;
							vertex.parent = parentVertex;
							nochange = false;
						}
					}
					
					vertex.bellmanVariant = (distanceComparator.compare(vertex.getShortestPathDistanceArr()[vertexIndex], vertex.getShortestPathDistanceArr()[vertexIndex -1]) != 0);
				}
			}
			
			if (nochange) {
				for (Vertex vertex : getInnerGraph())
					if (vertex != null)
						vertex.distance = vertex.getShortestPathDistanceArr() [vertexIndex];
				
				setHasNegativeCycle(false);
				return;
			}
		}
		
		setHasNegativeCycle(true);
		determineCycle();
	}

	private void determineCycle() {
		for (Vertex vertex : getInnerGraph()) {
			if (vertex.bellmanVariant) {
				Stack<Edge> negativeCycleStack = findCycle(vertex);

				if (negativeCycleStack != null) {
					int cycleWeight = 0;
					for (Edge negativeCycleEdge : negativeCycleStack)
						cycleWeight += negativeCycleEdge.Weight;
					
					if (cycleWeight < 0)
							setNegativeCycleStack(negativeCycleStack);
				}
			}
		}
	}
	
	private Stack<Edge> findCycle(Vertex sourceVertex) {
		Stack<Edge> cycleStack = new Stack<Edge>();
		
		// to avoid traversing stupid other cycles
		Set<Edge> processedVertices = new HashSet<Edge>();
		
		Vertex traversingVertex = sourceVertex;
		
		
		boolean allGood = true;
		while (traversingVertex.parent != null && traversingVertex.parent != sourceVertex) {
			
			for (Edge edge : traversingVertex.revAdj) {
				if (edge.otherEnd(traversingVertex) == traversingVertex.parent) {
					if (processedVertices.add(edge))
						cycleStack.add(edge);
					else
						allGood = false;
					break;
				}
			}
			
			if (!allGood) 
				break;
			
			traversingVertex = traversingVertex.parent; 
		}
		
		if (traversingVertex.parent == null || traversingVertex.parent != sourceVertex)
			cycleStack.clear();
		else
			for (Edge edge : traversingVertex.revAdj)
				if (edge.otherEnd(traversingVertex) == traversingVertex.parent) {
					cycleStack.add(edge);
					break;
				}
		
		return (cycleStack.size() > 0 ? cycleStack : null);
	}
	
	/**
	 * a simple comparator, comparing the integer values. Null values are considered as the heaviest and are put at last in the order. (Null Last implementation)
	 */
	private static Comparator<Integer> distanceComparator = new Comparator<Integer>() {
		
		@Override
		public int compare(Integer integer1, Integer integer2) {
			int result;
			if (integer1 != null && integer2 != null) {
				result = (integer1 > integer2) ? 1 : ((integer1 == integer2) ? 0 : -1);
			} else {
				if (integer1 == null && integer2 != null)
					result = 1;
				else if (integer1 != null && integer2 == null)
					result = -1;
				else
					result = 0;
			}
			return result;
		}
		
	};
	
}
