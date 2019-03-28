package challenges.codility;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Divide Work
 *
 * A company has employed N developers (numbered from 0 to N−1) and wants to divide them into two teams. The first is a front-end team with F developers. The second is a back-end team with N−F developers.
 * If the K-th developer is assigned to the front-end team then their contribution is A[K], and if they are assigned to the back-end team then their contribution is B[K]. What is the maximum sum of contributions
 * the company can achieve?
 *
 * Write a function:
 *  class Solution { public int solution(int[] A, int[] B, int F); }
 *  that, given two arrays A, B (consisting of N integers each) and the integer F, returns the maximum sum of contributions the company can achieve.
 *
 * Examples:
 *  1.  Given A = [4, 2, 1], B = [2, 5, 3] and F = 2, the function should return 10. There should be two front-end developers and one back-end developer.
 *      The 0th and 2nd developers should be assigned to the front-end team (with contributions 4 and 1) and the 1st developer should be assigned to the
 *      back-end team (with contribution 5).
 *
 *  2.  Given A = [7, 1, 4, 4], B = [5, 3, 4, 3] and F = 2, the function should return 18. The 0th and 3rd developers should be assigned to the front-end
 *      team and the 1st and 2nd developers should be assigned to the back-end team.
 *
 *  3.  Given A = [5, 5, 5], B = [5, 5, 5] and F = 1, the function should return 15. The 0th developer can be assigned to the front-end team and the 1st and
 *      2nd developers can be assigned to the back-end team.
 *
 * Write an efficient algorithm for the following assumptions:
 *  - N is an integer within the range [1..200,000];
 *  - arrays A and B have equal lengths;
 *  - each element of array A is an integer within the range [0..1,000];
 *  - F is an integer within the range [0..N].
 *
 * link: https://app.codility.com/c/run/certZCCXGZ-9PK2XWDXKTFQXBC4/
 *
 * @author Hxkandwal
 */
public class DivideWork extends AbstractCustomTestRunner {

    private static DivideWork _instance = new DivideWork();

    int ans = 0;
    public int _solution(int[] A, int[] B, int F) {
        dfs (A, B, F, 0, new ArrayList<>());
        return ans;
    }

    private void dfs (int [] A, int [] B, int K, int curr, List<Integer> indexes) {
        if (K == indexes.size()) {
            int lans = 0;
            for (int idx : indexes) lans += A [idx];
            for (int idx = 0; idx < B.length; idx ++)
                if (!indexes.contains (idx)) lans += B [idx];
            ans = Math.max(ans, lans);
        }
        else {
            int rem = K - indexes.size();
            for (int idx = curr; idx <= A.length - rem; idx ++) {
                indexes.add (idx);
                dfs (A, B, K, idx + 1, indexes);
                indexes.remove (indexes.size() - 1);
                dfs (A, B, K, idx + 1, indexes);
            }
        }
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int [] { 4, 2, 1 }, new int [] { 2, 5, 3 }, 2, 10);
    }

    public void runTest(final int [] A, final int [] B, final int K, final Integer expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { A, B, K });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
