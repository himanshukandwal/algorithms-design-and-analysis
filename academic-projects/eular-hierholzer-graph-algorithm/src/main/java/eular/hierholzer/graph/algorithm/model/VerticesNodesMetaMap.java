package eular.hierholzer.graph.algorithm.model;

import eular.hierholzer.graph.algorithm.model.EularEdgeLinkedList.Node;

/**
 * This class acts as a map between the vertex and the linkedlist nodes, for the quick 
 * access to the nodes<edge> object, in order to patch the different linkedlist into one,
 * in O(1) time. 
 * 
 * This is a quick map, based on the vertex.name rather than random ordering. 
 * The class with have size of g.verts.size() and Node<Edge> object corresponding to any
 * vertex can be accessed directly via the vertex.name position.
 * 
 * Example : 
 * 			verticesEdgeArray[vertex.name][0] -> vertex reference
 * 			verticesEdgeArray[vertex.name][1] -> EularEdgeLinkedList Node reference
 * 
 * @author G31
 *
 */
public class VerticesNodesMetaMap {
	
	public int size;
	public Object[][] verticesEdgeArray;
	public int entriesCount;
	
	public VerticesNodesMetaMap(int size) {
		this.size = size;
		this.verticesEdgeArray = new Object[size][2];
		this.entriesCount = 1; // for the null vertex node
	}
	
	@SuppressWarnings("unchecked")
	public Node<Edge> getEdgeNodeByVertex(Vertex vertex) {
		return (Node<Edge>) verticesEdgeArray[vertex.name][1];
	}
	
	public void	addEntry(Edge edge, Node<Edge> edgeNode) {
		if (verticesEdgeArray[edge.From.name][0] == null) {
			verticesEdgeArray[edge.From.name][0] = edge.From;
			verticesEdgeArray[edge.From.name][1] = edgeNode;
			entriesCount ++;
		}
		if (verticesEdgeArray[edge.To.name][0] == null) {
			verticesEdgeArray[edge.To.name][0] = edge.To;
			verticesEdgeArray[edge.To.name][1] = edgeNode;
			entriesCount ++;
		}
	}
	
	public boolean isComplete() {
		return (entriesCount == size);
	}
}
