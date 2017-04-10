package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

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
        	return new int[0];
        
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
	        if (row == rows) { row = rows - 1; col += 2; }
	        if (col == cols) { col = cols - 1; row += 2; }
	        row = (row < 0 ? 0 : row);
	        col = (col < 0 ? 0 : col);
	        
	        isTopDownFold = !isTopDownFold;
        }
        
		return ans;
    }
	
	public static int[] _findDiagonalOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[0];
        int m = matrix.length, n = matrix[0].length;
        
        int[] result = new int[m * n];
        int row = 0, col = 0, d = 1;

        for (int i = 0; i < m * n; i++) {
            result[i] = matrix[row][col];
            row -= d;
            col += d;
            
            if (row >= m) { row = m - 1; col += 2; d = -d;}
            if (col >= n) { col = n - 1; row += 2; d = -d;}
            if (row < 0)  { row = 0; d = -d;}
            if (col < 0)  { col = 0; d = -d;}
        }
        
        return result;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[][] {{ 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }}, new int [] { 1, 2, 4, 7, 5, 3, 6, 8, 9 });
		_instance.runTest(new int[][] {{ 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 }}, 
							new int [] { 1, 2, 5, 9, 6, 3, 4, 7, 10, 13, 14, 11, 8, 12, 15, 16 });
		_instance.runTest(new int[][] {{ 1, 2, 3 }}, new int [] { 1, 2, 3 });
		_instance.runTest(new int[][] {{ 1, 2 }, { 3, 4 }}, new int [] { 1, 2, 3, 4 });
		_instance.runTest(new int[][] {{ 1, 2 }, { 3, 4 }, { 5, 6 }}, new int [] { 1, 2, 3, 5, 4, 6 });
	}

	public void runTest(final int[][] matrix, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { matrix });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
	
}
