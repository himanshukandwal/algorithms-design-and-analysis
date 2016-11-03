package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Print all nodes at distance k from a given node
 * 
 * Given a binary tree, a target node in the binary tree, and an integer value k, print all the nodes that are at 
 * distance k from the given target node. No parent pointers are available.
 * 
 * link : http://www.geeksforgeeks.org/print-nodes-distance-k-given-node-binary-tree/
 * 
 * @author Hxkandwal
 *
 */
public class FindAllNodesAtDistanceKFromGivenTargetNode extends AbstractCustomTestRunner {
	
	private static FindAllNodesAtDistanceKFromGivenTargetNode _instance = new FindAllNodesAtDistanceKFromGivenTargetNode();
	
	private FindAllNodesAtDistanceKFromGivenTargetNode() {}
	
	// data structure
	public static class Node {
		int data;
		Node left, right;

		Node(int item) {
			data = item;
			left = right = null;
		}
		
		@Override
		public String toString() {
			return "[" + data + "]";
		}
	}
	
	public static Integer[] _printKdistance(Node root, Node target, int k) {
		List<Integer> collector = new ArrayList<>();
		
		// collect lower tree variables.
		dfsSearchLower (target, k, collector);
		
		// collect upper trees information.
		List<Node> targetPath = new ArrayList<>();
		dfsLocateTarget(root, target, k, targetPath);
		
		// fetching every possible value.
		for (int idx = 1; idx < targetPath.size(); idx ++) {
			Node pathNode = targetPath.get(idx);
			int leftover = k - idx;
			
			if (leftover == 0) {
				collector.add(root.data);
			} else {
				List<Integer> remainingDataCollector = new ArrayList<>();
				Node pathTowardsTarget = targetPath.get(idx - 1);
				
				if (pathNode.left == pathTowardsTarget)
					dfsSearchLower(pathNode.right, leftover - 1, remainingDataCollector);
				else
					dfsSearchLower(pathNode.left, leftover - 1, remainingDataCollector);
				
				collector.addAll(remainingDataCollector);
			}
		}
		return collector.toArray(new Integer[0]);
    }
	
	private static void dfsLocateTarget(Node root, Node target, int k, List<Node> targetPath) {
		if (root == target)
			targetPath.add(root);
		else {
			if (root.left != null) 
				dfsLocateTarget(root.left, target, k - 1, targetPath);
			
			if (!targetPath.isEmpty())
				targetPath.add(root);
			else if (root.right != null) { 
				dfsLocateTarget(root.right, target, k - 1, targetPath);
				
				if (!targetPath.isEmpty())
					targetPath.add(root);
			}
		}
	}

	private static void dfsSearchLower (Node node, int k, List<Integer> collector) {
		if (k == 0)
			collector.add(node.data);
		else {
			if (node.left != null) 
				dfsSearchLower (node.left, k - 1, collector);
			
			if (node.right != null)
				dfsSearchLower (node.right, k - 1, collector);
		}
	}

	/*
	 * Test engine code
	 */
	private static Node findNode (Node root, int nodeValue) {
		Node traverser = root;
		
		while (traverser.data != nodeValue)
			traverser = (traverser.data > nodeValue) ? traverser.left : traverser.right;
		
		return traverser;
	}
	
	private static Node makeBST(int[] array) {
		if (array == null || array.length == 0)
			return null;
		
		return makeBSTInner(array, 0, array.length - 1);
	}
	
	private static Node makeBSTInner(int[] array, int startIdx, int endIdx) {
		if (startIdx > endIdx)
			return null;
		
		int midIdx = ((endIdx - startIdx + 1) % 2 != 0) ? ((endIdx + startIdx) / 2) : ((endIdx + startIdx + 1) / 2); 
		
		Node centerNode = new Node(array [midIdx]);
		centerNode.left = makeBSTInner(array, startIdx, midIdx - 1);
		centerNode.right = makeBSTInner(array, midIdx + 1, endIdx);
		
		return centerNode;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 4, 8, 10, 12, 14, 20, 22 }, 8, 1, new Integer[] { 4, 10, 12 });
		_instance.runTest(new int[] { 4, 8, 10, 12, 14, 20, 22 }, 8, 2, new Integer[] { 20 });
		_instance.runTest(new int[] { 4, 8, 10, 12, 14, 20, 22 }, 8, 3, new Integer[] { 14, 22 });
		_instance.runTest(new int[] { 4, 8, 10, 12, 14, 20, 22 }, 8, 4, new Integer[] { });
		_instance.runTest(new int[] { 4, 8, 10, 12, 14, 20, 22 }, 20, 3, new Integer[] { 4, 10 });
//		
		// geeksforgeeks custom test case.
		
		Node root = new Node(20);
        root.left = new Node(8);
        root.right = new Node(22);
        root.left.left = new Node(4);
        root.left.right = new Node(12);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(14);
        Node target = root.left.right;
        
        Arrays.asList(_printKdistance(root, target, 2)).stream().forEach(System.out::println);
	}
	
	public void runTest(final int[] input, final int targetNode, final int k, final Integer[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input, targetNode, k });
		
		for (Object answer : answers)
			assertThat((Integer[]) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		Node root = makeBST((int[]) externalVariables[0]);
		Node target = findNode(root, (int) externalVariables[1]);

		Integer[] result = null;
		try {
			result = (Integer[]) method.invoke(_instance, new Object[] { root, target, (int) externalVariables[2] });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}	
    
}
