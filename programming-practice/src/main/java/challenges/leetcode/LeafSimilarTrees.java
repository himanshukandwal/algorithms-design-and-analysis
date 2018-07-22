package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 872. Leaf-Similar Trees
 *
 * Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.
 *
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 *
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 *
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 *
 * @author hxkandwal
 */
public class LeafSimilarTrees extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> seq1 = new ArrayList<>(), seq2 = new ArrayList<>();
        dfs (root1, seq1);
        dfs (root2, seq2);
        if (seq1.size() != seq2.size()) return false;
        for (int idx = 0; idx < seq1.size(); idx ++) if (seq1.get(idx) != seq2.get(idx)) return false;
        return true;
    }

    private void dfs(TreeNode r, List<Integer> seq) {
        if (r == null) return;
        dfs (r.left, seq);
        if (r.left == null && r.right == null) seq.add (r.val);
        dfs (r.right, seq);
    }
}
