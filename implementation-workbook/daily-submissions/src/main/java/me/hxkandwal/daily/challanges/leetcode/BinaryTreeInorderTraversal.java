package me.hxkandwal.daily.challanges.leetcode;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 94. Binary Tree Inorder Traversal
 * 
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * 
 * For example:
 * 		Given binary tree [1,null,2,3],
 *   1
 *    \
 *     2
 *    /
 *   3
 *   
 * return [1,3,2].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 * 
 * @author Hxkandwal
 */
public class BinaryTreeInorderTraversal extends AbstractCustomTestRunner {
	
	private static BinaryTreeInorderTraversal _instance = new BinaryTreeInorderTraversal();
	
	private BinaryTreeInorderTraversal() {}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public static List<Integer> inorderTraversal(TreeNode root) {
        
		return null;
    }

}
