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
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode (int val) { this.val = val; }
	}
	
	public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        return makeBST (nums, 0, nums.length - 1);
    }
    
	public TreeNode makeBST (int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) >>> 1;
        TreeNode node = new TreeNode (nums [mid]);
        node.left = makeBST (nums, start, mid - 1);
        node.right = makeBST (nums, mid + 1, end);
        return node;
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
		TreeNode root = null;
		
		int[] input = (int[]) externalVariables[0];
		
		try {
			root = (TreeNode) method.invoke(_instance, new Object[] { input });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		
		return inOrderTraversal(root);
	}	
	
	private String inOrderTraversal(TreeNode node) {
		if (node == null)
			return null;
		
		String childTreeStr, representation = ((childTreeStr = inOrderTraversal(node.left)) != null ? childTreeStr : "") 
								+ node.val +  
								((childTreeStr = inOrderTraversal(node.right)) != null ? childTreeStr : "");
		
		return representation;
	}
	
}
