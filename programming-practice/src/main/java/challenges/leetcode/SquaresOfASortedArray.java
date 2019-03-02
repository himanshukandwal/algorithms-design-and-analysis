package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;

/**
 * 977. Squares of a Sorted Array
 *
 * Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
 *
 * Example 1:
 *      Input: [-4,-1,0,3,10]
 *      Output: [0,1,9,16,100]
 *
 * Example 2:
 *      Input: [-7,-3,2,3,11]
 *      Output: [4,9,9,49,121]
 *
 * Note:
 *  1 <= A.length <= 10000
 *  -10000 <= A[i] <= 10000
 *  A is sorted in non-decreasing order.
 *
 * @author Hxkandwal
 */
public class SquaresOfASortedArray extends AbstractCustomTestRunner {

    public int[] _sortedSquares(int[] A) {
        return Arrays.stream(A).map(i -> i * i).sorted().toArray();
    }

    public int[] _sortedSquaresBetter(int[] A) {
        int[] ans = new int [A.length];
        int idx = -1;
        while (idx + 1 < A.length && A [idx + 1] < 0) idx ++;
        int jdx = idx + 1, k = 0;
        while (idx >= 0 && jdx < A.length) {
            if (A [idx] * A [idx] > A [jdx] * A [jdx]) {
                ans [k ++] = A [jdx] * A [jdx];
                jdx ++;
            } else {
                ans [k ++] = A [idx] * A [idx];
                idx --;
            }
        }

        while (idx >= 0) { ans [k ++] = A [idx] * A [idx]; idx --; }
        while (jdx < A.length) { ans [k ++] = A [jdx] * A [jdx]; jdx ++; }
        return ans;
    }
}
