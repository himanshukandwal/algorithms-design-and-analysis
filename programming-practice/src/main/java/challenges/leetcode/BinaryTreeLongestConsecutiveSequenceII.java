package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 549. Binary Tree Longest Consecutive Sequence II
 *
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
 *
 * Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the
 * path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child
 * order.
 *
 * Example 1:
 *
 *   Input:
 *                  1
 *                 / \
 *                2   3
 *
 *   Output: 2
 *   Explanation: The longest consecutive path is [1, 2] or [2, 1].
 *
 * Example 2:
 *
 *  Input:
 *                  2
 *                 / \
 *                1   3
 *
 *  Output: 3
 *  Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 *
 *  Note: All the values of tree nodes are in the range of [-1e7, 1e7].
 *
 * Created by Heman on 8/12/17.
 */
public class BinaryTreeLongestConsecutiveSequenceII extends AbstractCustomTestRunner {

    public static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int x) { val = x; }
    }

    public class Answer {
        public int incr, decr;
        public Answer (int incr, int decr) { this.incr = incr; this.decr = decr; }
    }

    private int maxVal;

    public int _longestConsecutive(TreeNode root) {
        longestConsecutiveInner (root);
        return maxVal;
    }

    public Answer longestConsecutiveInner (TreeNode n) {
        if (n == null) return new Answer (0, 0);
        Answer l = longestConsecutiveInner (n.left), r = longestConsecutiveInner (n.right);

        Answer ret = new Answer (1, 1);
        if (n.left != null) {
            if (n.val + 1 == n.left.val) ret.incr = l.incr + 1;
            if (n.val - 1 == n.left.val) ret.decr = l.decr + 1;
        }
        if (n.right != null) {
            if (n.val + 1 == n.right.val) ret.incr = Math.max (ret.incr, r.incr + 1);
            if (n.val - 1 == n.right.val) ret.decr = Math.max (ret.decr, r.decr + 1);
        }
        maxVal = Math.max (maxVal, ret.incr + ret.decr - 1);
        return ret;
    }

}
