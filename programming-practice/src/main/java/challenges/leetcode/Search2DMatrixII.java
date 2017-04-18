package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 240. Search a 2D Matrix II
 * 
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * - Integers in each row are sorted in ascending from left to right.
 * - Integers in each column are sorted in ascending from top to bottom.
 * 
 * For example,
 * 	Consider the following matrix:
 * 		[
 * 			[1,   4,  7, 11, 15],
 * 			[2,   5,  8, 12, 19],
 * 			[3,   6,  9, 16, 22],
 * 			[10, 13, 14, 17, 24],
 * 			[18, 21, 23, 26, 30]
 * 		]
 * 
 * Given target = 5, return true.
 * Given target = 20, return false.
 *  
 * @author Hxkandwal
 */
public class Search2DMatrixII extends AbstractCustomTestRunner {

	public boolean _searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int left = 0, right = matrix [0].length - 1;
        while (left < matrix.length && right >= 0) {
            if (matrix [left][right] > target) right --;
            else if (matrix [left][right] < target) left ++;
            else return true;
        }
        return false;
    }
	
}
