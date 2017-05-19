package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

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
    	int [] pi = new int [s.length()];
        for (int i = 0, j = 1; j < s.length(); j ++) {
            while (i > 0 && s.charAt (i) != s.charAt (j)) i = pi [i - 1];
            if (s.charAt (i) == s.charAt (j)) pi [j] = ++ i;
        }
        return pi [pi.length - 1] > 0 && s.length () % (s.length() - pi [pi.length - 1]) == 0;
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
