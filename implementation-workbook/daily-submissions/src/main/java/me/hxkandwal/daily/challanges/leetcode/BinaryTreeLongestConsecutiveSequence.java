package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Given a binary tree, find the length of the longest consecutive sequence path.
 * 
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
 * The longest consecutive path need to be from parent to child (cannot be the reverse).
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
	
	public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        return recurser (root, 1, 1);
    }
    
    private int recurser(TreeNode node, int current, int max) {
        if (node.left == null && node.right == null) return max;
        int lValue = 0, rValue = 0;
        
        if (node.left != null) {
            if (node.left.val == node.val + 1) 
                lValue = recurser (node.left, current + 1, Math.max(current + 1, max));
            else
                lValue = recurser (node.left, 1, Math.max(current, max));
        }
        
        if (node.right != null) {
            if (node.right.val == node.val + 1)
                rValue = recurser (node.right, current + 1, Math.max(current + 1, max));
            else
                rValue = recurser (node.right, 1, Math.max(current, max));
        }
        
        return Math.max (lValue, rValue);
    }
	
}
