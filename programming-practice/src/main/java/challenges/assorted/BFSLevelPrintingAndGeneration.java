package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import challenges.AbstractCustomTestRunner;

/**
 * Part of SymmetricTree program.
 * 
 * 101. Symmetric Tree
 * 
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * 
 * For example, this binary tree is represented as [1, 2, 2, 3, 4, 4, 3]:
 * 
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 
 * But the following tree is represented as [1, 2, 2, null, 3, null, 3]:
 * 
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * Note: Bonus points if you could solve it both recursively and iteratively.
 * 
 * @author Heman
 *
 */
public class BFSLevelPrintingAndGeneration extends AbstractCustomTestRunner {

	private static BFSLevelPrintingAndGeneration _instance = new BFSLevelPrintingAndGeneration();
	
	private BFSLevelPrintingAndGeneration() {}
	
	public static class Node {
		Integer value;
		Node left;
		Node right;
		
		public Node(Integer value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return "[" + value + "]";
		}
	}
	
	public static Node generateTree(Integer[] array) {
		Node root = null;
		
		if (array.length > 0) {
			int index = 0;
			Queue<Node> queue = new LinkedList<>();
			queue.add(root = new Node(array [index ++]));
			
			while (!queue.isEmpty() && index < array.length) {
				Node node = queue.poll();
				queue.add(node.left = new Node(array [index ++]));
				
				if (index < array.length) 
					queue.add(node.right = new Node(array [index ++]));
			}
		}
		
		return root;
	}
	
	public static Integer[] _printTreeBFS(Node root) {
		List<Integer> result = new ArrayList<>();
		
		if (root != null) {
			Queue<Node> queue = new LinkedList<>();
			queue.add(root);
			
			while (!queue.isEmpty()) {
				Node node = queue.poll();
				result.add(node.value);
				
				if (node.left != null)
					queue.add(node.left);
				
				if (node.right != null)
					queue.add(node.right);
			}
		}
		
		return result.toArray(new Integer[0]);
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new Integer[] { 1, 2, 2, 3, 4, 4, 3 });
		_instance.runTest(new Integer[] { 1, 2, 2, null, 3, null, 3 });
	}
	
	public void runTest(final Integer[] array) {
		List<Object> answers = runAll(getClass(), new Object[] { array });
		
		for (Object answer : answers) 
			assertThat((Integer[]) answer).isEqualTo(array);
		
		System.out.println("ok!");
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		Node root = generateTree((Integer[]) externalVariables[0]);
		Integer[] resultArray = null;
		
		try {
			resultArray = (Integer[]) method.invoke(_instance, new Object[] { root });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		
		return resultArray;
	}	
	
}
