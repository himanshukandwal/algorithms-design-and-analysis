package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 543. Diameter of Binary Tree
 * 
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is 
 * the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * 
 * Example:
 * 	Given a binary tree 
 * 	          1
 * 	         / \
 * 	        2   3
 * 	       / \     
 * 	      4   5    
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * 
 * @author Hxkandwal
 */
public class DiameterOfBinaryTree extends AbstractCustomTestRunner {
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int max = height (root.left) + height (root.right);
        return Math.max (max, Math.max (diameterOfBinaryTree(root.left), diameterOfBinaryTree (root.right)));
    }
    
    private int height (TreeNode node) {
        return (node == null) ? 0 : 1 + Math.max (height (node.left), height (node.right));
    }

}
