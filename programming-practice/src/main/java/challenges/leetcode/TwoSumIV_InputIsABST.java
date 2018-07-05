package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * 653. Two Sum IV - Input is a BST
 *
 * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their
 * sum is equal to the given target.
 *
 * Example 1:
 *      Input:
 *                  5
 *                 / \
 *                3   6
 *               / \   \
 *              2   4   7
 *
 *             Target = 9
 *
 *      Output: True
 *
 * Example 2:
 *      Input:
 *                  5
 *                 / \
 *                3   6
 *               / \   \
 *              2   4   7
 *
 *              Target = 28
 *
 *       Output: False
 *
 * Created by hxkandwal
 */
public class TwoSumIV_InputIsABST extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public boolean findTarget(TreeNode root, int k) {
        return dfs (new HashSet<>(), root, k);
    }

    private boolean dfs (Set<Integer> set, TreeNode root, int k) {
        if (root == null) return false;
        if (set.contains (k - root.val)) return true;
        set.add (root.val);
        return dfs (set, root.left, k) || dfs (set, root.right, k);
    }

    // other approach
    public boolean findTargetOther(TreeNode root, int k) {
        return dfs (root, root, k);
    }

    private boolean dfs (TreeNode r, TreeNode c, int k) {
        if (c == null) return false;
        return search (r, c, k - c.val) || dfs (r, c.left, k) || dfs (r, c.right, k);
    }

    private boolean search (TreeNode r, TreeNode c, int k) {
        if (r == null) return false;
        return (r.val == k && c != r)
                || (r.val > k && search (r.left, c, k))
                || (r.val < k && search (r.right, c, k));
    }
}
