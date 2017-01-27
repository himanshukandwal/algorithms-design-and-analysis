package me.hxkandwal.daily.challanges.assorted.graph;

import static com.google.common.truth.Truth.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;
import me.hxkandwal.daily.challanges.assorted.graph.model.Edge;
import me.hxkandwal.daily.challanges.assorted.graph.model.Graph;
import me.hxkandwal.daily.challanges.assorted.graph.model.Vertex;

import static me.hxkandwal.daily.challanges.assorted.graph.GraphUtilities.printVertexStack;

/**
 * Program to compute the topological ordering of a directed acyclic graph.
 * 
 * @author Hxkandwal
 *
 */
public class TopologicalOrder extends AbstractCustomTestRunner {
	
	private static TopologicalOrder _instance = new TopologicalOrder();
	
	private TopologicalOrder() {}
	
	public String _topologicalOrdering(Graph graph) {
		Stack<Vertex> orderedStack = new Stack<>();
		
		for (Vertex vertex : graph.getVertices())
			if (!vertex.isSeen() && vertex.getAdjacentEdges().size() == 0)
				performDFS(vertex, orderedStack);
		
		return printVertexStack(orderedStack);
	}
	
	private void performDFS(Vertex vertex, Stack<Vertex> collector) {
		vertex.setSeen(true);
		
		if (vertex.getAdjacentEdges().size() == 0)
			collector.push(vertex);
		else {
			for (Edge edge : vertex.getAdjacentEdges())
				if (!edge.otherEnd(vertex).isSeen())
					performDFS(edge.otherEnd(vertex), collector);
			
			collector.push(vertex);
		}
	}
	
    // driver method
    public static void main(String[] args) throws FileNotFoundException {
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/assorted/graph/graph-topological-order-1.txt", "7,6,5,4,2,3,1");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/assorted/graph/graph-topological-order-2.txt", "5,4,3,1,2");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/assorted/graph/graph-topological-order-3.txt", "2,5,4,3,1");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/assorted/graph/graph-topological-order-4.txt", "2,5,3,1,4");
    }

    private static void testComplex(final String filename, final String expectedOutput) throws FileNotFoundException {
        Graph graph = Graph.readGraph(new Scanner(new File(System.getProperty("user.dir") + filename)), true);

        _instance.runTest(graph, expectedOutput);
    }

    public void runTest(final Graph graph, final String expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { graph });

        for (Object answer : answers)
            assertThat((String) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }	
}
