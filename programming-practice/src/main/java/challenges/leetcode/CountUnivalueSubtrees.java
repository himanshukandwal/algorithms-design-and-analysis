package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 250. Count Univalue Subtrees
 *
 * Given a binary tree, count the number of uni-value subtrees.
 *
 * A Uni-value subtree means all nodes of the subtree have the same value.
 *
 * For example:
 * Given binary tree,
 *
 *                   5
 *                 /  \
 *                1   5
 *               / \   \
 *              5  5   5
 *
 * return 4.
 *
 * Created by Hxkandwal
 */
public class CountUnivalueSubtrees extends AbstractCustomTestRunner {

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public int _countUnivalSubtrees(TreeNode root) {
        int [] ans = new int [1];
        countUnivalSubtreesInner (root, ans);
        return ans [0];
    }

    private boolean countUnivalSubtreesInner (TreeNode n, int [] ans) {
        if (n == null) return true;
        boolean l = countUnivalSubtreesInner (n.left, ans), r = countUnivalSubtreesInner (n.right, ans);
        if (l && r && (n.left == null || n.left.val == n.val) && (n.right == null || n.right.val == n.val)) {
            ans [0] ++;
            return true;
        }
        return false;
    }
}
