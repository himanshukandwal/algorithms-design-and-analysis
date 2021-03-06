package challenges.leetcode;

import challenges.AbstractCustomTestRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 785. Is Graph Bipartite?
 *
 * Given an undirected graph, return true if and only if it is bipartite.
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another
 * node in B.
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.
 * There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 *
 * Example 1:
 *            Input: [[1,3], [0,2], [1,3], [0,2]]
 *            Output: true
 *            Explanation:  The graph looks like this:
 *
 *                            0----1
 *                            |    |
 *                            |    |
 *                            3----2
 *
 *                          We can divide the vertices into two groups: {0, 2} and {1, 3}.
 *
 * Example 2:
 *            Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 *            Output: false
 *            Explanation: The graph looks like this:
 *
 *                            0----1
 *                            | \  |
 *                            |  \ |
 *                            3----2
 *
 *                          We cannot find a way to divide the set of nodes into two independent subsets.
 *
 * Note:
 *  graph will have length in range [1, 100].
 *  graph[i] will contain integers in range [0, graph.length - 1].
 *  graph[i] will not contain i or duplicate values.
 *  The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 *
 * @author Hxkandwal
 */
public class IsGraphBipartite extends AbstractCustomTestRunner {

  // traditional way
  class Node {
    private int id;
    private List<Node> children = new ArrayList<>();
    private int code = -1;
    private boolean seen;

    public Node(int id) {
      this.id = id;
    }
  }

  public boolean _isBipartite(int[][] graph) {
    int len = graph.length;
    Node[] nodes = new Node [len];
    for (int idx = 0; idx < len; idx ++) nodes [idx] = new Node(idx);
    for (int idx = 0; idx < len; idx ++) {
      for (int adjIdx : graph [idx]) {
        nodes [idx].children.add (nodes [adjIdx]);
        nodes [adjIdx].children.add (nodes [idx]);
      }
    }

    for (int idx = 0; idx < len; idx ++)
      if (!nodes [idx].seen && !dfs (nodes [idx], 0)) return false;

    return true;
  }

  private boolean dfs (Node n, int code) {
    if (n.seen) return true;
    n.seen = true;
    n.code = code;
    for (Node adj : n.children) {
      if ((adj.seen && code == adj.code) ||
          (!adj.seen && !dfs (adj, code == 0 ? 1 : 0))) return false;
    }
    return true;
  }

  // better concise way
  public boolean _isBipartiteBetter(int[][] graph) {
    int len = graph.length;
    int [] color = new int [len];
    Arrays.fill (color, -1);

    for (int idx = 0; idx < len; idx ++) {
      if (color [idx] == -1) {
        color [idx] = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push (idx);

        while (!stack.isEmpty()) {
          int n = stack.pop();

          for (int adj : graph [n]) {
            if (color [adj] == -1) {
              stack.push (adj);
              color [adj] = color [n] ^ 1;
            }
            else if (color [adj] == color [n]) return false;
          }
        }
      }
    }
    return true;
  }

  // other way
  public boolean _isBipartiteDFS(int[][] graph) {
    int [] colors = new int [graph.length];
    Arrays.fill (colors, -1);

    for (int idx = 0; idx < graph.length; idx ++)
      if (colors [idx] == -1 && !dfs (graph, colors, idx, 0)) return false;
    return true;
  }

  private boolean dfs (int [][] g, int [] colors, int n, int color) {
    if (colors [n] != -1) return colors [n] == color;
    colors [n] = color;
    for (int adj : g [n])
      if (!dfs (g, colors, adj, color ^ 1)) return false;
    return true;
  }

}
