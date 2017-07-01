package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 100. Same Tree
 * 
 * Given two binary trees, write a function to check if they are equal or not.
 * 
 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 * 
 * @author Hxkandwal
 */
public class SameTree extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q ==  null) return p == null && q == null;
        return p.val == q.val && isSameTree (p.left, q.left) && isSameTree (p.right, q.right);
    }
	
}
