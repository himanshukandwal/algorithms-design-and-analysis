package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 234. Palindrome Linked List
 * 
 * Given a singly linked list, determine if it is a palindrome.
 * 
 * Follow up: Could you do it in O(n) time and O(1) space?
 * 
 * @author Hxkandwal
 */
public class PalindromeLinkedList extends AbstractCustomTestRunner {
	
	public static class ListNode {
		int val;
		ListNode next;
		
		public ListNode(int x) { val = x; next = null; } 
	}
	
	public boolean isPalindrome(ListNode head) {
        return head == null || recurse (head, head) != null;
    }
    
    private ListNode recurse (ListNode node, ListNode head) {
    	if (node == null) return head;
        ListNode res = recurse (head, node.next);
        if (res == null || res.val != node.val) return null;
        else return (res.next == null) ? head : res.next;
    }
    
}
