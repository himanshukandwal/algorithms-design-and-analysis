package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Longest path in a matrix with given constraints
 * 
 * Given a n*n matrix where numbers all numbers are distinct and are distributed from range 1 to n^2, 
 * find the maximum length path (starting from any cell) such that all cells along the path are increasing 
 * order with a difference of 1.
 * 
 * We can move in 4 directions from a given cell (i, j), i.e., we can move to (i+1, j) or (i, j+1) or (i-1, j) or (i, j-1) with the condition that the adjacent. 
 * Example:
 * 
 * Input:  mat[][] = {{1, 2, 9}
 *                    {5, 3, 8}
 *                    {4, 6, 7}}
 * Output: 4
 * 		 The longest path is 6-7-8-9.
 * 
 * @author Hxkandwal
 *
 */
public class LongestPathInMatrixWithConstraints extends AbstractCustomTestRunner {
	
	private static LongestPathInMatrixWithConstraints _instance = new LongestPathInMatrixWithConstraints();
	
	private LongestPathInMatrixWithConstraints() {}
	
	public static int _longestPath(int[][] matrix) {
		if (matrix.length <= 1)
			return 1;
		
		int len = matrix.length, max_length = 0;
		boolean[][] visited = new boolean [len][len];
		
		int visited_counter = 1;
		while (visited_counter <= Math.pow(len, 2)) {
			
		}
		
		return max_length;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("GEEKSFORGEEKS", "GEEKS");
		_instance.runTest("AAAAAAAAAA", "AAAAAAAAA");
		_instance.runTest("ABCDEFG", "");
		_instance.runTest("ABABABA", "ABABA");
		_instance.runTest("ATCGATCGA", "ATCGA");
		_instance.runTest("banana", "ana");
		_instance.runTest("abcpqrabpqpq", "ab");
	}

	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
