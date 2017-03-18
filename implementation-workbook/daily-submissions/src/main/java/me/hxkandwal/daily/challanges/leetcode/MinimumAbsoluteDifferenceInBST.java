package me.hxkandwal.daily.challanges.leetcode;

import java.util.Stack;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 530. Minimum Absolute Difference in BST
 * 
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
 * 
 * Example:
 * 
 * Input:
 * 
 *    1
 *     \
 *      3
 *     /
 *    2
 * 
 * Output: 1
 * 
 * Explanation: The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 * Note: There are at least two nodes in this BST.
 * 
 * @author Hxkandwal
 */
public class MinimumAbsoluteDifferenceInBST extends AbstractCustomTestRunner {

	public static class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 
		 public TreeNode(int x) { val = x; }
	}
	
	// Inorder traversal. Since it's BST and inorder, we don't bother to use Math.abs().
    public int getMinimumDifferenceBetter(TreeNode root) {
        int min = Integer.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>(); 
        TreeNode cur = root, pre = null; 
        while (cur != null || !stack.empty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop(); 
                if (pre != null) 
                    min = Math.min(min, cur.val - pre.val); 
                pre = cur; 
                cur = cur.right; 
            }
        }
        return min; 
    }
    
	public int getMinimumDifference(TreeNode root) {
        return getMin (null, root, Integer.MAX_VALUE);
    }
    
    private int getMin (TreeNode parent, TreeNode node, int min) {
        if (parent != null) 
            min = Math.min (min, Math.abs (node.val - parent.val));
        
        if (node.left != null) {
            TreeNode t = node.left;
            while (t.right != null) t = t.right;
            min = Math.min (min, Math.abs (node.val - t.val));
            min = Math.min (min, getMin (node, node.left, min));
        }
        
        if (node.right != null) {
            TreeNode t = node.right;
            while (t.left != null) t = t.left;
            min = Math.min (min, Math.abs (node.val - t.val));
            min = Math.min (min, getMin (node, node.right, min));
        }
        
        return min;
    }
    
}
