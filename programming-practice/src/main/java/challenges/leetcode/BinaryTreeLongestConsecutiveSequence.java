package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 298. Binary Tree Longest Consecutive Sequence
 *
 * Given a binary tree, find the length of the longest consecutive sequence path.
 * 
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * The longest consecutive path need to be from parent to child (cannot be the reverse).
 *
 * For example,
 *                 1
 *                  \
 *                  3
 *                 / \
 *                2   4
 *                    \
 *                    5
 *
 * Longest consecutive sequence path is 3-4-5, so return 3.
 *
 *                  2
 *                   \
 *                   3
 *                  /
 *                 2
 *                /
 *               1
 *
 * Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
 * 
 * @author Hxkandwal
 */
public class BinaryTreeLongestConsecutiveSequence extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public int _longestConsecutive(TreeNode root) {
        int [] ans = new int [1];
        if (root != null) longestConsecutiveInner (root, ans);
        return ans [0];
    }

    public int longestConsecutiveInner (TreeNode node, int [] ans) {
        if (node == null) return 0;
        int left = longestConsecutiveInner (node.left, ans), right = longestConsecutiveInner (node.right, ans);
        if (node.left  == null || node.val + 1 == node.left.val ) left ++; else left = 1;           // default value is 1 (for that node)
        if (node.right == null || node.val + 1 == node.right.val) right ++; else right = 1;
        ans [0] = Math.max (ans [0], Math.max (left, right));
        return Math.max (left, right);
    }
	
}
