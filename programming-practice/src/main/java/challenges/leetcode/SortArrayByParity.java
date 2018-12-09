package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 905. Sort Array By Parity
 *
 * Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.
 * You may return any answer array that satisfies this condition.
 *
 * Example 1:
 *      Input: [3,1,2,4]
 *      Output: [2,4,3,1]
 *              The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 *
 * Note:
 *  1 <= A.length <= 5000
 *  0 <= A[i] <= 5000
 *
 * @author Hxkandwal
 */
public class SortArrayByParity extends AbstractCustomTestRunner {

    public int[] _sortArrayByParity(int[] A) {
        int eIdx = -1;
        for (int idx = 0; idx < A.length; idx ++) {
            if (A [idx] % 2 == 0) {
                int t = A [eIdx + 1];
                A [eIdx + 1] = A [idx];
                A [idx] = t;
                eIdx ++;
            }
        }
        return A;
    }
}
