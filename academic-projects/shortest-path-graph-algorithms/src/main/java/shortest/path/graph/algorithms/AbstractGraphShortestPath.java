package shortest.path.graph.algorithms;

import java.util.Stack;

import shortest.path.graph.algorithms.algorithm.ShortestPathAlgorithmType;
import shortest.path.graph.algorithms.model.Edge;
import shortest.path.graph.algorithms.model.Graph;

/**
 * abstract class holding the common properties which are requisite in all the algorithm.
 * 
 * Since, we are not using interfaces, hence constructing the abstract methods here that 
 * will serve as implemented methods.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public abstract class AbstractGraphShortestPath {

	private Graph innerGraph;	// inner graph on which algorithm will work on.
	private ShortestPathAlgorithmType algorithmType; // a conscious type of the algorithm.
	private boolean hasNegativeCycle; // a flag that hold the information whether the algorithm has detected a negative cycle or not.
	private boolean detectCycle;	  // a control flag which is first seen by the algorithm before it attempts to look for a cycle.
	private Stack<Edge> negativeCycleStack; // data structure to hold the edges participating in the negative cycle (if  applicable)
	
	public AbstractGraphShortestPath() {}
	
	public AbstractGraphShortestPath(Graph graph) {
		this.innerGraph = graph;
	}
	
	public Graph getInnerGraph() {
		return innerGraph;
	}
	
	public void setInnerGraph(Graph innerGraph) {
		this.innerGraph = innerGraph;
	}
	
	public ShortestPathAlgorithmType getAlgorithmType() {
		return algorithmType;
	}
	
	public void setAlgorithmType(ShortestPathAlgorithmType algorithmType) {
		this.algorithmType = algorithmType;
	}
	
	public void setHasNegativeCycle(boolean hasNegativeCycle) {
		this.hasNegativeCycle = hasNegativeCycle;
	}
	
	public boolean hasNegativeCycle() {
		return hasNegativeCycle;
	}
	
	public void setDetectCycle(boolean detectCycle) {
		this.detectCycle = detectCycle;
	}
	
	public boolean haveToDetectCycle() {
		return detectCycle;
	}
	
	public Stack<Edge> getNegativeCycleStack() {
		if (negativeCycleStack == null) 
			negativeCycleStack = new Stack<Edge>();
		
		return negativeCycleStack;
	}
	
	public void setNegativeCycleStack(Stack<Edge> negativeCycleStack) {
		this.negativeCycleStack = negativeCycleStack;
	}

	public abstract void arrangeShortestPath();
	
	public void optimizeGraph() {
		// meant to be overridden to optimize graph to evaluate zero-length cycle. (Level 2)
	}
	
	public void cleanUp() {
		this.innerGraph = null;
		this.hasNegativeCycle = false;
		this.detectCycle = false;
		this.negativeCycleStack = null;
	}
	
}
