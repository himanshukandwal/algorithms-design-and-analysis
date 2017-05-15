package challenges.leetcode;

import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 445. Add Two Numbers II
 * 
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and 
 * each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 * 
 * Example:
 * 		Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 		Output: 7 -> 8 -> 0 -> 7
 * 
 * @author Hxkandwal
 */
public class AddTwoNumbersII extends AbstractCustomTestRunner {

	public class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) { val = x; }
	}
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		Stack<Integer> list1 = new Stack<>(), list2 = new Stack<>();
        while (l1 != null) { list1.push (l1.val); l1 = l1.next; }
        while (l2 != null) { list2.push (l2.val); l2 = l2.next; }
        
        int sum = 0;
        ListNode list = new ListNode(0);
        while (list1.size() > 0 || list2.size() > 0) {
            if (!list1.empty()) sum += list1.pop();
            if (!list2.empty()) sum += list2.pop();
            
            list.val = sum % 10;
            ListNode head = new ListNode (sum / 10);
            head.next = list;
            list = head;
            sum /= 10;
        }
        return list.val == 0 ? list.next : list;
    }
	
}
