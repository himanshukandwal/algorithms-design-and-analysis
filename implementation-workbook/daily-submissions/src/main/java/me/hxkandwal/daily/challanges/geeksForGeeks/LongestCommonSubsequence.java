package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Longest Common Subsequence
 * 
 * Given two sequences, find the length of longest subsequence present in both of them. A subsequence is a sequence that appears in the same relative order,
 * but not necessarily contiguous. For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”. So a string of length n has 2^n 
 * different possible subsequences.
 * 
 * It is a classic computer science problem, the basis of diff (a file comparison program that outputs the differences between two files), and has applications 
 * in bio-informatics.
 * 
 * Examples:
 * 		LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
 * 		LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
 * 
 * link: http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
 * 
 * @author Hxkandwal
 */
public class LongestCommonSubsequence extends AbstractCustomTestRunner {
	
	private static LongestCommonSubsequence _instance = new LongestCommonSubsequence();
	
	private LongestCommonSubsequence() {}

	public static int _longestCommonSubsequence(String a, String b) {
		if (a.length() == 0 || b.length() == 0)
			return 0;
		
		int [][] dp = new int [b.length() + 1][a.length() + 1];
		int maxLength = 0;
		
		for (int row = 0; row < b.length(); row ++) {
			for (int col = 0; col < a.length(); col ++)
				if (a.charAt(col) == b.charAt(row))
					maxLength = Math.max(maxLength, dp [row + 1][col + 1] = dp [row][col] + 1);
				else 
					dp [row + 1][col + 1] = Math.max(dp [row + 1][col], dp [row][col + 1]);
		}
		
		return maxLength;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("AGGTAB", "GXTXAYB", 4);		
		_instance.runTest("ABCDGH", "AEDFHR", 3);		
		_instance.runTest("AGGTAB", "GXTXAYB", 4);		
	}

	public void runTest(final String a, final String b, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a, b });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}
