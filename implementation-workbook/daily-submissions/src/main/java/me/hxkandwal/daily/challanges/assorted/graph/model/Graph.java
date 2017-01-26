package me.hxkandwal.daily.challanges.assorted.graph.model;

import java.io.IOException;
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
		this.vertices = new Vertex [this.numVertices = numVertices];
		this.numEdges = numEdges;
		
		for (int idx = 0; idx < vertices.length; idx ++)
			vertices [idx] = new Vertex(idx + 1);
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

	public static Graph readGraph(InputStream is, boolean directed) throws IOException {
		Graph graph = null;
		try {
			graph = new Graph(is.read(), is.read());
		
			for (int edgeIteration = 0; edgeIteration < graph.numEdges; edgeIteration ++) {
				Edge edge = new Edge (graph.vertices [is.read() - 1], graph.vertices [is.read() - 1], is.read());
				edge.getTo().getAdjacentEdges().add(edge);
				
			}
			
		} catch (Exception e) {
			is.close();
		} finally {
			is.close();
		}
		
		return graph;
	}
}
