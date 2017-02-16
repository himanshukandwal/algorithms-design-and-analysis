package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

public class LinkedListCycle extends AbstractCustomTestRunner {

private static LinkedListCycle _instance = new LinkedListCycle();
	
	private LinkedListCycle() {}
	
	public static class ListNode {
		int val;
		ListNode next;
		
		public ListNode(int x) { val = x; next = null; } 
		
		public String toString() { return String.valueOf(val); }
	}
	
	// works perfectly.
    public boolean _hasCycle(ListNode head) {
    	if (head != null && head.next != null) {
    		ListNode slow = head, fast = head;
    		
    		while (fast != null && fast.next != null) {
    			slow = slow.next;
    			fast = fast.next.next;
    			
    			if (fast == slow) return true;
    		}
    	}
    	
    	return false;
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
