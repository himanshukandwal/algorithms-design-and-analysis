package me.hxkandwal.daily.challanges;

import java.util.Iterator;
import java.util.List;

import me.hxkandwal.daily.challanges.assorted.tree.model.BinaryTreeNode;

/**
 * common class holding commonly used utility methods.
 * 
 * @author Hxkandwal
 *
 */
public class Utilities {
	
	public static <T> String printList(List<T> list) {
		StringBuilder sb = new StringBuilder();
		
		for (Iterator<T> collectorIterator = list.iterator(); collectorIterator.hasNext();) {
			sb.append(collectorIterator.next());
			
			if (collectorIterator.hasNext())
				sb.append(",");
		}
		
		return sb.toString();
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
