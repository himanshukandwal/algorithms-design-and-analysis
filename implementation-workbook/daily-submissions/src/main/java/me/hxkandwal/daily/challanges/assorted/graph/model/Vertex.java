package me.hxkandwal.daily.challanges.assorted.graph.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A general purpose vertex data structure for graph data structure.
 * 
 * @author Hxkandwal
 *
 */
public class Vertex {
	
	private int name;
	private List<Edge> adjacentEdges;
	private List<Edge> revAdjacentEdges;
	private boolean seen;
	
	public Vertex(int name) {
		this.name = name;
		this.adjacentEdges = new ArrayList<>();
		this.revAdjacentEdges = new ArrayList<>();
	}
	
	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public List<Edge> getAdjacentEdges() {
		return adjacentEdges;
	}
	
	public List<Edge> getRevAdjacentEdges() {
		return revAdjacentEdges;
	}
	
	public String toString() {
		return Integer.toString(name);
	}

	public boolean isSeen() {
		return seen;
	}
	
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
}
