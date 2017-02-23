package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 270. Closest Binary Search Tree Value
 * 
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 * 
 * Note: Given target value is a floating point.
 * 
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 * 	
 * @author Hxkandwal
 */
public class ClosestBinarySearchTreeValue extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode child = target < a ? root.left : root.right;
        if (child == null) return a;
        int b = closestValue (child, target);
        return Math.abs (a - target) < Math.abs (b - target)  ? a : b;
    }
	
}
