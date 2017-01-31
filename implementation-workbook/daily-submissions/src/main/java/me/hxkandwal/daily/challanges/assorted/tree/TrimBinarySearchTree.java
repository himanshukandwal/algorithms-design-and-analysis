package me.hxkandwal.daily.challanges.assorted.tree;

import static com.google.common.truth.Truth.assertThat;
import static me.hxkandwal.daily.challanges.Utilities.makeBST;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Trim Binary Search Tree
 * 
 * Given the root of a binary search tree and 2 numbers min and max, trim the tree such that all the numbers in the 
 * new tree are between min and max (inclusive). 
 * 
 * The resulting tree should still be a valid binary search tree.
 * 
 * link: http://www.ardendertat.com/2012/01/17/programming-interview-questions-26-trim-binary-search-tree/
 * 
 * @author Hxkandwal
 *
 */
public class TrimBinarySearchTree extends AbstractCustomTestRunner {
	
	private static TrimBinarySearchTree _instance = new TrimBinarySearchTree();
	
	private TrimBinarySearchTree() {}
			
	
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
