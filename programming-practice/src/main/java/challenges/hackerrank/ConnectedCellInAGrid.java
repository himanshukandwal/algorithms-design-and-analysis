package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Connected Cell in a Grid
 * 
 * Sample Input:
 * 	4
 * 	4
 * 	1 1 0 0
 * 	0 1 1 0
 * 	0 0 1 0
 * 	1 0 0 0
 * 
 * Sample Output: 5
 * 
 * @author Hxkandwal
 */
public class ConnectedCellInAGrid extends AbstractCustomTestRunner {
	
	private static ConnectedCellInAGrid _instance = new ConnectedCellInAGrid();
	
	public static int _getBiggestRegion(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int row = 0; row < matrix.length; row ++)
            for (int col = 0; col < matrix [0].length; col ++)
                if (matrix [row][col] == 1)
                    max = Math.max (max, dfsMatrix (matrix, row, col)); 
        return max;
    }
    
    static int [] dir = new int [] { 0, 1, 0, -1, 0 };
    
    private static int dfsMatrix (int [][] matrix, int row, int col) {
        matrix [row][col] = 0;
        int count = 1;
        for (int d = 0; d < dir.length - 1; d ++) {
            if (row + dir [d] >= 0 && row + dir [d] < matrix.length && col + dir [d + 1] >= 0 
            		&& col + dir [d + 1] < matrix [0].length && matrix[row + dir [d]][col + dir [d + 1]] == 1)
                count += dfsMatrix (matrix, row + dir [d], col + dir [d + 1]);
        }
        return count;
    }
	
	// driver method
    public static void main(String[] args) throws FileNotFoundException {
		_instance.runTest(new int[][] { new int[] { 1, 1, 0, 0 }, new int[] { 0, 1, 1, 0 }, 
										new int[] { 0, 0, 1, 0 }, new int[] { 1, 0, 0, 0 } }, 5);
    }

	public void runTest(final int[][] matrix, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { matrix });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
