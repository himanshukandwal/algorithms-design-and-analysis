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
