package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 1007. Minimum Domino Rotations For Equal Row
 *
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half
 * of the tile.)
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 *
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 * If it cannot be done, return -1.
 *
 * Example 1:
 *              Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 *              Output: 2
 *              Explanation: The first figure represents the dominoes as given by A and B: before we do any rotations.
 *                           If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
 *
 * Example 2:
 *              Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
 *              Output: -1
 *              Explanation: In this case, it is not possible to rotate the dominoes to make one row of values equal.
 *
 * Note:
 *  1 <= A[i], B[i] <= 6
 *  2 <= A.length == B.length <= 20000
 *
 * @author Hxkandwal
 */
public class MinimumDominoRotationsForEqualRow extends AbstractCustomTestRunner {

    private static final MinimumDominoRotationsForEqualRow _instance = new MinimumDominoRotationsForEqualRow();

    public int _minDominoRotations(int[] A, int[] B) {
        for (int idx = 0, a = 0, b = 0; idx < A.length; idx ++) {
            if (A [idx] != A [0] && B [idx] != A [0]) break;
            if (A [idx] != A [0]) a ++;
            if (B [idx] != A [0]) b ++;

            if (idx == A.length - 1) return Math.min(a, b);
        }

        for (int idx = 0, a = 0, b = 0; idx < A.length; idx ++) {
            if (A [idx] != B [0] && B [idx] != B [0]) break;
            if (A [idx] != B [0]) a ++;
            if (B [idx] != B [0]) b ++;

            if (idx == A.length - 1) return Math.min(a, b);
        }

        return -1;
    }

    public int _minDominoRotationsOther(int[] A, int[] B) {
        HashSet<Integer> s = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int[] countA = new int[7], countB = new int[7];
        for (int i = 0; i < A.length; ++i) {
            s.retainAll(new HashSet<>(Arrays.asList(A[i], B[i])));
            countA[A[i]]++;
            countB[B[i]]++;
        }
        for (int i : s) return Math.min(A.length - countA[i], B.length - countB[i]);
        return -1;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(
                new int[] { 2, 1, 2, 4, 2, 2 },
                new int[] { 5, 2, 6, 2, 3, 2 }, 2);
    }

    public void runTest(final int[] A, final int[] B, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { A, B });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
