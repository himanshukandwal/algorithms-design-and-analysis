package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

/**
 * 76. Minimum Window Substring
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
	
	// idea : https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems
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

	public String _minWindowOther(String s, String t) {
		Map<Character, Integer> a = new HashMap<>();
		for (char c : t.toCharArray()) a.put (c, a.getOrDefault(c, 0) + 1);

		String min = null;
		Map<Character, Integer> b = new HashMap<>();
		LinkedList<Integer> locs = new LinkedList<>();

		for (int idx = 0; idx < s.length(); idx ++) {
			char c = s.charAt(idx);

			if (a.containsKey(c)) {
				locs.add (idx);
				b.put (c, b.getOrDefault (c, 0) + 1);
			}

			while (compare (a, b)) {
				int fidx = locs.removeFirst();
				if (min == null || min.length() > (idx - fidx + 1)) min = s.substring(fidx, idx + 1);

				b.put (s.charAt(fidx), b.get (s.charAt(fidx)) - 1);
			}
		}
		return min == null ? "" : min;
	}

	private boolean compare (Map<Character, Integer> a, Map<Character, Integer> b) {
		for (Character k : a.keySet()) if (!b.containsKey(k) || b.get(k) < a.get (k)) return false;
		return true;
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
