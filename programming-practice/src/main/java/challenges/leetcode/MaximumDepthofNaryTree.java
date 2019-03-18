package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 559. Maximum Depth of N-ary Tree
 *
 * Given a n-ary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * For example, given a 3-ary tree:
 *
 *                      1
 *                   /  |  \
 *                  3   2   4
 *                /  \
 *               5    6
 *
 * We should return its max depth, which is 3.
 *
 * Note:
 *  The depth of the tree is at most 1000.
 *  The total number of nodes is at most 5000.
 *
 * @author Hxkandwal
 */
public class MaximumDepthofNaryTree extends AbstractCustomTestRunner {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}
        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // DFS
    public int _maxDepth(Node root) {
        if (root == null) return 0;
        int max = 1;
        for (Node child : root.children) max = Math.max (max, _maxDepth (child) + 1);
        return max;
    }

    // BFS
    public int _maxDepthIterative(Node root) {
        if (root == null) return 0;
        int ans = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer (root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size -- > 0) {
                Node n = queue.poll ();
                for (Node child : n.children) queue.offer (child);
            }
            ans ++;
        }
        return ans;
    }

}
