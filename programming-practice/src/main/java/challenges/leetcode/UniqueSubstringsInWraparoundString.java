package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 467. Unique Substrings in Wraparound String
 * 
 * Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this: 
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * 
 * Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s. In 
 * particular, your input is the string p and you need to output the number of different non-empty substrings of p in the 
 * string s.
 * 
 * Note: p consists of only lowercase English letters and the size of p might be over 10000.
 * 
 * Example 1:
 * 		Input: "a"	
 * 		Output: 1
 * 		Explanation: Only the substring "a" of string "a" is in the string s.
 * 
 * Example 2:
 * 		Input: "cac"
 * 		Output: 2
 * 		Explanation: There are two substrings "a", "c" of string "cac" in the string s.
 * 
 * Example 3:
 * 		Input: "zab"
 * 		Output: 6
 * 		Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.
 * 
 * @author Hxkandwal
 */
public class UniqueSubstringsInWraparoundString extends AbstractCustomTestRunner {
	
	private static UniqueSubstringsInWraparoundString _instance = new UniqueSubstringsInWraparoundString();

	public int _findSubstringInWraproundString(String p) {
		int [] map = new int [26];
        for (int idx = 0, count = 0; idx < p.length(); idx ++) {
            char ch = p.charAt (idx);
            if (idx == 0 || p.charAt (idx) - p.charAt (idx - 1) == 1 || p.charAt (idx - 1) - p.charAt (idx) == 25) count ++;
            else count = 1;
            map [ch - 'a'] = Math.max (map [ch - 'a'], count);
        }        
        int ans = 0;
        for (int val : map) ans += val;
        return ans;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("zab", 6);
	}

	public void runTest(final String p, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { p });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
}
