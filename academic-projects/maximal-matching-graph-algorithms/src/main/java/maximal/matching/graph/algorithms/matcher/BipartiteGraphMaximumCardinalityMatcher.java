package maximal.matching.graph.algorithms.matcher;

import java.util.LinkedList;
import java.util.Queue;

import maximal.matching.graph.algorithms.model.DebugLevel;
import maximal.matching.graph.algorithms.model.Edge;
import maximal.matching.graph.algorithms.model.Graph;
import maximal.matching.graph.algorithms.model.Layer;
import maximal.matching.graph.algorithms.model.Vertex;

/**
 * A class to implement validation checks and maximum cardinality matching logic, for bipartite graph.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class BipartiteGraphMaximumCardinalityMatcher extends AbstractBipartiteMatcher {
	
	/* debug level */
	private DebugLevel debuglevel;											
	
	/* cardinality */
	private int length;
	
	/**
	 * private constructor.
	 * 
	 * @param graph
	 */
	private BipartiteGraphMaximumCardinalityMatcher(Graph graph, DebugLevel debugLevel) {
		super(graph);
		this.debuglevel = debugLevel;
	}
	
	/**
	 * static getter method for the singleton instance.  
	 *  
	 * @param graph
	 * @return
	 */
	public static BipartiteGraphMaximumCardinalityMatcher getInstance(Graph graph, DebugLevel debugLevel) {
		BipartiteGraphMaximumCardinalityMatcher instance = new BipartiteGraphMaximumCardinalityMatcher(graph, debugLevel);
		return instance.setGraph(graph);
	}
	
	/**
	 * static getter method for the singleton instance.  
	 *  
	 * @param graph
	 * @return
	 */
	public static BipartiteGraphMaximumCardinalityMatcher getInstance(Graph graph) {
		BipartiteGraphMaximumCardinalityMatcher instance = new BipartiteGraphMaximumCardinalityMatcher(graph, DebugLevel.MINIMAL);
		return instance.setGraph(graph);
	}
	
	/**
	 * setter method for inner graph.
	 * 
	 * @param graph
	 * @return
	 */
	public BipartiteGraphMaximumCardinalityMatcher setGraph(Graph graph) {
		super.graph = graph;
		return this;
	}
	
	/**
	 * getter method for the enum 'debugLevel'
	 * 
	 * @return
	 */
	public DebugLevel getDebuglevel() {
		return debuglevel;
	}
		
	/**
	 * function to perform maximum cardinality matching in a given bipartite graph.
	 * 
	 * @return
	 */
	public Integer performMaximumMatching() {
		
		//since all the basic bipartite conditions are met, then proceed with the matching.
		if (validateGraph()) {
			
			for (Vertex vertex : graph) {
				for (Edge edge : vertex.Adj) {
					
					if (!edge.seen) {
						edge.seen = true;
								
						Vertex otherVertex = edge.otherEnd(vertex);
	
						if (otherVertex.mate == null && vertex.mate == null) {
							otherVertex.mate = vertex;
							vertex.mate = otherVertex;
	
							length++;
						}
					}
				}
			}
			
			// call main method
			detectMaximalMatching();
			
			// print the maximum cardinality achievable.
			System.out.println(length);
			
			// print the edges, if the 'debuglevel' has been set to VERBOSE.
			if (getDebuglevel().isVerboseEnabled()) {
				for (Vertex vertex : getGraph()) {
					if (vertex.mate != null && vertex.seen) {
						System.out.println(vertex + " " + vertex.mate);
						vertex.seen = false;
						vertex.mate.seen = false;
					}
				}
			}
			
		} else {
			System.out.println("G is not bipartite.");
			System.exit(1);
		}
		
		return length;
	}
	
	/**
	 * method to perform all the core validations requisite for the maximal matching to happen.
	 * 
	 * @return
	 */
	public boolean validateGraph() {
		boolean result = bipartiteBfsChecking();

		if (result) {
			int innerLayerNodes = 0;
			int outerLayerNodes = 0;
			
			// till this point, graph has been properly set.
			for (Vertex vertex : graph) {
				vertex.seen = false;
				
				if (vertex.layer == null)
					result = false;																	// disconnected graph. 
				else {
					if (vertex.layer.isInner()) {
						innerLayerNodes ++;															// count inner-layer nodes.
					} else {
						outerLayerNodes ++;															// count outer-layer nodes.
					}
				}
			}
			
			if (innerLayerNodes + outerLayerNodes != graph.numNodes)
				result = false;
		}

		return result;
	}
	
	/**
	 * an internal BFS traversal to perform bipartite graph checking.
	 *  
	 * @return
	 */
	private boolean bipartiteBfsChecking() {
		Queue<Vertex> processingQueue = new LinkedList<>();
		boolean result = true;
		
		for (Vertex vertex : getGraph()) {
			
			if (!vertex.seen) {
				vertex.seen = true;
				vertex.layer = Layer.OUTER;
				processingQueue.add(vertex);
				
				result = bipartiteBfsCheckingInternal(processingQueue, vertex.name);
				
				if (!result)
					break;
			}
		}
		
		return result;
	}
	
	private boolean bipartiteBfsCheckingInternal (Queue<Vertex> processingQueue, int rank) {
		boolean result = true;
		
		while (!processingQueue.isEmpty()) {
			Vertex vertex = processingQueue.poll();
			
			for (Edge edge : vertex.Adj) {
				if (!edge.seen) {
					Vertex otherVertex = edge.otherEnd(vertex);
					
					// if the vertex is not seen then add it to the queue. (after providing proper layering)
					if (!otherVertex.seen) {
						otherVertex.seen = true;
						otherVertex.layer = vertex.layer.other();
						processingQueue.add(otherVertex);
					} else {
						// else check if the existing layer is in accordance or not.
						if (otherVertex.layer == vertex.layer) {
							if (getDebuglevel().isErrorEnabled())
								System.out.println(otherVertex + " and " + vertex + " are on same layer : " + otherVertex.layer);
							
							result = false;
						}
					}
					
					edge.seen = true;
				} else {
					// if the edge is seen, check the other side vertex (not necessary, however precautionary)
					if (!edge.isBipartiteCompatible()) {
						if (getDebuglevel().isErrorEnabled())
							System.out.println(edge + " is not bipartite compatible");
						
						result = false;					
					}
				}
				
				// if found failure, break out !
				if (!result) 
					break;
			}
		}
		
		// removing the holds (pointers), GC considerations !
		processingQueue.clear();
		return result;
	}

	/**
	 * main method to detect the maximal matching in the graph.
	 * 
	 */
	public void detectMaximalMatching () {
			
		while (true) {
			Queue<Vertex> processingOuterNodesQueue = new LinkedList<>();
			
			for (Vertex u : graph) {
				u.parent = null;
				u.seen = false;
				u.isFrozen = false;
				
				if (u.mate == null && u.layer.isOuter()) {
					u.seen = true;
					processingOuterNodesQueue.add(u);
				}
			}
			
			boolean toExpand = false;
			
			while (!processingOuterNodesQueue.isEmpty()) {
				Vertex u = processingOuterNodesQueue.poll();
				
				for (Edge edge : u.Adj) {
					Vertex v = edge.otherEnd(u);
					
					if (!v.seen) {
						v.parent = u;
						v.seen = true;
						
						// augmenting path has been found.
						if (v.mate == null) {
							u.seen = true;
							
							processAugmentingPath (v);
							toExpand = true;
						} 
						else {
							Vertex x = v.mate;
							x.seen = true;
							x.parent = v;
							
							processingOuterNodesQueue.add (x);
						}
					}
				}
			}
			
			if (!toExpand)
				break;
		}
	}
	
	/*
	 * Helper function to increase size of matching, using an augmenting path. 
	 */
	private void processAugmentingPath(Vertex u) {
		boolean pathFrozen = false;
		Vertex traversing = u;
		
		while (traversing != null) {
			if ((pathFrozen = traversing.isFrozen))
				break;
			
			traversing = traversing.parent;
		}
		
		if (pathFrozen)
			return;
			
		Vertex p = u.parent;
		Vertex x = p.parent;
		
		u.isFrozen = true;
		p.isFrozen = true;
		
		u.seen = true;
		
		u.mate = p;
		p.mate = u;
		
		while (x != null) {
			Vertex nmx = x.parent;
			Vertex y = nmx.parent;
			
			x.mate = nmx;
			nmx.mate = x;
			
			x.isFrozen = true;
			nmx.isFrozen = true;
			
			x = y;
		}
		
		length ++;
	}
		
}