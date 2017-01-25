package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;
import me.hxkandwal.daily.challanges.leetcode.SymmetricTree.Node;

/**
 * 98. Validate Binary Search Tree
 * 
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * 
 * Example 1: Binary tree [ 2, 1, 3 ], return true.
 * 
 *     2
 *    / \
 *   1   3
 * 
 * Example 2: Binary tree [ 1, 2, 3 ], return false.
 * 
 *     1
 *    / \
 *   2   3
 *   
 * @author Hxkandwal
 *
 */
public class ValidateBinarySearchTree extends AbstractCustomTestRunner {
	
	private static ValidateBinarySearchTree _instance = new ValidateBinarySearchTree();
	
	public ValidateBinarySearchTree() {}
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	// manager/dispatcher function.
	public boolean isValidBST(TreeNode root) {
	    boolean result = false;
	    
	    
	    
		return result;
	}
	
	private Integer[] getMinMax(TreeNode node) {
		Integer[] result = new Integer[2];
		
		if (node.left != null) {
			Integer[] leftResult = getMinMax(node.left);
			if (leftResult) {
				
			}
		}
		
		return result;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new Integer[] { 1, 2, 2, 3, 4, 4, 3 }, true);
		_instance.runTest(new Integer[] { 1, 2, 2, null, 3, null, 3 }, false);
		_instance.runTest(new Integer[] { 1, 2, 2, null, 3, 3 }, true);
	}
	
	public void runTest(final Integer[] array, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array });
		
		for (Object answer : answers) 
			assertThat((boolean) answer).isEqualTo (expectedOutput);
		
		System.out.println("ok!");
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		Node root = generateTree((Integer[]) externalVariables[0]);
		boolean resultArray = false;
		
		try {
			resultArray = (boolean) method.invoke(_instance, new Object[] { root });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		
		return resultArray;
	}
	
	 // logic borrowed and enhanced from BFSLevelPrintingAndGeneration program.
	public static Node generateTree(Integer[] array) {
		Node root = null;
		
		if (array.length > 0 && array [0] != null) {
			int index = 0;
			Queue<Node> queue = new LinkedList<>();
			
			queue.add(root = new Node(array [index ++]));
			while (!queue.isEmpty() && index < array.length) {
				Node node = queue.poll();
				
				if (array [index] != null) {
					node.left = new Node(array [index]);
					queue.add(node.left);
				}
				index ++;
				
				if (index < array.length && array [index] != null) {
					node.right = new Node(array [index]);
					queue.add(node.right);
				}
				index ++;
			}
		}
		
		return root;
	}
}
