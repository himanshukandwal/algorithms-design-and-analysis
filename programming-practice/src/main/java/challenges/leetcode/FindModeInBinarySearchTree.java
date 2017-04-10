package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 501. Find Mode in Binary Search Tree
 * 
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) 
 * in the given BST.
 * 
 * Assume a BST is defined as follows:
 * 
 * 	The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * 	The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * 	Both the left and right subtrees must also be binary search trees.
 * 
 * For example:
 * 	Given BST [1,null,2,2],
 *	   1
 *	    \
 *	     2
 *	    /
 *	   2
 *	return [2].
 * 
 * Note: If a tree has more than one mode, you can return them in any order.
 * 
 * Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 * 
 * @author Hxkandwal
 */
public class FindModeInBinarySearchTree extends AbstractCustomTestRunner {
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	int maxCount;
    int currentVal;
    int currentCount;
    int modesCount;
    int [] modes;
    
    public int[] findMode(TreeNode root) {
        inorder (root);
        modes = new int [modesCount];
        modesCount = currentCount = 0;
        inorder (root);
        return modes;
    }
    
    public void handle (int val) {
        if (currentVal != val) { 
            currentVal = val; currentCount = 0;
        }
        currentCount ++;
        if (currentCount > maxCount) { maxCount = currentCount; modesCount = 1; }
        else if (currentCount == maxCount) { if (modes != null) modes[modesCount] = currentVal; modesCount ++; }
    }
    
    public void inorder (TreeNode node) {
        if (node == null) return;
        inorder (node.left);
        handle (node.val);
        inorder (node.right);
    }

}
