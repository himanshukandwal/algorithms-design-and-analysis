package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Given two sequences, the shared subsequence is a sequence that is a subsequence of both sequences.
 * 
 * A maximum-size shared subsequence is a shared subsequence of maximum size.
 * 
 * For example, the maximum-size shared subsequence of (3,2,8,2,3,9,4,3,9) and (1,3,2,3,7,9) is (3,2,3,9).
 * 
 * link: http://www.cs.ucr.edu/~neal/2004/cs141/index.cgi?ClassS04CS141/Hwk4
 * 
 * @author Hxkandwal
 *
 */
public class MaximumSharedSubsequence extends AbstractCustomTestRunner {
	
	private static MaximumSharedSubsequence _instance = new MaximumSharedSubsequence();
	
	public MaximumSharedSubsequence() {}

	public static int[] _getMaximalSharedSubsequence(int[] a, int[] b) {
		int[][] dp = new int[a.length + 1][b.length + 1];
		
		// dp build up.
		for (int row = 0; row < a.length; row ++)
			for (int col = 0; col < b.length; col ++)
				if (a [row] == b [col] && dp [row + 1][col] == dp [row][col + 1])
					dp [row + 1][col + 1] = Math.max(dp [row + 1][col], dp [row][col + 1]) + 1;
				else
					dp [row + 1][col + 1] = Math.max(dp [row + 1][col], dp [row][col + 1]);
		
		// result is stored at the very corner of the matrix
		int idx = dp[a.length][b.length] - 1, row = a.length, col = b.length;
		int[] result = new int [idx + 1];
		
		// result retrieval
		while (idx >= 0) {
			if (dp [row][col] > dp [row - 1][col] && dp [row][col] > dp [row][col - 1])
				result [idx --] = a [row -- - 1];
			else {
				while (dp [row][col] == dp [row][col - 1])
					col --;
				
				while (dp [row][col] == dp [row - 1][col])
					row --;
			}	
		}
		
		return result;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 3, 2, 8, 2, 3, 9, 4, 3, 9 }, new int[] { 1, 3, 2, 3, 7, 9 }, new int[] { 3, 2, 3, 9 });
	}

	public void runTest(final int [] a, final int [] b, final int [] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a, b });

		for (Object answer : answers)
			assertThat((int []) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
}
