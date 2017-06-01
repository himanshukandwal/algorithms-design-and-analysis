package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 124. Binary Tree Maximum Path Sum
 * 
 * Given a binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node 
 * in the tree along the parent-child connections. The path must contain at least one node and does 
 * not need to go through the root.
 * 
 * For example: Given the below binary tree,
 * 
 * 		  1
 *       / \
 *      2   3
 * 
 * Return 6.
 * 
 * @author Hxkandwal
 */
public class BinaryTreeMaximumPathSum extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}

	public int maxPathSum(TreeNode root) {
		int [] max = new int [1];
		max [0] = Integer.MIN_VALUE;
		maxPathSum (max, root);
		return max [0];
	}

	private int maxPathSum(int[] max, TreeNode root) {
		if (root == null) return 0;
		int leftMax = Math.max (0, maxPathSum (max, root.left));
		int rightMax = Math.max (0, maxPathSum (max, root.right));
		max [0] = Math.max (max [0], root.val + leftMax + rightMax);
		return root.val + Math.max (leftMax, rightMax);
	}
 	
}
