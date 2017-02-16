package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 142. Linked List Cycle II
 * 
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * 
 * @author Hxkandwal
 *
 */
public class LinkedListCycleII extends AbstractCustomTestRunner {
	
	private static LinkedListCycleII _instance = new LinkedListCycleII();
	
	private LinkedListCycleII() {}
	
	public static class ListNode {
		int val;
		ListNode next;
		
		public ListNode(int x) { val = x; next = null; } 
		
		public String toString() { return String.valueOf(val); }
	}
	
	// works perfectly.
    public ListNode _detectCycleFastEqual(ListNode head) {
    	if (head != null && head.next != null) {
    		ListNode slow = head, fast = head, finder = head;
    		
    		while (fast != null && fast.next != null) {
    			slow = slow.next;
    			fast = fast.next.next;
    			
    			if (fast == slow) break;
    		}
    		
    		if (fast != slow) return null;
    		else {
    			while (finder != slow) {
    				finder = finder.next;
    				slow = slow.next;
    			}
    			return slow;
    		}
    	}
    	
    	return null;
    }
	
	// extra work needed
    public ListNode detectCycleFastFaster(ListNode head) {
    	if (head != null && head.next != null) {
    		ListNode slow = head, fast = slow.next, finder = head, start = head;
    		
    		while (fast != null && fast.next != null && fast != slow) {
    			slow = slow.next;
    			fast = fast.next.next;
    		}
    		
    		if (fast != slow) return null;
    		else {
    			finder = slow;
    			while (finder != start) {
    				do { finder = finder.next; } while (finder != slow && finder != start);
    			
    				if (finder != start)  start = start.next;
    				else break;
    			}
    			return start;
    		}
    	}
    	
    	return null;
    }

	// driver method
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = head;
		
		_instance.runTest(head, head);
		
		head = new ListNode(3);
		head.next = new ListNode(2);
		head.next.next = new ListNode(0);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = head.next;
		
		_instance.runTest(head, head.next);
	}

	public void runTest(final ListNode head, final ListNode expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { head });

		for (Object answer : answers)
			assertThat((ListNode) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}    
    
}
