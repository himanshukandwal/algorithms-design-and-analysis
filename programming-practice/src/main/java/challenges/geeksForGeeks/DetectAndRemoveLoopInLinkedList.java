package challenges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * Detect and Remove Loop in a Linked List
 * 
 * Write a function detectAndRemoveLoop() that checks whether a given Linked List contains loop 
 * and if loop is present then removes the loop and returns true. 
 * 
 * And if the list doesn’t contain loop then returns false. 
 * Below diagram shows a linked list with a loop. 
 * 
 * 			1 -> 2 -> 3    
 * 				 ^	  |	
 * 				 |	  v
 * 				 5 <- 4
 * 					
 * detectAndRemoveLoop() must change the below list to 1 -> 2 -> 3 -> 4 -> 5 -> NULL.
 * 
 * link : http://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
 * 
 * @author Hxkandwal
 *
 */
public class DetectAndRemoveLoopInLinkedList extends AbstractCustomTestRunner {
	
	private static DetectAndRemoveLoopInLinkedList _instance = new DetectAndRemoveLoopInLinkedList();
	
	private DetectAndRemoveLoopInLinkedList() {}
	
	public class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) { val = x; }
	}
	
	/* method : LCM cyclist strategy, start two pointers one hopping with 1 length and other with 2 length. 
	 * 			if hit null, no loop else if they become same then there is a loop. 
	 */
	public static ListNode _detectAndRemoveLoop(final ListNode head) {
		ListNode oneStep = head, twoStep = head;
		
		do {
			oneStep = oneStep.next;
			twoStep = (twoStep.next != null) ? twoStep.next.next : twoStep.next;
		} while (oneStep != null && twoStep != null && oneStep != twoStep);

		/* cycle detected for sure till this point. Now working for cycle removal. 
		 * find cycle length, start from head and another from kth place next. start traversal, both will land up on cycle head. 
		 * */
		if (oneStep == twoStep) {
			
			int cycleLength = 1;
			twoStep = twoStep.next;

			while (twoStep != oneStep) {
				cycleLength ++;
				twoStep = twoStep.next;
			}

			oneStep = twoStep = head;
			for (int jump = 0; jump < cycleLength; jump ++)
				twoStep = twoStep.next;

			if (oneStep != twoStep)
				while ((oneStep = oneStep.next) != (twoStep = twoStep.next));
			
			while (twoStep.next != oneStep)
				twoStep = twoStep.next;

			twoStep.next = null;
		}
		
		return head;
	}


	// driver method
	public static void main(String[] args) {
		_instance.runTest("", null);
		_instance.runTest(null, null);
		_instance.runTest("12345", "12345");
		_instance.runTest("123452", "12345");
		_instance.runTest("1234567894", "123456789");
		_instance.runTest("1234567891", "123456789");
	}
	
	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		ListNode head, tail;
		head = tail =  null;
		
		String input = (String) externalVariables[0];
		
		if (input == null || input.isEmpty())
			return null;
		
		Map<Integer, ListNode> indexedMap = new HashMap<>();
		
		for (int idx = 0; idx < input.length(); idx ++) {
			int value = Integer.valueOf(String.valueOf(input.charAt(idx))).intValue();
			
			if (head == null) {
				indexedMap.put(value, tail = head = new ListNode(value));
			} else {
				if (indexedMap.containsKey(value)) 
					tail = tail.next = indexedMap.get(value);
				else 
					indexedMap.put(value, tail = tail.next = new ListNode(value));
			}
		}
		
		try {
			tail = (ListNode) method.invoke(_instance, new Object[] { head });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		
		if (tail != null) {
			StringBuilder sb = new StringBuilder();
		
			while (tail != null) {
				sb.append(tail.val);
				tail = tail.next;
			}
			
			return sb.toString();
		} else {
			return null;
		}
	}
	
}
