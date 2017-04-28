package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 148. Sort List
 * 
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 * @author Hxkandwal
 *
 */
public class SortList extends AbstractCustomTestRunner {
	
	private static SortList _instance = new SortList();

	public static class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) { val = x; next = null; } 
		public String toString() { return "" + val; }
	}
	
	public ListNode _sortList(ListNode head) {
		if (head == null || head.next == null) return head;
		
		// step 1. cut the list to two halves
		ListNode prev = null, slow = head, fast = head;
		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		prev.next = null;
		
		// step 2. sort each half
		ListNode l1 = _sortList(head), l2 = _sortList(slow);
		    
		// step 3. merge l1 and l2
		return merge(l1, l2);
	}
	
	private ListNode merge(ListNode l1, ListNode l2) {
		ListNode l = new ListNode(0), p = l;
		
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) { p.next = l1; l1 = l1.next; } 
			else { p.next = l2; l2 = l2.next; }
			p = p.next;
		}
		
		if (l1 != null) p.next = l1;
		if (l2 != null) p.next = l2;
		return l.next;
	}
    
	// driver method
	public static void main(String[] args) {
		ListNode head = new ListNode(2);
		head.next = new ListNode(1);
		
		_instance.runTest(head, head.next);
	}

	public void runTest(final ListNode head, final ListNode expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { head });

		for (Object answer : answers)
			assertThat((ListNode) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
