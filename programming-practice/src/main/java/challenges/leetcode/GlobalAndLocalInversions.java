package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 775. Global and Local Inversions
 *
 * We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.
 * The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].
 * The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].
 *
 * Return true if and only if the number of global inversions is equal to the number of local inversions.
 *
 * Example 1:
 *              Input: A = [1,0,2]
 *              Output: true
 *              Explanation: There is 1 global inversion, and 1 local inversion.
 *
 * Example 2:
 *              Input: A = [1,2,0]
 *              Output: false
 *              Explanation: There are 2 global inversions, and 1 local inversion.
 *
 * Note:
 *  A will be a permutation of [0, 1, ..., A.length - 1].
 *  A will have length in range [1, 5000].
 *  The time limit for this problem has been reduced.
 *
 * @author Hxkandwal
 */
public class GlobalAndLocalInversions extends AbstractCustomTestRunner {

    class FenwickTree {
        int [] arr = new int [5001];

        public int greater(int idx) {
            idx ++;
            int ans = 0;
            while (idx < arr.length) {
                ans += arr [idx];
                idx += idx & (~idx + 1);
            }
            return ans;
        }

        public void add (int idx) {
            idx ++;
            while (idx > 0) {
                arr [idx] ++;
                idx -= idx & (~idx + 1);
            }
        }
    }

    public boolean _isIdealPermutation(int[] A) {
        int l = 0, g = 0;
        FenwickTree ft = new FenwickTree();
        ft.add (A [0]);

        for (int idx = 1; idx < A.length; idx ++) {
            g += ft.greater (A [idx]);
            ft.add (A [idx]);
            l += (A [idx - 1] > A [idx]) ? 1 : 0;
        }
        return l == g;
    }
}
