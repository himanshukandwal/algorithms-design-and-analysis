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
        Stack<Integer> list1 = new Stack<>(), list2 = new Stack<>(), list3 = new Stack<>();
        while (l1 != null) { list1.push (l1.val); l1 = l1.next; }
        while (l2 != null) { list2.push (l2.val); l2 = l2.next; }
        
        int carry = 0;
        while (list1.size() > 0 || list2.size() > 0) {
            int sum = 0;
            if (list1.size() > 0 && list2.size() > 0) {
                sum = list1.pop () + list2.pop () + carry;
                list3.push (sum % 10);
            } else if (list1.size() > 0) {
                sum = list1.pop () + carry;
                list3.push (sum % 10);
            } else {
                sum = list2.pop () + carry;
                list3.push (sum % 10);
            }
            carry = sum / 10;
        }
        if (carry > 0) list3.push (carry);
        
        ListNode ans = new ListNode (0), res = ans;
        while (!list3.isEmpty())  res = res.next = new ListNode (list3.pop ()); 
        return ans.next;
    }
	
}
