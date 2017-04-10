package challenges.assorted.tree;

import static challenges.Utilities.makeBST;
import static challenges.Utilities.print;
import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import challenges.AbstractCustomTestRunner;
import challenges.assorted.tree.model.BinaryTree;

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
	
	public static String _boundaryTraversal(BinaryTree root) {
		if (root == null) 
			return null;
		
		List<Integer> collector = new ArrayList<>();
		collector.add(root.getValue());
		
		if (root.getLeft() != null) {
			collector.add(root.getLeft().getValue());
			innerRecursion(true, root.getLeft(), collector);
		}
		
		if (root.getRight() != null) {
			innerRecursion(false, root.getRight(), collector);
			collector.add(root.getRight().getValue());
		}
		
		return print(collector);
	}
	
	private static boolean innerRecursion (boolean isLeft, BinaryTree node, List<Integer> collector) {
		if (node.getLeft() == null && node.getRight() == null)
			return collector.add(node.getValue());
		else {
			if (isLeft) {
				if (node.getLeft() != null) {
					collector.add(node.getLeft().getValue());
					
					// remove if already added 
					if (innerRecursion(isLeft, node.getLeft(), collector))
						collector.remove(collector.size() - 1);
				}
				
				if (node.getRight() != null)
					innerRecursion(isLeft, node.getRight(), collector);
					
			} else {
				if (node.getLeft() != null)
					innerRecursion(isLeft, node.getLeft(), collector);
				
				if (node.getRight() != null && !innerRecursion(isLeft, node.getRight(), collector))
						collector.add(node.getLeft().getValue());
			}
		}
		
		return false;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(null, null);
		_instance.runTest(new int[] { 25, 12, 30, 10, 36, 15 }, "10,12,25,30,36,15");
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
