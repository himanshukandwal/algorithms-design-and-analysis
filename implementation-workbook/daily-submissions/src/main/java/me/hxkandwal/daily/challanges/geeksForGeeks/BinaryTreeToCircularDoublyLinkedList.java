package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Convert a Binary Tree to a Circular Doubly Link List
 * 
 * Given a Binary Tree, convert it to a Circular Doubly Linked List (In-Place).
 * 
 *  a) The left and right pointers in nodes are to be used as previous and next pointers respectively in 
 *     converted Circular Linked List.
 *  
 *  b) The order of nodes in List must be same as Inorder of the given Binary Tree.
 *  
 *  c) The first node of Inorder traversal must be head node of the Circular List.
 *  
 * 
 * @author Hxkandwal
 *
 */
public class BinaryTreeToCircularDoublyLinkedList extends AbstractCustomTestRunner {
	
	private static BinaryTreeToCircularDoublyLinkedList _instance = new BinaryTreeToCircularDoublyLinkedList();
	
	private BinaryTreeToCircularDoublyLinkedList() {}
	
	public class Node {
		int val;	
		Node next;
		Node previous;
		
		public Node (int x) { val = x; }
		
		@Override
		public String toString() { return "(" + val + ")"; }
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(null, null);
		_instance.runTest("10#12 15#25 30 36", "25 12 30 10 36 15");
	}
	
	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		Node head, tail, root;
		head = tail = root = null;
		
		String input = (String) externalVariables[0];
		
		if (input == null || input.isEmpty())
			return null;
		
		Map<Integer, Node> indexedMap = new HashMap<>();
		
		String[] layers = input.split("#");
		for (String layer : layers) {
			
		}
		
		for (int idx = 0; idx < input.length(); idx ++) {
			int value = Integer.valueOf(String.valueOf(input.charAt(idx))).intValue();
			
			if (head == null) {
				indexedMap.put(value, tail = head = new Node(value));
			} else {
				if (indexedMap.containsKey(value)) 
					tail = tail.next = indexedMap.get(value);
				else 
					indexedMap.put(value, tail = tail.next = new Node(value));
			}
		}
		
		try {
			tail = (Node) method.invoke(_instance, new Object[] { head });
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
