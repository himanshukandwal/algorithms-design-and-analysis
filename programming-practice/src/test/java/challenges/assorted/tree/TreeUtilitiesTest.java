package challenges.assorted.tree;

import static challenges.assorted.tree.TreeUtilities.generateBinarySearchTree;
import static challenges.assorted.tree.TreeUtilities.printBinarySearchTreeInorder;
import static challenges.assorted.tree.TreeUtilities.printBinarySearchTreePostorder;
import static challenges.assorted.tree.TreeUtilities.printBinarySearchTreePreorder;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import challenges.assorted.tree.model.BinaryTree;

public class TreeUtilitiesTest {

	@Test
	public void testPrintBinarySearchTreeInorder() {
		BinaryTree root = generateBinarySearchTree(new int[] {});
		assertEquals(0, printBinarySearchTreeInorder(root).length());
		
		root = generateBinarySearchTree(new int[] { 1, 2, 3 });
		assertEquals("1,2,3", printBinarySearchTreeInorder(root));
		
		root = generateBinarySearchTree(new int[] { 1, 2, 3, 4 });
		assertEquals("1,2,3,4", printBinarySearchTreeInorder(root));
		
		root = generateBinarySearchTree(new int[] { 1, 2, 3, 4, 5 });
		assertEquals("1,2,3,4,5", printBinarySearchTreeInorder(root));
	}

	@Test
	public void testPrintBinarySearchTreePreorder() {
		BinaryTree root = generateBinarySearchTree(new int[] {});
		assertEquals(0, printBinarySearchTreeInorder(root).length());
		
		root = generateBinarySearchTree(new int[] { 1, 2, 3 });
		assertEquals("2,1,3", printBinarySearchTreePreorder(root));
		
		root = generateBinarySearchTree(new int[] { 1, 2, 3, 4 });
		assertEquals("2,1,3,4", printBinarySearchTreePreorder(root));
		
		root = generateBinarySearchTree(new int[] { 1, 2, 3, 4, 5 });
		assertEquals("3,1,2,4,5", printBinarySearchTreePreorder(root));
	}
	
	@Test
	public void testPrintBinarySearchTreePostorder() {
		BinaryTree root = generateBinarySearchTree(new int[] {});
		assertEquals(0, printBinarySearchTreePostorder(root).length());
		
		root = generateBinarySearchTree(new int[] { 1, 2, 3 });
		assertEquals("1,3,2", printBinarySearchTreePostorder(root));
		
		root = generateBinarySearchTree(new int[] { 1, 2, 3, 4 });
		assertEquals("1,4,3,2", printBinarySearchTreePostorder(root));
		
		root = generateBinarySearchTree(new int[] { 1, 2, 3, 4, 5 });
		assertEquals("2,1,5,4,3", printBinarySearchTreePostorder(root));
	}
	
}
