package me.hxkandwal.daily.challanges.assorted.tree;

import me.hxkandwal.daily.challanges.assorted.tree.model.BinaryTree;

/**
 * Class primarily consisting of utility methods for tree data structures.
 * 
 * @author Hxkandwal
 *
 */
public class TreeUtilities {
	
	public static BinaryTree generateBinarySearchTree(int[] array) {
		if (array == null || array.length == 0) 
			return null;
		
		int mid = array.length / 2;
		
		BinaryTree root = new BinaryTree(array [mid]); 
		
		root.setLeft(innerRecursionGenerateBinarySearchTree (array, 0, mid));
		root.setRight(innerRecursionGenerateBinarySearchTree (array, mid + 1, array.length - 1));
		
		return root;
	}
	
	public static String printBinarySearchTreeInorder(BinaryTree root) {
		
		return null;
	}
	
	private static BinaryTree innerRecursionGenerateBinarySearchTree(int[] array, int start, int end) {
		int mid = (start + end) / 2;
		
		BinaryTree node = new BinaryTree(array [mid]); 
		node.setLeft(innerRecursionGenerateBinarySearchTree (array, start, mid));
		node.setRight(innerRecursionGenerateBinarySearchTree (array, mid + 1, end));
		
		return node;
	}
	
}
