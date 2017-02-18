package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 226. Invert Binary Tree Add to List
 * 
 * Invert a binary tree.
 * 
 *         4
 *    	 /   \
 *		2     7
 *	   / \   / \
 *	  1   3 6   9
 *
 * to
 *    	   4
 *    	 /   \
 *    	7     2
 *     / \   / \
 *    9   6 3   1
 * 
 * @author Hxkandwal
 */
public class InvertBinaryTree extends AbstractCustomTestRunner {
	
	private static InvertBinaryTree _instance = new InvertBinaryTree();
	
	public InvertBinaryTree() {}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public TreeNode invertTree (TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return root;
        
        if (root.left == null && root.right != null) {
        	invertTree(root.right);
        	root.left = root.right;
        	root.right = null;
        } else if (root.left != null && root.right == null) {
        	invertTree(root.left);
        	root.right = root.left;
        	root.left = null;
        } else {
        	invertTree(root.left);
        	invertTree(root.right);
        	TreeNode swapper = root.left;
        	root.left = root.right;
        	root.right = swapper;
        }
        	
		return root;
    }
	
}
