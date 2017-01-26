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
	
	public Vertex(int name) {
		this.name = name;
		this.adjacentEdges = new ArrayList<>();
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
	
	public String toString() {
		return Integer.toString(name);
	}

}
