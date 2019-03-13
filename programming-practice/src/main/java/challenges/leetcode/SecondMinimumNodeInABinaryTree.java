package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 671. Second Minimum Node In a Binary Tree
 *
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node.
 * If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.
 *
 * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
 * If no such second minimum value exists, output -1 instead.
 *
 * Example 1:
 *              Input:
 *                              2
 *                             / \
 *                            2   5
 *                               / \
 *                              5   7
 *
 *              Output: 5
 *              Explanation: The smallest value is 2, the second smallest value is 5.
 *
 * Example 2:
 *              Input:
 *                              2
 *                             / \
 *                            2   2
 *
 *              Output: -1
 *              Explanation: The smallest value is 2, but there isn't any second smallest value.
 *
 * @author Hxkandwal
 */
public class SecondMinimumNodeInABinaryTree extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public int _findSecondMinimumValueOther(TreeNode root) {
        // root.val is smaller, now find smaller value than root.val
        // if root is null or root does not have any child, the there is no value smaller than root.val
        if (root == null || (root.left == null && root.right == null)) return -1;
        int left = root.left == null ? -1 :
                (root.left.val == root.val ?
                        _findSecondMinimumValueOther(root.left) :
                        root.left.val
                );
        int right = root.right == null ? -1 :
                (root.right.val == root.val ?
                        _findSecondMinimumValueOther(root.right) :
                        root.right.val
                );

        return left == -1 || right == -1 ? Math.max(left, right) : Math.min(left, right);
    }

    TreeNode firstMin = null;
    TreeNode secondMin = null;

    public int _findSecondMinimumValue(TreeNode root) {
        dfs (root);
        return secondMin == null ? -1 : secondMin.val;
    }

    private void dfs (TreeNode n) {
        if (n == null) return;
        if (firstMin == null || firstMin.val > n.val) { secondMin = firstMin; firstMin = n; }
        else if (firstMin.val != n.val && (secondMin == null || secondMin.val > n.val)) secondMin = n;
        dfs (n.left);
        dfs (n.right);
    }
}
