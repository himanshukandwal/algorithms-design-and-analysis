package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 395. Longest Substring with At Least K Repeating Characters
 * 
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that 
 * every character in T appears no less than k times.
 * 
 * Example 1:
 * 		Input: s = "aaabb", k = 3
 * 		Output: 3
 * 				The longest substring is "aaa", as 'a' is repeated 3 times.
 * 
 * Example 2:
 * 		Input: s = "ababbc", k = 2
 * 		Output: 5
 * 				The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 * 
 * @author Hxkandwal
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters extends AbstractCustomTestRunner {
	
	private static LongestSubstringWithAtLeastKRepeatingCharacters _instance = new LongestSubstringWithAtLeastKRepeatingCharacters();
	
	/**
	 * Concise python:
	 * def longestSubstring(self, s, k):
	 * 	for c in set(s):
	 * 		if s.count(c) < k:
	 * 			return max(self.longestSubstring(t, k) for t in s.split(c))
	 *  return len(s)
	 */
	public int _longestSubstring(String s, int k) {
		int [] map = new int [256];
        for (char ch : s.toCharArray()) map [ch] ++;
        for (int idx = 0, ans = 0; idx < s.length(); idx ++)
            if (map [s.charAt (idx)] < k) {
                for (String str : s.split (String.valueOf (s.charAt (idx)))) ans = Math.max (ans, _longestSubstring (str, k));
                return ans;
            }
        return s.length();
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("aaabb", 3, 3);
		_instance.runTest("ababbc", 2, 5);
		_instance.runTest("bbaaacbd", 3, 3);
	}

	public void runTest(final String s, final int k, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, k });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
