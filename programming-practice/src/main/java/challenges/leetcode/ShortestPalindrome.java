package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 214. Shortest Palindrome
 * 
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest 
 * palindrome you can find by performing this transformation.
 * 
 * For example:
 * 
 * 		Given "aacecaaa", return "aaacecaaa".
 * 		
 * 		Given "abcd", return "dcbabcd".
 * 
 * @author Hxkandwal
 */
public class ShortestPalindrome extends AbstractCustomTestRunner {
	
	private static ShortestPalindrome _instance = new ShortestPalindrome();

	public String _shortestPalindrome(String s) {
        if (s.length() == 0) return s;
        String ans = s;
        for (int idx = s.length()/2; idx >= 0; idx --) {
            int j = idx, k = idx + 1;
            while (j >= 0 && k < s.length () && s.charAt (j) == s.charAt (k)) { j --; k ++; }
            if (j < 0 && s.length() - k < ans.length()) ans = s.substring (k);
            j = idx - 1; k = idx + 1;
            while (j >= 0 && k < s.length () && s.charAt (j) == s.charAt (k)) { j --; k ++; }
            if (j < 0 && s.length() - k < ans.length()) ans = s.substring (k);
        }
        StringBuilder sb = new StringBuilder (ans);
        return sb.reverse().toString() + s;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("abcd", "dcbabcd");
		_instance.runTest("aacecaaa", "aaacecaaa");
	}
	
	public void runTest(final String s, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });
		
		for (Object answer : answers) 
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
	
}
