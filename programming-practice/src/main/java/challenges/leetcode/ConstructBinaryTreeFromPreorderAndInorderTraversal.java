package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * 
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * 
 * Note: You may assume that duplicates do not exist in the tree.
 * 
 * @author Hxkandwal
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left, right;
		public TreeNode (int x) { val = x; }
	}
	
	private static int pidx = 0;
	
	public static TreeNode _buildTree (int[] preorder, int[] inorder) {
        return buildTreeInner (preorder, inorder, 0, inorder.length - 1);
    }
    
    private static TreeNode buildTreeInner (int[] preorder, int[] inorder, int inStart, int inEnd) {
    	if (pidx >= preorder.length || inStart > inEnd) return null;
        int rootVal = preorder [pidx ++];
        TreeNode parent = new TreeNode (rootVal);
        
        if (inStart == inEnd) return parent;
        if (inorder [inEnd] == rootVal) parent.left = buildTreeInner (preorder, inorder,  inStart, inEnd - 1);
        else if (inorder [inStart] == rootVal) parent.right = buildTreeInner (preorder, inorder,  inStart + 1, inEnd);
        else {
            int split = inStart;
            while (split + 2 <= inEnd && inorder [split + 1] != rootVal) split ++;    
            parent.left = buildTreeInner (preorder, inorder,  inStart, split);
            parent.right = buildTreeInner (preorder, inorder,  split + 2, inEnd);
        }
        return parent;
    }
    
    // driver method
	public static void main(String[] args) {
		printTree(_buildTree(new int [] { 3, 1, 2, 4 }, new int [] { 1, 2, 3, 4 }));
	}
	
	private static void printTree(TreeNode node) {
		if (node == null) return;
		printTree(node.left);
		System.out.print(" " + node.val);
		printTree(node.right);
	}
	
}