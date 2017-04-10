package shortest.path.graph.algorithms.algorithm;

import shortest.path.graph.algorithms.AbstractGraphShortestPath;
import shortest.path.graph.algorithms.BFSGraphAlgorithm;
import shortest.path.graph.algorithms.BellmanFordTake1Algorithm;
import shortest.path.graph.algorithms.BellmanFordTake3Algorithm;
import shortest.path.graph.algorithms.CalculatePathsAlgorithm;
import shortest.path.graph.algorithms.DAGShortestPathAlgorithm;
import shortest.path.graph.algorithms.DijkstraAlgorithm;
import shortest.path.graph.algorithms.util.GraphTopologyAnalyzer;

/**
 * a simple enum listing at the enumeration
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public enum ShortestPathAlgorithmType {

	// Enums level : 1
	BFS ("Breadth First Search algorithm", "BFS", new BFSGraphAlgorithm()),
	DAG ("Directed Acyclic Graph algorithm", "DAG", new DAGShortestPathAlgorithm()),
	DIJ ("Dijkstra's algorithm", "Dij", new DijkstraAlgorithm()),
	BFO ("Bellman Ford Take 3 algorithm", "B-F", new BellmanFordTake3Algorithm()),
	BFO1 ("Bellman Ford Take 1 algorithm ", "B-F", new BellmanFordTake1Algorithm()),
	
	// Enums level : 2
	CPA ("Directed Acyclic Graph algorithm for calculating all the possible paths", "CPA", new CalculatePathsAlgorithm());
	
	private String name;
	private String acronym;
	private AbstractGraphShortestPath algorithm;
	private GraphTopologyAnalyzer analyzer;
	
	private ShortestPathAlgorithmType(String name, String acronym, AbstractGraphShortestPath algorithm) {
		this.name = name;
		this.acronym = acronym;
		this.algorithm = algorithm;
		this.algorithm.setAlgorithmType(this);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAcronym() {
		return acronym;
	}
	
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	
	public AbstractGraphShortestPath getAlgorithm(GraphTopologyAnalyzer analyzer) {
		getAlgorithm();
		this.analyzer = analyzer;
		return algorithm;
	}
	
	public AbstractGraphShortestPath getAlgorithm(GraphTopologyAnalyzer analyzer, boolean detectCycle) {
		getAlgorithm(analyzer);
		this.algorithm.setDetectCycle(detectCycle);
		return algorithm;
	}
	
	public AbstractGraphShortestPath getAlgorithm(boolean detectCycle) {
		this.algorithm.setDetectCycle(detectCycle);
		return algorithm;
	}
	
	public AbstractGraphShortestPath getAlgorithm() {
		this.algorithm.cleanUp();
		return algorithm;
	}
	
	public void setAlgorithm(AbstractGraphShortestPath algorithm) {
		this.algorithm = algorithm;
	}
	
	public GraphTopologyAnalyzer getAnalyzer() {
		return analyzer;
	}
	
	public void setAnalyzer(GraphTopologyAnalyzer analyzer) {
		this.analyzer = analyzer;
	}
	
	@Override
	public String toString() {
		return getAcronym();
	}
	
}
