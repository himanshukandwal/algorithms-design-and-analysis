package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import challenges.AbstractCustomTestRunner;

/**
 * 101. Symmetric Tree
 * 
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * 
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 * 
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 
 * But the following [1,2,2,null,3,null,3] is not:
 * 
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * Note: Bonus points if you could solve it both recursively and iteratively.
 * 
 * @author hxkandwal
 */
public class SymmetricTree extends AbstractCustomTestRunner {
	
	private static SymmetricTree _instance = new SymmetricTree();
	
	public static class Node {
		int value;
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

	public boolean _isSymmetric(Node root) {
		if (root == null) return true;
		return isSymmetric(root.left, root.right);
	}

	public boolean isSymmetric(Node a, Node b) {
		if (a == null || b == null) return a == null && b == null;
		return a.value == b.value && isSymmetric(a.left, b.right) && isSymmetric(a.right, b.left);
	}
	
    // logic borrowed and enhanced from BFSLevelPrintingAndGeneration program.
	public static Node generateTree(Integer[] array) {
		Node root = null;
		
		if (array.length > 0 && array [0] != null) {
			int index = 0;
			Queue<Node> queue = new LinkedList<>();
			
			queue.add(root = new Node(array [index ++]));
			while (!queue.isEmpty() && index < array.length) {
				Node node = queue.poll();
				
				if (array [index] != null) {
					node.left = new Node(array [index]);
					queue.add(node.left);
				}
				index ++;
				
				if (index < array.length && array [index] != null) {
					node.right = new Node(array [index]);
					queue.add(node.right);
				}
				index ++;
			}
		}
		
		return root;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new Integer[] { 1, 2, 2, 3, 4, 4, 3 }, true);
		_instance.runTest(new Integer[] { 1, 2, 2, null, 3, null, 3 }, false);
		_instance.runTest(new Integer[] { 1, 2, 2, null, 3, 3 }, true);
	}
	
	public void runTest(final Integer[] array, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array });
		
		for (Object answer : answers) 
			assertThat((boolean) answer).isEqualTo (expectedOutput);
		
		System.out.println("ok!");
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		Node root = generateTree((Integer[]) externalVariables[0]);
		boolean resultArray = false;
		
		try {
			resultArray = (boolean) method.invoke(_instance, new Object[] { root });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		
		return resultArray;
	}
	
}
