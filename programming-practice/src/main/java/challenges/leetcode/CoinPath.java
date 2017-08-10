package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;

/**
 * 656. Coin Path
 *
 * Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B. The integer B denotes that from any place (suppose
 * the index is i) in the array A, you can jump to any one of the place in the array A indexed i+1, i+2, …, i+B if this place can be jumped to.
 *
 * Also, if you step on the index i, you have to pay Ai coins. If Ai is -1, it means you can’t jump to the place indexed i in the array.
 *
 * Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N using the minimum coins. You need to return the
 * path of indexes (starting from 1 to N) in the array you should take to get to the place indexed N using minimum coins.
 *
 * If there are multiple paths with the same cost, return the lexicographically smallest such path.
 *
 * If it's not possible to reach the place indexed N then you need to return an empty array.
 *
 * Example 1:
 *      Input: [1,2,4,-1,2], 2
 *      Output: [1,3,5]
 *
 * Example 2:
 *      Input: [1,2,4,-1,2], 1
 *      Output: []
 *
 * Note:
 *  -   Path Pa1, Pa2, ..., Pan is lexicographically smaller than Pb1, Pb2, ..., Pbm, if and only if at the first i where Pai and Pbi differ,
 *      Pai < Pbi; when no such i exists, then n < m.
 *  -   A1 >= 0. A2, ..., AN (if exist) will in the range of [-1, 100].
 *  -   Length of A is in the range of [1, 1000].
 *  -   B is in the range of [1, 100].
 *
 * Created by Hxkandwal
 */
public class CoinPath extends AbstractCustomTestRunner {

    private static CoinPath _instance = new CoinPath();

    public List<Integer> _cheapestJump(int[] A, int B) {
        int [] c = new int [A.length], parent = new int [A.length], length = new int [A.length];
        Arrays.fill (parent, -1);
        Arrays.fill (c, Integer.MAX_VALUE);
        c [0] = 0;

        for (int idx = 0; idx < A.length; idx ++) {
            if (A [idx] == -1) continue;
            for (int j = Math.max (0, idx - B); j < idx; j ++) {
                if (A[j] == -1) continue;
                if (c [j] + A [idx] < c [idx] || (c [j] + A [idx] == c [idx] && length [j] + 1 > length [idx])) {
                    c [idx]  = c [j] + A [idx];
                    parent [idx] = j;
                    length [idx] = length [j] + 1;
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        for (int cur = A.length - 1; cur >= 0; cur = parent [cur]) path.add (0, cur + 1);
        return path.get (0) != 1 ? Collections.emptyList() : path;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int [] { 1, 2, 4, -1, 2 }, 2, Arrays.asList(1, 3, 5));
        _instance.runTest(new int [] { 1, 2, 4, -1, 2 }, 1, Arrays.asList());
        _instance.runTest(new int [] { 0, 0, 0, 0, 0, 0 }, 3, Arrays.asList(1, 2, 3, 4, 5, 6));
        _instance.runTest(new int [] { 0, -1, 0, 0, 0, 0 }, 3, Arrays.asList(1, 3, 4, 5, 6));
    }

    public void runTest(final int[] A, final int B, final List<Integer> expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { A, B });

        for (Object answer : answers)
            assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
