package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import challenges.AbstractCustomTestRunner;

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
        StringBuilder sb = new StringBuilder();
        preOrder (root, sb);
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    private void preOrder (TreeNode node, StringBuilder build) {
        if (node == null) return;
        build.append (node.val).append(",");
        preOrder (node.left, build);
        preOrder (node.right, build);
    }

    private class Index { int idx; }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] parts = data.split(",");
        Index idx = new Index ();
        return deserializeTree (parts, idx, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private TreeNode deserializeTree (String[] parts, Index idx, int min, int max) {
        if (parts == null || parts.length <= idx.idx) return null;
        int val = Integer.valueOf(parts [idx.idx]);
        if (val > min && val < max) {
            TreeNode node = new TreeNode (val);
            idx.idx ++;
            node.left = deserializeTree (parts, idx, min, val);
            node.right = deserializeTree (parts, idx, val, max);
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
