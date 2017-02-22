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
        Object [] res = recurse (head, head);
        return (Boolean) res [0];
    }
    
    private Object[] recurse (ListNode node, ListNode head) {
        if (node == null) return new Object [] { Boolean.valueOf(true), head } ;
        Object [] res = recurse (node.next, head);
        boolean ans = ((Boolean) res [0]) && ((ListNode) res [1]).val == node.val;
        return new Object [] { Boolean.valueOf(ans), ((ListNode) res [1]).next };
    }
    
}
