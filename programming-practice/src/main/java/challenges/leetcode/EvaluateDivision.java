package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

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

    class Node {
        String s;
        Map<String, Double> map = new HashMap<>();
        public Node (String s) { this.s = s; }
    }

    public double[] _calcEquation(String[][] equations, double[] values, String[][] queries) {
        double [] ans = new double [queries.length];
        Arrays.fill (ans, -1.0);
        Map<String, Node> map = new HashMap<>();

        for (int idx = 0; idx < equations.length; idx ++) {
            String [] e = equations [idx];
            map.putIfAbsent(e [0], new Node (e [0]));
            map.putIfAbsent(e [1], new Node (e [1]));
            map.get (e [0]).map.put (e [1], values [idx]);
            map.get (e [1]).map.put (e [0], 1.0 / values [idx]);
        }

        for (int idx = 0; idx < queries.length; idx ++) {
            String [] q = queries [idx];
            if (map.containsKey (q [0]) && map.containsKey (q [1])) {
                Double answer = dfs (map, new HashSet<>(), q [0], q [1]);
                if (answer != null) ans [idx] = answer;
            }
        }
        return ans;
    }

    private Double dfs(Map<String, Node> map, Set<String> seen, String s, String f) {
        Node n = map.get (s);
        seen.add (n.s);

        if (n.map.containsKey(f)) return n.map.get(f);
        for (String ne : n.map.keySet()) {
            if (seen.contains (ne)) continue;

            Double a = dfs (map, seen, ne, f);
            if (a != null) return a * n.map.get (ne);
        }
        return null;
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
