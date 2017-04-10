package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import challenges.AbstractCustomTestRunner;

/**
 * 143. Reorder List
 * 
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * You must do this in-place without altering the nodes' values.
 * 
 * For example,
 * 		Given {1,2,3,4}, reorder it to {1,4,2,3}.
 * 
 * @author Hxkandwal
 */
public class ReorderList extends AbstractCustomTestRunner {
	
	private static ReorderList _instance = new ReorderList();
	
	private ReorderList() {}
	
	public static class ListNode {
		int val;
		ListNode next;
		
		ListNode(int x) { val = x; }
		
		@Override
		public String toString() {
			return "(" + val + ")";
		}
	}
	
    public void _reorderList(ListNode head) {
    	if (head != null && head.next != null) {
	    	ListNode startTraverser = head, endGoalFollower = head, endGoal = null;
	    	while (endGoalFollower.next != endGoal) endGoalFollower = endGoalFollower.next;
	    	endGoal = endGoalFollower;
	    	endGoalFollower = head;
	    	
	    	while (startTraverser != endGoal) {
	    		while (endGoalFollower.next != endGoal) endGoalFollower = endGoalFollower.next;
	    		if (endGoalFollower == startTraverser) break;
	    		
	    		ListNode future = startTraverser.next;
	    		startTraverser.next = endGoalFollower.next;
	    		startTraverser.next.next = future;
	    		endGoalFollower.next = null;
	    		endGoal = endGoalFollower;
	    		startTraverser = endGoalFollower = future;
	    	}
    	}
    }

	// driver method
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		_instance._reorderList(head);
		
		assertThat(head.next.val).isEqualTo(3);
		System.out.println("ok!");
	}

}
