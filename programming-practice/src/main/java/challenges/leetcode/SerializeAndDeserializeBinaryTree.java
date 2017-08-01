package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 297. Serialize and Deserialize Binary Tree
 *
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should
 * work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * For example, you may serialize the following tree
 *
 *              1
 *             / \
 *            2   3
 *           / \
 *          4   5
 *
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please
 * be creative and come up with different approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 *
 * Created by Hxkandwal
 */
public class SerializeAndDeserializeBinaryTree extends AbstractCustomTestRunner {

    public static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int x) { val = x; }
    }

    public static class Implementation1 {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "(NULL)";
            String left = serialize(root.left), right = serialize(root.right);
            return "(" + root.val + "," + left + "," + right + ")";
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.length() == 0 || data.equals("(NULL)")) return null;
            int val = 0, idx = 1, sign = 1;
            for (idx = 1; idx < data.length(); idx++) {
                if (data.charAt(idx) == ',') break;

                if (data.charAt(idx) == '-') sign = -1;
                else val = 10 * val + (data.charAt(idx) - '0');
            }
            TreeNode node = new TreeNode(val * sign);

            int iter = 2;
            while (iter-- > 0) {
                int start = ++idx, open = 1;
                idx++;
                while (idx < data.length() && open != 0) {
                    if (data.charAt(idx) == '(') open++;
                    else if (data.charAt(idx) == ')') open--;
                    idx++;
                }
                if (iter == 1) node.left = deserialize(data.substring(start, idx));
                else node.right = deserialize(data.substring(start, idx));
            }
            return node;
        }
    }

    public class Implementation2 {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder ();
            stringify (root, sb);
            return sb.toString ();
        }

        private void stringify (TreeNode node, StringBuilder sb) {
            if (node == null) sb.append ("X,");
            else {
                sb.append (node.val + ",");
                stringify (node.left, sb);
                stringify (node.right, sb);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Deque <String> nodes = new ArrayDeque<>();
            nodes.addAll (Arrays.asList (data.split(",")));
            return buildTree (nodes);
        }

        private TreeNode buildTree (Deque<String> nodes) {
            String val = nodes.poll ();
            if (val.equals ("X")) return null;
            else {
                TreeNode node = new TreeNode (Integer.valueOf (val));
                node.left = buildTree (nodes);
                node.right = buildTree (nodes);
                return node;
            }
        }
    }
}
