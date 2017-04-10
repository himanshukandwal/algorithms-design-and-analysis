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
	
	private SortList() {}

	public static class ListNode {
		int val;
		ListNode next;
		
		public ListNode(int x) { val = x; next = null; } 
		
		public String toString() { return String.valueOf(val); }
	}
	
    public ListNode _sortList(ListNode head) {
    	if (head != null && head.next != null) 
    		return mergeSort(head, null);
    	return head;
    }
    
    private ListNode mergeSort (ListNode start, ListNode end) {
    	if (start != end && start.next != end) {
    		ListNode mid = getMidElement(start, end);
    		
    		mergeSort(start, mid);
    		mergeSort(mid.next, end);
    		return merge (start, mid, end);
    	}
    	return start;
    }
    
    private ListNode merge (ListNode start, ListNode mid, ListNode end) {
    	ListNode head = null, builder = null;
    	
    	while (start != mid && mid != end) {
    		if (start.val <= mid.val) { 
    			if (head == null) head = builder = start; 
    			else builder.next = start;
    			
    			start = start.next;
    			builder.next = null;
    		} else {
    			ListNode previous = start;
    			while (previous.next != mid) previous = previous.next;
    			
    			if (head == null) head = builder = previous.next;
    			else builder.next = previous.next;
    			
    			mid = mid.next;
    			builder.next = null;
    			previous.next = mid;
    		}
    	}
    	
    	if (start == mid) builder.next = mid;
    	if ( mid == end) builder.next = start;
    	
    	return head;
    }
    
    private ListNode getMidElement(ListNode node, ListNode end) {
		ListNode slow = node, fast = node;
		while (fast != end && (end == null || (end != null && fast != end.next))) {
			fast = fast.next;
			if (fast == null || fast.next == null) break;
			else {
				fast = fast.next;
				slow = slow.next;
			}
		}
		return slow;
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
