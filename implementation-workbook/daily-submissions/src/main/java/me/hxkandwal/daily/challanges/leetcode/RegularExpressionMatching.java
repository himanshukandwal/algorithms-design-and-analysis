package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 10. Regular Expression Matching
 * 
 * Implement regular expression matching with support for '.' and '*'.
 * 
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * Some examples:
 * 
 * 			isMatch("aa","a") = false
 * 			isMatch("aa","aa") = true
 * 			isMatch("aaa","aa") = false
 * 			isMatch("aa", "a*") = true
 * 			isMatch("aa", ".*") = true
 * 			isMatch("ab", ".*") = true
 * 			isMatch("aab", "c*a*b") = true
 * 
 * @author Hxkandwal
 *
 */
public class RegularExpressionMatching extends AbstractCustomTestRunner {
	
	private static RegularExpressionMatching _instance = new RegularExpressionMatching();
	
	public RegularExpressionMatching() {}
	
	public static boolean _isMatch(String s, String p) {
		return isMatchInner(s, 0, p, 0);
    }
	
	private static boolean isMatchInner(String s, int sIdx, String p, int pIdx) {
		if (sIdx >= s.length() && ((pIdx == p.length() - 1 && p.charAt(pIdx) == '*') || (pIdx >= p.length())))
				return true;
		
		if ((sIdx >= s.length() && pIdx < p.length()) || (pIdx >= p.length() && sIdx < s.length()))
			return false;
		
		if (p.charAt(pIdx) == '.' || p.charAt(pIdx) == s.charAt(sIdx))
			return isMatchInner(s, sIdx + 1, p, pIdx + 1);
		else if (p.charAt(pIdx) == '*')
			return isMatchInner(s, sIdx + 1, p, pIdx) || isMatchInner(s, sIdx + 1, p, pIdx + 1) || isMatchInner(s, sIdx, p, pIdx + 1); // for zero or more occurences
		else
			return isMatchInner(s, sIdx, p, pIdx + 1);
	}
	
	// driver method
    public static void main(String[] args) throws FileNotFoundException {
    	_instance.runTest("aa", "a", false);
    	_instance.runTest("aa","aa", true);
		_instance.runTest("aaa", "aa", false);
		_instance.runTest("aaaaaaaaaaaaaaa", "a*", true);
		_instance.runTest("aaaaaaaaaaaaaaabbbbbbb", "a*b*", true);
		_instance.runTest("aaaaaaaaaaaaaaacbbbbbbb", "a*cb*", true);
		_instance.runTest("aa", "a*", true);
		_instance.runTest("aa", "ab", false);
		_instance.runTest("aa", ".*", true);
		_instance.runTest("ab", ".*", true);
		_instance.runTest("aab", "c*a*b", true);
		_instance.runTest("abcd", "d*", true);
    }

	public void runTest(final String s, final String t, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, t });
		
		for (Object answer : answers) 
			assertThat((boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
