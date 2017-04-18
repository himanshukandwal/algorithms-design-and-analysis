package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 74. Search a 2D Matrix
 * 
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * - Integers in each row are sorted from left to right.
 * - The first integer of each row is greater than the last integer of the previous row.
 * 
 * For example,
 * 	Consider the following matrix:
 * 		[
 * 			[1,   3,  5,  7],
 * 			[10, 11, 16, 20],
 * 			[23, 30, 34, 50]
 * 		]
 * 
 * 	Given target = 3, return true.
 * 
 * @author Hxkandwal
 */
public class Search2DMatrix extends AbstractCustomTestRunner {
	
	private static Search2DMatrix _instance = new Search2DMatrix();

	public boolean _searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int rows = matrix.length, cols = matrix [0].length;
        int left = 0, right = rows * cols - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (matrix [mid/cols][mid % cols] > target) right = mid - 1;
            else if  (matrix [mid/cols][mid % cols] < target) left = mid + 1;
            else return true;
        }
        return false;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[][] { new int[] { 1, 3, 5 } }, 0, false);
	}

	public void runTest(final int[][] matrix, final int target, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { matrix, target });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
