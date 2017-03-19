package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 21. Merge Two Sorted Lists
 * 
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the 
 * nodes of the first two lists.
 * 
 * @author Hxkandwal
 *
 */
public class MergeTwoSortedLists extends AbstractCustomTestRunner {

	private static MergeTwoSortedLists _instance = new MergeTwoSortedLists();
	
	private MergeTwoSortedLists() {}
	
	public class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) { val = x; }
	}
    
	public ListNode _mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode traverser1 = l1, traverser2 = l2, ans = null, traverser3 = null;
        while (traverser1 != null && traverser2 != null) {
            if (traverser1.val > traverser2.val) {
                if (ans == null) traverser3 = ans = traverser2;
                else traverser3 = traverser3.next = traverser2;
                traverser2 = traverser2.next;
                traverser3.next = null;
            } 
            else {
                if (ans == null) traverser3 = ans = traverser1;
                else traverser3 = traverser3.next = traverser1;
                traverser1 = traverser1.next;
                traverser3.next = null;
            }
        }
        if (traverser3 != null) traverser3.next = (traverser1 != null) ? traverser1 : traverser2;
        else ans = (traverser1 != null) ? traverser1 : traverser2;
        return ans;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("135", "246", "123456");
		_instance.runTest("", "12345", "12345");
		_instance.runTest(null, "12345", "12345");
		_instance.runTest("12345", "", "12345");
		_instance.runTest("12345", null, "12345");
		_instance.runTest(null, null, null);
		_instance.runTest("", "", null);
		_instance.runTest("1", "1", "11");
	}
	
	public void runTest(final String l1, final String l2, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { l1, l2 });
		
		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		ListNode l1 = createList((String) externalVariables[0]);
		ListNode l2 = createList((String) externalVariables[1]);
		
		ListNode answer = null;
		try {
			answer = (ListNode) method.invoke(_instance, new Object[] { l1, l2 });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		
		if (answer != null) {
			StringBuilder sb = new StringBuilder();
		
			while (answer != null) {
				sb.append(answer.val);
				answer = answer.next;
			}
			
			return sb.toString();
		} else {
			return null;
		}
	}
	
	private ListNode createList(String content) {
		ListNode head, tail;
		head = tail =  null;
		
		if (content == null || content.isEmpty())
			return null;
		
		for (int idx = 0; idx < content.length(); idx ++) {
			if (head == null) {
				tail = head = new ListNode(Character.getNumericValue(content.charAt(idx)));
			} else {
				tail = tail.next = new ListNode(Character.getNumericValue(content.charAt(idx)));
			}
		}
		
		return head;
	}
	
}
