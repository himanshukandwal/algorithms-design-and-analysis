package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Given a matrix that is filled with ‘O’, ‘G’, and ‘W’ where ‘O’ represents open space, ‘G’ represents guards and ‘W’ represents walls in a Bank. Replace all of the O’s in the matrix with their shortest distance from a guard, without being able to go through any walls. Also, replace the guards with 0 and walls with -1 in output matrix. 
 * Expected Time complexity is O(MN) for a M x N matrix.
 * 
 * Example :
 * Input: 
 *  O  O  O  O  G
 *  O  W  W  O  O
 *  O  O  O  W  O
 *  G  W  W  W  O
 *  O  O  O  O  G
 *  
 *  Output:
 *  3  3  2  1  0
 *  2 -1 -1  2  1
 *  1  2  3 -1  2
 *  0 -1 -1 -1  1
 *  1  2  2  1  0
 *  
 *  
 * link : http://www.geeksforgeeks.org/find-shortest-distance-guard-bank/
 * 
 * @author Hxkandwal
 *
 */
public class ShortestDistanceFromGuard extends AbstractCustomTestRunner {

	private static ShortestDistanceFromGuard _instance = new ShortestDistanceFromGuard();
	
	private ShortestDistanceFromGuard() {}
	
	// method : BFS from the guard nodes. (all from guard nodes one step at a time)
	public static int[][] _compute(char[][] matrix) {
		int[][] res = null;
		
		if (matrix.length == 0) 
			return res;
		
		// a simple data structure to hold information about the nodes to process.
		class Coordinates {
			public int row;
			public int col;
			public int distance;
			
			public Coordinates(int row, int col, int d) {
				this.row = row;
				this.col = col;
				this.distance = d;
			}
		}
		
		res = new int [matrix.length][matrix[0].length];
		Queue<Coordinates> nodesToProcess = new LinkedList<>();
			
		for (int row = 0; row < matrix.length; row ++) {
			for (int col = 0; col < matrix[0].length; col ++) {
				res [row][col] = -1;
				
				if (matrix [row][col] == 'G') {
					res [row][col] = 0;
					nodesToProcess.add(new Coordinates(row, col, 0));
				}
			}
		}
		
		while (!nodesToProcess.isEmpty()) {
			Coordinates c = nodesToProcess.poll();
			
			if (c.row - 1 >= 0 && res [c.row - 1][c.col] == -1 && matrix [c.row - 1][c.col] != 'W') {
				res [c.row - 1][c.col] = c.distance + 1;
				nodesToProcess.add(new Coordinates(c.row - 1, c.col, c.distance + 1));
			}
			
			if (c.row + 1 < matrix.length && res [c.row + 1][c.col] == -1 && matrix [c.row + 1][c.col] != 'W') {
				res [c.row + 1][c.col] = c.distance + 1;
				nodesToProcess.add(new Coordinates(c.row + 1, c.col, c.distance + 1));
			}
			
			if (c.col - 1 >= 0 && res [c.row][c.col - 1] == -1 && matrix [c.row][c.col - 1] != 'W') {
				res [c.row][c.col - 1] = c.distance + 1;
				nodesToProcess.add(new Coordinates(c.row, c.col - 1, c.distance + 1));
			}
			
			if (c.col + 1 < matrix[0].length && res [c.row][c.col + 1] == -1 && matrix [c.row][c.col + 1] != 'W') {
				res [c.row][c.col + 1] = c.distance + 1;
				nodesToProcess.add(new Coordinates(c.row, c.col + 1, c.distance + 1));
			}
		}
		
		return res;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(
				new char[][] { { 'O', 'O', 'O', 'O', 'G' }, 
							   { 'O', 'W', 'W', 'O', 'O' }, 
							   { 'O', 'O', 'O', 'W', 'O' },
							   { 'G', 'W', 'W', 'W', 'O' }, 
							   { 'O', 'O', 'O', 'O', 'G' } },
				
				new int[][] { { 3, 3, 2, 1, 0 }, 
							  { 2, -1, -1, 2, 1 }, 
							  { 1, 2, 3, -1, 2 }, 
							  { 0, -1, -1, -1, 1 },
							  { 1, 2, 2, 1, 0 } });
	}

	public void runTest(final char[][] matrix, final int[][] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { matrix });

		for (Object answer : answers) {
			for (int row = 0; row < expectedOutput.length; row ++)
				assertThat(((int[][]) answer)[row]).isEqualTo(expectedOutput[row]);
		}
		
		System.out.println("ok!");
	}
	
}
