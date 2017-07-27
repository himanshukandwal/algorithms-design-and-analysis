package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.PriorityQueue;

import challenges.AbstractCustomTestRunner;

/**
 * 23. Merge k Sorted Lists
 * 
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
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
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new ListNode[] { new ListNode(1) }, new ListNode(1));
	}

	public void runTest(final ListNode[] lists, final ListNode expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { lists });

		for (Object answer : answers)
			assertThat((ListNode) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
		
}
