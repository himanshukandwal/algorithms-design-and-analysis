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
        int l1 = 0, l2 = 0;
        for (ListNode ln = headA; ln != null; ln = ln.next, l1 ++);
        for (ListNode ln = headB; ln != null; ln = ln.next, l2 ++);
        if (l1 > l2) while (l2 != l1) { headA = headA.next; l2 ++; }
        if (l1 < l2) while (l2 != l1) { headB = headB.next; l1 ++; }
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

}
