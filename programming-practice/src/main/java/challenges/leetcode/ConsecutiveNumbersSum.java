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

    /*
     * In AP (Arithmetic Progression),
     *   nth term is defined as: Tn = a + (n + 1)*d
     *   and the sum is computed as:
     *
     *      sum = n/2 * (a + tn) => n/2 * (a + (a + (n + 1)*d)) => n/2 * (2a + (n + 1) * d)
     *   or
     *
     *  2 * sum = n * (2a + n + 1)
     *
     *   Here d = 1 and sum = N
     *
     * So, try all the possible n, to get 'a' by (sum - (n + 1) * n/2) / n, if a is an integer, it counts.
     *
     * where, 'a' are the starting point of AP, so the problem reduces to finding how many starting points occur to make sum N
     * and while we are varying the length 'n'
     */
    public int _consecutiveNumbersSumMath(int N) {
        int res = 1, count;
        while (N % 2 == 0) N /= 2;
        for (int i = 3; i * i <= N; res *= count + 1, i += 2)
            for (count = 0; N % i == 0; N /= i, count++);
        return N == 1 ? res : res * 2;
    }

    // TLE
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
