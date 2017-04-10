package minimum.spanning.tree.graph.algorithms.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import minimum.spanning.tree.graph.algorithms.KruskalAlgorithm;
import minimum.spanning.tree.graph.algorithms.model.Graph;

/**
 * Level 1 Driver
 * 
 * This driver servers as main class for Kruskal algorithm. 
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class Level1KruskalDriver {
	
	public static void main(String[] args) {

		try {
			Scanner in;
			if (args.length > 0) {
				File inputFile = new File(args[0]);
				in = new Scanner(inputFile);
			} else {
				in = new Scanner(System.in);
			}
			
			Graph graph = Graph.readGraph(in, false);
			
			KruskalAlgorithm algorithm = new KruskalAlgorithm();
			algorithm.kruskalMST(graph);
			
			System.out.println("Output:");
			System.out.println(algorithm.getMinimumCost());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
