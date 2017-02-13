package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Shortest Palindrome
 * 
 * Given a string S, you are allowed to convert it to a palindrome by adding characters anywhere to it. Find and return the shortest palindrome 
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
public class ShortestPalindromeBothEnds extends AbstractCustomTestRunner {
	
	private static ShortestPalindromeBothEnds _instance = new ShortestPalindromeBothEnds();
	
	private ShortestPalindromeBothEnds() {}

	public static String _shortestPalindrome(String s) {
        int lastEndIndexFromStart = -1, firstStartIndexFromEnd = Integer.MAX_VALUE;
        
        int [][] dp = new int [s.length() + 1][s.length() + 1]; 
        for (int row = 0; row < s.length(); row ++) {
        	for (int col = row; col >= 0; col --) {
				if (s.charAt(row) == s.charAt(col)) 
					dp [row + 1][col + 1] = (row == col) ? 1 : (row - col == 1 || dp [row][col + 2] > 0) ? dp [row][col + 2] + 2 : 0;
				
				if (col == 0) lastEndIndexFromStart = Math.max (lastEndIndexFromStart, dp [row + 1][col + 1] - 1);
				if (col + dp [row + 1][col + 1] == s.length()) firstStartIndexFromEnd = Math.min(firstStartIndexFromEnd, col);
			}
        }
        
        if (lastEndIndexFromStart + 1 == s.length()) return s;
        
        StringBuilder answer = new StringBuilder(s);
        if (lastEndIndexFromStart > 0 || firstStartIndexFromEnd < s.length() - 1) {
        	if (lastEndIndexFromStart > 0 && firstStartIndexFromEnd < s.length() - 1) {
        		if (s.length() - lastEndIndexFromStart + 1 > firstStartIndexFromEnd)
        			for (int idx = firstStartIndexFromEnd - 1; idx >= 0; idx --) answer.append(s.charAt(idx));
        		else
        			for (int idx = lastEndIndexFromStart + 1; idx < s.length(); idx ++) answer.insert(0, s.charAt(idx));
        	} else if (lastEndIndexFromStart > 0)
        		for (int idx = lastEndIndexFromStart + 1; idx < s.length(); idx ++) answer.insert(0, s.charAt(idx));
        	else
        		for (int idx = firstStartIndexFromEnd - 1; idx >= 0; idx --) answer.append(s.charAt(idx));
        } else 
        	for (int idx = 1; idx < s.length(); idx ++) answer.insert(0, s.charAt(idx));
        
		return answer.toString();
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("aacecaaa", "aaacecaaa");
		_instance.runTest("abcd", "dcbabcd");	
		_instance.runTest("abb", "abba");	
	}
	
	public void runTest(final String s, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });
		
		for (Object answer : answers) 
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
		
}
