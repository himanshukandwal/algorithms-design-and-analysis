package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 104. Maximum Depth of Binary Tree
 * 
 * Given a binary tree, find its maximum depth.
 * 
 * 111. Minimum Depth of Binary Tree
 * 
 * Given a binary tree, find its minimum depth.
 * 
 * @author Hxkandwal
 *
 */
public class MaximumMinimumDepthOfBinaryTree extends AbstractCustomTestRunner {

	public static class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
	
	// this code will ensure that only leaves are getting considered.
	public static int maxDepth(TreeNode root) {
		if (root == null)
        	return 0;
        
		return Math.max(maxDepth (root.left), maxDepth (root.right)) + 1;
    }

	// this code will ensure that only leaves are getting considered.
	// note :  we cannot apply max depth logic as that will take non-leaf with only one child into priority.
    public static int minDepth(TreeNode root) {
    	if (root == null)
        	return 0;
        
        if (root.left == null && root.right == null)	
            return 1;
        else if (root.left == null)
            return minDepth (root.right) + 1;
        else if (root.right == null)    
            return minDepth (root.left) + 1;
        else
            return Math.min(minDepth (root.left), minDepth (root.right)) + 1;  
    }
	
}
