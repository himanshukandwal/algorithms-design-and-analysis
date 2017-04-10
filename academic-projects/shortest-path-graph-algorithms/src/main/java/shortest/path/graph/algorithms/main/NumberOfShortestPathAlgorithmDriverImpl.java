package shortest.path.graph.algorithms.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

import shortest.path.graph.algorithms.AbstractGraphShortestPath;
import shortest.path.graph.algorithms.algorithm.ShortestPathAlgorithmSelectionFactory;
import shortest.path.graph.algorithms.algorithm.ShortestPathAlgorithmType;
import shortest.path.graph.algorithms.model.Edge;
import shortest.path.graph.algorithms.model.Graph;
import shortest.path.graph.algorithms.model.Vertex;
import shortest.path.graph.algorithms.util.GraphTopologyAnalyzer;

/**
 * Level 2 : Solution
 * 
 * Driver class for executing the algorithm for computing the number of shortest path per node. 
 * 
 * @author G31 
 *
 */
public class NumberOfShortestPathAlgorithmDriverImpl {
	
	public static void main(String[] args) {
		try {
			Scanner in;
			if (args.length > 0) {
				File inputFile = new File(args[0]);
				in = new Scanner(inputFile);
			} else {
				in = new Scanner(System.in);
			}

			Graph graph = Graph.readGraph(in, true);
			
			// select the optimal algorithm depending upon the graph topology. (Factory pattern implemented) 
			AbstractGraphShortestPath algorithm = ShortestPathAlgorithmSelectionFactory.selectAlgorithm(graph, true);
			algorithm.arrangeShortestPath();
			
			if (algorithm.hasNegativeCycle()) {
				// handle negative cycle scenario
				System.out.println("Unable to solve problem. Graph has a negative cycle.");
				
				// call Bellman ford Take 1 algorithm (custom changed to detect cycle)
				algorithm = ShortestPathAlgorithmType.BFO1.getAlgorithm();
				algorithm.setInnerGraph(graph);
				algorithm.arrangeShortestPath();
				printStackOrder(algorithm.getNegativeCycleStack());
				
				// leave the program.
				System.exit(1);
			}
			
			// build algorithm parameters, which performs the task of for detecting all the possible shortest paths for a vertex.
			algorithm = ShortestPathAlgorithmType.CPA.getAlgorithm(algorithm.getAlgorithmType().getAnalyzer());
			algorithm.setInnerGraph(graph);
			algorithm.optimizeGraph();
			
			// check for zero length cycle
			GraphTopologyAnalyzer analyzer = GraphTopologyAnalyzer.analyze(graph, true);
			
			if (analyzer.isAcyclic()) {
				// execute the algorithm build above, if and only if it satisfies with the DAG properties.
				algorithm.arrangeShortestPath();
				
				StringBuffer sb = new StringBuffer();
				
				int sum = 0;
				for (Vertex vertex : algorithm.getInnerGraph()) {
					
					if (algorithm.getInnerGraph().numNodes <= 100)
						sb.append(vertex + " " + (vertex.distance == null ? "INF" : vertex.distance) + " " + (vertex.shortestPathCount == null ? "0" : vertex.shortestPathCount) + "\n");
					
					sum += (vertex.shortestPathCount == null ? 0 : vertex.shortestPathCount); 
				}
				
				// print the sum and the vertices data (if the number of nodes <= 100)
				System.out.println(sum);
				System.out.println(sb.toString());
				
			} else {
				// unable to make a DAG out of the supplied graph. There are many cycles present. Displaying the one such possible combination.
				System.out.println("Non-positive cycle in graph. DAC is not applicable");
				printStackOrder(analyzer.getCycle(), algorithm.getInnerGraph());
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void printStackOrder(Stack<Edge> order) {
		int index = order.size() - 1;
		
		for (; index >= 0; index --)
			System.out.print(order.get(index).From + " -> ");
		
		if (order.size() > 0) 
			System.out.println(order.get(index + 1).To);
		
		System.out.println();
	}
	
	public static void printStackOrder(Stack<Vertex> order, Graph graph) {
		int index = order.size() - 1;
		
		Vertex vertex = graph.verts.get(order.get(index).name);
		index --;
		while (index >= 0) {
			for (Edge edge : vertex.Adj) {
				if (edge.otherEnd(vertex) == order.get(index)) {
					System.out.println(edge.From + " " + edge.To + " " + edge.Weight);
					vertex = edge.otherEnd(vertex);
					index --;
					break;
				}
			}
		}
	}
	
}
