package challenges.leetcode;

import static challenges.Utilities.copyStack;
import static challenges.Utilities.makeBST;
import static challenges.Utilities.print;
import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;
import challenges.assorted.tree.model.BinaryTree;

/**
 * 257. Binary Tree Paths
 * 
 * Given a binary tree, return all root-to-leaf paths. For example, given the following binary tree:
 * 
 *     1
 *	 /   \
 *  2     3
 *	 \
 * 	  5
 * 
 * All root-to-leaf paths are: ["1->2->5", "1->3"]
 * 
 * @author Hxkandwal
 *
 */
@SuppressWarnings({"unchecked", "rawtypes", "serial"})
public class BinaryTreePaths extends AbstractCustomTestRunner {
	
	private static BinaryTreePaths _instance = new BinaryTreePaths();
	
	private BinaryTreePaths() {}
	
	// data-structure to store the state critical information.
	public static class StateTuple {
		
		private BinaryTree node;
		private Stack<Integer> pathStack;
		
		public StateTuple(BinaryTree node) {
			this.node = node;
			this.pathStack = new Stack<>();
			this.pathStack.push(this.node.getValue());
		}
		
		public StateTuple(BinaryTree node, Stack<Integer> pathStack) {
			this.node = node;
			this.pathStack = pathStack;
		}
		
		public BinaryTree getNode() {
			return node;
		}
		
		public Stack<Integer> getPathStack() {
			return pathStack;
		}
	}
	
	public List<String> _binaryTreePaths(BinaryTree root) {
		if (root == null)
			return null;
		
		StateTuple stateTuple = new StateTuple(root);
		List<String> collector = new ArrayList<>();
		
		Queue<StateTuple> queue = new LinkedList<>();
		queue.add(stateTuple);
		
		// BFS traversal of tree. (logic reused from Pac-man project)
		while (!queue.isEmpty()) {
			StateTuple tuple = queue.poll();
			BinaryTree node = tuple.getNode();
			
			if (node.getLeft() == null && node.getRight() == null) 
				collector.add(print(tuple.getPathStack(), "->"));
			else {
				if (node.getLeft() != null) {
					Stack<Integer> childPathStack = copyStack(tuple.getPathStack());
					childPathStack.push(node.getLeft().getValue());
					queue.add(new StateTuple(node.getLeft(), childPathStack));
				}
				
				if (node.getRight() != null) {
					Stack<Integer> childPathStack = copyStack(tuple.getPathStack());
					childPathStack.push(node.getRight().getValue());
					queue.add(new StateTuple(node.getRight(), childPathStack));
				}
			}
		}
		
		return collector;
	}
	
	// highly optimized solution
	public List<String> _binaryTreePaths2(BinaryTree root) {
	    List<String> answer = new ArrayList<String>();
	    if (root != null) searchBT(root, "", answer);
	    return answer;
	}
	
	private void searchBT(BinaryTree root, String path, List<String> answer) {
	    if (root.getLeft() == null && root.getRight() == null) answer.add(path + root.getValue());
	    if (root.getLeft() != null) searchBT (root.getLeft(), path + root.getValue() + "->", answer);
	    if (root.getRight() != null) searchBT (root.getRight(), path + root.getValue() + "->", answer);
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(null, null);
		_instance.runTest(new int[] { 25, 12, 30, 10, 36, 15 }, new ArrayList() {{ add("10->12->25"); add("10->12->30"); add("10->15->36"); }});
	}
	
	public void runTest(final int[] array, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array });
		
		for (Object answer : answers)
			assertThat((List<String>) answer).isEqualTo(expectedOutput);
		
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
