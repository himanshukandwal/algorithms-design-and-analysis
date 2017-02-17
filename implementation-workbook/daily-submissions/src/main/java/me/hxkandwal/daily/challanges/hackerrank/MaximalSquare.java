package me.hxkandwal.daily.challanges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 221. Maximal Square
 * 
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * 
 * For example, given the following matrix:
 * 
 * 	1 0 1 0 0
 * 	1 0 1 1 1
 * 	1 1 1 1 1
 * 	1 0 0 1 0
 * 
 * Return 4.
 * 
 * @author Hxkandwal
 */
public class MaximalSquare extends AbstractCustomTestRunner {
	
	private static MaximalSquare _instance = new MaximalSquare();
	
	private MaximalSquare() {}
	
    public int _maximalSquare(char[][] matrix) {
    	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
    	      
    	int max = 0, n = matrix.length, m = matrix[0].length;
    	
    	/**
    	 * 	dp (i, j) represents the length of the square whose lower-right corner is located at (i, j)
    	 * 	dp (i, j) = min { dp (i-1, j-1), dp (i-1, j), dp (i, j-1) }
    	 **/
    	int[][] dp = new int [n + 1][m + 1];
    	
    	for (int row = 1; row <= n; row ++)
    		for (int col = 1; col <= m; col ++)
    			if (matrix [row - 1][col - 1] == '1') {
    				dp [row][col] = Math.min (dp [row - 1][col - 1], Math.min (dp [row - 1][col], dp [row][col - 1])) + 1;
    				max = Math.max (max, dp [row][col]);
    			}
    	
    	// return the area
    	return max * max;
	}
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new char[][] { new char [] { '1', '0', '1', '0', '0' }, 
										 new char [] { '1', '0', '1', '1', '1' },
										 new char [] { '1', '1', '1', '1', '1' },
										 new char [] { '1', '0', '0', '1', '0' },}, 4);
	}

	public void runTest(final char[][] matrix, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { matrix });

		for (Object answer : answers)
				assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	    
    
}
