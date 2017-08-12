package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 333. Largest BST Subtree
 *
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.
 *
 * Note: A subtree must include all of its descendants.
 *
 * Here's an example:
 *
 *                 10
 *                / \
 *               5  15
 *              / \   \
 *            1   8   7
 *
 * The Largest BST Subtree in this case is the highlighted one. The return value is the subtree's size, which is 3.
 *
 * Follow up: Can you figure out ways to solve it with O(n) time complexity?
 *
 * Created by Hxkandwal
 */
public class LargestBSTSubtree extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public class Answer {
        public boolean isValid;
        public int size;
        public Integer max, min;

        public Answer (boolean isValid, int size, Integer min, Integer max) {
            this.isValid = isValid;
            this.size = size; this.max = max; this.min = min;
        }
    }

    public int _largestBSTSubtree(TreeNode root) {
        Answer ans = largestBSTSubtreeInner (root);
        return ans.size;
    }

    public Answer largestBSTSubtreeInner (TreeNode node) {
        if (node == null) return new Answer (true, 0, null, null);
        Answer left = largestBSTSubtreeInner (node.left), right = largestBSTSubtreeInner (node.right);

        if (left.isValid && right.isValid) {
            if ((node.left == null || node.val > left.max) && (node.right == null || node.val < right.min))
                return new Answer (true, left.size + right.size + 1, node.left == null ? node.val : left.min, node.right == null ? node.val : right.max);
        }
        return new Answer (false, Math.max (left.size, right.size), null, null);
    }
}
