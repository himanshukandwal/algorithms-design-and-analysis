package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 203. Remove Linked List Elements
 * 
 * Remove all elements from a linked list of integers that have value val.
 * 
 * Example:
 * 		Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 * 		Return: 1 --> 2 --> 3 --> 4 --> 5
 * 
 * @author Hxkandwal
 */
public class RemoveLinkedListElements extends AbstractCustomTestRunner {
	
	private static RemoveLinkedListElements _instance = new RemoveLinkedListElements();
	
	private RemoveLinkedListElements() {}
	
	public static class ListNode {
		int val;
		ListNode next;
		
		ListNode(int x) { val = x; }
		
		@Override
		public String toString() {
			return "(" + val + ")";
		}
	}
	
    public ListNode _removeElements(ListNode head, int val) {
    	if (head == null) return head;
    	ListNode traverser = null, traverserHead = head;
    	
    	while (traverserHead != null && traverserHead.val == val) traverserHead = traverserHead.next;
    	if (traverserHead != null) {
    		traverser = traverserHead;
    		
    		while (traverser.next != null) {
    			if (traverser.next.val == val)
    				traverser.next = traverser.next.next;
    			else traverser = traverser.next;
    		}
    	}
    	return traverserHead;
    }
    
	// driver method
	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(3);
		node.next.next.next = new ListNode(4);
		
		_instance._removeElements(node, 3);
		assertThat(node.next.next.val).isEqualTo(4); 
		System.out.println("ok!");
	}     

}
