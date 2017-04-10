package eular.hierholzer.graph.algorithm.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import eular.hierholzer.graph.algorithm.model.Edge;
import eular.hierholzer.graph.algorithm.model.EularEdgeLinkedList;
import eular.hierholzer.graph.algorithm.model.EularEdgeLinkedList.Node;
import eular.hierholzer.graph.algorithm.model.Graph;
import eular.hierholzer.graph.algorithm.model.Vertex;
import eular.hierholzer.graph.algorithm.model.VerticesNodesMetaMap;

/**
 * A graph G is called Eulerian if it is connected and the degree of every vertex is an even number. 
 * 
 * It is known that such graphs aways have a tour (a cycle that may not be simple) that goes through 
 * every edge of the graph exactly once. Such a tour (sometimes called a circuit) is called an Euler tour. 
 * 
 * If the graph is connected, and it has exactly 2 nodes of odd degree, then it has an Euler Path connecting 
 * these two nodes, that includes all the edges of the graph exactly once. 
 * 
 * In this project, We present an algorithm implementation that finds an Euler tour or an Euler Path in a given graph. 
 * 
 * http://www.mathcs.emory.edu/~rg/book/chap5.pdf
 * 
 * @author G31
 *
 */
public class HierholzerAlgorithmImpl {

	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;
	
	public static List<Edge> findEulerTour(Graph g) {
		
		/* checking Eular preconditions */
		int oddEdgeNodesCount = 0;
		Vertex eularPathStartNode = null;
		Vertex eularPathEndNode = null;

		for (Vertex vertex : g.verts) {
			if (vertex != null && vertex.Adj.size() % 2 != 0) {
				if (eularPathStartNode == null)
					eularPathStartNode = vertex;
				else
					eularPathEndNode = vertex;
				oddEdgeNodesCount ++;
				
				if (oddEdgeNodesCount > 2) {
					break;
				}
			}
		}
		
		if (oddEdgeNodesCount > 0 && oddEdgeNodesCount != 2) {
			System.out.println("Graph is not Eulerian");
			return null;
		}
		
		/* if the graph has no eular path then, considering eular tour and setting start node as : node 1 */
		eularPathStartNode = (eularPathStartNode == null ? g.verts.get(1) : eularPathStartNode);
		
		VerticesNodesMetaMap verticesNodesMetaMap = new VerticesNodesMetaMap(g.verts.size());
		
		EularEdgeLinkedList<Edge> eularEdges = FindEulerTourInternal (g, verticesNodesMetaMap, eularPathStartNode, eularPathEndNode, 0);
		
		return eularEdges;
	}

	/*
	 * Main Recursive Algorithm performing the activity of implementing Hierholzer Algorithm, post all the condition have been met.
	 */
	private static EularEdgeLinkedList<Edge> FindEulerTourInternal(Graph g, VerticesNodesMetaMap verticesNodesMetaMap, Vertex startNode, Vertex endNode, int intialSize) {
		
		EularEdgeLinkedList<Edge> eularEdges = new EularEdgeLinkedList<>(verticesNodesMetaMap);
		Vertex futureVertex = startNode;
		boolean foundway = true;
		
		/*
		 * This loop, iteratively builds the Eular path (EularEdgeLinkedList<Edge>), by using the connecting adjacent edges.
		 */
		while (foundway) {
			foundway = false;
			for (Iterator<Edge> futureVertexIterator = futureVertex.Adj.iterator(); futureVertexIterator.hasNext();) {
				Edge edge = futureVertexIterator.next();
				
				/* 
				 * If an edge has already been used by the other pairing node, then mark it as processed and remove in order to prevent
				 * future checking.
				 */
				if (edge.isUsed) {
					futureVertex.processedEdges.add(edge);
					futureVertexIterator.remove();
					continue;
				}
				
				edge.isUsed = true;
				futureVertex.seen = true;
				futureVertex.processedEdges.add(edge);
				futureVertexIterator.remove();
				
				futureVertex = edge.otherEnd(futureVertex);
				eularEdges.addLast(edge);
				foundway = true;
				break;
			}
		}

		/*
		 * If all the edges are not yet covered, find eular path again, however,
		 * this time starting with node which is wihtin the current Eular path
		 * and has an edge which is not yet covered yet.
		 * 
		 * Then patch all the results together.
		 */
		
		/* choosing the start point from the present circuit, for the leftover circuit discovery ! */
		
		if (!verticesNodesMetaMap.isComplete() || g.numEdges != (intialSize + eularEdges.size)) {
			
			Vertex branchedStartNode = null;
			Node<Edge> reverseTraversalNode = eularEdges.last;
			Vertex traversingOtherVertex = reverseTraversalNode.item.From;
			
			while (branchedStartNode == null) {
				for (Iterator<Edge> traversingOtherVertexIterator = traversingOtherVertex.Adj.iterator(); traversingOtherVertexIterator.hasNext();) {
					Edge edge = traversingOtherVertexIterator.next();
					
					/* 
					 * If an edge has already been used by the other pairing node, then mark it as processed and remove in order to prevent
					 * future checking.
					 */
					if (edge.isUsed) {
						futureVertex.processedEdges.add(edge);
						traversingOtherVertexIterator.remove();
						continue;
					}
					
					branchedStartNode = traversingOtherVertex;
					break;
				}
				traversingOtherVertex = reverseTraversalNode.item.otherEnd(traversingOtherVertex);
				reverseTraversalNode = reverseTraversalNode.prev;
			}
			
			EularEdgeLinkedList<Edge> leftoverEdges = FindEulerTourInternal(g, verticesNodesMetaMap, branchedStartNode, endNode, (intialSize + eularEdges.size));
			
			Node<Edge> edgeNode = verticesNodesMetaMap.getEdgeNodeByVertex(branchedStartNode);
			
			/* 
			 * Patching with the global Structure using the verticesNodesMetaMap, Expansion of nodes.
			 * 
			 *  We find the immediate Node<Edge> next to the branchedStartNode vertex from the map, and add
			 *  the complete chain surrounding that. 
			 * 
			 */
			int leftoverEdgesSize = leftoverEdges.size();
			
			if (edgeNode.next == null) {
				edgeNode.next = leftoverEdges.first;
				leftoverEdges.first.prev = edgeNode;
			} else {
				Node<Edge> edgeNodeNext = edgeNode.next;
				edgeNode.next = leftoverEdges.first;
				leftoverEdges.first.prev = edgeNode;
				leftoverEdges.last.next = edgeNodeNext;
				edgeNodeNext.prev = leftoverEdges.last; 
			}
			// reset the original size
			eularEdges.incrementSize(leftoverEdgesSize);
		}

		return eularEdges;
	}

	/**
	 * Method to verify whether a tour is Eular or not. 
	 * 
	 * @param g
	 * @param tour
	 * @param start
	 * @return
	 */
	public static boolean verifyTour(Graph g, List<Edge> tour, Vertex start) {

		/* check : tour must contain all the edges */
		if (tour.size() != g.numEdges) {
			return false;
		}
		
		/* check : tour must start from the start node */
		if (!(tour.get(0).From == start) && !(tour.get(0).To == start)) {
			return false;
		}
		
		/* check : tour must have all the edges adjacent to each other */
		Vertex traversingVertex = start;
		for (Edge edge : tour) {
			if (edge.From == traversingVertex || edge.To == traversingVertex) {
				traversingVertex = edge.otherEnd(traversingVertex);
			} else {
				return false;
			}
		}
		
		return true;
	}
	
	public static void memory() {
		long memAvailable = Runtime.getRuntime().totalMemory();
		long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		System.out.println("Memory: " + memUsed / 1000000 + " MB / " + memAvailable / 1000000 + " MB.");
	}

	public static void timer() {
		if (phase == 0) {
			startTime = System.currentTimeMillis();
			phase = 1;
		} else {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			System.out.println("Time: " + elapsedTime + " msec.");
			memory();
			phase = 0;
		}
	}

	public static void main(String[] args) {
		try {
			Scanner in;
			if (args.length > 0) {
				File inputFile = new File(args[0]);
				in = new Scanner(inputFile);
			} else {
				in = new Scanner(System.in);
			}

			// read undirected graph from stream "in"
			Graph g = Graph.readGraph(in, false);

			timer();
			List<Edge> eularEdges = findEulerTour (g);
			timer();
			
			// print the eular tour/circuit
			for (Edge eularEdge : eularEdges) {
				System.out.println(eularEdge);
			}
			
			timer();
			verifyTour(g, eularEdges, g.verts.get(1));
			timer();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
