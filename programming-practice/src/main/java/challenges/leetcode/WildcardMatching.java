package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 44. Wildcard Matching
 * 
 * Implement wildcard pattern matching with support for '?' and '*'.
 * 
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * 
 * The matching should cover the entire input string (not partial). The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * 
 * Some examples:
 * 	isMatch("aa","a") → false
 * 	isMatch("aa","aa") → true
 * 	isMatch("aaa","aa") → false
 * 	isMatch("aa", "*") → true
 * 	isMatch("aa", "a*") → true
 * 	isMatch("ab", "?*") → true
 * 	isMatch("aab", "c*a*b") → false
 * 
 * @author Hxkandwal
 */
public class WildcardMatching extends AbstractCustomTestRunner {
	
	private static WildcardMatching _instance = new WildcardMatching();

	// grouping with starts or the specific char index
	public boolean _isMatch(String s, String p) {
		int [][] dp = new int [p.length() + 1][s.length() + 1];
        for (int row = 0; row < p.length(); row ++) {
            char pch = p.charAt (row);
            for (int col = 0; col < s.length(); col ++) {
                char sch = s.charAt (col);    
                
                if (pch == '*')
                    dp [row + 1][col + 1] = (dp [row][col + 1] == row || dp [row][col] == row) ? row : -1;
                else
                    dp [row + 1][col + 1] = (dp [row][col] == row) ? row : -1;
            }
        }
        return dp [p.length()][s.length()] == p.length() -1;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("aa", "aa", true);
		_instance.runTest("aa", "*", true);
		_instance.runTest("aaa", "aa", false);
		_instance.runTest("ab", "?*", true);
		_instance.runTest("ho", "ho**", true);
		_instance.runTest("abefcdgiescdfimde", "ab*cd?i*de", true);
		_instance.runTest("babaaababaabababbbbbbaabaabbabababbaababbaaabbbaaab","***bba**a*bbba**aab**b", false);
		_instance.runTest("c","*?*", true);
		_instance.runTest("ab","*ab", true);
		_instance.runTest("ddcab","*dd*b", true);
	}

	public void runTest(final String s, final String p, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, p });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	} 
	
}
