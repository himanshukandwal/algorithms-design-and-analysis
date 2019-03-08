package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 424. Longest Repeating Character Replacement
 *
 * Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times.
 * Find the length of a longest substring containing all repeating letters you can get after performing the above operations.
 *
 * Note: Both the string's length and k will not exceed 104.
 *
 * Example 1:
 *      Input: s = "ABAB", k = 2
 *      Output: 4
 *      Explanation: Replace the two 'A's with two 'B's or vice versa.
 *
 * Example 2:
 *      Input: s = "AABABBA", k = 1
 *      Output: 4
 *      Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 *                   The substring "BBBB" has the longest repeating letters, which is 4.
 *
 * @author Hxkandwal
 */
public class LongestRepeatingCharacterReplacement extends AbstractCustomTestRunner {

    // using sliding window technique from: [1004. Max Consecutive Ones III]
    public int characterReplacement(String s, int k) {
        int ans = 0;
        for (char c = 'A'; c <= 'Z'; c ++) {
            int start = 0, K = k;
            for (int idx = 0; idx < s.length(); idx ++) {
                if (s.charAt(idx) != c) K --;
                if (K < 0 && s.charAt(start ++) != c) K ++;
            }
            ans = Math.max(ans, s.length() - start);
        }
        return ans;
    }
}
