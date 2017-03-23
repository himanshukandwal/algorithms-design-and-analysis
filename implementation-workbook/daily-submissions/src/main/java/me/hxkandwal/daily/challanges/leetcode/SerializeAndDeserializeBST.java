package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

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
        preorder (root, build);
        return build.toString().trim();
    }
    
    public void preorder (TreeNode node, StringBuilder build) {
        if (node == null) return;
        build.append(" " + node.val);
        preorder (node.left, build);
        preorder (node.right, build);
    }

    public class Indexer {
    	int index;
    	
    	public Indexer (int index) { this.index = index; }
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataParts = data.split("\\ ");
        Indexer idxr = new Indexer(0);
        return generate (dataParts, idxr, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public TreeNode generate (String[] dataParts, Indexer idxr, int min, int max) {
        if (dataParts.length == 0 || idxr.index >= dataParts.length || dataParts [idxr.index].isEmpty()) return null;
        int val = Integer.valueOf(dataParts [idxr.index]);
        
        if (val > min && val < max) {
        	idxr.index ++;
            TreeNode node = new TreeNode (val);
            node.left = generate (dataParts, idxr, min, val);
            node.right = generate (dataParts, idxr, val, max);
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
