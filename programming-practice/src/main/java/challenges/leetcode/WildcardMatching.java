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

	public boolean _isMatch(String s, String p) {
		int pidx = 0, sidx = 0;
        while (pidx < p.length() && sidx < s.length()) {
            char pch = p.charAt (pidx), sch = s.charAt (sidx);
            if (pch == '?' || pch == sch) pidx ++;
            else if (pch == '*') {
            	if (_isMatch (s.substring (sidx), p.substring (pidx + 1))) return true;
            } else return false;
            sidx ++;
        }
        if (pidx < p.length() && !p.substring(pidx).matches("\\**")) return false;
        return sidx == s.length ();
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("aa", "aa", true);
		_instance.runTest("aa", "*", true);
		_instance.runTest("aaa", "aa", false);
		_instance.runTest("ab", "?*", true);
		_instance.runTest("ho", "ho?*", false);
		_instance.runTest("ho", "ho**", true);
		_instance.runTest("abefcdgiescdfimde", "ab*cd?i*de", true);
		_instance.runTest("babaaababaabababbbbbbaabaabbabababbaababbaaabbbaaab","***bba**a*bbba**aab**b", false);
	}

	public void runTest(final String s, final String p, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, p });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	} 
	
}
