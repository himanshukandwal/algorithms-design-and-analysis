package me.hxkandwal.daily.challanges.assorted.tree;

import static me.hxkandwal.daily.challanges.Utilities.printList;

import java.util.ArrayList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;
import me.hxkandwal.daily.challanges.assorted.tree.model.BinaryTreeNode;

/**
 * Boundary Traversal of binary tree
 * 
 * Given a binary tree, print boundary nodes of the binary tree Anti-Clockwise starting from the root.
 * 
 * link: http://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
 * 
 * @author Hxkandwal
 *
 */
public class BoundaryTraversalBST extends AbstractCustomTestRunner {
	
	private static BoundaryTraversalBST _instance = new BoundaryTraversalBST();
	
	private BoundaryTraversalBST() {}
	
	public static String _boundaryTraversal(BinaryTreeNode root) {
		if (root == null) 
			return null;
		
		List<Integer> collector = new ArrayList<>();
		collector.add(root.getValue());
		
		if (root.getLeft() != null)
			innerRecursion(true, root.getLeft(), collector);
		
		if (root.getRight() != null)
			innerRecursion(false, root.getRight(), collector);
		
		return printList(collector);
	}
	
	private static void innerRecursion (boolean isLeft, BinaryTreeNode node, List<Integer> collector) {
		if (node.getLeft() == null && node.getRight() == null)
			collector.add(node.getValue());
		else {
			if (isLeft) {
				if (node.getLeft() != null) {
					collector.add(node.getLeft().getValue());
					innerRecursion(isLeft, node.getLeft(), collector);
				}
				
				if (node.getRight() != null)
					innerRecursion(isLeft, node.getRight(), collector);
					
			} else {
				if (node.getRight() != null) {
					if (node.getLeft() != null)
						innerRecursion(isLeft, node.getLeft(), collector);
					
					innerRecursion(isLeft, node.getRight(), collector);
					collector.add(node.getLeft().getValue());
				}
			}
		}
	}
	
}
