package me.hxkandwal.daily.challanges.assorted.graph.model;

import java.util.Scanner;

/**
 * A general purpose graph data structure.
 * 
 * @author Hxkandwal
 *
 */
public class Graph {
	
	private int numVertices;
	private int numEdges;
	private Vertex[] vertices;
	
	public Graph(int numVertices, int numEdges) {
		this.vertices = new Vertex [this.numVertices = numVertices];
		this.numEdges = numEdges;
		
		for (int idx = 0; idx < vertices.length; idx ++)
			vertices [idx] = new Vertex(idx + 1);
	}

	public int getNumVertices() {
		return numVertices;
	}

	public int getNumEdges() {
		return numEdges;
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public static Graph readGraph(Scanner sc, boolean directed) {
		Graph graph = new Graph(sc.nextInt(), sc.nextInt());
		
		for (int edgeIteration = 0; edgeIteration < graph.numEdges; edgeIteration ++) {
			Edge edge = new Edge (graph.vertices [sc.nextInt() - 1], graph.vertices [sc.nextInt() - 1], sc.nextInt());
			edge.getFrom().getAdjacentEdges().add(edge);
			
			if (!directed) 
				edge.getTo().getAdjacentEdges().add(edge);
			else
				edge.getTo().getRevAdjacentEdges().add(edge);
		}
		
		sc.close();
		return graph;
	}
}
