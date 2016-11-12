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
		_instance.runTest(new int[] { 10, 3, 2, 8, 2, 3, 9, 4, 3, 9 }, new int[] { 10, 1, 3, 2, 3, 7, 9 }, new int[] { 10, 3, 2, 3, 9 });
		_instance.runTest(new int[] { 1, 3, 2, 8, 2, 3, 9, 4, 3, 9 }, new int[] { 10, 1, 3, 2, 3, 7, 9 }, new int[] { 1, 3, 2, 3, 9 });
		_instance.runTest(
				new int[] { 48, 29, 25, 7, 21, 32, 32, 13, 38, 16, 13, 29, 8, 28, 0, 21, 11, 27, 17, 44, 28, 10, 49, 23, 20, 33, 35, 40, 4, 15, 
						40, 34, 23, 40, 3, 39, 26, 45, 16, 23, 22, 39, 25, 32, 2, 34, 3, 46, 16, 19, 4, 25, 36, 14, 37, 30, 34, 49, 5, 9, 32, 19, 
						19, 6, 33, 9, 28, 32, 1, 29, 41, 42, 11, 12, 31, 13, 33, 5, 31, 6, 35, 10, 27, 36, 45, 48, 38, 5, 27, 21, 34, 23, 11, 20, 
						22, 25, 11, 44, 3, 32 }, 
				new int[] { 33, 31, 9, 41, 49, 35, 12, 3, 43, 2, 47, 43, 11, 29, 11, 24, 4, 15, 28, 48, 3, 28, 9, 20, 10, 0, 1, 26, 35, 37, 48, 
						26, 32, 8, 14, 48, 9, 45, 16, 27, 13, 21, 6, 28, 36, 1, 16, 4, 41, 33, 49, 36, 20, 44, 46, 26, 36, 42, 22, 29, 29, 24, 
						30, 3, 20, 42, 3, 36, 14, 1, 44, 26, 35, 9, 47, 32, 43, 47, 29, 45, 36, 20, 0, 48, 10, 18, 40, 20, 41, 42, 11, 5, 30, 
						32, 46, 20, 38, 9, 19, 24 }, 
				new int[] { 29, 32, 16, 13, 21, 28, 49, 20, 26, 22, 3, 36, 14, 9, 32, 29, 45, 48, 20, 11, 32 });
	}

	public void runTest(final int [] a, final int [] b, final int [] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a, b });

		for (Object answer : answers)
			assertThat((int []) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
}
