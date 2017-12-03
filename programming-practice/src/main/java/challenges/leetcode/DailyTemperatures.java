package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static com.google.common.truth.Truth.assertThat;

/**
 * 739. Daily Temperatures My SubmissionsBack to Contest
 *
 * Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would have to wait until a warmer temperature.
 * If there is no future day for which this is possible, put 0 instead.
 *
 * For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 *
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 */
public class DailyTemperatures extends AbstractCustomTestRunner {

    private static DailyTemperatures _instance = new DailyTemperatures();

    public int[] _dailyTemperatures(int[] temperatures) {
        int [] ans = new int [temperatures.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int idx = temperatures.length - 1; idx >= 0; idx --) {
            ans [idx] = Integer.MAX_VALUE;
            for (Integer t : map.keySet())
                if (temperatures [idx] < t) ans [idx] = Math.min (ans [idx], map.get (t) - idx);
            map.put (temperatures [idx], idx);
            if (ans [idx] == Integer.MAX_VALUE) ans [idx] = 0;
        }
        return ans;
    }

    // better approach : to save in descending order, while working out increasing order.
    public int[] _dailyTemperaturesBetter(int[] temperatures) {
        int [] ans = new int [temperatures.length];
        Stack<Integer> stk = new Stack<>();
        for (int idx = 0; idx < temperatures.length; idx ++) {
            while (!stk.isEmpty() && temperatures [stk.peek()] < temperatures [idx]) ans [stk.peek()] = idx - stk.pop ();
            stk.push (idx);
        }
        return ans;
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
