package me.hxkandwal.daily.challanges.leetcode;

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
 *
 */
public class MatrixDiagonalTraverse extends AbstractCustomTestRunner {
	
	private static MatrixDiagonalTraverse _instance = new MatrixDiagonalTraverse();
	
	public MatrixDiagonalTraverse() {}
	
	public static int[] _findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) 
        	return null;
        
        if (matrix.length == 1)
        	return matrix [0];
        
		return null;
    }

}
