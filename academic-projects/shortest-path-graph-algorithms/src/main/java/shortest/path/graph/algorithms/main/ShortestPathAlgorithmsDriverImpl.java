package shortest.path.graph.algorithms.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import shortest.path.graph.algorithms.AbstractGraphShortestPath;
import shortest.path.graph.algorithms.algorithm.ShortestPathAlgorithmSelectionFactory;
import shortest.path.graph.algorithms.model.Graph;
import shortest.path.graph.algorithms.model.Vertex;

/**
 * Level 1 : Solution
 * 
 * Implementation of shortest path algorithms: BFS, Dijkstra's algorithm, DAG shortest paths, and Bellman-Ford algorithm. 
 * 
 * @author G31 
 *
 */
public class ShortestPathAlgorithmsDriverImpl {
	
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
			
			AbstractGraphShortestPath algorithm = ShortestPathAlgorithmSelectionFactory.selectAlgorithm (graph);
			algorithm.arrangeShortestPath();
			
			if (algorithm.hasNegativeCycle()) {
				System.out.println("Unable to solve problem. Graph has a negative cycle.");
				System.exit(1);
			}
			
			int sum = 0;
			StringBuilder sb = new StringBuilder();
			
			if (graph.numNodes <= 100) {
				for (Vertex vertex : graph) {
					sb.append(vertex + " " + (vertex.distance == null ? "INF" : vertex.distance) + " " + (vertex.parent == null ? "-" : vertex.parent) + "\n");
					
					if (vertex.distance != null)
						sum += vertex.distance;
				}
			}
			
			System.out.println(algorithm.getAlgorithmType() + " " + sum);
			System.out.println(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
