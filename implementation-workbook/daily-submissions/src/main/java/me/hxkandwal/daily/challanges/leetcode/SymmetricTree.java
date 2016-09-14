package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;
import me.hxkandwal.daily.challanges.geeksForGeeks.SortedArrayToBalancedBST.Node;

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
 * @author Hxkandwal
 *
 */
public class SymmetricTree extends AbstractCustomTestRunner {
	
	private static SymmetricTree _instance = new SymmetricTree();
	
	private SymmetricTree() {}
	
    public boolean _isSymmetric(Node root) {
        
    	return false;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[]{}, false);
	}
	
	public void runTest(final int[] array, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array });
		
		for (Object answer : answers) 
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		Node root = null;
		
		int[] input = (int[]) externalVariables[0];
		
		
		
		try {
			root = (Node) method.invoke(_instance, new Object[] { input });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		
		return inOrderTraversal(root);
	}	
	
	private String inOrderTraversal(Node node) {
		if (node == null)
			return null;
//		
//		String childTreeStr, representation = ((childTreeStr = inOrderTraversal(node.left)) != null ? childTreeStr : "") 
//								+ node.val +  
//								((childTreeStr = inOrderTraversal(node.right)) != null ? childTreeStr : "");
//		
		return "";
	}
	
}
