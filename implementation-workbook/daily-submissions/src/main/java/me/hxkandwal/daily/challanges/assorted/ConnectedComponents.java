package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Connected Components
 * 
 * find count of connected components in a graph.
 * 
 * @author Hxkandwal
 *
 */
public class ConnectedComponents extends AbstractCustomTestRunner {

	private static ConnectedComponents _instance = new ConnectedComponents();
	
	private ConnectedComponents() {}
	
	public static int _findConnectedComponents(int[][] matrix) {
		if (matrix == null || matrix.length == 0) 
			return 0;
		
		boolean [][] visited = new boolean [matrix[0].length][matrix.length];
		int components = 0;
		
		for (int row = 0; row < matrix.length; row ++) {
			for (int col = 0; col < matrix[0].length; col ++) {
				if (!visited [row][col] && matrix [row][col] == 1) {
					visited [row][col] = true;
					determineComponent(matrix, visited, row, col);
					components ++;
				}
			}
		}
		
		return components;
	}
	
	private static void determineComponent(int[][] matrix, boolean[][] visited, int row, int col) {
		Queue<AbstractMap.SimpleEntry<Integer, Integer>> queue = new LinkedList<>();
		queue.add(new AbstractMap.SimpleEntry<Integer, Integer>(row, col));
		
		while (!queue.isEmpty()) {
			AbstractMap.SimpleEntry<Integer, Integer> tuple = queue.poll();
			
			int rowValue = tuple.getKey(), colValue = tuple.getValue();
			
			if (rowValue + 1 < matrix.length && !visited [rowValue + 1][colValue] && matrix [rowValue + 1][colValue] == 1) {
				visited [rowValue + 1][colValue] = true;
				queue.add(new AbstractMap.SimpleEntry<Integer, Integer>(rowValue + 1, colValue));
			}
			
			if (rowValue - 1 >= 0 && !visited [rowValue - 1][colValue] && matrix [rowValue - 1][colValue] == 1) {
				visited [rowValue - 1][colValue] = true;
				queue.add(new AbstractMap.SimpleEntry<Integer, Integer>(rowValue - 1, colValue));
			}
			
			if (colValue + 1 < matrix[0].length && !visited [rowValue][colValue + 1] && matrix [rowValue][colValue + 1] == 1) {
				visited [rowValue][colValue + 1] = true;
				queue.add(new AbstractMap.SimpleEntry<Integer, Integer>(rowValue, colValue + 1));
			}
			
			if (colValue - 1 >= 0 && !visited [rowValue][colValue - 1] && matrix [rowValue][colValue - 1] == 1) {
				visited [rowValue][colValue - 1] = true;
				queue.add(new AbstractMap.SimpleEntry<Integer, Integer>(rowValue, colValue - 1));
			}	
		}
 	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [][] {{0,1,0,1},
										{0,1,0,1},
										{0,1,0,1},
										{0,1,0,1}}, 2);
		
		_instance.runTest(new int [][] {{0,1,0,0},
										{0,1,0,0},
										{0,1,0,0},
										{0,1,0,0}}, 1);
		
		_instance.runTest(new int [][] {{0,0,0,0},
										{0,0,0,0},
										{0,0,0,0},
										{0,0,0,0}}, 0);

		_instance.runTest(new int [][] {{0,1,0,1},
										{0,0,0,0},
										{0,1,0,1},
										{1,0,0,1}}, 5);
	}

	public void runTest(final int[][] array, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}
