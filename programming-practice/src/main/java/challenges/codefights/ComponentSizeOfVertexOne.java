package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import challenges.AbstractCustomTestRunner;

/**
 * Component size of Vertex one
 * 
 * Given the adjacency matrix of an undirected graph with no loops or multiple edges, find the size of the connected component 
 * of vertex 1 (0-based).
 * 
 * Example: matrix = [[false, true, false],
 *           		  [true, false, false],
 *           		  [false, false, false]]
 *           
 * 			The output should be bfsComponentSize(matrix) = 2. The component contains vertices 0 and 1.
 * 
 * link: https://codefights.com/tournaments/NQ7925XFfJXXqJCvj/C
 * 
 * @author Hxkandwal
 * 
 */
public class ComponentSizeOfVertexOne extends AbstractCustomTestRunner {
	
	private static ComponentSizeOfVertexOne _instance = new ComponentSizeOfVertexOne();
			
	public ComponentSizeOfVertexOne() {}
	
	public static int bfsComponentSize(boolean[][] matrix) {
		int size = 1;
		
		if (matrix.length >= 1) {
			
			// queue to put future start points (bfs expansion)
			Queue<Integer> queue = new LinkedList<>();
			queue.add(1);
			
			while (! queue.isEmpty()) {
				int rowIdx = queue.poll(), colIdx = 0;
				
				while (colIdx >= 0 && colIdx < matrix [0].length) {
					if (matrix [rowIdx][colIdx]) {
						matrix [rowIdx][colIdx] = false;
						matrix [colIdx][rowIdx] = false;
						queue.add(rowIdx);
						size ++;
					}
					
					colIdx ++;
				}
			}
			
		}
		
		return size;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new boolean [][] {{ false, true, false }, 
											{ true, false, false }, 
											{ false, false, false }}, 3);
		
		_instance.runTest(new boolean [][] {{ false, true, false }, 
											{ false, false, false }, 
											{ false, false, false }}, 1);		
	}

	public void runTest(final boolean [][] matrix, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { matrix });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
