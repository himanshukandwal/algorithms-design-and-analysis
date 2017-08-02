package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 606. Construct String from Binary Tree
 *
 * You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.
 *
 * The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't
 * affect the one-to-one mapping relationship between the string and the original binary tree.
 *
 * Example 1:
 *      Input: Binary tree: [1,2,3,4]
 *
 *              1
 *            /   \
 *           2     3
 *          /
 *         4
 *
 *      Output: "1(2(4))(3)"
 *      Explanation: Originally it needs to be "1(2(4)())(3()())", but you need to omit all the unnecessary empty parenthesis pairs.
 *                   And it will be "1(2(4))(3)".
 *
 * Example 2:
 *      Input: Binary tree: [1,2,3,null,4]
 *
 *                  1
 *                /   \
 *              2     3
 *                     \
 *                     4
 *
 *      Output: "1(2()(4))(3)"
 *      Explanation: Almost the same as the first example, except we can't omit the first parenthesis pair to break the one-to-one mapping
 *                   relationship between the input and the output.
 *
 * Created by Hxkandwal
 */
public class ConstructStringFromBinaryTree extends AbstractCustomTestRunner {

    public static class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val) { this.val = val; }
    }

    public String tree2str(TreeNode t) {
        if (t == null) return "";
        String left = tree2str (t.left), right = tree2str (t.right);
        if (left.length () == 0 && right.length () == 0) return String.valueOf (t.val);
        else return t.val + "(" + left + ")" + (right.length () > 0 ? "(" + right + ")" : "");
    }
}
