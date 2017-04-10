 package shortest.path.graph.algorithms.algorithm;

import shortest.path.graph.algorithms.AbstractGraphShortestPath;
import shortest.path.graph.algorithms.model.Graph;
import shortest.path.graph.algorithms.util.GraphTopologyAnalyzer;

/**
 * Factory pattern implemented to ease the selection of algorithm required to accomplish the purpose.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class ShortestPathAlgorithmSelectionFactory {
	
	/**
	 * an overloaded method which will make a judicious selection based on the graph topological conditions.
	 * 
	 * @param graph
	 * @param detectCycle
	 * @return
	 */
	public static AbstractGraphShortestPath selectAlgorithm (Graph graph, boolean detectCycle) {
		GraphTopologyAnalyzer analyzer = null;
		AbstractGraphShortestPath algorithm = null;
		
		if (graph.isUniformWeighted()) {
			// select BFS
			algorithm = ShortestPathAlgorithmType.BFS.getAlgorithm();
			
		} else if (graph.isNonNegativelyWeighted()) {
			// select Dijkstra's algorithm
			algorithm = ShortestPathAlgorithmType.DIJ.getAlgorithm();
			
		} else if ((analyzer = GraphTopologyAnalyzer.analyze(graph)).isAcyclic()) {
			// select DAG
			algorithm = ShortestPathAlgorithmType.DAG.getAlgorithm(analyzer);
			
		} else {
			// select Bellman-Ford algorithm
			algorithm = ShortestPathAlgorithmType.BFO.getAlgorithm();
			algorithm.setDetectCycle(detectCycle);
		}
		
		// setting the inner graph
		algorithm.setInnerGraph(graph);
		return algorithm;
	}
	
	/**
	 * a default method which will call the specific method with the default values. 
	 *  
	 * @param graph
	 * @return
	 */
	public static AbstractGraphShortestPath selectAlgorithm (Graph graph) {
		return selectAlgorithm(graph, false);
	}

}
