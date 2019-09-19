package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 1180. Count Substrings with Only One Distinct Letter
 *
 * Given a string S, return the number of substrings that have only one distinct letter.
 *
 * Example 1:
 *      Input: S = "aaaba"
 *      Output: 8
 *      Explanation: The substrings with one distinct letter are "aaa", "aa", "a", "b".
 *                   "aaa" occurs 1 time.
 *                   "aa" occurs 2 times.
 *                   "a" occurs 4 times.
 *                   "b" occurs 1 time.
 *                   So the answer is 1 + 2 + 4 + 1 = 8.
 *
 * Example 2:
 *      Input: S = "aaaaaaaaaa"
 *      Output: 55
 *
 * Constraints:
 *  1 <= S.length <= 1000
 *  S[i] consists of only lowercase English letters.
 *
 * @author Hxkandwal
 */
public class CountSubstringsWithOnlyOneDistinctLetter extends AbstractCustomTestRunner {

    public int _countLetters(String S) {
        int ans = 0, repeat = 1;
        for (int idx = 1; idx < S.length(); idx ++, repeat ++) {
            if (S.charAt(idx) != S.charAt(idx - 1)) {
                ans += repeat * (repeat + 1) / 2;
                repeat = 0;
            }
        }
        return ans += repeat * (repeat + 1) / 2;
    }
}
