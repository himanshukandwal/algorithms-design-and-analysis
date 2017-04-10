package eular.hierholzer.graph.algorithm.main;

/**
 * 
 * @author rbk
 * Example to illustrate an algorithm that tests if a given
 * undirected graph is bipartite.  Nodes are classified into
 * Outer and Inner nodes, the two sides of the bipartition.
 * Rewritten: Vertex, Edge, and Graph classes into their own files
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import eular.hierholzer.graph.algorithm.model.Edge;
import eular.hierholzer.graph.algorithm.model.Graph;
import eular.hierholzer.graph.algorithm.model.Vertex;

public class CheckBipartiteNew {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;
		if (args.length > 0) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}
		Graph g = Graph.readGraph(in, false); // read undirected graph from
												// stream "in"
		Queue<Vertex> Q = new LinkedList<>();

		for (Vertex u : g) {
			u.seen = false;
			u.parent = null;
			u.distance = Integer.MAX_VALUE;
		}

		// Run BFS on every component
		for (Vertex src : g) {
			if (!src.seen) {
				src.distance = 0;
				Q.add(src);
				src.seen = true;

				while (!Q.isEmpty()) {
					Vertex u = Q.remove();
					for (Edge e : u.Adj) {
						Vertex v = e.otherEnd(u);
						if (!v.seen) {
							v.seen = true;
							v.parent = u;
							v.distance = u.distance + 1;
							Q.add(v);
						} else {
							if (u.distance == v.distance) {
								System.out.println("Graph is not bipartite");
								return;
							}
						}
					}
				}
			}
		}
		System.out.println("Graph is bipartite");
		for (Vertex u : g) {
			if (u.distance % 2 == 0) {
				System.out.println(u + " Outer");
			} else {
				System.out.println(u + " Inner");
			}
		}
	}
}