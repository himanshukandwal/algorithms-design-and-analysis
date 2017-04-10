package maximal.matching.graph.algorithms.model;

import java.util.Set;

/**
 * a collection class to hold all the possible matchings from a vertex. (Edge wise)
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 */
public class Matchings {
	
	private Matchings parent;			// in case of a merge, this matching will be merged to the superior one.
	private Vertex startVertex;			// vertex on which matchings have been opened.
	private Set<Matching> matchings;	// matchings for each edge connected with the vertex. (will be growing in-parallel) 
										// set -> as we would like it to be ordered length-wise.
	
	/**
	 * constructor.
	 * 
	 * @param startVertex
	 */
	public Matchings(Vertex startVertex) {
		this.startVertex = startVertex;
		
	}
	
	public Matchings getParent() {
		return parent;
	}
	
	public Set<Matching> getMatchings() {
		return matchings;
	}
	

}
