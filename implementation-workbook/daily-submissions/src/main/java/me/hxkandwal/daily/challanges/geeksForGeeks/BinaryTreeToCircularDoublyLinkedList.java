package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;
import me.hxkandwal.daily.challanges.geeksForGeeks.SortedArrayToBalancedBST.Node;

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
 * link : http://www.geeksforgeeks.org/convert-a-binary-tree-to-a-circular-doubly-link-list/
 * 
 * @author Hxkandwal
 *
 */
public class BinaryTreeToCircularDoublyLinkedList extends AbstractCustomTestRunner {
	
	private static BinaryTreeToCircularDoublyLinkedList _instance = new BinaryTreeToCircularDoublyLinkedList();
	
	private BinaryTreeToCircularDoublyLinkedList() {}
	
	/* method : keep sending left side (or main node up) and join left side of parent by traversing to end and 
	 * 			right by just overwriting (as left-most node is coming up) */
	public static Node _converter(Node node) {
		if (node == null)
			return node;
		
		Node head = recurseInner(node);
		
		// make it cyclic
		Node traverser = head;
		while (traverser.right != null)
			traverser = traverser.right;
		
		traverser.right = head;
		head.left = traverser;
		
		return head;
	}
	
	private static Node recurseInner(Node node) {
		if (node.left == null)  // if left is null, the right must also be null.
			return node;
		
		Node left = recurseInner(node.left);
		
		Node traverser = left;
		while (traverser.right != null)
			traverser = traverser.right;
		
		traverser.right = node;
		node.left = traverser;  // chain connected (from left)
		
		if (node.right != null) {
			Node right = recurseInner(node.right);	
			node.right = right;
			right.left = node;	// chain connected (from right)
		}
		
		return left;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(null, null);
		_instance.runTest(new int[] { 25, 12, 30, 10, 36, 15 }, "25 12 30 10 36 15");
	}
	
	public void runTest(final int[] array, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array });
		
		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		Node head, tail;
		head = tail = null;
		
		int[] array = (int[]) externalVariables[0];
		
		if (array == null || array.length == 0)
			return null;
		
		Node root = SortedArrayToBalancedBST._makeBST(array);
		
		try {
			tail = head = (Node) method.invoke(_instance, new Object[] { root });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		
		if (head != null) {
			StringBuilder sb = new StringBuilder();
		
			do {
				sb.append(tail.val);
				tail = tail.right;
				
				if (tail != head) 
					sb.append(" ");
				
			} while (tail != head);
			
			return sb.toString();
		} else {
			return null;
		}
	}	

}
