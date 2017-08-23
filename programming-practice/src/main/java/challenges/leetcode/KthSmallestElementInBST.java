package challenges.leetcode;

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
        TreeNode n = root;
        while (n != null) {
            if (n.left == null) { if (k == 1) return n.val; k --; n = n.right; continue; }
            TreeNode pre = n.left;
            while (pre.right != null && pre.right != n) pre = pre.right;
            if (pre.right == null) {
                pre.right = n;
                n = n.left;
            } else {
                pre.right = null;
                if (k == 1) return n.val; k --; n = n.right;
            }
        }
        return 0;
    }
	
}
