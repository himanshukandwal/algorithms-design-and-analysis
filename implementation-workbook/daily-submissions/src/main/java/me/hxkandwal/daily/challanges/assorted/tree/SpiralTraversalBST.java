package me.hxkandwal.daily.challanges.assorted.tree;

import static com.google.common.truth.Truth.assertThat;
import static me.hxkandwal.daily.challanges.Utilities.makeBST;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;
import me.hxkandwal.daily.challanges.assorted.tree.model.BinaryTree;

/**
 * Spiral Traversal of binary tree.
 * 
 * @author Hxkandwal
 */
public class SpiralTraversalBST extends AbstractCustomTestRunner {

	private static SpiralTraversalBST _instance = new SpiralTraversalBST();
	
	private SpiralTraversalBST() {}
	
	public static String _spiralTraversal (BinaryTree root) {
		if (root == null)
			return null;
		
		boolean isAlternating = false;
		StringBuilder answer = new StringBuilder();
		Queue<BinaryTree> queue = new LinkedList<>();
		queue.add(root);
		
		int size = queue.size();
		while (!queue.isEmpty()) {
			
			int localSize = 0;
			StringBuilder localAnswer = new StringBuilder();
			
			while (size -- > 0) {
				BinaryTree node = queue.poll();
				
				if (isAlternating)
					localAnswer.insert(0, node.getValue() + " ");
				else
					localAnswer.append(node.getValue() + " ");
				
				if (node.getLeft() != null) {
					queue.add(node.getLeft());
					localSize ++;
				}
				
				if (node.getRight() != null) {
					queue.add(node.getRight());
					localSize ++;
				}
			}
			
			isAlternating = !isAlternating;
			answer.append(localAnswer.toString());
			size = localSize;
		}
		
		return answer.toString();
	}
	

	// driver method
	public static void main(String[] args) {
		_instance.runTest(null, null);
		_instance.runTest(new int[] { 4, 2, 5, 1, 6, 3, 7 }, "1 3 2 4 5 6 7 ");
	}
	
	public void runTest(final int[] array, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array });
		
		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		int[] array = (int[]) externalVariables[0];
		
		if (array == null || array.length == 0)
			return null;
		
		try {
			return method.invoke(_instance, new Object[] { makeBST(array) });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			return null;
		}
	}	
}
