package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 *  Write a procedure to print the elements of a singly linked list in 
 *  reverse order, using only O(1) extra space.
 *  
 * @author Hxkandwal
 *
 */
public class PrintReverseLinkedList extends AbstractCustomTestRunner {

	private static PrintReverseLinkedList _instance = new PrintReverseLinkedList();
	
	private PrintReverseLinkedList() {}

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
	
	public static String _reversal(final LinkNode head) {
		StringBuilder builder = new StringBuilder();
		reversalInner(builder, head);
		
		return builder.toString();
	}
	
	private static void reversalInner(StringBuilder recorder, LinkNode traverser) {
		if (traverser == null) 
			return;
		else {
			reversalInner(recorder, traverser.next);
			recorder.append(traverser.data);
		}
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("12345", "54321");
		_instance.runTest("1253545", "5453521");
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
			return (String) method.invoke(_instance, new Object[] { head });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}

	}
}
