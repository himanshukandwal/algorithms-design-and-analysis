package me.hxkandwal.daily.challanges.assorted.graph.model;

import java.io.InputStream;

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
		this.numVertices = numVertices;
		this.numEdges = numEdges;
		vertices = new Vertex [numVertices];
	}

	public int getNumVertices() {
		return numVertices;
	}

	public void setNumVertices(int numVertices) {
		this.numVertices = numVertices;
	}

	public int getNumEdges() {
		return numEdges;
	}

	public void setNumEdges(int numEdges) {
		this.numEdges = numEdges;
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public static Graph readGraph(InputStream is) {
		
		return null;
	}
}
