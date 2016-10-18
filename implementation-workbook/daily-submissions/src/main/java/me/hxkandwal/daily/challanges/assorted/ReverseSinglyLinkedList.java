package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * A iterative/recursive procedure to reverse a singly linked list.
 * 
 * @author Hxkandwal
 *
 */
public class ReverseSinglyLinkedList extends AbstractCustomTestRunner {
	
	private static ReverseSinglyLinkedList _instance = new ReverseSinglyLinkedList();
	
	private ReverseSinglyLinkedList() {}

	/*
	 * Data structure for linked list
	 */
	static class LinkNode {
		int data;
		LinkNode next;
		
		public LinkNode(int data) {
			this.data = data;
		}
		
		@Override
		public String toString() {
			return "[" + data + "]";
		}
	}
	
	// method 1: iterative approach
	public static LinkNode _reverseIteratively(final LinkNode head) {
		LinkNode traverser = head;
		LinkNode follower = null;
		
		while (traverser != null) {
			if (follower == null) {
				follower = traverser;
				traverser = traverser.next;
				follower.next = null;
			} else {
				LinkNode futureTraverser = traverser.next;
				traverser.next = follower;
				follower = traverser;
				traverser = futureTraverser;
			}	
		}
		
		return follower;
	}
	
	// method 1: recursive approach
	public static LinkNode _reverseRecursively(final LinkNode head) {
		return reverseRecursivelyInner(head, null);
	}
	
	public static LinkNode reverseRecursivelyInner(LinkNode traverser, LinkNode follower) {
		if (traverser == null)
			return follower;
		else {
			LinkNode head = reverseRecursivelyInner(traverser.next, traverser);
			if (follower != null) {
				follower.next = null;
				traverser.next = follower;
			}
			return head;
		}
	}
		
	// driver method
	public static void main(String[] args) {
		_instance.runTest("12345", "54321");
		_instance.runTest("1", "1");
		_instance.runTest("", null);
	}
	
	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		LinkNode head, tail;
		head = tail =  null;
		
		String input = (String) externalVariables[0];
		
		if (input == null || input.isEmpty())
			return null;
		
		for (int idx = 0; idx < input.length(); idx ++) {
			if (head == null) {
				tail = head = new LinkNode(Integer.valueOf(String.valueOf(input.charAt(idx))).intValue());
			} else {
				tail = tail.next = new LinkNode(Integer.valueOf(String.valueOf(input.charAt(idx))).intValue());
			}
		}
		
		try {
			head = (LinkNode) method.invoke(_instance, new Object[] { head });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		
		if (head != null) {
			StringBuilder sb = new StringBuilder();
		
			while (head != null) {
				sb.append(head.data);
				head = head.next;
			}
			
			return sb.toString();
		} else {
			return null;
		}
	}
	
}
