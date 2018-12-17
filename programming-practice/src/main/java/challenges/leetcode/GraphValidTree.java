package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 261. Graph Valid Tree
 * 
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to 
 * check whether these edges make up a valid tree.
 * 
 * For example:
 * 		Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * 		Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 * 
 * @author Hxkandwal
 */
public class GraphValidTree extends AbstractCustomTestRunner {
	
	private static GraphValidTree _instance = new GraphValidTree();

    public class Node {
        public int val;
        public Node parent;
        Node (int val) {
            this.val = val;
            this.parent = this;
        }
    }

    // Strategy using disjoint data structure. Nice!
    public boolean _validTree(int n, int[][] edges) {
        if (n == 0) return true;
        Node[] nodes = new Node [n];
        for (int idx = 0; idx < n; idx ++) nodes [idx] = new Node (idx);
        for (int [] e : edges) {
            Node x = nodes [e [0]], y = nodes [e [1]];
            Node rx = find (x), ry = find (y);
            if (rx == ry) return false;     // cycle
            rx.parent = ry;                 // no need to maintain ranks here. Just keep x and y together.
            n --;
        }
        return n == 1;
    }

    private Node find (Node n) {
        return n.parent == n ? n : find (n.parent);
    }

    // another strategy using AlgorithmsLive! video (ep - 1)
    // - a graph with n nodes, with have all unique paths, i.e. no cycles and hence  n - 1 edges.
    // - if we traverse all the edges there will be no missing node (connected).
    public class GraphNode {
        int val;
        boolean seen;
        List<GraphNode> children = new ArrayList<>();
        GraphNode(int val) { this.val = val; }
    }

    public boolean validTreeOther(int n, int[][] edges) {
        if (n == 0) return true;
        if (edges.length != n - 1) return false;
        GraphNode[] nodes = new GraphNode [n];
        for (int idx = 0; idx < n; idx ++) nodes [idx] = new GraphNode (idx);
        for (int[] e : edges) {
            nodes [e [0]].children.add(nodes [e [1]]);
            nodes [e [1]].children.add(nodes [e [0]]);
        }
        dfs (nodes [0]);
        for (GraphNode node : nodes) if (!node.seen) return false;
        return true;
    }

    private void dfs(GraphNode n) {
        if (n == null || n.seen) return;
        n.seen = true;
        for (GraphNode c : n.children) dfs (c);
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(5, new int[][] { new int [] { 0, 1 }, new int [] { 0, 2 }, new int [] { 0, 3 }, new int [] { 1, 4 } }, true);
		_instance.runTest(5, new int[][] { new int [] { 0, 1 }, new int [] { 1, 2 }, new int [] { 2, 3 }, new int [] { 1, 3 }, new int [] { 1, 4 } }, false);
	}
	
	public void runTest(final int n, final int[][] edges, final boolean expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[]{n, edges});

        for (Object answer : answers)
            assertThat((Boolean) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
