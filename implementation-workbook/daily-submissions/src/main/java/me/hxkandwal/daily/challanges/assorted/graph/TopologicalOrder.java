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

/**
 * Program to compute the topological ordering of a directed acyclic graph.
 * 
 * @author Hxkandwal
 *
 */
public class TopologicalOrder extends AbstractCustomTestRunner {
	
	private static TopologicalOrder _instance = new TopologicalOrder();
	
	private TopologicalOrder() {}
	
	public Stack<Vertex> _topologicalOrdering(Graph graph) {
		Stack<Vertex> orderedStack = new Stack<>();
		
		for (Vertex vertex : graph.getVertices())
			if (!vertex.isSeen())
				performDFS(vertex, orderedStack);
		
		return orderedStack;
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
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MandragoraForest-Big-1.txt");
    }

    private static void testComplex(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(System.getProperty("user.dir") + filename));

        int[] input = new int[sc.nextInt()];
        for (int idx = 0; idx < input.length; idx ++)
            input [idx] = sc.nextInt();

        _instance.runTest(input, sc.nextLong());

        sc.close();
    }

    public void runTest(final int[] healthPoints, final long expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { healthPoints });

        for (Object answer : answers)
            assertThat((Long) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }	
}
