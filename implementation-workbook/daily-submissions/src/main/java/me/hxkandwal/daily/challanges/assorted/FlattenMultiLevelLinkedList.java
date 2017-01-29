package me.hxkandwal.daily.challanges.assorted;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Flatten a multi-level linked list
 * 
 * Given a linked list with both next and down pointers, flatten the list into a single linked list.
 * For example, given
 * 
 * A---B---C----D--E---F--NULL
 *              |
 *              G---H---I---J--NULL
 *                      |
 *                     K---L--NULL
 *                     
 * should become A-B-C-G-H-K-L-I-J-D-E-F-NULL
 *
 * link: https://discuss.leetcode.com/topic/51658/flatten-a-multi-level-linked-list
 * 
 * @author Hxkandwal
 *
 */
public class FlattenMultiLevelLinkedList extends AbstractCustomTestRunner {
	
	private static FlattenMultiLevelLinkedList _instance = new FlattenMultiLevelLinkedList();
	
	private FlattenMultiLevelLinkedList() {}
	
	// linked list data-structure.
	public static class DualDirectedLinkedList {
		
		private String value;
		private DualDirectedLinkedList next;
		private DualDirectedLinkedList down;
		
		public DualDirectedLinkedList(String value) {
			this.value = value;
		}
		
		public DualDirectedLinkedList(String value, DualDirectedLinkedList next, DualDirectedLinkedList down) {
			this (value);
			this.next = next;
			this.down = down;
		}
		
		public String getValue() {
			return value;
		}
		
		public DualDirectedLinkedList getNext() {
			return next;
		}
		
		public void setNext(DualDirectedLinkedList next) {
			this.next = next;
		}
		
		public DualDirectedLinkedList getDown() {
			return down;
		}
		
		public void setDown(DualDirectedLinkedList down) {
			this.down = down;
		}
		
	}
	
	public static String _flattenedList(DualDirectedLinkedList head) {
		StringBuilder answer = new StringBuilder();
		
		return null;
	}
	
	private static void innerRecursion(DualDirectedLinkedList node, StringBuilder answer) {
		while (node != null) {
			answer.append(node.value);
			
			if (node.next == null)
				return;
			
			answer.append("-");
			
			if (node.next.down != null) {
				innerRecursion(node.next.down, answer);
				answer.append("-");
			}
		}
	}

}
