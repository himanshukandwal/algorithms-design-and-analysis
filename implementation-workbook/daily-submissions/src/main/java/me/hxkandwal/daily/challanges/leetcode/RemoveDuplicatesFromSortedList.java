package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 83. Remove Duplicates from Sorted List
 * 
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * 
 * For example,
 *  
 * 		Given 1->1->2, return 1->2.
 * 		Given 1->1->2->3->3, return 1->2->3.
 * 
 * @author Hxkandwal
 *
 */
public class RemoveDuplicatesFromSortedList extends AbstractCustomTestRunner {
	
	private static RemoveDuplicatesFromSortedList _instance = new RemoveDuplicatesFromSortedList();
	
	private RemoveDuplicatesFromSortedList() {}
	
	public class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) { val = x; }
	}
	
    public ListNode _deleteDuplicates(ListNode head) {
        ListNode followingNode = null;
        ListNode traversingNode = head;
        
        Integer previousValue = null;
        while (traversingNode != null) {
			if (previousValue == null) {
				previousValue = traversingNode.val;
				followingNode = traversingNode;
			} else if (traversingNode.val == previousValue) {
				ListNode futureNode = traversingNode.next;
				followingNode.next = futureNode;
			} else {
				previousValue = traversingNode.val;
				followingNode = traversingNode;
			}
			
			traversingNode = traversingNode.next;
		}
        		
    	return head;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("", null);
		_instance.runTest(null, null);
		_instance.runTest("112", "12");
		_instance.runTest("122", "12");
		_instance.runTest("11233", "123");
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
		
		for (int idx = 0; idx < input.length(); idx ++) {
			if (head == null) {
				tail = head = new ListNode(Integer.valueOf(String.valueOf(input.charAt(idx))).intValue());
			} else {
				tail = tail.next = new ListNode(Integer.valueOf(String.valueOf(input.charAt(idx))).intValue());
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
