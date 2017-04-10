package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 108. Convert Sorted Array to Binary Search Tree
 * 
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * 
 * @author Hxkandwal
 */
public class ConvertSortedArraytoBinarySearchTree extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public TreeNode sortedArrayToBST(int[] nums) {
        return generate (nums, 0, nums.length - 1);
    }
    
    private TreeNode generate (int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = (end + start) >>> 1;
        TreeNode m = new TreeNode (nums [mid]);
        m.left = generate (nums, start, mid - 1);
        m.right = generate (nums, mid + 1, end);
        return m;
    }
    
}
