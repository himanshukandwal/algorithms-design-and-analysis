package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 538. Convert BST to Greater Tree
 * 
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the 
 * original key plus sum of all keys greater than the original key in BST.
 * 
 * Example:
 * 		Input: The root of a Binary Search Tree like this:
 * 				  5
 * 			  	/   \
 * 			   2     13
 * 
 * 		Output: The root of a Greater Tree like this:
 * 				 18
 * 				/   \
 * 			  20     13
 * 
 * @author Hxkandwal
 */
public class ConvertBSTToGreaterTree extends AbstractCustomTestRunner {

	public class TreeNode {
		int val;
		TreeNode left, right;
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode convertBST(TreeNode root) {
		convert (root, 0);
        return root;
    }
    
    private int convert (TreeNode node, int max) {
        if (node == null) return max;
        int rval = convert (node.right, max);
        int lval = convert (node.left, node.val + rval);
        node.val += rval;
        return lval;
    }
    
}
