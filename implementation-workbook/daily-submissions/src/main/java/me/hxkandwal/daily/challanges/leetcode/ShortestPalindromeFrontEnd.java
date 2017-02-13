package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Shortest Palindrome
 * 
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome 
 * you can find by performing this transformation.
 * 
 * For example:
 * 		Given "aacecaaa", 
 * 		return "aaacecaaa".
 * 
 * 		Given "abcd", 
 * 		return "dcbabcd".
 * 
 * @author Hxkandwal
 *
 */
public class ShortestPalindromeFrontEnd extends AbstractCustomTestRunner {
	
	private static ShortestPalindromeFrontEnd _instance = new ShortestPalindromeFrontEnd();
	
	private ShortestPalindromeFrontEnd() {}

	public static String shortestPalindrome(String s) {
        int lastEndIndexFromStart = -1;
        
        int [][] dp = new int [s.length() + 1][s.length() + 1]; 
        for (int row = 0; row < s.length(); row ++) {
        	for (int col = row; col >= 0; col --) {
				if (s.charAt(row) == s.charAt(col)) 
					dp [row + 1][col + 1] = (row == col) ? 1 : (row - col == 1 || dp [row][col + 2] > 0) ? dp [row][col + 2] + 2 : 0;
				
				if (col == 0) lastEndIndexFromStart = Math.max (lastEndIndexFromStart, dp [row + 1][col + 1] - 1);
			}
        }
        
        if (lastEndIndexFromStart + 1 == s.length()) return s;
        
        StringBuilder answer = new StringBuilder(s);
        if (lastEndIndexFromStart > 0)
        	for (int idx = lastEndIndexFromStart + 1; idx < s.length(); idx ++) answer.insert(0, s.charAt(idx));
        else 
        	for (int idx = 1; idx < s.length(); idx ++) answer.insert(0, s.charAt(idx));
        
		return answer.toString();
    }

	public static String _shortestPalindromeOptimized(String s) {
        int lastEndIndexFromStart = -1;
        
        List<Integer> dp = new ArrayList<>(s.length());
        List<Integer> dpNext = new ArrayList<>(s.length());
        
        for (int row = 0; row < s.length(); row ++) {
        	for (int col = row; col >= 0; col --) {
				if (s.charAt(row) == s.charAt(col)) 
					dpNext.add((row == col) ? 1 : (row - col == 1 || dp.size() <= col + 2) ? (dp.size() <= col + 2 ? dp.get(col + 1) : 0) + 2 : 0);
				
				if (col == 0) lastEndIndexFromStart = Math.max (lastEndIndexFromStart, dpNext.get(col) - 1);
			}
        	
        	dp.clear(); dp.addAll(dpNext); dpNext.clear();
        }
        
        if (lastEndIndexFromStart + 1 == s.length()) return s;
        
        StringBuilder answer = new StringBuilder(s);
        if (lastEndIndexFromStart > 0)
        	for (int idx = lastEndIndexFromStart + 1; idx < s.length(); idx ++) answer.insert(0, s.charAt(idx));
        else 
        	for (int idx = 1; idx < s.length(); idx ++) answer.insert(0, s.charAt(idx));
        
		return answer.toString();
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("aacecaaa", "aaacecaaa");
		_instance.runTest("abcd", "dcbabcd");	
		_instance.runTest("abb", "bbabb");	
	}
	
	public void runTest(final String s, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });
		
		for (Object answer : answers) 
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
		
}
