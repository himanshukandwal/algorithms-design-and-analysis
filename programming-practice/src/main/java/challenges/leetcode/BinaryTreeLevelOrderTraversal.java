package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 102. Binary Tree Level Order Traversal
 * 
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level). 
 * 
 * For example: Given binary tree [3,9,20,null,null,15,7],
 * 
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 * 
 * return its level order traversal as: 
 * [
 * 	[3],
 * 	[9,20],
 * 	[15,7]
 * ]
 *   
 * @author Hxkandwal
 */
@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class BinaryTreeLevelOrderTraversal extends AbstractCustomTestRunner {
	
	private static BinaryTreeLevelOrderTraversal _instance = new BinaryTreeLevelOrderTraversal();

	public static class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 
		 public TreeNode(int x) { val = x; }
	}
	
	public List<List<Integer>> _levelOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
        levelOrderInner (root, ans, 1);
        return ans;
    }
    
    private void levelOrderInner (TreeNode root, List<List<Integer>> ans, int level) {
        if (root == null) return;
        while (ans.size () < level) ans.add (new ArrayList<>());
        ans.get (level - 1).add (root.val);
        levelOrderInner (root.left, ans, level + 1);
        levelOrderInner (root.right, ans, level + 1);
    }
	
	// driver method
	public static void main(String[] args) {
		TreeNode node = new TreeNode(3);
		node.left = new TreeNode(9);
		
		TreeNode right = new TreeNode(20);
		right.left = new TreeNode(15);
		right.right = new TreeNode(7);
				
		node.right = right;
		
		_instance.runTest(node, new ArrayList() {{ add(new ArrayList() {{ add(3); }}); add(new ArrayList() {{ add(9); add(20); }}); add(new ArrayList() {{ add(15); add(7); }}); }});
	}

	public void runTest(final TreeNode node, final List<List<Integer>> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { node });

		for (Object answer : answers)
			assertThat((List<List<Integer>>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
