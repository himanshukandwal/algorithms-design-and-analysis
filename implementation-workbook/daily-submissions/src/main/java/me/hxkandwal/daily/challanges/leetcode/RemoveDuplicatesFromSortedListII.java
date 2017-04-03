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
	
	public ListNode _deleteDuplicates(ListNode head) {
        ListNode traverser = head, storage = null, newHead = null, newTraverser = null;
        boolean toAdd = true;
        while (traverser != null) {
            if (storage != null) { 
                if (traverser.val != storage.val) { 
                    if (toAdd) {
                        if (newTraverser == null) newHead = newTraverser = storage;
                        else { newTraverser.next = storage; newTraverser = newTraverser.next; }
                    }
                    toAdd = true;
                } else toAdd = false;
            }
            storage = traverser;
            traverser = traverser.next;
            storage.next = null;
        }
        if (toAdd) {
            if (newTraverser == null) newHead = newTraverser = storage;
            else { newTraverser.next = storage; newTraverser = newTraverser.next; }
        }
        return newHead;
    }
	
	// cool idea.
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null) return null;
		ListNode fakeHead = new ListNode(0);
		fakeHead.next = head;
		ListNode pre = fakeHead, cur = head;
        
        while (cur != null) {
			while (cur.next != null && cur.val == cur.next.val) cur = cur.next;
			if (pre.next == cur) pre = pre.next;
			else pre.next = cur.next;
			cur = cur.next;
        }
        
        return fakeHead.next;
    }

}
