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
        maxPathSumInner (root, max);
        return max [0];
    }
    
    private int maxPathSumInner (TreeNode node, int [] max) {
        if (node == null) return 0;
        int left = Math.max (0, maxPathSumInner (node.left, max));
        int right = Math.max (0, maxPathSumInner (node.right, max));
        max [0] = Math.max (max [0], node.val + left + right);
        return node.val + Math.max (left, right);
    }
 	
}
