package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 712. Minimum ASCII Delete Sum for Two Strings
 *
 * Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.
 *
 * Example 1:
 *      Input: s1 = "sea", s2 = "eat"
 *      Output: 231
 *
 *      Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
 *                   Deleting "t" from "eat" adds 116 to the sum.
 *                   At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
 *
 * Example 2:
 *      Input: s1 = "delete", s2 = "leet"
 *      Output: 403
 *      Explanation: Deleting "dee" from "delete" to turn the string into "let",
 *                   adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
 *                   At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
 *                   If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
 *
 * Note:
 *  0 < s1.length, s2.length <= 1000.
 *  All elements of each string will have an ASCII value in [97, 122].
 *
 * @author hxkandwal
 */
public class MinimumASCIIDeleteSumForTwoStrings extends AbstractCustomTestRunner {

    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int [s1.length() + 1][s2.length() + 1];
        for (int r = 0; r < s1.length(); r ++) dp [r + 1][0] = dp [r][0] + s1.charAt(r);
        for (int c = 0; c < s2.length(); c ++) dp [0][c + 1] = dp [0][c] + s2.charAt(c);

        for (int r = 0; r < s1.length(); r ++) {
            for (int c = 0; c < s2.length(); c ++) {
                char rc = s1.charAt(r), cc = s2.charAt(c);
                dp [r + 1][c + 1] = (rc == cc ? dp [r][c]: Math.min(dp[r][c + 1] + rc, dp [r + 1][c] + cc));
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
