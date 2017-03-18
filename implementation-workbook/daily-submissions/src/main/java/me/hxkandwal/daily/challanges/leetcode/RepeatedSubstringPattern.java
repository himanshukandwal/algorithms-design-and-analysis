package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 459. Repeated Substring Pattern
 * 
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring 
 * together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
 * 
 * Example 1:
 * 		Input: "abab"
 * 		Output: True
 * 		Explanation: It's the substring "ab" twice.
 * 
 * Example 2:
 * 		Input: "aba"
 * 		Output: False
 * 
 * Example 3:
 * 		Input: "abcabcabcabc"
 * 		Output: True
 * 		Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 * 
 * @author Hxkandwal
 */
public class RepeatedSubstringPattern extends AbstractCustomTestRunner {
	
	private static RepeatedSubstringPattern _instance = new RepeatedSubstringPattern();

    public boolean _repeatedSubstringPattern(String s) {
        int [] pfx = new int [s.length()];
        for (int fdx = 0, idx = 1; idx < s.length(); idx ++) {
        	while (fdx > 0 && s.charAt(fdx) != s.charAt(idx)) fdx = pfx [fdx - 1]; 
            pfx [idx] = (s.charAt(fdx) == s.charAt(idx)) ? fdx ++ + 1 : 0;
        }
        int len = pfx [s.length() - 1];
        return len > 0 && (s.length() % (s.length() - len) == 0);
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("abab", true);
		_instance.runTest("aba", false);
		_instance.runTest("a", false);
		_instance.runTest("abaababaab", true);
		_instance.runTest("aaaabaaaab", true);
	}
	
	public void runTest(final String s, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });
		
		for (Object answer : answers) 
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}    
    
}
