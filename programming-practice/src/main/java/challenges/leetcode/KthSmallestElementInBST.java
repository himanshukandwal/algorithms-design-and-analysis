package challenges.leetcode;

import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 230. Kth Smallest Element in a BST
 * 
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * 
 * Note: You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * 
 * Follow up: What if the BST is modified (insert/delete operations) often and you need to find the kth smallest 
 * frequently? How would you optimize the kthSmallest routine?
 *  
 * @author Hxkandwal
 */
public class KthSmallestElementInBST extends AbstractCustomTestRunner {
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}

	public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> bstk = new Stack<>();
        int smallest = 0;
        TreeNode node = root;
        
        while (node != null) {
            while (node != null) {  bstk.push (node);  node = node.left; }    
            smallest ++; node = bstk.pop();
            
            if (smallest == k) return node.val;
            while (node.right == null && !bstk.isEmpty()) { 
                node = bstk.pop(); smallest ++; 
                if (smallest == k) return node.val;
            }
            node = node.right;
        }
        return 0;
    }
	
}
