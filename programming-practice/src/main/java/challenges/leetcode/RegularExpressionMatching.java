package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.List;

import challenges.AbstractCustomTestRunner;

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
	
	public RegularExpressionMatching() {}
	
	public static boolean _isMatch(String s, String p) {
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
        return dp [s.length ()][p.length()];
	}
	
	// driver method
    public static void main(String[] args) throws FileNotFoundException {
    	_instance.runTest("aa", "a*", true);
    	_instance.runTest("aa", "a", false);
    	_instance.runTest("aa","aa", true);
		_instance.runTest("aaa", "aa", false);
		_instance.runTest("aaaaaaaaaaaaaaa", "a*", true);
		_instance.runTest("aaaaaaaaaaaaaaabbbbbbb", "a*b*", true);
		_instance.runTest("aaaaaaaaaaaaaaacbbbbbbb", "a*cb*", true);
		_instance.runTest("aa", "ab", false);
		_instance.runTest("aa", ".*", true);
		_instance.runTest("ab", ".*", true);
		_instance.runTest("aab", "c*a*b", true);
		_instance.runTest("abcd", "d*", false);
    }

	public void runTest(final String s, final String t, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, t });
		
		for (Object answer : answers) 
			assertThat((boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
