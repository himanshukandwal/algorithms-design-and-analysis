package shortest.path.graph.algorithms;

import java.util.Stack;

import shortest.path.graph.algorithms.model.Edge;
import shortest.path.graph.algorithms.model.Graph;
import shortest.path.graph.algorithms.model.Vertex;

/**
 * A Directed Acyclic Graph algorithm, providing the shortest path.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class DAGShortestPathAlgorithm extends AbstractGraphShortestPath {

	private Stack<Vertex> topologicalOrder;
	
	public DAGShortestPathAlgorithm() {
		super();
	}
	
	public DAGShortestPathAlgorithm(Graph graph) {
		super(graph);
	}
	
	public Stack<Vertex> getTopologicalOrder() {
		return topologicalOrder;
	}
	
	public void setTopologicalOrder(Stack<Vertex> topologicalOrder) {
		this.topologicalOrder = topologicalOrder;
	}
	
	@Override
	public void arrangeShortestPath() {
		setTopologicalOrder(getAlgorithmType().getAnalyzer().getTopologicalOrder());
		
		/* since the GraphCyclicityAnalyzer will leave the graph in topologically ordered state (i.e. not resetting graph back to
		 * its original state, hence, we have all the vertices with its parent node well set to work ahead in DAG order. 
		 */
		boolean processNow = false;
		
		for (int index = getTopologicalOrder().size() - 1; index >= 0; index --) {
			Vertex vertex = getTopologicalOrder().get(index);
			
			if (processNow) {
				if (vertex.parent != null) {
					Vertex parent = vertex.parent;
					
					if (parent.distance != null) {
						int effectiveDistance = parent.distance;

						for (Edge parentOutgoingEdge : parent.Adj) {
							if (parentOutgoingEdge.otherEnd(parent) == vertex) {
								effectiveDistance += parentOutgoingEdge.Weight;
								break;
							}
						}

						for (Edge incomingEdge : vertex.revAdj) {
							Vertex otherParent = incomingEdge.otherEnd(parent);

							if (otherParent.distance != null && (otherParent.distance + incomingEdge.Weight) < effectiveDistance) {
								vertex.parent = otherParent;	
								effectiveDistance = otherParent.distance + incomingEdge.Weight;
							}
						}
						vertex.distance = effectiveDistance;
					}
				}
			} else {
				vertex.parent = null;
			}
			
			if (vertex == getInnerGraph().source) 
				processNow = true;
		}
	}
	
}
