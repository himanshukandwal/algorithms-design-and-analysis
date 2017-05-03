package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 513. Find Bottom Left Tree Value
 * 
 * Given a binary tree, find the leftmost value in the last row of the tree.
 * 
 * 	Example 1:
 * 	Input:
 * 	
 * 	    2
 * 	   / \
 * 	  1   3
 * 	
 * 	Output:	1
 * 
 * 	Example 2: 
 * 	Input:
 * 	
 * 	        1
 * 	       / \
 * 	      2   3
 * 	     /   / \
 * 	    4   5   6
 * 	       /
 * 	      7
 * 	
 * 	Output: 7
 * 
 * Note: You may assume the tree (i.e., the given root node) is not NULL.
 * 
 * @author Hxkandwal
 *
 */
public class FindBottomLeftTreeValue extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public int findBottomLeftValue(TreeNode root) {
		return find (root, 0, new int [] { root.val, 0 })[0];
    }
    
    private int [] find (TreeNode node, int level, int [] prev) {
        if (node == null) return prev;
        int [] ret = find (node.left, level + 1, prev [1] < level ? new int [] { node.val, level } : prev);
        ret = find (node.right, level + 1, ret);
        return ret;
    }
    
}
