package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 563. Binary Tree Tilt
 *
 * Given a binary tree, return the tilt of the whole tree.
 *
 * The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of
 * all right subtree node values. Null node has tilt 0.
 *
 * The tilt of the whole tree is defined as the sum of all nodes' tilt.
 *
 * Example:
 *
 * Input:
 *              1
 *            /   \
 *           2     3
 *
 * Output: 1
 * Explanation:
 *      Tilt of node 2 : 0
 *      Tilt of node 3 : 0
 *      Tilt of node 1 : |2-3| = 1
 *      Tilt of binary tree : 0 + 0 + 1 = 1
 *
 * Note:
 *  -   The sum of node values in any subtree won't exceed the range of 32-bit integer.
 *  -   All the tilt values won't exceed the range of 32-bit integer.
 *
 * Created by Hxkandwal
 */
public class BinaryTreeTilt extends AbstractCustomTestRunner {

    public static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int x) { val = x; }
    }

    public int _findTilt(TreeNode root) {
        int [] ans = findTiltInner (root);
        return ans [1];
    }

    private int[] findTiltInner (TreeNode n) {
        if (n == null) return new int [] { 0, 0 };
        int [] l = findTiltInner (n.left), r = findTiltInner (n.right);
        return new int [] { l [0] + r [0] + n.val, l [1] + r [1] + Math.abs (l [0] - r [0]) };
    }
}
