package challenges.assorted.tree;

import challenges.assorted.tree.model.BinaryTree;

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
		
		return innerRecursionGenerateBinarySearchTree (array, 0, array.length - 1);
	}
	
	public static String printBinarySearchTreeInorder(BinaryTree root) {
		StringBuilder output = new StringBuilder();
		innerRecursionBinarySearchTreeInorder(root, output);
		return output.toString();
	}

	public static String printBinarySearchTreePreorder(BinaryTree root) {
		StringBuilder output = new StringBuilder();
		innerRecursionBinarySearchTreePreorder(root, output);
		return output.toString();
	}

	public static String printBinarySearchTreePostorder(BinaryTree root) {
		StringBuilder output = new StringBuilder();
		innerRecursionBinarySearchTreePostorder(root, output);
		return output.toString();
	}
	
	private static BinaryTree innerRecursionGenerateBinarySearchTree(int[] array, int start, int end) {
		int mid = (start + end) / 2;
		
		BinaryTree node = new BinaryTree(array [mid]);
		
		if (mid > start)
			node.setLeft(innerRecursionGenerateBinarySearchTree (array, start, mid - 1));
		
		if (mid < end)
			node.setRight(innerRecursionGenerateBinarySearchTree (array, mid + 1, end));
		
		return node;
	}
	
	private static void innerRecursionBinarySearchTreeInorder(BinaryTree node, StringBuilder output) {
		if (node != null) {
			if (node.getLeft() != null) {
				innerRecursionBinarySearchTreeInorder(node.getLeft(), output);
				output.append(",");
			}
			
			output.append(node.getValue());
			
			if (node.getRight() != null) {
				output.append(",");
				innerRecursionBinarySearchTreeInorder(node.getRight(), output);
			}
		}
	}
	
	private static void innerRecursionBinarySearchTreePreorder(BinaryTree node, StringBuilder output) {
		if (node != null) {
			output.append(node.getValue());
			
			if (node.getLeft() != null) {
				output.append(",");
				innerRecursionBinarySearchTreePreorder(node.getLeft(), output);
			}
			
			if (node.getRight() != null) {
				output.append(",");
				innerRecursionBinarySearchTreePreorder(node.getRight(), output);
			}
		}
	}
	
	private static void innerRecursionBinarySearchTreePostorder(BinaryTree node, StringBuilder output) {
		if (node != null) {
			if (node.getLeft() != null) {
				innerRecursionBinarySearchTreePostorder(node.getLeft(), output);
				output.append(",");
			}
			
			if (node.getRight() != null) {
				innerRecursionBinarySearchTreePostorder(node.getRight(), output);
				output.append(",");
			}
			
			output.append(node.getValue());
		}
	}
	
}