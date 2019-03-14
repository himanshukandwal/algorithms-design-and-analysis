package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 886. Possible Bipartition
 *
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
 * Each person may dislike some other people, and they should not go into the same group.
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
 * Return true if and only if it is possible to split everyone into two groups in this way.
 *
 * Example 1:
 *              Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
 *              Output: true
 *              Explanation: group1 [1,4], group2 [2,3]
 *
 * Example 2:
 *              Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
 *              Output: false
 *
 * Example 3:
 *              Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 *              Output: false
 *
 * Note:
 *  1 <= N <= 2000
 *  0 <= dislikes.length <= 10000
 *  1 <= dislikes[i][j] <= N
 *  dislikes[i][0] < dislikes[i][1]
 *  There does not exist i != j for which dislikes[i] == dislikes[j].
 *
 * @author Hxkandwal
 */
public class PossibleBipartition extends AbstractCustomTestRunner {

    class Node {
        private int id;
        private List<Node> adj = new ArrayList<>();
        public int color = -1;

        public Node (int id) { this.id = id; }
    }

    public boolean _possibleBipartition(int N, int[][] dislikes) {
        Node[] nodes = new Node [N + 1];
        for (int idx = 1; idx <= N; idx ++) nodes [idx] = new Node (idx);
        for (int [] d : dislikes) {
            nodes [d [0]].adj.add (nodes [d [1]]);
            nodes [d [1]].adj.add (nodes [d [0]]);
        }

        for (int idx = 1; idx <= N; idx ++)
            if (nodes [idx].color == -1 && !dfs (nodes [idx], 0)) return false;

        return true;
    }

    private boolean dfs (Node n, int color) {
        if (n.color != -1) return n.color == color;
        n.color = color;
        for (Node adj : n.adj)
            if (!dfs (adj, color ^ 1)) return false;
        return true;
    }
}
