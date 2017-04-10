package shortest.path.graph.algorithms.util;

import java.util.Stack;

import shortest.path.graph.algorithms.model.Edge;
import shortest.path.graph.algorithms.model.Graph;
import shortest.path.graph.algorithms.model.Vertex;

/**
 * A class crafted specifically for performing topology analysis of graph
 * 
 * This class is controlled by a flag 'determineCycle' which, if set true, determines the 
 * 'first' cycle which is detected while DFSing. 
 * 
 * Hence, this class provides two features :
 * 
 * a) 'determineCycle' = false
 * 
 * 	  provides topological order of the graph (if present) 
 * 		This information is provided two ways :
 * 			-	isAcyclic()  			(a boolean flag)
 * 			-	getTopologicalOrder()  	(a method to provide the topological order)
 * 
 *   
 * b) 'determineCycle' = true
 * 
 * 	  provides topological order of the graph (if present) (case a) 
 * 	  provides the information about the cycle detected :
 * 			-	getCycle()  			(a boolean flag)
 * 			-	getTopologicalOrder()  	(a method to provide the topological order)
 *  
 * The most important point here is that we are not breaking the graph in any way.
 * 
 * This class has many wrapper methods/constructors, which serves not much of purpose in business logic
 * however, are kept as we have binded several intrinsic test cases with them to simulate different test
 * conditions.  
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class GraphTopologyAnalyzer {

	private boolean acyclic;
	private Stack<Vertex> topologicalOrder;
	private boolean determineCycle;
	private Stack<Vertex> cycle;
	private boolean cycleHeadFound; 
	
	public Stack<Vertex> getTopologicalOrder() {
		if (topologicalOrder == null)
			topologicalOrder = new Stack<Vertex>();

		return topologicalOrder;
	}
	
	public Stack<Vertex> getCycle() {
		if (cycle == null) 
			cycle = new Stack<Vertex>();
		
		return cycle;
	}
	
	public void setTopologicalOrder(Stack<Vertex> topologicalOrder) {
		this.topologicalOrder = topologicalOrder;
	}
	
	public boolean isAcyclic() {
		return acyclic;
	}
	
	public void setAcyclic(boolean acyclic) {
		this.acyclic = acyclic;
	}

	public boolean isDetermineCycle() {
		return determineCycle;
	}
	
	public void setDetermineCycle(boolean determineCycle) {
		this.determineCycle = determineCycle;
	}
	
	private boolean getCycleHeadFound() {
		return cycleHeadFound;
	}
	
	private void setCycleHeadFound(boolean cycleHeadFound) {
		this.cycleHeadFound = cycleHeadFound;
	}
	
	/**
	 * wrapper method to find the topological order (if present) in a graph
	 * 
	 * @param graph
	 * @return
	 */
	public static GraphTopologyAnalyzer analyze(Graph graph) {
		return new GraphTopologyAnalyzer().orderGraph(graph, null);
	}
	
	/**
	 * wrapper method to find the topological order (if present) in a graph
	 * 
	 * @param graph
	 * @return
	 */
	public static GraphTopologyAnalyzer analyze(Graph graph, boolean determineCycle) {
		return new GraphTopologyAnalyzer().orderGraph(graph, null, determineCycle);
	}

	/**
	 * wrapper method to find the topological order (if present) in a graph
	 * 
	 * comment : mainly used while testing
	 * 
	 * @param graph
	 * @return
	 */
	public static GraphTopologyAnalyzer analyze(Graph graph, Integer optionalSource) {
		return new GraphTopologyAnalyzer().orderGraph(graph, optionalSource);
	}
	
	/**
	 * method to find the topological order (if present) in a graph
	 * 
	 * @param graph
	 * @return
	 */
	public GraphTopologyAnalyzer orderGraph(Graph graph, Integer optionalSource, boolean determineCycle) {
		setDetermineCycle(determineCycle);
		return orderGraph(graph, optionalSource);
	}
	
	/**
	 * method to find the topological order (if present) in a graph
	 * 
	 * @param graph
	 * @return
	 */
	public GraphTopologyAnalyzer orderGraph(Graph graph, Integer optionalSource) {
		Stack<Vertex> topologicalOrderStack = new Stack<>();
		
		if (optionalSource != null) {
			for (Vertex graphVertex : graph.verts) {
				if (graphVertex != null && graphVertex.name == optionalSource) {
					performDFSOrdering(topologicalOrderStack, graphVertex.name, null, graphVertex);
					break;
				}
			}
		}

		int participatingVertices = 0;
		
		for (Vertex graphVertex : graph) {
			if (!graphVertex.seen) {
				if (!determineCycle || (determineCycle && (graphVertex == graph.source || (graphVertex != graph.source && graphVertex.distance != null)))) {
					participatingVertices ++;
					performDFSOrdering(topologicalOrderStack, graphVertex.name, null, graphVertex);	 
				}
			} else {
				participatingVertices ++;
			}
		}

		if (topologicalOrderStack.size() == participatingVertices) {
			setTopologicalOrder(topologicalOrderStack);
			setAcyclic(true);
		} else {
			if (!determineCycle) {
				graph.resetGraph();
			}
		}
		
		return this;
	}

	private void performDFSOrdering(Stack<Vertex> topologicalOrderStack, int rank, Vertex parentVertex, Vertex vertex) {
		vertex.seen = true;
		vertex.rank = rank;
		
		for (Edge connectingEdge : vertex.Adj) {
			Vertex otherVertex = connectingEdge.otherEnd(vertex);

			if (!otherVertex.seen) {
				if (otherVertex.Adj.size() == 0) {
					otherVertex.parent = vertex;
					otherVertex.seen = true;

					topologicalOrderStack.add(otherVertex);
				} else {
					performDFSOrdering(topologicalOrderStack, rank, vertex, otherVertex);					
				}
			} else {
				if (otherVertex.parent == null) {
					// case : potential cycle.
					if (otherVertex.rank == vertex.rank) {
						vertex.topologicallyOrderable = false;
						otherVertex.topologicallyOrderable = false;
						
						// initiate cycle (only once)
						if (determineCycle && getCycle().size() == 0) {
							setCycleHeadFound(false);
							getCycle().add(otherVertex);
							getCycle().add(vertex);
						}
							
						break;
					} else {
						otherVertex.parent = vertex;
						otherVertex.rank = rank;
					}
				}
			}
			
			vertex.topologicallyOrderable = vertex.topologicallyOrderable && otherVertex.topologicallyOrderable;
			
			// record cycle till completion (only once)
			if (determineCycle && !vertex.isTopologicallyOrderable() && !otherVertex.isTopologicallyOrderable() && !getCycleHeadFound()) {
				getCycle().add(vertex);	
				
				if (getCycle().get(0) == vertex) 
					setCycleHeadFound(true);
			}	
		}
		
		if (vertex.topologicallyOrderable) {
			vertex.parent = parentVertex;
			topologicalOrderStack.push(vertex);
		}
	}
	
}
