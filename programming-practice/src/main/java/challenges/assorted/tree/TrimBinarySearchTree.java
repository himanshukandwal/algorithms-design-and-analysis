package challenges.assorted.tree;

import static challenges.assorted.tree.TreeUtilities.generateBinarySearchTree;
import static challenges.assorted.tree.TreeUtilities.printBinarySearchTreeInorder;
import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;
import challenges.assorted.tree.model.BinaryTree;

/**
 * Trim Binary Search Tree
 * 
 * Given the root of a binary search tree and 2 numbers min and max, trim the tree such that all the numbers in the 
 * new tree are between min and max (inclusive). 
 * 
 * The resulting tree should still be a valid binary search tree.
 * 
 * link: http://www.ardendertat.com/2012/01/17/programming-interview-questions-26-trim-binary-search-tree/
 * 
 * @author Hxkandwal
 *
 */
public class TrimBinarySearchTree extends AbstractCustomTestRunner {
	
	private static TrimBinarySearchTree _instance = new TrimBinarySearchTree();
	
	private TrimBinarySearchTree() {}
		
	public static String _trim(BinaryTree root, int min, int max) {
		if (root == null)
			return null;
		return printBinarySearchTreeInorder(innerRecursiveTrimmingInPostorder(root, min, max));
	}
	
	private static BinaryTree innerRecursiveTrimmingInPostorder(BinaryTree node, int min, int max) {
		if (node.getLeft() != null) {
			node.setLeft(innerRecursiveTrimmingInPostorder(node.getLeft(), min, max));
		}
		
		if (node.getRight() != null) {
			node.setRight(innerRecursiveTrimmingInPostorder(node.getRight(), min, max));
		}
		
		if (node.getValue() < min || node.getValue() > max)
			return (node.getLeft() != null ? node.getLeft() : (node.getRight() != null ? node.getRight() : null));
		
		return node;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(null, -100, 100, null);
		_instance.runTest(generateBinarySearchTree(new int[] { 1, 2, 3, 4, 5, 6, 7 }), -100, 100, "1,2,3,4,5,6,7");
		_instance.runTest(generateBinarySearchTree(new int[] { 1, 2, 3, 4, 5, 6, 7 }), 0, 100, "1,2,3,4,5,6,7");
		_instance.runTest(generateBinarySearchTree(new int[] { 1, 2, 3, 4, 5, 6, 7 }), -100, 0, "");
		_instance.runTest(generateBinarySearchTree(new int[] { 1, 2, 3, 4, 5, 6, 7 }), 2, 100, "2,3,4,5,6,7");
		_instance.runTest(generateBinarySearchTree(new int[] { 1, 2, 3, 4, 5, 6, 7 }), 0, 6, "1,2,3,4,5,6");
		_instance.runTest(generateBinarySearchTree(new int[] { 1, 2, 3, 4, 5, 6, 7 }), 3, 5, "3,4,5");
		_instance.runTest(generateBinarySearchTree(new int[] { 1, 2, 3, 4, 5, 6, 7 }), 3, 3, "3");
	}
	
	public void runTest(final BinaryTree root, final int min, final int max, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { root, min, max });
		
		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
