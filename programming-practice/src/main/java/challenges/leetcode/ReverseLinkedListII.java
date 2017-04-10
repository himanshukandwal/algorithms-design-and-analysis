package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import challenges.AbstractCustomTestRunner;

/**
 * 92. Reverse Linked List II
 * 
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * 
 * For example:
 * 		Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * 		return 1->4->3->2->5->NULL.
 * 
 * Note:
 * 		Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of list.
 * 
 * @author Hxkandwal
 */
public class ReverseLinkedListII extends AbstractCustomTestRunner {
	
	private static ReverseLinkedListII _instance = new ReverseLinkedListII();
	
	private ReverseLinkedListII() {}
	
	public static class ListNode {
		int val;
		ListNode next;
		
		ListNode(int x) { val = x; }
		
		@Override
		public String toString() {
			return "(" + val + ")";
		}
	}
	
    public ListNode _reverseBetween(ListNode head, int m, int n) {
    	if (head == null || head.next == null || m == n) return head;
    	int count = 1;
    	ListNode traverserHead = head, traverser = head;
    	if (m == 1) {
    		while (count < n + 1) { traverser = traverser.next; count ++; }
    		
	    	ListNode rHead = reverser (traverserHead, traverser);
	    	traverserHead.next = traverser;
			return rHead;
    	} else {
    		while (count < m - 1 && traverserHead.next != null) { traverserHead = traverserHead.next; count ++; }
    		ListNode passed = traverserHead.next;
    				
    		traverser = passed; count = m;
    		while (count < n + 1) { traverser = traverser.next; count ++; }
    		
    		ListNode rHead = reverser (passed, traverser);
    		passed.next = traverser;
    		traverserHead.next = rHead;
    		return head;
    	}
    }

    private ListNode reverser(ListNode node, ListNode end) {
    	if (node.next == end) { return node; }
    	else {
    		ListNode passed = node.next;
    		ListNode rHead = reverser(passed, end);
    		passed.next = node;
    		node.next = null;
    		return rHead;
    	}
    }

	// driver method
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		ListNode answer = _instance._reverseBetween(head, 2, 3);
		assertThat(answer.val).isEqualTo(1);
		assertThat(answer.next.val).isEqualTo(3);
		assertThat(answer.next.next.val).isEqualTo(2);
		System.out.println("ok!");
		
		head = new ListNode(3);
		head.next = new ListNode(5);
		answer = _instance._reverseBetween(head, 1, 2);
		assertThat(answer.val).isEqualTo(5);
		assertThat(answer.next.val).isEqualTo(3);
		System.out.println("ok!");
	}    
    
}
