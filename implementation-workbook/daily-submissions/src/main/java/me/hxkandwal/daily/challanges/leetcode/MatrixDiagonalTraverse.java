package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 498. Diagonal Traverse
 * 
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
 * 
 * Example:
 * 		Input:	[ [ 1, 2, 3 ],
 * 				  [ 4, 5, 6 ],
 * 				  [ 7, 8, 9 ]
 * 				]
 * 
 * 		Output:  [1,2,4,7,5,3,6,8,9]
 * 
 * @author Hxkandwal
 */
public class MatrixDiagonalTraverse extends AbstractCustomTestRunner {
	
	private static MatrixDiagonalTraverse _instance = new MatrixDiagonalTraverse();
	
	public MatrixDiagonalTraverse() {}
	
	public static int[] _findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) 
        	return null;
        
        if (matrix.length == 1)
        	return matrix [0];
        
        int idx = 0, rows = matrix.length, cols = matrix[0].length, row = 0, col = 0;
        boolean isTopDownFold = false;
        
        int [] ans = new int [rows * cols];
        
        while (idx < ans.length) {
	        while (row >= 0 && row < rows && col >= 0 && col < cols) {
	        	ans [idx ++] = matrix [row][col];
	        	
	        	if (isTopDownFold) {
					row ++; col --;
				} else {
					row --; col ++;
				}
	        }
	        
	        // correction step
	        row = (row < 0 ? 0 : row);
	        if (row == rows) {
	        	row = rows - 1;
	        	col += (rows == cols ? 1 : 2);
	        }
	        
	        col = (col < 0 ? 0 : col);
	        if (col == cols) {
	        	col = cols - 1;
	        	row += (rows == cols ? 1 : 2);
	        }
	        
	        isTopDownFold = !isTopDownFold;
        }
        
		return ans;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[][] {{ 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }}, new int [] { 1, 2, 4, 7, 5, 3, 6, 8, 9 });
//		_instance.runTest(new int[][] {{ 1, 2, 3 }}, new int [] { 1, 2, 3 });
//		_instance.runTest(new int[][] {{ 1, 2 }, { 3, 4 }}, new int [] { 1, 2, 3, 4 });
//		_instance.runTest(new int[][] {{ 1, 2 }, { 3, 4 }, { 5, 6 }}, new int [] { 1, 2, 3, 5, 4, 6 });
	}

	public void runTest(final int[][] matrix, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { matrix });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
	
}
