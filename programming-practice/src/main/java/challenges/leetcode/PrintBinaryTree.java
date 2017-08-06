package challenges.leetcode;

import challenges.AbstractCustomTestRunner;
import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 655. Print Binary Tree
 *
 *
 *
 * Created by Hxkandwal
 */
public class PrintBinaryTree extends AbstractCustomTestRunner {

    private static PrintBinaryTree _instance = new PrintBinaryTree();

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public List<List<String>> _printTree(TreeNode root) {
        List<List<String>> ans = new ArrayList<>();
        if (root == null) return ans;
        int level = height (root), size = (int) Math.pow (2, level) - 1;
        for (int l = 0; l < level; l ++) {
            List<String> row = new ArrayList<>();
            for (int s = 0; s < size; s ++) row.add ("");
            ans.add (row);
        }
        ans.get (0).set (level + (level % 2 == 0 ? -1 : 0), String.valueOf (root.val));
        dfs (ans, root, 0, level + (level % 2 == 0 ? -1 : 0), level - 1);
        return ans;
    }

    private void dfs (List<List<String>> ans, TreeNode n, int index, int level, int diff) {
        if (n == null) return;
        if (n.left != null) {
            ans.get (index + 1).set (level - diff, String.valueOf (n.left.val));
            dfs (ans, n.left, index + 1, level - diff, diff - 1);
        }
        if (n.right != null) {
            ans.get (index + 1).set (level + diff, String.valueOf (n.right.val));
            dfs (ans, n.right, index + 1, level + diff, diff - 1);
        }
    }

    private int height (TreeNode n) {
        if (n == null) return 0;
        return Math.max (height (n.left), height (n.right)) + 1;
    }

    // driver method
    public static void main(String[] args) {
        testCase1();;
        testCase2();
    }

    private static void testCase1 () {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);

        _instance.runTest(root, null);
    }

    private static void testCase2 () {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        root.right = new TreeNode(6);;
        root.right.right = new TreeNode(7);

        _instance.runTest(root, null);
    }

    private static void testCase3 () {
        // [1,2,5,3,null,null,null,4]

        _instance.runTest(root, null);
    }

    public void runTest(final TreeNode root, final List<List<String>> expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { root });

        for (Object answer : answers)
            assertThat((List<List<String>>) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
