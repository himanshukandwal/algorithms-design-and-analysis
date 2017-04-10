package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * 
 * @author Hxkandwal
 */
public class LowestCommonAncestorOfBinarySearchTree extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
		public String toString() { return "(" + val + ")"; }
	}
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor (root.left, p, q);
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor (root.right, p, q);
        else return root;
    }
	
}
