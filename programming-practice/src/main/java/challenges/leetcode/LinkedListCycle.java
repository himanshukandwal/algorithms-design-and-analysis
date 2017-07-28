package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 141. Linked List Cycle
 * 
 * Given a linked list, determine if it has a cycle in it.
 * 
 * Follow up: Can you solve it without using extra space?
 * 
 * @author Hxkandwal
 */
public class LinkedListCycle extends AbstractCustomTestRunner {

	private static LinkedListCycle _instance = new LinkedListCycle();
	
	public static class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) { val = x; next = null; } 
	}
	
    public boolean _hasCycle(ListNode head) {
    	if (head == null || head.next == null) return false;
        ListNode slow = head, fast = slow.next;
        while (fast.next != null && fast.next.next != null && fast != slow) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return fast == slow;
    }
	
	// driver method
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = head;
		
		_instance.runTest(head, true);
		
		head = new ListNode(3);
		head.next = new ListNode(2);
		head.next.next = new ListNode(0);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = head.next;
		
		_instance.runTest(head, true);
	}

	public void runTest(final ListNode head, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { head });

		for (Object answer : answers)
			assertThat((boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
