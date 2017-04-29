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
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode slow = head, fast = head;
        // 1. get the mid point.
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next; fast = fast.next.next;
        }
        
        // 2. reverse from the mid point
        ListNode t2 = reversal (slow.next);
        slow.next = null;
        
        // 3. stitch the lists.
        ListNode t1 = head;
        while (t1 != null && t2 != null) {
            ListNode future1 = t1.next, future2 = t2.next;
            t1 = t1.next = t2;
            t1 = t1.next = future1;
            t2 = future2;
        }
    }
    
    private ListNode reversal (ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = head, trv = head.next;
        head.next = null;
        while (trv != null) {
            ListNode future = trv.next;
            trv.next = prev;
            prev = trv;
            trv = future;
        }
        return prev;
    }
    
	// driver method
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		_instance.reorderList(head);
		
		assertThat(head.next.val).isEqualTo(3);
		System.out.println("ok!");
	}

}
