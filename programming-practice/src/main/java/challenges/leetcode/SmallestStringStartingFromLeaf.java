package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 988. Smallest String Starting From Leaf
 *
 * Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z': a value of 0 represents 'a', a value of 1 represents 'b', and
 * so on. Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
 *
 * (As a reminder, any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that
 * has no children.)
 *
 * Example 1:
 *      Input: [0,1,2,3,4,3,4]
 *      Output: "dba"
 *
 * Example 2:
 *      Input: [25,1,3,1,3,0,2]
 *      Output: "adz"
 *
 * Example 3:
 *      Input: [2,2,1,null,1,0,null,0]
 *      Output: "abc"
 *
 * Note:
 *  The number of nodes in the given tree will be between 1 and 1000.
 *  Each node in the tree will have a value between 0 and 25.
 *
 * @author Hxkandwal
 */
public class SmallestStringStartingFromLeaf extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) { val = x; }
    }

    public String _smallestFromLeaf(TreeNode root) {
        return dfs (root, "", null);
    }

    private String dfs (TreeNode n, String build, String ans) {
        if (n == null) return ans;
        String current = build + (char) ('a' + n.val);
        if (n.left == null && n.right == null) {
            String rev = new StringBuilder(current).reverse().toString();
            if (ans == null || ans.compareTo(rev) > 0) ans = rev;
        }
        ans = dfs (n.left, current, ans);
        ans = dfs (n.right, current, ans);
        return ans;
    }
}
