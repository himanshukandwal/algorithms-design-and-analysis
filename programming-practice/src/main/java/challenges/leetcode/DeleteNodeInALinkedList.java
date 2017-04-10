package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import challenges.AbstractCustomTestRunner;

/**
 * 237. Delete Node in a Linked List
 * 
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 * 
 * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list 
 * should become 1 -> 2 -> 4 after calling your function.
 * 
 * @author Hxkandwal
 */
public class DeleteNodeInALinkedList extends AbstractCustomTestRunner {
	
	private static DeleteNodeInALinkedList _instance = new DeleteNodeInALinkedList();
	
	private DeleteNodeInALinkedList() {}
	
	public static class ListNode {
		int val;
		ListNode next;
		
		ListNode(int x) { val = x; }
		
		@Override
		public String toString() {
			return "(" + val + ")";
		}
	}
	
    public void _deleteNode(ListNode node) {
    	node.val=node.next.val;
        node.next=node.next.next;
    }
	
	// driver method
	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(3);
		node.next.next.next = new ListNode(4);
		
		_instance._deleteNode(node.next.next);
		assertThat(node.next.next.next).isNull(); 
		System.out.println("ok!");
	}   

}
