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
		TreeNode left, right;
		public TreeNode(int x) { val = x; }
	}
	
	private int height (TreeNode node) {
        if (node == null) return 0;
        return Math.max (height (node.left), height (node.right)) + 1;
    }
    
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs (height (root.left) - height (root.right)) <= 1 && isBalanced (root.left) && isBalanced (root.right);
    }
    
}
