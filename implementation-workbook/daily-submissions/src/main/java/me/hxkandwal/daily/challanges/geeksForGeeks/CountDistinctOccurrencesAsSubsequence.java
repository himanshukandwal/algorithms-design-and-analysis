package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Count distinct occurrences as a subsequence
 * 
 * Given a two strings S and T, find count of distinct occurrences of T in S as a subsequence.
 * 
 * Examples:
 * 
 * a)		Input  : S = banana, T = ban
 * 			Output : 3
 * 			
 * 			T appears in S as below three subsequences.
 * 			[ban], [ba  n], [b   an]
 * 
 * b) 		Input  : S = geeksforgeeks, T = ge
 * 			Output : 6
 * 			
 * 			T appears in S as below three subsequences.
 * 			[ge], [     ge], [g e], [g    e] [g     e] and [     g e] 
 * 
 * link: http://www.geeksforgeeks.org/count-distinct-occurrences-as-a-subsequence/
 * 
 * @author Hxkandwal
 *
 */
public class CountDistinctOccurrencesAsSubsequence extends AbstractCustomTestRunner {
	
	private static CountDistinctOccurrencesAsSubsequence _instance = new CountDistinctOccurrencesAsSubsequence();
	
	private CountDistinctOccurrencesAsSubsequence() {}

	// practise to visualtize it.
	public static int _findSubsequenceCount(String input, String pattern) {
		int[][] dp = new int [pattern.length()][input.length()];
		
		for (int row = 0; row < pattern.length(); row++) {
			char ch = input.charAt(row);

			// starting from row as before that, all has to be zero, as pattern of length (row + 1) cannot be present in input of length (< row + 1)
			for (int col = row; col < input.length(); col++) {
				if (ch == input.charAt(col))
					dp[row][col] = (col == 0 && row == 0) ? 1 : ((row == 0) ? dp [row][col - 1] + 1 : dp[row - 1][col  - 1] + dp [row][col -1]);
				else
					dp[row][col] = (col - 1 < 0) ? 0 : dp[row][col - 1];
			}
		}
		
		return dp [pattern.length() - 1][input.length() - 1];
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("banana", "ban", 3);
		_instance.runTest("geeksforgeeks", "ge", 6);
		_instance.runTest("wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco", "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxp", 4);
	}

	public void runTest(final String input, final String pattern, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input, pattern });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
