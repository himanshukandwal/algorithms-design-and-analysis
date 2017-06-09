package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 392. Is Subsequence
 * 
 * Given a string s and a string t, check if s is subsequence of t.
 * You may assume that there is only lower case English letters in both s and t. t is potentially a very 
 * long (length ~= 500,000) string, and s is a short string (<=100).
 * 
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) 
 * of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence 
 * of "abcde" while "aec" is not).
 * 
 * Example 1:
 * 		s = "abc", t = "ahbgdc"
 * 		Return true.
 * 
 * Example 2:
 * 		s = "axc", t = "ahbgdc"
 * 		Return false.
 * 
 * Follow up:
 * 		If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T 
 * 		has its subsequence. In this scenario, how would you change your code?
 * 
 * @author Hxkandwal
 */
public class IsSubsequence extends AbstractCustomTestRunner {
	
	private static IsSubsequence _instance = new IsSubsequence();

	public boolean _isSubsequence(String s, String t) {
		int sidx = 0;
        for (int idx = 0; idx < t.length() && sidx < s.length(); idx ++)
            if (s.charAt(sidx) == t.charAt (idx)) sidx ++;
        return sidx == s.length();
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("abc", "ahbgdc", true);
		_instance.runTest("axc", "ahbgdc", false);
	}

	public void runTest(final String s, final String t, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, t });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
		
}
