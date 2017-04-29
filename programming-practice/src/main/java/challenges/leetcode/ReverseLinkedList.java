package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import challenges.AbstractCustomTestRunner;

/**
 * 206. Reverse Linked List
 * 
 * Reverse a singly linked list.
 * 
 * @author Hxkandwal
 */
public class ReverseLinkedList extends AbstractCustomTestRunner {
	
	private static ReverseLinkedList _instance = new ReverseLinkedList();
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
    public ListNode _reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode passed = head.next;
        ListNode rHead = _reverseList(passed);
        passed.next = head;
        head.next = null;
        return rHead;
    }

	// driver method
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		
		assertThat(_instance._reverseList(head).val).isEqualTo(3);
		System.out.println("ok!");
	}
}
