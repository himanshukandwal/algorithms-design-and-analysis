package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists
 * 
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * 		[
 * 			1->4->5,
 * 		 	1->3->4,
 * 		 	2->6
 * 		]
 *
 * Output: 1->1->2->3->4->4->5->6
 * 
 * @author Hxkandwal
 */
public class MergeKSortedLists extends AbstractCustomTestRunner {
	
	private static MergeKSortedLists _instance = new MergeKSortedLists();

	public static class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) { val = x; next = null; } 
	}
	
	public ListNode _mergeKLists(ListNode[] lists) {
		ListNode dh = new ListNode (0), dt = dh;
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode list : lists) if (list != null) heap.offer (list);
        while (!heap.isEmpty()) {
            ListNode node = heap.poll ();
            dt = dt.next = node;
            if (node.next != null) heap.offer (node.next);
        }
        return dh.next;
    }

	// Divide and Conquer approach
	public ListNode _mergeKListsDnC(ListNode[] lists) {
		if (lists.length == 0) return null;
		return merge (0, lists.length - 1, lists);
	}

	private ListNode merge(int start, int end, ListNode[] lists) {
		if (start >= end) return lists[start];
		int mid = start + (end - start) / 2;
		ListNode left = merge(start, mid, lists);
		ListNode right = merge(mid + 1, end, lists);

		ListNode dh = new ListNode(0), t = dh;
		while (left != null && right != null) {
			if (left.val > right.val) {
				t = t.next = right;
				right = right.next;
			} else {
				t = t.next = left;
				left = left.next;
			}
		}
		if (left != null) t.next = left;
		if (right != null) t.next = right;

		return dh.next;
	}
}
