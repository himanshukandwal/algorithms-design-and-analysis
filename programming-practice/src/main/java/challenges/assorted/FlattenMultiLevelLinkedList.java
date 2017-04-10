package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

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
 * should become A-B-C-G-H-K-L-I-J-D-E-F
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
		
		@Override
		public String toString() {
			return new StringBuilder("[").append(getValue()).append("]").toString();
		}
		
	}
	
	public static String _flattenedList(DualDirectedLinkedList head) {
		if (head == null)
			return null;
		
		StringBuilder answer = new StringBuilder();
		innerRecursion(head, answer);
		return answer.toString();
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
			
			node = node.next;
		}
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(null, null);
		
		// Dual-directed Linked list construction.
		DualDirectedLinkedList head = new DualDirectedLinkedList("A");
		DualDirectedLinkedList builder = head;
		builder = builder.next = new DualDirectedLinkedList("B");
		builder = builder.next = new DualDirectedLinkedList("C");
		builder = builder.next = new DualDirectedLinkedList("D");
		DualDirectedLinkedList secondBuilder = builder;
		builder = builder.next = new DualDirectedLinkedList("E");
		builder = builder.next = new DualDirectedLinkedList("F");
		secondBuilder = secondBuilder.down = new DualDirectedLinkedList("G");
		secondBuilder = secondBuilder.next = new DualDirectedLinkedList("H");
		secondBuilder = secondBuilder.next = new DualDirectedLinkedList("I");
		DualDirectedLinkedList thirdBuilder = secondBuilder;
		secondBuilder = secondBuilder.next = new DualDirectedLinkedList("J");
		thirdBuilder = thirdBuilder.down = new DualDirectedLinkedList("K");
		thirdBuilder = thirdBuilder.next = new DualDirectedLinkedList("L");
		
		_instance.runTest(head, "A-B-C-G-H-K-L-I-J-D-E-F");
	}
	
	public void runTest(final DualDirectedLinkedList head, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { head });
		
		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
