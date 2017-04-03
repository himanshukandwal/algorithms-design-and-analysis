package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 82. Remove Duplicates from Sorted List II
 * 
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from 
 * the original list.
 * 
 * For example,
 * 		Given 1->2->3->3->4->4->5, return 1->2->5.
 * 		Given 1->1->1->2->3, return 2->3.
 * 
 * @author Hxkandwal
 */
public class RemoveDuplicatesFromSortedListII extends AbstractCustomTestRunner {
	
	public class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) { val = x; }
	}

}
