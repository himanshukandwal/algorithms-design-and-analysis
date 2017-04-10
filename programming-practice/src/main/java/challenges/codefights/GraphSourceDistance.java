package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import challenges.AbstractCustomTestRunner;

/**
 * Given the adjacency matrix of the connected undirected graph with no loops or multiple edges and the 
 * index of the start vertex, find the distances between the start vertex and each vertex of the graph.
 * 
 * Example: 
 * 
 * For matrix = [[false, false, true],
 * 		         [false, false, true],
 * 		         [true, true, false]]
 * 
 * 		and startVertex = 0, the output should be
 * 		bfsDistancesUnweightedGraph(matrix, startVertex) = [0, 2, 1].
 * 
 * @author Hxkandwal
 *
 */
public class GraphSourceDistance extends AbstractCustomTestRunner {
	
	private static GraphSourceDistance _instance = new GraphSourceDistance();
	
	private GraphSourceDistance() {}
	
	public static int[] _bfsDistancesUnweightedGraph(boolean[][] matrix, int startVertex) {
	    int [] distance = new int [matrix.length];
	    Arrays.fill (distance, -1);
	    distance [startVertex] = 0;
	    
	    Queue<Integer> processingQueue = new LinkedList<>();
	    processingQueue.add(startVertex);
	    
	    while (!processingQueue.isEmpty()) {
	    	int node = processingQueue.poll();
	    	for (int idx = 0; idx < matrix.length; idx ++) {
	    		if (matrix[node][idx] && idx != node && distance[idx] == -1) {
	    			distance [idx] = distance [node] + 1;
	    			processingQueue.add(idx);
	    		}
	    	}
	    }
	    
	    return distance;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new boolean[][] { { false, false, true }, 
											{ false, false, true }, 
											{ true, true, false } }, 0,
				new int[] { 0, 2, 1 });
		
		_instance.runTest(new boolean[][] { { false, true, false, false }, 
											{ true, false, true, true },
											{ false, true, false, true }, 
											{ false, true, true, false } }, 3, 
				new int[] { 2, 1, 1, 0 });
		
		_instance.runTest(new boolean[][] { { false, true, true }, 
											{ true, false, false }, 
											{ true, false, false } }, 0,
				new int[] { 0, 1, 1 });

		_instance.runTest(new boolean[][] { 
						{ false, true, false, false, false, false, false, false },
						{ true, false, true, false, false, false, false, false },
						{ false, true, false, true, false, false, false, false },
						{ false, false, true, false, true, false, false, false },
						{ false, false, false, true, false, true, false, false },
						{ false, false, false, false, true, false, true, false },
						{ false, false, false, false, false, true, false, true },
						{ false, false, false, false, false, false, true, false } },
				7, new int[] { 7, 6, 5, 4, 3, 2, 1, 0 });	
	}

	public void runTest(final boolean[][] matrix, final int startVertex, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { matrix, startVertex });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
