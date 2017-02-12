package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 516. Longest Palindromic Subsequence
 * 
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 * 
 * Example 1:
 * 		Input: "bbbab"
 * 		Output: 4
 * 		
 * 		One possible longest palindromic subsequence is "bbbb".
 * 
 * Example 2:
 * 		Input: "cbbd"
 * 		Output: 2
 * 
 * 		One possible longest palindromic subsequence is "bb".
 * 
 * Use case studies:
 * 
 * 		a	b	c	e	b	a									
 *	a	1	0	0	0	0	0									
 *	b	0	1	0	0	0	0									
 *	c	0	0	1	0	0	0									
 *	e	0	0	0	1	0	0									
 *	b	0	3	0	0	1	0									
 *	a	5	0	0	0	0	1
 *
 *		a	b	c	c	b	a	
 *	a	1	0	0	0	0	0	
 *	b	0	1	0	0	0	0	
 *	c	0	0	1	0	0	0	
 *	c	0	0	2	1	0	0	
 *	b	0	4	0	0	1	0	
 *	a	6	0	0	0	0	1	
 *
 *		b	b	b	a	b										
 *	b	1	0	0	0	0										
 *	b	2	1	0	0	0										
 *	b	3	2	1	0	0										
 *	a	3	2	0	1	0										
 *	b	4	3	3	0	1
 *										
 * @author Hxkandwal
 */
public class LongestPalindromicSubsequence extends AbstractCustomTestRunner {
	
	private static LongestPalindromicSubsequence _instance = new LongestPalindromicSubsequence();
	
	private LongestPalindromicSubsequence() {}
	
	public static int _longestPalindromeSubseq(String s) {
        int [][] dp = new int [s.length() + 1][s.length() + 1];
        
        int maxLength = 0;
        for (int row = 0; row < s.length(); row ++) {
			for (int col = 0; col <= row; col ++) {
				if (s.charAt(row) == s.charAt(col)) {
					dp [row + 1][col + 1] = (row == col) ? 1 : (dp [row][col + 2] > 0) ? dp [row][col + 2] + 2 : (row - col > 1 ? 3 : 2);
				} else 
					dp [row + 1][col + 1] = (row != col) ? dp [row][col + 1] : 0;
					
				maxLength = Math.max(maxLength, dp [row + 1][col + 1]);
			}
		}
        
		return maxLength;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("bbbab", 4);
		_instance.runTest("cbbd", 2);
		_instance.runTest("abceba", 5);
		_instance.runTest("abccba", 6);
		_instance.runTest("abcdcba", 7);
		_instance.runTest("abcdecba", 7);
	}
	
	public void runTest(final String s, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });
		
		for (Object answer : answers) 
			assertThat((int) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
