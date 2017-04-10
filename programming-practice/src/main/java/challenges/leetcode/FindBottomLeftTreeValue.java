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
        int[] ans = findval (root.left, new int[] { 0, root.val }, 1);
        ans = findval (root.right, ans, 1);
        return ans [1];
    }
    
    private int[] findval (TreeNode node, int[] bestAnswer, int depth) {
        if (node == null) return bestAnswer;
        if (node.left == null && node.right == null) { 
            if (depth > bestAnswer [0])
                return new int [] { depth, node.val };
            return bestAnswer;
        } 
        
        int [] ans = findval (node.left, bestAnswer, depth + 1);
        ans = findval (node.right, ans, depth + 1);
        return ans;
    }
    
}
