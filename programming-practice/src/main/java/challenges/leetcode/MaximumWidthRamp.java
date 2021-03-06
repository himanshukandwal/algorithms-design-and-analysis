package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.Stack;

/**
 * 962. Maximum Width Ramp
 *
 * Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].  The width of such a ramp is j - i.
 * Find the maximum width of a ramp in A.  If one doesn't exist, return 0.
 *
 *   Example 1:
 *     Input: [6,0,8,2,1,5]
 *     Output: 4
 *     Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): A[1] = 0 and A[5] = 5.
 *
 *  Example 2:
 *      Input: [9,8,1,0,1,9,4,0,4,1]
 *      Output: 7
 *      Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): A[2] = 1 and A[9] = 1.
 *
 * Note:
 *  2 <= A.length <= 50000
 *  0 <= A[i] <= 50000
 *
 * @author Hxkandwal
 */
public class MaximumWidthRamp extends AbstractCustomTestRunner {

    public int maxWidthRamp(int[] A) {
        Stack<Integer> stack= new Stack<>();
        int max = 0;
        for (int idx = A.length - 1; idx >= 0; idx --) {
            for (int j : stack) if (A [j] >= A [idx]) max = Math.max(max, j - idx);
            if (stack.isEmpty() || A [stack.peek()] < A [idx]) stack.push(idx);
        }
        return max;
    }

    public int maxWidthRampBetter(int[] A) {
        int N = A.length;
        Integer[] B = new Integer[N];

        for (int i = 0; i < N; ++i) B[i] = i;

        Arrays.sort(B, (i, j) -> A[i] - A[j]);
        int ans = 0, m = N;
        for (int i: B) {
            ans = Math.max(ans, i - m);
            m = Math.min(m, i);
        }

        return ans;
    }

}
