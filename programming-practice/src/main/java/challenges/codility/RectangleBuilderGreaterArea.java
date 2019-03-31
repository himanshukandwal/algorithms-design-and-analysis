package challenges.codility;

import challenges.AbstractCustomTestRunner;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;

/**
 *  Rectangle Builder Greater Area
 *
 *  Halfling Woolly Proudhoof is an eminent sheep herder. He wants to build a pen (enclosure) for his new flock of sheep. The pen will be rectangular and built
 *  from exactly four pieces of fence (so, the pieces of fence forming the opposite sides of the pen must be of equal length). Woolly can choose these pieces out
 *  of N pieces of fence that are stored in his barn. To hold the entire flock, the area of the pen must be greater than or equal to a given threshold X.
 *
 *  Woolly is interested in the number of different ways in which he can build a pen. Pens are considered different if the sets of lengths of their sides are different.
 *  For example, a pen of size 1×4 is different from a pen of size 2×2 (although both have an area of 4), but pens of sizes 1×2 and 2×1 are considered the same.
 *
 *  Write a function:
 *          class Solution { public int solution(int[] A, int X); }
 *          that, given an array A of N integers (containing the lengths of the available pieces of fence) and an integer X,
 *          returns the number of different ways of building a rectangular pen satisfying the above conditions.
 *          The function should return −1 if the result exceeds 1,000,000,000.
 *
 * For example, given X = 5 and the following array A:
 *
 *   A[0] = 1
 *   A[1] = 2
 *   A[2] = 5
 *   A[3] = 1
 *   A[4] = 1
 *   A[5] = 2
 *   A[6] = 3
 *   A[7] = 5
 *   A[8] = 1
 *
 * the function should return 2.
 * The figure above shows available pieces of fence (on the left) and possible to build pens (on the right).
 * The pens are of sizes 1x5 and 2x5. Pens of sizes 1×1 and 1×2 can be built, but are too small in area.
 * It is not possible to build pens of sizes 2×3 or 3×5, as there is only one piece of fence of length 3.
 *
 * Write an efficient algorithm for the following assumptions:
 *  N is an integer within the range [0..100,000];
 *  X is an integer within the range [1..1,000,000,000];
 *  each element of array A is an integer within the range [1..1,000,000,000].
 *
 *  link: https://app.codility.com/demo/results/training25BTGU-P75/
 *
 * @author Hxkandwal
 */
public class RectangleBuilderGreaterArea extends AbstractCustomTestRunner {

    private static RectangleBuilderGreaterArea _instance =  new RectangleBuilderGreaterArea();

    public int _solution(int[] A, int X) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : A) map.put (a, map.getOrDefault(a, 0) + 1);
        for (Iterator<Integer> itr = map.keySet().iterator(); itr.hasNext();) {
            Integer key = itr.next();
            if (map.get (key) == 1) itr.remove();
            else if (map.get(key) >= 4 && key * key >= X) ans ++;
        }
        List<Integer> vals = new ArrayList<>(map.keySet());
        Collections.sort (vals);

        for (int idx = 0; idx < vals.size(); idx ++) {
            int start = idx + 1, end = vals.size();
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (vals.get(idx) * vals.get(mid) >= X) end = mid;
                else start = mid + 1;
            }
            ans += (vals.size() - end);
            if (ans > 1_000_000_000) return -1;
        }
        return ans;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int [] { 2, 2, 2 }, 4, 0);
        _instance.runTest(new int [] { 2, 2, 2, 2 }, 4, 1);
        _instance.runTest(new int [] { 6, 6, 6, 6, 6, 6 }, 10, 1);
        _instance.runTest(new int [] { 1, 2, 5, 1, 1, 2, 3, 5, 1 }, 5, 2);
    }

    public void runTest(final int[] A, final int X, final Integer expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { A, X });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
