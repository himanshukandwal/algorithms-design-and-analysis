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
 *V
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

    /**
     * Evidently, we only care about the comparisons between adjacent elements. If the comparisons are represented by -1, 0, 1 (for <, =, >), then we want the longest
     * sequence of alternating 1, -1, 1, -1, ... (starting with either 1 or -1).
     * These alternating comparisons form contiguous blocks. We know when the next block ends: when it is the last two elements being compared, or when the sequence isn't
     * alternating. For example, take an array like A = [9,4,2,10,7,8,8,1,9]. The comparisons are [1,1,-1,1,-1,0,-1,1]. The blocks are [1], [1,-1,1,-1], [0], [-1,1].
     **/
    // using java comparator
    public int _maxTurbulenceSizeComparator(int[] A) {
        int ans = 1;
        for (int idx = 1, start = 0; idx < A.length; idx ++) {
            int c = Integer.compare(A [idx - 1], A [idx]);
            if (c == 0) start = idx;
            else {
                if (idx == A.length - 1 || c * Integer.compare (A [idx], A [idx + 1]) != -1) {
                    ans = Math.max (ans, idx - start + 1);
                    start = idx;
                }
            }
        }
        return ans;
    }

    // using two counter to keep track of local sequences.
    public int _maxTurbulenceSizeSequences(int[] A) {
        int inc = 1, dec = 1, ans = 1;
        for (int idx = 1; idx < A.length; idx ++) {
            if (A [idx - 1] > A [idx]) {
                dec = inc + 1;              // these are the points, not the edge length
                inc = 1;
            }
            else if (A [idx - 1] < A [idx]) {
                inc = dec + 1;
                dec = 1;
            } else inc = dec = 1;
            ans = Math.max (ans, Math.max (inc, dec));
        }
        return ans;
    }
}
