package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 1031. Maximum Sum of Two Non-Overlapping Subarrays
 *
 * Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.
 * (For clarification, the L-length subarray could occur before or after the M-length subarray.)
 *
 * Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:
 *  0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
 *  0 <= j < j + M - 1 < i < i + L - 1 < A.length.
 *
 *
 * Example 1:
 *              Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
 *              Output: 20
 *              Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
 *
 * Example 2:
 *              Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
 *              Output: 29
 *              Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
 *
 * Example 3:
 *              Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
 *              Output: 31
 *              Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.
 *
 * Note:
 *  L >= 1
 *  M >= 1
 *  L + M <= A.length <= 1000
 *  0 <= A[i] <= 1000
 *
 * @author Hxkandwal
 */
public class MaximumSumOfTwoNonOverlappingSubarrays extends AbstractCustomTestRunner {

    private static MaximumSumOfTwoNonOverlappingSubarrays _instance = new MaximumSumOfTwoNonOverlappingSubarrays();

    public int _maxSumTwoNoOverlap(int[] A, int L, int M) {
        int[] pre = new int [A.length + 1];
        for (int idx = 0; idx < A.length; idx ++) pre [idx + 1] = pre [idx] + A [idx];

        int ans = 0;
        for (int idx = 0; idx + L - 1 < A.length; idx ++) {
            int left = maxSum(A, pre, 0, idx - 1, M), right = maxSum(A, pre, idx + L, A.length - 1, M);
            ans = Math.max (ans, pre [idx + L] - pre [idx] + Math.max(left, right)
            );
        }
        return ans;
    }

    public int maxSum(int[] a, int [] pre, int l, int r, int s) {
        if (l > r || r - l + 1 < s) return -1;
        int ans = 0;
        for (int idx = l; idx + s - 1 <= r; idx ++)
            ans = Math.max(ans, pre [idx + s] - pre [idx]);
        return ans;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int [] { 0, 6, 5, 2, 2, 5, 1, 9, 4 }, 1, 2, 20);
        _instance.runTest(new int [] { 2, 1, 5, 6, 0, 9, 5, 0, 3, 8 }, 4, 3, 31);
    }

    public void runTest(final int[] A, final int L, final int M, int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { A, L, M });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
