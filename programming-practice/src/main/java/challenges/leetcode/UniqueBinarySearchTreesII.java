package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

/**
 * 95. Unique Binary Search Trees II
 * 
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 * 
 * For example,
 * 		Given n = 3, your program should return all 5 unique BST's shown below.
 * 			
 * 			   1         3     3      2      1
 * 			    \       /     /      / \      \
 * 			     3     2     1      1   3      2
 * 			    /     /       \                 \
 * 			   2     1         2                 3
 * 
 * @author Hxkandwal
 */
public class UniqueBinarySearchTreesII extends AbstractCustomTestRunner {

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public List<TreeNode> _generateTrees(int n) {
        if (n == 0) return Arrays.asList();
        return generateTreesInner (new HashMap<>(), 1, n);
    }

    private List<TreeNode> generateTreesInner (Map<String, List<TreeNode>> map, int start, int end) {
        if (map.containsKey (start + "#" + end)) return map.get (start + "#" + end);
        List<TreeNode> ans = new ArrayList<>();
        if (start >= end) { ans.add (start > end ? null : new TreeNode (start)); return ans; }

        for (int k = start; k <= end; k ++) {
            List<TreeNode> left = generateTreesInner (map, start, k - 1), right = generateTreesInner (map, k + 1, end);

            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode n = new TreeNode (k);
                    n.left = l; n.right = r;
                    ans.add (n);
                }
            }
        }

        map.put (start + "#" + end, ans);
        return ans;
    }
}
