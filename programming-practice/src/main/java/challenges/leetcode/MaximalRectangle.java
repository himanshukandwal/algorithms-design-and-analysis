package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 85. Maximal Rectangle
 * 
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * 
 * For example, given the following matrix:
 * 	
 * 	1 0 1 0 0
 * 	1 0 1 1 1
 * 	1 1 1 1 1
 * 	1 0 0 1 0
 * 
 * Return 6.
 * 
 * @author Hxkandwal
 */
public class MaximalRectangle extends AbstractCustomTestRunner {
	
	private static MaximalRectangle _instance = new MaximalRectangle();
	
	private MaximalRectangle() {}
	
	public int _maximalRectangle (char[][] matrix) {
		if (matrix.length == 0) return 0;
	    int rows = matrix.length, cols = matrix [0].length;
	    
	    int left [] = new int [cols], right [] = new int [cols], height [] = new int [cols];
	    Arrays.fill(right, cols);
	    
	    int maxA = 0;
	    for (int row = 0; row < rows; row ++) {
	        int cur_left = 0, cur_right = cols; 
	        
	        for (int col = 0; col < cols; col ++) { // compute height (can do this from either side)
	            if (matrix[row][col] == '1') height [col] ++; 
	            else height [col] = 0;
	        }
	        
	        for (int col = 0; col < cols; col ++) { // compute left (from left to right)
	            if (matrix [row][col] == '1') left [col] = Math.max (left [col], cur_left);
	            else { left [col] = 0; cur_left = col + 1; }
	        }
	        
	        // compute right (from right to left)
	        for(int col = cols - 1; col >= 0; col --) {
	            if (matrix [row][col] == '1') right[col] = Math.min (right [col], cur_right);
	            else { right [col] = cols; cur_right = col; }    
	        }
	        
	        // compute the area of rectangle (can do this from either side)
	        for (int col = 0; col < cols; col ++)
	            maxA = Math.max (maxA, (right [col] - left [col]) * height [col]);
	    }
	    
	    return maxA;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new char [][] { "10100".toCharArray(), 
										  "10111".toCharArray(),
										  "11111".toCharArray(),
										  "10010".toCharArray() }, 6);
	}

	public void runTest(final char[][] matrix, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { matrix });

		for (Object answer : answers)
				assertThat ((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	

}
