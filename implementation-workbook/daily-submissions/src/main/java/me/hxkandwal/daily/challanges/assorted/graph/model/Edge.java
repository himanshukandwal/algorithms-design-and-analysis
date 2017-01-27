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
	private int weight;
	private boolean seen;
	
	public Edge(Vertex from, Vertex to) {
		this.from = from;
		this.to = to;
	}

	public Edge(Vertex from, Vertex to, int weight) {
		this(from, to);
		this.weight = weight;
	}
	
	public Vertex otherEnd(Vertex vertex) {
		return (to == vertex ? from : to);
	}

	public Vertex getTo() {
		return to;
	}

	public Vertex getFrom() {
		return from;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	public String toString() {
		return "(" + from + "," + to + ")";
	}
	
}
