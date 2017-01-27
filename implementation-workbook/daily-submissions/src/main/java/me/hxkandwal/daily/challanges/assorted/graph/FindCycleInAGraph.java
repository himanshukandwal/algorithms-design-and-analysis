package me.hxkandwal.daily.challanges.assorted.graph;

import static com.google.common.truth.Truth.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;
import me.hxkandwal.daily.challanges.assorted.graph.model.Graph;
import me.hxkandwal.daily.challanges.assorted.graph.model.Vertex;

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
		Stack<Vertex> collector = new Stack<>();
		
		for (Vertex vertex : graph.getVertices())
			if (!vertex.isSeen() && vertex.getRevAdjacentEdges().size() == 0)
				performDFS(vertex, collector);
		
		return false;
	}
	
	private static boolean performDFS(Vertex vertex, Stack<Vertex> collector) {
		
		return false;
	}
	
    // driver method
    public static void main(String[] args) throws FileNotFoundException {
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/assorted/graph/graph-topological-order-1.txt", false);
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/assorted/graph/graph-find-cycle-1.txt", true);
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
