package challenges.assorted.graph;

import static com.google.common.truth.Truth.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import challenges.AbstractCustomTestRunner;
import challenges.assorted.graph.model.Edge;
import challenges.assorted.graph.model.Graph;
import challenges.assorted.graph.model.Vertex;

/**
 * Class that determines whether a directed graph has cycles or not.
 * 
 * @author Hxkandwal
 *
 */
public class FindCycleInAGraph extends AbstractCustomTestRunner {
	
	private static FindCycleInAGraph _instance = new FindCycleInAGraph();
	
	private FindCycleInAGraph() {}
	
	public static boolean _hasCycle(Graph graph) {
		
		for (Vertex vertex : graph.getVertices()) { 
			if (!vertex.isSeen() && vertex.getRevAdjacentEdges().size() == 0) {
				Set<Integer> recordedVertices = new HashSet<>();
				recordedVertices.add(vertex.getName());
				
				if (performDFS(vertex, recordedVertices))
					return true;
			}
		}
		
		return false;
	}
	
	private static boolean performDFS(Vertex vertex, Set<Integer> recordedVertices) {
		if (vertex.getAdjacentEdges().size() == 0)
				vertex.setSeen(true);
		else {
			for (Edge edge : vertex.getAdjacentEdges()) {
				Vertex otherVertex = edge.otherEnd(vertex);
				
				if (recordedVertices.contains(otherVertex.getName()))
					return true;
				
				if (!otherVertex.isSeen())  {
					recordedVertices.add(otherVertex.getName());
					
					if (performDFS(otherVertex, recordedVertices))
						return true;
				}
			}
		}
		
		recordedVertices.remove(vertex.getName());
		return false;
	}
	
    // driver method
    public static void main(String[] args) throws FileNotFoundException {
        testComplex("/src/test/resources/challenges/assorted/graph/graph-topological-order-1.txt", false);
        testComplex("/src/test/resources/challenges/assorted/graph/graph-find-cycle-1.txt", true);
    }

    private static void testComplex(final String filename, final boolean expectedOutput) throws FileNotFoundException {
        Graph graph = Graph.readGraph(new Scanner(new File(System.getProperty("user.dir") + filename)), true);

        _instance.runTest(graph, expectedOutput);
    }

    public void runTest(final Graph graph, final boolean expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { graph });

        for (Object answer : answers)
            assertThat((Boolean) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
