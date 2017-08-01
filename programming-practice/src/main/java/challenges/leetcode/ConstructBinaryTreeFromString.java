package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 536. Construct Binary Tree from String
 *
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents
 * the root's value and a pair of parenthesis contains a child binary tree with the same structure.
 *
 * You always start to construct the left child node of the parent first if it exists.
 *
 * Example:
 *      Input: "4(2(3)(1))(6(5))"
 *      Output: return the tree root node representing the following tree:
 *
 *              4
 *            /  \
 *           2    6
 *          / \   /
 *        3   1  5
 *
 * Note: There will only be '(', ')', '-' and '0' ~ '9' in the input string.
 *       An empty tree is represented by "" instead of "()".
 *
 * Created by Hxkandwal
 */
public class ConstructBinaryTreeFromString extends AbstractCustomTestRunner {

    public static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode str2tree(String s) {
        if (s.length () == 0) return null;
        int idx = 0, sign = 1, val = 0;
        while (idx < s.length() && s.charAt (idx) != '(') {
            if (s.charAt (idx) == '-') sign = -1;
            else val = 10 * val + (s.charAt (idx) - '0');
            idx ++;
        }
        TreeNode node = new TreeNode (val *= sign);
        if (idx < s.length ()) {
            int iter = 2;
            while (iter -- > 0 && idx < s.length ()) {
                int open = 1, start = ++ idx;
                while (open != 0 && idx < s.length ()) {
                    if (s.charAt (idx) == '(') open ++;
                    else if (s.charAt (idx) == ')') open --;
                    idx ++;
                }
                if (idx - 1 >= 0 || idx < s.length ()) {
                    if (iter == 1) node.left = str2tree (s.substring (start, idx - 1));
                    else node.right = str2tree (s.substring (start, idx - 1));
                }
            }
        }
        return node;
    }

}
