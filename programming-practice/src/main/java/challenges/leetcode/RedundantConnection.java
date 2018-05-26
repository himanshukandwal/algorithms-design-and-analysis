package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

/**
 * 684. Redundant Connection
 *
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added.
 * The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.
 * Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.
 * The answer edge [u, v] should be in the same format, with u < v.
 *
 * Example 1:
 *      Input: [[1,2], [1,3], [2,3]]
 *      Output: [2,3]
 *
 *      Explanation: The given undirected graph will be like this:
 *
 *                       1
 *                      / \
 *                     2 - 3
 *
 * Example 2:
 *      Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 *      Output: [1,4]
 *
 *      Explanation: The given undirected graph will be like this:
 *
 *                      5 - 1 - 2
 *                          |   |
 *                          4 - 3
 *
 * Note:
 *  The size of the input 2D-array will be between 3 and 1000.
 *  Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 *
 * Update (2017-09-26): We have overhauled the problem description + test cases and specified clearly the graph is an undirected graph.
 *                      For the directed graph follow up please see Redundant Connection II). We apologize for any inconvenience caused.
 *
 * @author hxkandwal
 */
public class RedundantConnection extends AbstractCustomTestRunner {

    private static RedundantConnection _instance = new RedundantConnection();

    class Node {
        int val;
        Node parent;
        boolean seen;
        List<Node> neighbors = new ArrayList<>();

        public Node(int val) { this.val = val; }
    }

    public int[] _findRedundantConnection(int[][] edges) {
        Map<Integer, Node> map = new HashMap<>();
        for (int [] e : edges) {
            int start = e [0], end = e [1];
            map.putIfAbsent(start, new Node(start));
            map.putIfAbsent(end, new Node(end));

            map.get(start).neighbors.add(map.get(end));
            map.get(end).neighbors.add(map.get(start));

            map.get(start).parent = map.get(start);
            if (!dfs (map.get(start))) return e;
            for (int k : map.keySet()) { map.get(k).seen = false; map.get(k).parent = null; }
        }
        return null;
    }

    private boolean dfs (Node n) {
        boolean res = true;
        for (Node ne : n.neighbors) {
            if (ne == n.parent) continue;
            if (ne.parent != null) return false;
            ne.parent = n;
            if (!(res = res & dfs (ne))) break;
        }
        return res;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int[][]{new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4}, new int[]{1, 4}, new int[]{1, 5}}, new int[]{1, 4});
    }

    public void runTest(final int[][] edges, final int[] expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { edges });

        for (Object answer : answers)
            assertThat((int[]) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
