package challenges.assorted.graph;

import java.util.Iterator;
import java.util.Stack;

import challenges.assorted.graph.model.Graph;
import challenges.assorted.graph.model.Vertex;

/**
 * Class primarily consisting of utility methods for graph and related data structures.
 * 
 * @author Hxkandwal
 *
 */
public class GraphUtilities {
	
	public static String printVertexStack(Stack<Vertex> stack) {
		StringBuilder sb = new StringBuilder();
		
		for (Iterator<Vertex> stackIterator = stack.iterator(); stackIterator.hasNext();) {
			sb.append(stackIterator.next().getName());
			
			if (stackIterator.hasNext()) 
				sb.append(",");
		}
		
		return sb.toString();
	}
	
	public static void resetGraph(Graph graph) {
		for (Vertex vertex : graph.getVertices())
			vertex.setSeen(false);
	}
	
}
