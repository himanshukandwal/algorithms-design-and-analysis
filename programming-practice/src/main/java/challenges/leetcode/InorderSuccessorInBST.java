package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Stack;

/**
 * 285. Inorder Successor in BST
 * 
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * 
 * Note: If the given node has no in-order successor in the tree, return null.
 *
 * Example 1:
 *
 * 		Input: root = [2,1,3], p = 1
 *
 * 					2
 * 	   			   / \
 * 	 			  1   3
 * 		Output: 2
 *
 * Example 2:
 *
 * 		Input: root = [5,3,6,2,4,null,null,1], p = 6
 *
 * 							5
 * 						   / \
 * 						  3   6
 * 						 / \
 * 						2   4
 * 					   /
 * 					  1
 *
 * 		Output: null
 * 
 * @author Hxkandwal
 */
public class 	InorderSuccessorInBST extends AbstractCustomTestRunner {
	
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


	// iterative approach
	public TreeNode _inorderSuccessor(TreeNode root, TreeNode p) {
		if (root == null) return null;
		while (p.val > root.val) root = root.right;
		Stack<TreeNode> stk = new Stack<>();
		while (root.val != p.val) {
			stk.push (root);
			root = root.val > p.val ? root.left : root.right;
		}
		// check child path, if present
		if (root.right != null) {
			root = root.right;
			while (root.left != null) root = root.left;
			return root;
		}
		// check parent path, if present
		while (!stk.isEmpty()) {
			TreeNode n = stk.pop();
			if (n.val > p.val) return n;
		}
		return null;
	}
}
