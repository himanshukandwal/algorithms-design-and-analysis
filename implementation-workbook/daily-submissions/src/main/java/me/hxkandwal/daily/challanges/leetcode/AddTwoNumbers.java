package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 2. Add Two Numbers
 * 
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each 
 * of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 		
 * Example:
 * 		Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 		Output: 7 -> 0 -> 8
 * 
 * @author Hxkandwal
 */
public class AddTwoNumbers extends AbstractCustomTestRunner {

	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode (0), ans = head;
        ListNode traverser1 = l1, traverser2 = l2;
        int carry = 0;
        while (traverser1 != null || traverser2 != null) {
            int sum = 0;
            if (traverser1 != null) { sum += traverser1.val; traverser1 = traverser1.next; }
            if (traverser2 != null) { sum += traverser2.val; traverser2 = traverser2.next; }
            sum += carry;
            ans = ans.next = new ListNode (sum % 10);
            carry = sum / 10;
        }
        if (carry > 0) ans.next = new ListNode (carry);
        return head.next;
    }
}
