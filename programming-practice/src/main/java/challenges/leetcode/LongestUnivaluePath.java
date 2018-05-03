package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 687. Longest Univalue Path
 *
 * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
 *
 * Example 1:
 *      Input:
 *                      5
 *                     / \
 *                    4   5
 *                   / \   \
 *                  1   1   5
 *
 *      Output: 2
 *
 * Example 2:
 *      Input:
 *                      1
 *                     / \
 *                    4   5
 *                   / \   \
 *                  4   4   5
 *
 *      Output: 2
 *
 * Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 *
 * @author hxkandwal
 */
public class LongestUnivaluePath extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    int max = 0;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        dfs (root);
        return max;
    }

    public int dfs(TreeNode n) {
        if (n == null) return 0;
        int l = dfs (n.left), r = dfs (n.right);
        int ret = 0, path = 0;

        if (n.left != null && n.left.val == n.val) {
            ret = Math.max (ret, l + 1);
            path += l + 1;
        }

        if (n.right != null && n.right.val == n.val) {
            ret = Math.max (ret, r + 1);
            path += r + 1;
        }

        max = Math.max(max, path);
        return ret;
    }
}
