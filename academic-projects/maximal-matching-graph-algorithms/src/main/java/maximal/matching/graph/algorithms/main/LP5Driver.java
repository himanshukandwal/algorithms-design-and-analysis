package maximal.matching.graph.algorithms.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import maximal.matching.graph.algorithms.model.Graph;

public class LP5Driver {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;
		boolean VERBOSE = false;

		if (args.length > 0) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}
		if (args.length > 1) {
			VERBOSE = true;
		}
		Graph g = Graph.readGraph(in, false); // read undirected graph from stream "in"
		
		// Create your own class and call the function to find a maximum
		// matching.
		// int result = Matching.matching(g);
		// System.out.println(result);
		// if (VERBOSE) {
		// Output the edges of M.
		// }
	}
}
