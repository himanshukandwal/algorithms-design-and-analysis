package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 54. Spiral Matrix
 * 
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * 
 * For example,
 * 	Given the following matrix:
 * 		[
 * 		 	[ 1, 2, 3 ],
 * 		 	[ 4, 5, 6 ],
 * 		 	[ 7, 8, 9 ]
 * 		]
 * 
 * You should return [1,2,3,6,9,8,7,4,5].
 * 
 * @author Hxkandwal
 */
public class SpiralMatrix extends AbstractCustomTestRunner {
	
	private static SpiralMatrix _instance = new SpiralMatrix();

	public List<Integer> _spiralOrder(int[][] matrix) {
		List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0) return ans;
        int rows = matrix.length, cols = matrix [0].length, layer = 0;
        
        while ((rows - 2 * layer > 0) && (cols - 2 * layer) > 0) {
            for (int col = layer; col < cols - layer; col ++) ans.add (matrix [layer][col]);
            for (int row = layer + 1; row < rows - 1 - layer; row ++) ans.add (matrix [row][cols - 1 - layer]);
            for (int col = cols - 1 - layer; col >= layer && (rows - 2 * layer > 1); col --) ans.add (matrix [rows - 1 - layer][col]);
	        for (int row = rows - 2 - layer; row > layer && (cols - 2 * layer > 1); row --) ans.add (matrix [row][layer]);
            layer ++;
        }
        return ans;
    }
	
	public List<Integer> _spiralOrderBook(int[][] matrix) {
		 List<Integer> ans = new ArrayList<>();
	        if (matrix.length == 0) return ans;
	        int row = 0, col = -1, rows = matrix.length, cols = matrix [0].length;
	        while (true) {
	            for (int idx = 0; idx < cols; idx ++) ans.add (matrix [row][++ col]);
	            if (--rows == 0) break;
	            for (int idx = 0; idx < rows; idx ++) ans.add (matrix [++ row][col]);
	            if (--cols == 0) break;
	            for (int idx = 0; idx < cols; idx ++) ans.add (matrix [row][-- col]);
	            if (--rows == 0) break;
	            for (int idx = 0; idx < rows; idx ++) ans.add (matrix [-- row][col]);
	            if (--cols == 0) break;
	        }
	        return ans;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[][] { new int[] { 1 } }, Arrays.asList(1));
		_instance.runTest(new int[][] { new int[] { 1 }, new int[] { 2 } }, Arrays.asList(1, 2));
		_instance.runTest(new int[][] { new int[] { 1 }, new int[] { 2 }, new int[] { 3 } }, Arrays.asList(1, 2, 3));
		_instance.runTest(new int[][] { new int[] { 1, 2 }  }, Arrays.asList(1, 2));
		_instance.runTest(new int[][] { new int[] { 1, 2, 3 }  }, Arrays.asList(1, 2, 3));
		_instance.runTest(new int[][] { new int[] { 1, 2, 3 }, 
										new int[] { 4, 5, 6 }, 
										new int[] { 7, 8, 9 } }, Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5));	
	}

	@SuppressWarnings("unchecked")
	public void runTest(final int[][] matrix, final List<Integer> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { matrix });

		for (Object answer : answers)
			assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
