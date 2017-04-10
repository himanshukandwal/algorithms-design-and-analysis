package challenges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Number of paths with exactly k coins
 * 
 * Given a matrix where every cell has some number of coins. Count number of ways to reach bottom right 
 * from top left with exactly k coins. We can move to (i+1, j) and (i, j+1) from a cell (i, j).
 * 
 * Example:
 * 		Input:  k = 12
 * 		
 * 			mat[][] = { 
 * 						{ 1, 2, 3 },
 *                     	{ 4, 6, 5 },
 *                     	{ 3, 2, 1 }
 *                    };
 *                    
 *      Output:  2
 *      There are two paths with 12 coins
 *      	
 *      1 -> 2 -> 6 -> 2 -> 1
 *      1 -> 2 -> 3 -> 5 -> 1
 * 
 * link : http://www.geeksforgeeks.org/number-of-paths-with-exactly-k-coins/
 * 
 * @author Hxkandwal
 *
 */
public class NumberOfPathsWithExactlykCoins extends AbstractCustomTestRunner {
	
	private static NumberOfPathsWithExactlykCoins _instance = new NumberOfPathsWithExactlykCoins();
	
	private NumberOfPathsWithExactlykCoins() {}

	/**
	 * this is a recursion tree, where every possibility will be evaluated, every node will be secludedly and repeatedly tested 
	 * under all the possible combinations possible and leaves with values 0, will be notified upwards.
	 */
	public static int _pathCount(int [][] matrix, int m, int n, int k) {
		if (m == matrix.length - 1 && n == matrix[0].length - 1)
			return (matrix[m][n] == k) ? 1 : 0;
		
		else if (m == matrix.length - 1 || n == matrix[0].length - 1)
			return (m == matrix.length - 1) ? _pathCount(matrix, m, n + 1, k - matrix[m][n]) : _pathCount(matrix, m  + 1, n, k - matrix[m][n]); 
		
		else
		    return _pathCount(matrix, m + 1, n, k - matrix[m][n]) + _pathCount(matrix, m, n + 1, k - matrix[m][n]); 
	}
		
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[][] { { 1, 2, 3 },
										{ 4, 6, 5 },
										{ 3, 2, 1 }
									  }, 0, 0, 12, 2);		
	}

	public void runTest(final int [][] matrix, final int m, final int n, final int k, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { matrix, m, n, k });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
		
}
