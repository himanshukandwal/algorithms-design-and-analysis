package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 109. Convert Sorted List to Binary Search Tree
 * 
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * 
 * @author Hxkandwal
 */
public class ConvertSortedListToBinarySearchTree extends AbstractCustomTestRunner {

	public class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) { val = x; }
	}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        ListNode mid = getMid (head);
        ListNode left = head, right = mid.next;
        TreeNode c = new TreeNode (mid.val);
        
        while (left != null && left.next != mid) left = left.next;
        if (left != null) {
            left.next = null; left = head;
            mid.next = null;    
            c.left = sortedListToBST (left);
        }
        if (right != null) c.right = sortedListToBST (right);
        return c;
    }
    
    private ListNode getMid (ListNode node) {
        ListNode fast = node, slow = node;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
	
}
