package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 647. Palindromic Substrings
 *
 * Given a string, your task is to count how many palindromic substrings in this string.
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 *
 * Example 1:
 *      Input: "abc"
 *      Output: 3
 *
 *      Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Example 2:
 *      Input: "aaa"
 *      Output: 6
 *
 *      Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 * Note: The input string length won't exceed 1000.
 *
 * @author hxkandwal
 */
public class PalindromicSubstrings extends AbstractCustomTestRunner {

    public int _countSubstrings(String s) {
        int [][] dp = new int [s.length() + 1][s.length() + 1];
        int ans = 0;
        for (int r = 0; r < s.length(); r ++) {
            char rch = s.charAt(r);
            for (int c = 0; c <= r; c ++) {
                char cch = s.charAt(c);
                if (rch == cch && (r - c <= 1 || dp [r][c + 2] != 0)) {
                    dp [r + 1][c + 1] = 1;
                    ans ++;
                }
            }
        }
        return ans;
    }

    public int _countSubstringsOther(String s) {
        if (s == null || s.length() == 0) return 0;

        int count = 0;
        for (int i = 0; i < s.length(); i++) { // i is the mid point
            extendPalindrome(s, i, i, count); // odd length;
            extendPalindrome(s, i, i + 1, count); // even length
        }

        return count;
    }

    private int extendPalindrome(String s, int left, int right, int count) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++; left--; right++;
        }

        return count;
    }
}
