package me.hxkandwal.daily.challanges.assorted.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * A general purpose vertex data structure for graph data structure.
 * 
 * @author Hxkandwal
 *
 */
public class Vertex {
	
	private int id;
	private String name;
	private List<Edge> adjacentEdges;
	
	public Vertex(int id) {
		this.id = id;
		this.adjacentEdges = new ArrayList<>();
	}
	
	public Vertex(int id, String name) {
		this(id);
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Edge> getAdjacentEdges() {
		return adjacentEdges;
	}

}
