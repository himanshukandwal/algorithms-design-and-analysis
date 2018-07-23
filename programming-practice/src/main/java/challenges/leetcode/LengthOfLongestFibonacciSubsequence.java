package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

/**
 * 873. Length of Longest Fibonacci Subsequence
 *
 * A sequence X_1, X_2, ..., X_n is fibonacci-like if:
 *  n >= 3
 *  X_i + X_{i+1} = X_{i+2} for all i + 2 <= n
 *
 * Given a strictly increasing array A of positive integers forming a sequence, find the length of the longest fibonacci-like subsequence of A.
 * If one does not exist, return 0.
 *
 * (Recall that a subsequence is derived from another sequence A by deleting any number of elements (including none) from A, without changing the
 * order of the remaining elements.  For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].)
 *
 * Example 1:
 *      Input: [1,2,3,4,5,6,7,8]
 *      Output: 5
 *      Explanation: The longest subsequence that is fibonacci-like: [1,2,3,5,8].
 *
 * Example 2:
 *      Input: [1,3,7,11,12,14,18]
 *      Output: 3
 *      Explanation: The longest subsequence that is fibonacci-like: [1,11,12], [3,11,14] or [7,11,18].
 *
 * Note:
 *  -  3 <= A.length <= 1000
 *  -  1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
 *
 *  (The time limit has been reduced by 50% for submissions in Java, C, and C++.)
 *
 * @author hxkandwal
 */
public class LengthOfLongestFibonacciSubsequence extends AbstractCustomTestRunner {

    private static LengthOfLongestFibonacciSubsequence _instance = new LengthOfLongestFibonacciSubsequence();

    public int _lenLongestFibSubseqCleaner(int[] A) {
        int ans = 0, n = A.length;
        int [][] dp = new int [n][n];  // rows = start, cols = end. i.e. dp [r][c] = max length of sub-seq from idx r to c.

        for (int idx = 2; idx < n; idx ++) {
            int a = A [idx], s = 0, e = idx - 1;
            while (s < e) {
                int v = A [s] + A [e];
                if (v > a) e --;
                else if (v < a) s ++;
                else {
                    ans = Math.max (ans, dp [e][idx] = (dp [s][e] == 0 ? 2 : dp [s][e]) + 1);
                    s ++;
                }
            }
        }
        return ans;
    }

    public int _lenLongestFibSubseq(int[] A) {
        // newvalue, [[oldvalue, old-length]]
        Map<Integer, List<int[]>> dp = new HashMap<>();
        int ans = 0;
        for (int idx = 2; idx < A.length; idx ++) {
            int a = A [idx];
            if (dp.containsKey(a)) {
                for (int[] v : dp.get(a)) {
                    int pv = v [0], len = v [1];
                    int nv = pv + a;
                    ans = Math.max (ans, ++ len);
                    dp.computeIfAbsent(nv, k -> new ArrayList<int[]>()).add (new int[] { a, len });
                }
            }
            int s = 0, e = idx - 1;
            while (s < e) {
                int v = A [s] + A [e];
                if (v > a) e --;
                else if (v < a) s ++;
                else {
                    ans = Math.max (ans, 3);
                    dp.computeIfAbsent(A [e] + a, k -> new ArrayList<>()).add (new int[] { a, 3 });
                    s ++;
                }
            }
        }
        return ans;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }, 5 );
        _instance.runTest(new int[] { 2, 4, 7, 8, 9, 10, 14, 15, 18, 23, 32, 50 }, 5);
    }

    public void runTest(final int[] A, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { A });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
