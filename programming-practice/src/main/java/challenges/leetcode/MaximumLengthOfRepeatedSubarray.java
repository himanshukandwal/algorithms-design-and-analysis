package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 718. Maximum Length of Repeated Subarray
 *
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 *
 * Example 1:
 *              Input:  A: [1,2,3,2,1]
 *                      B: [3,2,1,4,7]
 *              Output: 3
 *              Explanation: The repeated subarray with maximum length is [3, 2, 1].
 * Note:
 *  1 <= len(A), len(B) <= 1000
 *  0 <= A[i], B[i] < 100
 *
 * @author Hxkandwal
 */
public class MaximumLengthOfRepeatedSubarray extends AbstractCustomTestRunner {

    public int _findLengthDP(int[] A, int[] B) {
        int m = A.length, n = B.length, ans = 0;
        int [][] dp = new int [m + 1][n + 1];

        for (int r = 0; r < m; ++ r) {
            for (int c = 0; c < n; ++ c) {
                if (A [r] == B [c]) {
                    dp [r + 1][c + 1] = dp [r][c] + 1;
                    ans = Math.max (ans, dp [r + 1][c + 1]);
                }
            }
        }
        return ans;
    }

    // single array DP (faster)
    public int _findLengthFastestDP(int[] A, int[] B) {
        int [] dp = new int [A.length + 1];

        int ans = 0;
        for (int r = 0; r < B.length; r ++) {
            // sub-array builds towards right, we should traverse from r -> l to use prev iteration info.
            for (int c = A.length - 1; c >= 0; c --) {
                // diagonal building of sub-array (dp [r + 1][c + 1] = dp [r][c] + 1),
                // so we add from whats build up ahead of the run (i.e on left as we are moving from r -> l)
                if (A [c] == B [r]) {
                    dp [c + 1] = dp [c] + 1;
                    ans = Math.max (ans, dp [c + 1]);
                } else dp [c + 1] = 0;
            }
        }

        return ans;
    }

    public int _findLength(int[] A, int[] B) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int idx = 0; idx < B.length; idx ++) {
            if (!map.containsKey(B [idx])) map.put (B [idx], new ArrayList<>());
            map.get (B [idx]).add (idx);
        }

        int ans = 0;
        for (int idx = 0; idx < A.length; idx ++) {
            int a = A [idx];
            if (!map.containsKey(a)) continue;
            for (int k : map.get (a)) {
                int j = idx;
                while (j < A.length && k < B.length && A [j] == B [k]) {
                    j ++; k ++;
                }
                ans = Math.max (ans, j - idx);
            }
        }

        return ans;
    }
}
