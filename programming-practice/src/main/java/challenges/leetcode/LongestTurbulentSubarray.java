package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 978. Longest Turbulent Subarray
 *
 * A sub-array A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:
 *  For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
 *  OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
 *
 * That is, the sub-array is turbulent if the comparison sign flips between each adjacent pair of elements in the sub-array.
 * Return the length of a maximum size turbulent sub-array of A.
 *
 * Example 1:
 *          Input: [9,4,2,10,7,8,8,1,9]
 *          Output: 5
 *          Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])
 *
 * Example 2:
 *          Input: [4,8,12,16]
 *          Output: 2
 *
 * Example 3:
 *          Input: [100]
 *          Output: 1
 *
 * Note:
 *  1 <= A.length <= 40000
 *  0 <= A[i] <= 10^9
 *
 * @author Hxkandwal
 */
public class LongestTurbulentSubarray extends AbstractCustomTestRunner {

    public int _maxTurbulenceSize(int[] A) {
        int ans = 1, idx = 0;
        while (idx < A.length - 1) {
            int j = idx + 1;
            if (A [idx] != A [idx + 1]) {
                boolean g = A [idx] > A [idx + 1];  // greater check
                while (j < A.length - 1) {
                    if (g && A [j] >= A [j + 1]) break;
                    if (!g && A [j] <= A [j + 1]) break;
                    g = !g;
                    j ++;
                }
                ans = Math.max (ans, j - idx + 1);
            }
            idx = j;
        }
        return ans;
    }
}
