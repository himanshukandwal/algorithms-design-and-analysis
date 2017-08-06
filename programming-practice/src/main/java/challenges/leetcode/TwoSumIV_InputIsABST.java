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
 * Created by Hxkandwal
 */
public class TwoSumIV_InputIsABST extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public boolean findTarget(TreeNode root, int k) {
        return dfs (new HashSet<>(), k, root);
    }

    private boolean dfs (Set<Integer> set, int k, TreeNode n) {
        if (n == null) return false;
        if (set.contains (k - n.val)) return true;
        set.add (n.val);
        boolean res = dfs (set, k, n.left);
        if (res) return true;
        return dfs (set, k, n.right);
    }
}
