package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * medallia challenge.
 * 
 * @author Hxkandwal
 *
 */
public class CorruptedLinkedList extends AbstractCustomTestRunner {
	
	private static CorruptedLinkedList _instance = new CorruptedLinkedList();
	
	private CorruptedLinkedList() {}

	/**
	 * Data structures.s
	 */
	public static class Node {
		private final Node next;

		Node(Node next) {
			this.next = next;
		}

		Node next() {
			return next;
		}
	}

	public static int _findCorruptedIndex(Node a, Node b) {
		Node pointerA = a, pointerB = b;

		Set<Integer> bhashes = new HashSet<>();

		while (pointerB != null) {
			bhashes.add(pointerB.hashCode());
			pointerB = pointerB.next();
		}

		int position = -1;
		while (pointerA != null) {
			if (bhashes.contains(pointerA.hashCode()))
				return position;

			position++;
			pointerA = pointerA.next();
		}

		return -1;
	}
	
	public static void main(String[] args) {
		_instance.runTest(5, 5, -1, -1);
	}

	public void runTest(final int aLen, final int bLen, final int bIdx, final int expectedOutput) {
		Node aLast = null;
		Node bLast = null;
		Node bHead = null;
		for (int i = 0; i < bLen; i++) {
			bHead = new Node(bLast);
			bLast = bHead;
			if (i == bLen - bIdx - 1) {
				aLast = bHead;
			}
		}

		Node aHead = null;
		for (int i = 0; i < aLen; i++) {
			aHead = new Node(aLast);
			aLast = aHead;
		}

		List<Object> answers = runAll(getClass(), new Object[] { aHead, bHead });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
