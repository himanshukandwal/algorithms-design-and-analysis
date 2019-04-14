package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static com.google.common.truth.Truth.assertThat;

/**
 * 739. Daily Temperatures
 *
 * Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would have to wait until a warmer temperature.
 * If there is no future day for which this is possible, put 0 instead.
 *
 * For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 *
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 *
 * @author Hxkandwal
 */
public class DailyTemperatures extends AbstractCustomTestRunner {

    private static DailyTemperatures _instance = new DailyTemperatures();

    public int[] _dailyTemperatures(int[] T) {
        // using value index reverse array
        int [] arr = new int [101];
        Arrays.fill(arr, Integer.MAX_VALUE);

        for (int idx = T.length - 1; idx >= 0; idx --) {
            int minIdx = Integer.MAX_VALUE;

            for (int j = T [idx] + 1; j < arr.length; j ++)
                if (arr [j] < minIdx) minIdx = arr [j];

            arr [T [idx]] = idx;
            T [idx] = minIdx == Integer.MAX_VALUE ? 0 : (minIdx - idx);
        }
        return T;
    }

    // better approach : to save in descending order, while working out increasing order.
    public int[] _dailyTemperaturesBetter(int[] T) {
        Stack<Integer> stack = new Stack<>();
        for (int idx = 0; idx < T.length; idx ++) {
            int t = T [idx];
            while (!stack.isEmpty() && T [stack.peek()] < T [idx]) {
                int j = stack.pop();
                T [j] = idx - j;
            }
            stack.push (idx);
        }
        for (int idx : stack) T [idx] = 0;
        return T;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int[] { 73, 74, 75, 71, 69, 72, 76, 73 }, new int[] { 1, 1, 4, 2, 1, 1, 0, 0 });
    }

    public void runTest(final int [] arr, final int[] expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { arr });

        for (Object answer : answers)
            assertThat((int[]) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
