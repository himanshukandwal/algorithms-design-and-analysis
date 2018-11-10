package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Game of Two Stacks
 *
 * link: https://www.hackerrank.com/challenges/game-of-two-stacks/problem
 */
public class GameOfTwoStacks extends AbstractCustomTestRunner {

    private static GameOfTwoStacks _instance = new GameOfTwoStacks();

    public int _twoStacks(int x, int[] a, int[] b) {
        int ans = 0, i = 0, j = 0, sum = 0;
        while (i < a.length && sum + a [i] <= x) sum += a [i ++];
        ans = i --;
        while (j < b.length) {
            while (i >= 0 && sum > x) sum -= a [i --];
            if (sum > x) break;
            sum += b [j ++];
            if (sum <= x) ans = Math.max (ans, (i + 1) + j);
        }
        return ans;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(5,
                new int[] { 1,0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1 },
                new int[] { 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1 },
                11);
    }

    public void runTest(final int x, final int[] a, final int[] b, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { x, a, b });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
