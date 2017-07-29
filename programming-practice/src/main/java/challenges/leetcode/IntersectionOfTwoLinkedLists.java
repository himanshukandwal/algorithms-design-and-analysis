package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 160. Intersection of Two Linked Lists
 * 
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * For example, the following two linked lists:
 * 
 * A:          a1 → a2
 * 		                ↘
 * 		                  c1 → c2 → c3
 * 		                ↗
 * B:     	b1 → b2 → b3
 * 
 * begin to intersect at node c1.
 * 
 * Notes:
 * 		If the two linked lists have no intersection at all, return null.
 * 		The linked lists must retain their original structure after the function returns.
 * 		You may assume there are no cycles anywhere in the entire linked structure.
 * 		Your code should preferably run in O(n) time and use only O(1) memory.
 * 
 * @author Hxkandwal
 */
public class IntersectionOfTwoLinkedLists extends AbstractCustomTestRunner {
	
	public class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) { val = x; }
	}
	
	public ListNode _getIntersectionNode(ListNode headA, ListNode headB) {
		int lenA = 0, lenB = 0;
        for (ListNode traverser = headA; traverser != null; traverser = traverser.next) lenA ++;
        for (ListNode traverser = headB; traverser != null; traverser = traverser.next) lenB ++;
        int diff = Math.abs (lenA - lenB);
        ListNode larger = (lenA > lenB) ? headA : headB;
        ListNode smaller = (larger == headA) ? headB : headA;
        
        while (diff -- > 0) larger = larger.next;
        while (larger != smaller) { larger = larger.next; smaller = smaller.next; }
        return larger; 
    }

}
