package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 285. Inorder Successor in BST
 * 
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * 
 * Note: If the given node has no in-order successor in the tree, return null.
 * 
 * @author Hxkandwal
 */
public class InorderSuccessorInBST extends AbstractCustomTestRunner {
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		if (root == null) return null;

		if (root.val <= p.val) return inorderSuccessor(root.right, p);
		TreeNode left = inorderSuccessor(root.left, p);
		return (left != null) ? left : root;
	}
}
