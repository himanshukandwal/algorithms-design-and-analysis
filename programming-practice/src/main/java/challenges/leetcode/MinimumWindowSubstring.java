package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 76.Minimum Window Substring
 * 
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * 
 * For example,
 * 		S = "ADOBECODEBANC" T = "ABC"
 * 		Minimum window is "BANC".
 * 
 * @author Hxkandwal
 */
public class MinimumWindowSubstring extends AbstractCustomTestRunner {
	
	private static MinimumWindowSubstring _instance = new MinimumWindowSubstring();
	
	public String _minWindow(String s, String t) {
		int [] map = new int [256];
        for (char ch : t.toCharArray()) map [ch] ++;
        
        int start = 0, end = 0, d = Integer.MAX_VALUE, counter = 0, head = 0;
        while (end < s.length ()) {
            if (map [s.charAt (end ++)] -- > 0) counter ++;
            while (counter == t.length()) {
                if (end - start < d) d = end - (head = start);
                if (map [s.charAt (start ++)] ++ == 0) counter --;
            }
        }
        return (d == Integer.MAX_VALUE) ? "" : s.substring (head, head + d);
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("babb", "baba", "");
		_instance.runTest("ab", "a", "a");
		_instance.runTest("zqyvbfeiee", "ze", "zqyvbfe");
		_instance.runTest("adobecodebanc", "abc", "banc");
	}

	public void runTest(final String s, final String t, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, t });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	} 

}
