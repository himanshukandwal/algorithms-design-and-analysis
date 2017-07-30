package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 652. Find Duplicate Subtrees
 *
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to
 * return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with same node values.
 *
 * Example 1:
 *                 1
 *                / \
 *               2   3
 *              /   / \
 *             4   2   4
 *                /
 *               4
 *
 * The following are two duplicate subtrees:
 *              2
 *             /
 *            4
 * and
 *          4
 *
 * Therefore, you need to return above trees' root in the form of a list.
 *
 * Created by Hxkandwal
 */
public class FindDuplicateSubtrees extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> ans = new ArrayList<>();
        Map<String, List<TreeNode>> map = new HashMap<>();
        find (map, root);
        for (String key : map.keySet()) if (map.get (key).size () > 1) ans.add (map.get (key).get (0));
        return ans;
    }

    private String find (Map<String, List<TreeNode>> map, TreeNode n) {
        if (n == null) return "";
        String left = find (map, n.left), right = find (map, n.right);
        String total = left + "#" + n.val + "#" + right;
        map.computeIfAbsent (total, k -> new ArrayList<>()).add (n);
        return total;
    }
}
