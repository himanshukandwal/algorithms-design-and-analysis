package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Sorted Array to Balanced BST
 * 
 * Given a sorted array. Write a function that creates a Balanced Binary Search Tree using array elements.
 * 
 * Input:  Array {1, 2, 3}
 * Output: A Balanced BST
 *      2
 *    /  \
 *   1    3 
 *   
 * Input: Array {1, 2, 3, 4}
 * Output: A Balanced BST
 *       3
 *     /  \
 *    2    4
 *  /
 * 1
 * 
 * link : http://www.geeksforgeeks.org/sorted-array-to-balanced-bst/
 * 
 * @author Hxkandwal
 *
 */
public class SortedArrayToBalancedBST extends AbstractCustomTestRunner {
	
	private static SortedArrayToBalancedBST _instance = new SortedArrayToBalancedBST();
	
	private SortedArrayToBalancedBST() {}
	
	public static class Node {
		int val;
		Node left;
		Node right;
		
		public Node(int val) { this.val = val; }
		
		@Override
		public String toString() { return "(" + val + ")"; }
	}
	
	public static Node _makeBST(int[] array) {
		if (array == null || array.length == 0)
			return null;
		
		return makeBSTInner(array, 0, array.length - 1);
	}
	
	private static Node makeBSTInner(int[] array, int startIdx, int endIdx) {
		if (startIdx > endIdx)
			return null;
		
		int midIdx = ((endIdx - startIdx + 1) % 2 != 0) ? ((endIdx + startIdx) / 2) 
															: ((endIdx + startIdx + 1) / 2); 
		
		Node centerNode = new Node(array [midIdx]);
		centerNode.left = makeBSTInner(array, startIdx, midIdx - 1);
		centerNode.right = makeBSTInner(array, midIdx + 1, endIdx);
		
		return centerNode;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(null, null);
		_instance.runTest(new int[] {1, 2, 3}, "123");
		_instance.runTest(new int[] {1, 2, 3, 4}, "1234");
		_instance.runTest(new int[] {1, 2, 3, 4, 5}, "12345");
	}
	
	public void runTest(final int[] input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		Node root = null;
		
		int[] input = (int[]) externalVariables[0];
		
		try {
			root = (Node) method.invoke(_instance, new Object[] { input });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		
		return inOrderTraversal(root);
	}	
	
	private String inOrderTraversal(Node node) {
		if (node == null)
			return null;
		
		String childTreeStr, representation = ((childTreeStr = inOrderTraversal(node.left)) != null ? childTreeStr : "") 
								+ node.val +  
								((childTreeStr = inOrderTraversal(node.right)) != null ? childTreeStr : "");
		
		return representation;
	}
	
}
