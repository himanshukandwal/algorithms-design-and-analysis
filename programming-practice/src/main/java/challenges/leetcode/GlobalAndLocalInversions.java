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

    // other way using value index reverse array
    public boolean _isIdealPermutationVIRArray(int[] A) {
        // A local inversion is also a global inversion.
        // Thus, we only need to check if our permutation has any one non-local inversion
        // (A[i] > A[j], i < j) with j - i > 1.

        // Fenwick tree is used to count All, however, problem here is simple to check if there is
        // any single one. Hence, FT is an overkill here.

        // value index reverse array
        int [] arr = new int [5000];
        for (int idx = A.length - 1; idx >= 0; idx --) {
            int a = A [idx];
            // can also employ range queries (mutable) if this is not good enough.
            for (int j = a - 1; j >= 0; j --)
                if (arr [j] > 0 && arr [j] - idx > 1) return false;
            arr [a] = idx;
        }
        return true;
    }

    public boolean _isIdealPermutationFaster(int[] A) {
        /**
         * In performing a brute force, we repeatedly want to check whether there is some j >= i+2
         * for which A[i] > A[j]. This is the same as checking for A[i] > min(A[i+2:]).
         * If we knew these minimums min(A[0:]), min(A[1:]), min(A[2:]), ... we could make this
         * check quickly.
         */
        int minIdx = A.length - 1;
        for (int idx = A.length - 3; idx >= 0; idx --) {
            if (A [idx] > A [minIdx]) return false;
            if (A [idx + 1] < A [minIdx]) minIdx = idx + 1;
        }
        return true;
    }

    /**
     * Let's try to characterize all ideal permutations: ones that do not have non-local inversions. Where does the 0 go?
     *
     * If the 0 occurs at index 2 or greater, then A[0] > A[2] = 0 is a non-local inversion. So 0 can only occur at index 0 or 1.
     * If A[1] = 0, then we must have A[0] = 1 otherwise A[0] > A[j] = 1 is a non-local inversion. Otherwise, A[0] = 0.
     * To recap, the possibilities are either A = [0] + (ideal permutation of 1...N-1) or A = [1, 0] + (ideal permutation of 2...N-1).
     */
    public boolean _isIdealPermutationCoolest(int[] A) {
        for (int i = 0; i < A.length; ++ i)
            if (Math.abs(A[i] - i) > 1)
                return false;
        return true;
    }
}
