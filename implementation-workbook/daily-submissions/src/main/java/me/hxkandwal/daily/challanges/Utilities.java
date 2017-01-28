package me.hxkandwal.daily.challanges;

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

import me.hxkandwal.daily.challanges.assorted.tree.model.BinaryTreeNode;

/**
 * A common class holding frequently used utility methods.
 * 
 * @author Hxkandwal
 *
 */
public class Utilities {
	
	public static <T> String print(Collection<T> collection) {
		StringBuilder sb = new StringBuilder();
		
		for (Iterator<T> collectorIterator = collection.iterator(); collectorIterator.hasNext();) {
			sb.append(collectorIterator.next());
			
			if (collectorIterator.hasNext())
				sb.append(",");
		}
		
		return sb.toString();
	}
	
	public static <T> String print(Collection<T> collection, String seperator) {
		StringBuilder sb = new StringBuilder();
		
		for (Iterator<T> collectorIterator = collection.iterator(); collectorIterator.hasNext();) {
			sb.append(collectorIterator.next());
			
			if (collectorIterator.hasNext())
				sb.append(seperator);
		}
		
		return sb.toString();
	}
	
	public static <T> Stack<T> copyStack(Stack<T> stack) {
		Stack<T> copyStack = new Stack<>();
		
		for (T item : stack) 
			copyStack.push(item);
		
		return copyStack;
	}
	
	public static BinaryTreeNode makeBST(int[] array) {
		if (array == null || array.length == 0)
			return null;
		
		return makeBSTInner(array, 0, array.length - 1);
	}
	
	private static BinaryTreeNode makeBSTInner(int[] array, int startIdx, int endIdx) {
		if (startIdx > endIdx)
			return null;
		
		int midIdx = ((endIdx - startIdx + 1) % 2 != 0) ? ((endIdx + startIdx) / 2) 
															: ((endIdx + startIdx + 1) / 2); 
		
		BinaryTreeNode centerNode = new BinaryTreeNode(array [midIdx]);
		centerNode.setLeft(makeBSTInner(array, startIdx, midIdx - 1));
		centerNode.setRight(makeBSTInner(array, midIdx + 1, endIdx));
		
		return centerNode;
	}

}
