package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

/**
 * 399. Evaluate Division
 *
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers.
 * If the answer does not exist, return -1.0.
 *
 * Example:
 *
 *  Given a / b = 2.0, b / c = 3.0.
 *  queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 *  return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive.
 * This represents the equations. Return vector<double>.
 *
 * According to the example above:
 *
 *  equations = [ ["a", "b"], ["b", "c"] ],
 *  values = [2.0, 3.0],
 *  queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 *
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 *
 * @author hxkandwal
 */
public class EvaluateDivision extends AbstractCustomTestRunner {

    private static EvaluateDivision _instance = new EvaluateDivision();

    public double[] _calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int idx = 0; idx < equations.length; idx ++) {
            String n = equations [idx][0], d = equations [idx][1];

            map.computeIfAbsent (n, k -> new HashMap<>()).put (d, values [idx]);
            map.computeIfAbsent (d, k -> new HashMap<>()).put (n, 1.0/values [idx]);
        }

        for (String k : map.keySet()) {
            for (String f : map.get(k).keySet()) {
                for (String s : map.get(k).keySet()) {
                    map.get(f).put (s, map.get(f).get(k) * map.get(k).get(s));
                }
            }
        }

        double [] ans = new double [queries.length];
        Arrays.fill (ans, -1.0);

        for (int idx = 0; idx < queries.length; idx ++) {
            String n = queries [idx][0], d = queries [idx][1];
            if (!map.containsKey(n) || !map.get(n).containsKey(d)) continue;
            ans [idx] = map.get (n).get(d);
        }
        return ans;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new String[][] { { "x1", "x2" }, { "x2", "x3" }, { "x3", "x4" }, { "x4", "x5" } },
                          new double[] { 3.0d, 4.0d, 5.0d, 6.0d },
                          new String [][] { { "x1", "x5" }, { "x5", "x2" } , { "x2", "x4" }, { "x2", "x2" }, { "x2", "x9" }, { "x9", "x9" } },
                          new double[] { 360.0d, 0.00833d, 20.0d, 1.0d, -1.0d, -1.0d });
    }

    public void runTest(final String[][] equations, final double[] values, final String[][] queries, final double[] expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { equations, values, queries });

        for (Object answer : answers) {
            assertThat((double[]) answer).isEqualTo(expectedOutput, 0.0001);
        }

        System.out.println("ok!");
    }
}
