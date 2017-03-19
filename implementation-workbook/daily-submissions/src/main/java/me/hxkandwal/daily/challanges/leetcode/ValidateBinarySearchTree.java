package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	// better answer
	public boolean _isValidBSTBetter(TreeNode root) {
		return isValidBST (root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isValidBST (TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isValidBST (node.left, min, node.val) && isValidBST (node.right, node.val, max); 
    }
	
	// manager/dispatcher function.
	public boolean _isValidBST(TreeNode root) {
		return (root == null ? true : getMinMax(root) != null);
	}
	
	private Integer[] getMinMax(TreeNode node) {
		if (node != null && (node.left != null && node.left.val > node.val) || (node.right != null && node.right.val < node.val))
			return null;
		
		Integer[] result = new Integer[2];
		if (node.left != null) {
	    	Integer[] minMax = getMinMax(node.left);
	    	
	    	if (minMax == null || ((minMax [0] >= node.val) || (minMax [1] >= node.val)))
	    		return null;
	    	else 
	    		result [0] = minMax [0]; 
	    } else
	    	result [0] = node.val;
		
		if (node.right != null) {
	    	Integer[] minMax = getMinMax(node.right);
	    	
	    	if (minMax == null || ((minMax [0] <= node.val) || (minMax [1] <= node.val)))
	    		return null;
	    	else 
	    		result [1] = minMax [1]; 
	    } else
	    	result [1] = node.val;
		
		return result;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new Integer[] { 2, 1, 3 }, true);
		_instance.runTest(new Integer[] { }, true);
		_instance.runTest(new Integer[] { 1, 1 }, false);
		_instance.runTest(new Integer[] { 1, 2, 3 }, false);
	}
	
	public void runTest(final Integer[] array, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array });
		
		for (Object answer : answers) 
			assertThat((boolean) answer).isEqualTo (expectedOutput);
		
		System.out.println("ok!");
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		TreeNode root = generateTree((Integer[]) externalVariables[0]);
		boolean resultArray = false;
		
		try {
			resultArray = (boolean) method.invoke(_instance, new Object[] { root });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		
		return resultArray;
	}
	
	// BFS way of creating the tree.
	public static TreeNode generateTree(Integer[] array) {
		TreeNode root = null;
		
		Queue<TreeNode> queue = new LinkedList<>();
		int idx = 0;
		
		while (idx < array.length) {
			if (idx == 0) {
				root = new TreeNode(array [idx ++]);
				queue.add(root);
			} else {
				TreeNode node = queue.poll();
				node.left = new TreeNode(array [idx ++]);
				queue.add(node.left);
				
				if (idx < array.length) {
					node.right = new TreeNode(array [idx ++]);
					queue.add(node.right);
				}
			}
		}
		
		return root;
	}
}
