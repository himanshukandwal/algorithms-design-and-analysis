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
	
	public Edge(Vertex to, Vertex from) {
		this.to = to;
		this.from = from;
	}

	public Edge(Vertex to, Vertex from, int weight) {
		this(to, from);
		this.weight = weight;
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
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
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
