package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 110. Balanced Binary Tree
 * 
 * Given a binary tree, determine if it is height-balanced.
 * 
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two 
 * subtrees of every node never differ by more than 1.
 * 
 * @author Hxkandwal
 */
public class BalancedBinaryTree extends AbstractCustomTestRunner {
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        if (Math.abs (maxDepth (root.left) - maxDepth (root.right)) > 1) return false;
        return isBalanced (root.left) && isBalanced(root.right);
    }
    
    private int maxDepth(TreeNode node) {
        if (node == null) return 0;
        return Math.max(maxDepth (node.left), maxDepth (node.right)) + 1;
    }

}
