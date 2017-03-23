package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 449. Serialize and Deserialize BST
 * 
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a 
 * file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another 
 * computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization 
 * algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized 
 * to the original tree structure.
 * 
 * The encoded string should be as compact as possible.
 * 
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 * 
 * @author Hxkandwal
 */
public class SerializeAndDeserializeBST extends AbstractCustomTestRunner {
	
	private static SerializeAndDeserializeBST _instance = new SerializeAndDeserializeBST();

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	// Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder build = new StringBuilder();
        inorder (root, build);
        return build.toString().trim();
    }
    
    public void inorder (TreeNode node, StringBuilder build) {
        if (node == null) { build.append(" #"); return; }
        inorder (node.left, build);
        build.append(" " + node.val);
        inorder (node.right, build);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataParts = data.split("\\ ");
        return generate (dataParts, 0, dataParts.length - 1);
    }
    
    public TreeNode generate (String[] dataParts, int start, int end) {
        int mid = (start + end) >>> 1;
        if (!dataParts [mid].equals("#")) {
            TreeNode node = new TreeNode (Integer.valueOf(dataParts [mid]));
            node.left = generate (dataParts, start, mid - 1);
            node.right = generate (dataParts, mid + 1, end);
            return node;
        }
        return null;
    }
    
	// driver method
	public static void main(String[] args) {
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		root.right = new TreeNode(3);
		_instance.runTest(root, "1 2 3");
		
		root = new TreeNode(1);
		root.right = new TreeNode(2);
		_instance.runTest(root, "1 2");
	} 
	
	public void runTest(final TreeNode root, final String expectedOutput) {
		String data = _instance.serialize(root);
		TreeNode node = _instance.deserialize(data);
		assertThat(validatingInorder(node).trim()).isEqualTo(expectedOutput);
		System.out.println("ok!");
	} 
	
	public static String validatingInorder (TreeNode node) {
        if (node == null) return "";
        String build = validatingInorder (node.left);
        build += " " + node.val;
        build += validatingInorder (node.right);
        return build;
    }
    
}
