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
 *	b	1	1	0	0	0	0									
 *	c	1	1	1	0	0	0   <= this means we have longest subsequence of 1 length, which is true as every char is unique and is palindrome of length 1. 									
 *	e	1	1	1	1	0	0									
 *	b	3	3	1	1	1	0									
 *	a	5	3	1	1	1	1
 *
 *		a	b	c	c	b	a	
 *	a	1	0	0	0	0	0	
 *	b	1	1	0	0	0	0	
 *	c	1	1	1	0	0	0	
 *	c	2	2	2	1	0	0	
 *	b	4	4	2	1	1	0	
 *	a	6	4	2	1	1	1	
 *
 *		b	b	b	a	b										
 *	b	1	0	0	0	0										
 *	b	2	1	0	0	0										
 *	b	3	2	1	0	0										
 *	a	3	2	1	1	0										
 *	b	4	3	3	1	1
 *						
 * Diagonal curving, propagating information right to left (column wise, so that next hit has the information of latest updates)
 * this wont effect the regular left to right as we are curving diagonally however, next hit will happen earlier (row > col) than last hit,
 * this suggest that we need that information earlier, hence right to left information transfer.
 *  				
 * @author Hxkandwal
 */
public class LongestPalindromicSubsequence extends AbstractCustomTestRunner {
	
	private static LongestPalindromicSubsequence _instance = new LongestPalindromicSubsequence();
	
	private LongestPalindromicSubsequence() {}
	
	// when curving inwards, throw information towards extreme left so that when we find an extension we can determine correct length (try bbbab and agbdba)
	// information propagation is extremely important.
	public static int _longestPalindromeSubsequence (String s) {
        int [][] dp = new int [s.length() + 1][s.length() + 1];
        
        int maxLength = 1;
        for (int row = 0; row < s.length(); row ++) {
        	dp [row + 1][row + 1] = 1;
        	
			for (int col = row - 1; col >= 0; col --) {
				if (s.charAt(row) == s.charAt(col))
					dp [row + 1][col + 1] = dp [row][col + 2] + 2;
				else 
					dp [row + 1][col + 1] = Math.max (dp [row][col + 1], dp [row + 1][col + 2]);
					
				maxLength = Math.max (maxLength, dp [row + 1][col + 1]);
			}
		}
        
		return maxLength;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("agbdba", 5);
		_instance.runTest("bbbab", 4);
		_instance.runTest("cbbd", 2);
		_instance.runTest("abceba", 5);
		_instance.runTest("abccba", 6);
		_instance.runTest("abcdcba", 7);
		_instance.runTest("abcdecba", 7);
		_instance.runTest("abcdecbafghigf", 7);
		_instance.runTest("abcdecbafghigfttttuvwxyzzzzyxwvu", 14);
		_instance.runTest("tuvwxyzzzzyxwvuttttabcdecbafghigf", 16);	
	}
	
	public void runTest(final String s, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });
		
		for (Object answer : answers) 
			assertThat((int) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
