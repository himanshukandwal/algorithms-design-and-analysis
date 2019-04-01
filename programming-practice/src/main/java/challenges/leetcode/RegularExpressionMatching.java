package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.io.FileNotFoundException;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

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
 */
public class RegularExpressionMatching extends AbstractCustomTestRunner {
	
	private static RegularExpressionMatching _instance = new RegularExpressionMatching();

	public boolean _isMatch(String s, String p) {
		boolean [][] dp = new boolean [s.length() + 1][p.length() + 1];
		dp [s.length()][p.length()] = true;

		for (int c = p.length() - 2; c >= 0; c --)
			dp [s.length()][c] = (p.charAt(c + 1) == '*') && dp [s.length()][c + 2];

		for (int r = s.length() - 1; r >= 0; r --) {
			char rch = s.charAt(r);
			for (int c = p.length() - 1; c >= 0; c --) {
				char cch = p.charAt(c);

				if (c + 1 < p.length() && p.charAt(c + 1) == '*') {
					dp [r][c] = dp [r][c + 2] || ((rch == cch || cch == '.') && dp [r + 1][c]);
				}
				else dp [r][c] = (rch == cch || cch == '.') && dp [r + 1][c + 1];
			}
		}
		return dp [0][0];
	}

	public static boolean _isMatchOther(String s, String p) {
		if (p.length () == 0) return s.length() == 0;

		boolean [][] dp = new boolean [s.length() + 1][p.length() + 1];
		dp [0][0] = true;

		for (int col = 1; col < p.length(); col ++)
			dp [0][col + 1] = p.charAt (col) == '*' && dp [0][col - 1];

		for (int row = 0; row < s.length (); row ++) {
			char rch = s.charAt (row);
			for (int col = 0; col < p.length (); col ++) {
				char cch = p.charAt (col);

				if (cch == '*') {
					boolean case1 = dp [row + 1][col - 1];
					boolean case2 = (p.charAt (col - 1) == '.' || p.charAt (col - 1) == rch) && dp [row][col + 1];
					dp [row + 1][col + 1] = case1 || case2;

				} else dp [row + 1][col + 1] = (rch == cch || cch == '.') && dp [row][col];
			}
		}
		return dp [s.length()][p.length()];
	}
	
	// driver method
    public static void main(String[] args) throws FileNotFoundException {
		_instance.runTest("aa", "a*", false);
		_instance.runTest("abcd", "d*", false);
		_instance.runTest("aab", "c*a*b", true);
		_instance.runTest("aaa", "ab*a*c*a", true);
		_instance.runTest("aaaaaaaaaaaaaaacbbbbbbb", "a*cb*", true);
	}

	public void runTest(final String s, final String t, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, t });
		
		for (Object answer : answers) 
			assertThat((boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
