package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 156. Binary Tree Upside Down
 * 
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) 
 * or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. 
 * 
 * Return the new root.
 * 
 * For example:
 * 		Given a binary tree {1,2,3,4,5},
 * 	
 *    			 1
 *   			/ \
 *  		   2   3
 * 			  / \
 *			 4   5
 * 
 * return the root of the binary tree [4,5,2,#,#,3,1].
 * 
 * 				4
 * 			   / \
 * 			  5   2
 * 				 / \
 * 				3   1
 * 
 * @author Hxkandwal
 */
public class BinaryTreeUpsideDown extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left, right;
		public TreeNode(int x) { val = x; }
	}
	
	public TreeNode upsideDownBinaryTree(TreeNode root) {
        return reverser (root);
    }
    
    private TreeNode reverser (TreeNode node) {
        if (node == null || node.left == null) return node;
        TreeNode left = reverser (node.left);
        node.left.right = node;
        node.left.left = node.right;
        node.left = node.right = null;
        return left;
    }
    
}
