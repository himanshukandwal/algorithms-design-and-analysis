package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 147. Insertion Sort List
 * 
 * Sort a linked list using insertion sort.
 * 
 * @author Hxkandwal
 */
public class InsertionSortList extends AbstractCustomTestRunner {
	
	private static InsertionSortList _instance = new InsertionSortList();
	
	private InsertionSortList() {}

	public static class ListNode {
		int val;
		ListNode next;
		
		public ListNode(int x) { val = x; next = null; } 
		
		public String toString() { return String.valueOf(val); }
	}
	
	public ListNode _insertionSortList(ListNode head) {
		if (head != null && head.next != null) {
			ListNode current = head.next, previous = head, future = current;
			
			while (current != null) {
				if (previous.val <= current.val) current = (previous = current).next;
				else {
					previous.next = future = current.next;
					current.next = null;
					ListNode traverser = head;
					
					if (traverser.val > current.val) { 
						current.next = head; head = current; 
					} else {
						while (traverser.next != previous && traverser.next.val < current.val) traverser = traverser.next;
						current.next = traverser.next;
						traverser.next = current;
					}
					current = future;
				}
			}			
		}
		
		return head;
    }

}
