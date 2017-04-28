package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 562. Longest Line of Consecutive One in Matrix
 * 
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, 
 * diagonal or anti-diagonal.
 * 
 * Example:
 * 	Input:
 * 		[[0,1,1,0],
 * 		 [0,1,1,0],
 * 		 [0,0,0,1]]
 * 
 * 	Output: 3
 * 
 * Hint: The number of elements in the given matrix will not exceed 10,000.
 * 
 * @author Hxkandwal
 */
public class LongestLineOfConsecutiveOneInMatrix extends AbstractCustomTestRunner {
	
	private static LongestLineOfConsecutiveOneInMatrix _instance = new LongestLineOfConsecutiveOneInMatrix();
	
	int [] rdir = new int [] { 0, 1, 1, -1 };
    int [] cdir = new int [] { 1, 1, 0, -1 };
    
    public int longestLine(int[][] M) {
        if (M.length == 0) return 0;
        int max = 0;
        
        int [][][] dp = new int [M.length][M [0].length][4];
        for (int row = 0; row < M.length; row ++) 
            for (int col = 0; col < M [0].length; col ++)  
                Arrays.fill (dp [row][col], -1);
                
        for (int row = 0; row < M.length; row ++)
            for (int col = 0; col < M [0].length; col ++)
                if (M [row][col] == 1)
                    for (int idx = 0; idx < 4; idx ++) max = Math.max (max, dfs (M, dp, row, col, idx));
        return max;
    }
    
    private int dfs (int [][] M, int [][][] dp, int row, int col, int idx) {
        if (row < 0 || row >= M.length || col < 0 || col >= M [0].length || M [row][col] != 1) return 0;
        if (dp [row][col][idx] != -1) return dp [row][col][idx];
        int count = 1;
        count += dfs (M, dp, row + rdir [idx], col + cdir [idx], idx);
        return dp [row][col][idx] = count;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(3, Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()"));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final int number, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { number });

		for (Object answer : answers)
			assertThat((List<String>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
