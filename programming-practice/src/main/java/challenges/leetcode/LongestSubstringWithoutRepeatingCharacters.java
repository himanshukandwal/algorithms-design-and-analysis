package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 3. Longest Substring Without Repeating Characters
 * 
 * Given a string, find the length of the longest substring without repeating characters.
 * 
 * Examples:
 * 		Given "abcabcbb", the answer is "abc", which the length is 3.
 * 		Given "bbbbb", the answer is "b", with the length of 1.
 * 		Given "pwwkew", the answer is "wke", with the length of 3. 
 * 
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * 
 * @author Hxkandwal
 *
 */
public class LongestSubstringWithoutRepeatingCharacters extends AbstractCustomTestRunner {
	
	private static LongestSubstringWithoutRepeatingCharacters _instance = new LongestSubstringWithoutRepeatingCharacters();
	
	// sliding window checking.
	public static int _lengthOfLongestSubstring(String s) {
		int [] map = new int [256];
        int max = 0, start = 0;
        for (int idx = 0; idx < s.length(); idx ++) {
            if (map [s.charAt (idx)] ++ > 0) {
                while (s.charAt (start) != s.charAt (idx)) map [s.charAt (start ++)] --;
                map [s.charAt (start ++) ] --;
            }
            max = Math.max (max, idx - start  + 1);
        }
        return max;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("abcabcbb", 3);	
		_instance.runTest("bbbbb", 1);
		_instance.runTest("pwwkew", 3);
		_instance.runTest("", 0);
		_instance.runTest("abba", 2);
		_instance.runTest("dvdf", 3);
		_instance.runTest("uqinntq", 4);
		_instance.runTest("tmmzuxt", 5);
	}
	
	public void runTest(final String input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}		

}
