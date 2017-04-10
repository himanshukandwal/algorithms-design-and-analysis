package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Given a list L, and items a and b, return a new list containing the 
 * elements of L that are between a and b.
 * 
 * @author Hxkandwal
 *
 */
public class BetweenOperartionInLinkedList extends AbstractCustomTestRunner {
	
	private static BetweenOperartionInLinkedList _instance = new BetweenOperartionInLinkedList();
	
	private BetweenOperartionInLinkedList() {}

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
	
	public static LinkNode _between(final LinkNode head, final int start, final int end) {
		LinkNode result = null, traverser = head, follower = null;
		
		LinkNode resultHead = null;
		while (traverser != null) {
			if (traverser.data > start && traverser.data < end) {
				if (result == null)
					result = resultHead = traverser;
				else
					result = result.next = traverser;
				
				traverser = traverser.next;
				result.next = null;
				
				if (follower != null) 
					follower.next = traverser;
				
			} else {
				follower = traverser;
				traverser = traverser.next;
			}
		}
		
		return resultHead;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("12345", 2, 4, "3");
		_instance.runTest("12345", 1, 4, "23");
		_instance.runTest("1253545", 1, 4, "23");
	}
	
	public void runTest(final String input, final int start, final int end, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input, start, end });
		
		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		LinkNode head, tail;
		head = tail =  null;
		
		String input = (String) externalVariables[0];
		int start = (Integer) externalVariables [1];
		int end = (Integer) externalVariables [2];
		
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
			tail = (LinkNode) method.invoke(_instance, new Object[] { head, start, end });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		
		if (tail != null) {
			StringBuilder sb = new StringBuilder();
		
			while (tail != null) {
				sb.append(tail.data);
				tail = tail.next;
			}
			
			return sb.toString();
		} else {
			return null;
		}
	}
	
}