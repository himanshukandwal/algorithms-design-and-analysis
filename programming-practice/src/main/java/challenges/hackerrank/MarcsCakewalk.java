package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Marc's Cakewalk
 *
 * Marc loves cupcakes, but he also likes to stay fit. Each cupcake has a calorie count, and Marc can walk a distance to expend those calories.
 * If Marc has eaten j cupcakes so far, after eating a cupcake with c calories he must walk at least 2^j * c miles to maintain his weight.
 *
 * Given the individual calorie counts for each of the cupcakes, find and print a long integer denoting the minimum number of miles Marc must
 * walk to maintain his weight. Note that he can eat the cupcakes in any order.
 *
 * https://www.hackerrank.com/challenges/marcs-cakewalk/problem
 *
 * @author hxkandwal
 */
public class MarcsCakewalk extends AbstractCustomTestRunner {

    private static MarcsCakewalk _instance = new MarcsCakewalk();

    public long _marcsCakewalk(int[] calorie) {
        Arrays.sort(calorie);

        int n = calorie.length;
        long ans = calorie [n - 1];

        for (int idx = n - 2; idx >= 0; idx --) ans += (1l << (n - 1 - idx)) * calorie [idx];
        return ans;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int [] { 1, 3, 2 }, 11);
        _instance.runTest(new int [] { 819, 701, 578, 403, 50, 400, 983, 665, 510, 523, 696, 532, 51, 449,
                333, 234, 958, 460, 277, 347, 950, 53, 123, 227, 646, 190, 938, 61,
                409, 110, 61, 178, 659, 989, 625, 237, 944, 550, 954, 439 }, 59715404338867l);
    }

    public void runTest(final int[] calorie, final long expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { calorie });

        for (Object answer : answers)
            assertThat((long) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
