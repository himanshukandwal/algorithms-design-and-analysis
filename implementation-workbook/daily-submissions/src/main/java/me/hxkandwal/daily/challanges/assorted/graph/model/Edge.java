package me.hxkandwal.daily.challanges.assorted.graph.model;

/**
 * A general purpose edge data structure for vertex data structure.
 * 
 * @author Hxkandwal
 *
 */
public class Edge {
	
	private Vertex to;
	private Vertex from;
	private boolean directed;
	
	public Edge(Vertex to, Vertex from) {
		this.to = to;
		this.from = from;
	}
	
	public Vertex otherEnd(Vertex vertex) {
		return (to == vertex ? from : to);
	}

	public Vertex getTo() {
		return to;
	}

	public void setTo(Vertex to) {
		this.to = to;
	}

	public Vertex getFrom() {
		return from;
	}

	public void setFrom(Vertex from) {
		this.from = from;
	}

	public boolean isDirected() {
		return directed;
	}

	public void setDirected(boolean directed) {
		this.directed = directed;
	}
	
}
