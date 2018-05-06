package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;

/**
 * 829. Consecutive Numbers Sum
 *
 * Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?
 *
 * Example 1:
 *      Input: 5
 *      Output: 2
 *
 *      Explanation: 5 = 5 = 2 + 3
 *
 * Example 2:
 *      Input: 9
 *      Output: 3
 *
 *      Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
 *
 * Example 3:
 *      Input: 15
 *      Output: 4
 *
 *      Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 *
 * Note: 1 <= N <= 10 ^ 9.
 *
 * @author Hxkandwal
 */
public class ConsecutiveNumbersSum extends AbstractCustomTestRunner {

    private static ConsecutiveNumbersSum _instance = new ConsecutiveNumbersSum();

    public int _consecutiveNumbersSum(int N) {
        //  number       start    length
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        for (int n = 0; n <= N; n ++) {
            Map<Integer, Integer> data = new HashMap<>();
            data.put(n, 0);
            map.put (n, data);

            for (int j = 1; j <= n/2; j ++) {
                Map<Integer, Integer> odata = map.get (n - j);
                if (odata.containsKey(j + 1)) data.put(j, odata.get(j + 1) + 1);
            }
        }
        return map.get(N).size();
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(5, 2);
        _instance.runTest(3749, 4);
        _instance.runTest(8953, 4);
    }

    @SuppressWarnings("unchecked")
    public void runTest(final int N, final Integer expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { N });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
