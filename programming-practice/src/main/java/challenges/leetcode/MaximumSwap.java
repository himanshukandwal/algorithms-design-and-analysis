package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 670. Maximum Swap
 *
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.
 *
 * Example 1:
 *      Input:  2736
 *      Output: 7236
 *      Explanation: Swap the number 2 and the number 7.
 *
 * Example 2:
 *      Input:  9973
 *      Output: 9973
 *      Explanation: No swap.
 *
 * Note: The given number is in the range [0, 10^8]
 */
public class MaximumSwap extends AbstractCustomTestRunner {

    // O(n) algorithm
    public int maximumSwap(int num) {
        char[] n = String.valueOf(num).toCharArray();
        int [] last = new int [10];
        for (int idx = 0; idx < n.length; idx ++) last [n [idx] - '0'] = idx;
        for (int idx = 0; idx < n.length; idx ++) {
            int c = n [idx] - '0';
            for (int d = 9; d >= 0; d --) {
                if (last [d] > idx && c < d) {
                    char t = n [idx];
                    n [idx] = n [last [d]];
                    n [last [d]] = t;
                    return Integer.valueOf(String.valueOf(n));
                }
            }
        }
        return num;
    }

    // O(n^2) algorithm
    public int _maximumSwap(int num) {
        char[] n = String.valueOf(num).toCharArray();
        String ans = String.valueOf(n);
        for (int idx = 0; idx < n.length; idx ++) {
            for (int jdx = idx + 1; jdx < n.length; jdx ++) {
                if (n [idx] != n [jdx]) {
                    swap (n, idx, jdx);
                    if (ans.compareTo(String.valueOf(n)) < 0) ans = String.valueOf(n);
                    swap (n, idx, jdx);
                }
            }
        }
        return Integer.valueOf(ans);
    }

    private void swap(char[] n, int a, int b) {
        char t = n [a];
        n [a] = n [b];
        n [b] = t;
    }
}
