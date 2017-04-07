package me.hxkandwal.daily.challanges.assorted.tree;

import static com.google.common.truth.Truth.assertThat;
import static me.hxkandwal.daily.challanges.assorted.tree.TreeUtilities.generateBinarySearchTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;
import me.hxkandwal.daily.challanges.assorted.tree.model.BinaryTree;

/**
 * Tree Level Order Print
 * 
 * Given a binary tree of integers, print it in level order. The output will contain space between the numbers in the same level, 
 * and new line between different levels.
 * 
 * link: http://www.ardendertat.com/2011/12/05/programming-interview-questions-20-tree-level-order-print/
 * 
 * @author Hxkandwal
 */
public class TreeLevelOrderPrint extends AbstractCustomTestRunner {
	
	private static TreeLevelOrderPrint _instance = new TreeLevelOrderPrint();
	
	public static String _levelOrderPrinting(BinaryTree root) {
		if (root == null)
			return null;
		
		StringBuilder output = new StringBuilder();
		Queue<BinaryTree> queue = new LinkedList<>();
		queue.add(root);
		
		int levelSize = queue.size();
		while(!queue.isEmpty()) {
			int buildingLevelSize = 0;
			
			while (levelSize -- > 0) {
				BinaryTree node = queue.poll();
				output.append(node.getValue()).append(" ");
				
				if (node.getLeft() != null) {
					queue.add(node.getLeft());
					buildingLevelSize ++;
				}
				
				if (node.getRight() != null) {
					queue.add(node.getRight());
					buildingLevelSize ++;
				}
			}
			
			if (buildingLevelSize > 0) 
				output.append(", ");
			
			levelSize = buildingLevelSize;
		}
		
		return output.toString();
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(null, null);
		_instance.runTest(generateBinarySearchTree(new int[] { 1, 2, 3, 4, 5, 6, 7 }), "4 , 2 6 , 1 3 5 7 ");
	}
	
	public void runTest(final BinaryTree root, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { root });
		
		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
