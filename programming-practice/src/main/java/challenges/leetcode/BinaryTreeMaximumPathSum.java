package challenges.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * 124. Binary Tree Maximum Path Sum
 * 
 * Given a binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node 
 * in the tree along the parent-child connections. The path must contain at least one node and does 
 * not need to go through the root.
 * 
 * For example: Given the below binary tree,
 * 
 * 		  1
 *       / \
 *      2   3
 * 
 * Return 6.
 * 
 * @author Hxkandwal
 */
public class BinaryTreeMaximumPathSum extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public int maxPathSum(TreeNode root) {
		TreeNode dummyHead = new TreeNode(0);
		Map<TreeNode, List<Set<Integer>>> map = new HashMap<>();
		map.put(dummyHead, Arrays.asList(new HashSet<>(), new HashSet<>()));
		return maxPathSumInner(map, dummyHead, root, 0);
	}
	    
    private int maxPathSumInner (Map<TreeNode, List<Set<Integer>>> map, TreeNode parent, TreeNode node, int max) {
        if (node == null) return max;
        Set<Integer> fset = new HashSet<>(), sset = new HashSet<>();
        sset.add (node.val);
        
        for (Integer p : map.get (parent).get (0)) { 
            fset.add (p + node.val); max = Math.max (max, p + node.val);
        }
        
        map.put (node, Arrays.asList (fset, sset));
        max = maxPathSumInner (map, node, node.left, max);
        if (node.left == null) return max;
        for (Integer p : map.get (node.left).get (1)) { 
            sset.add (p + node.val); 
            fset.add (p + node.val);
            max = Math.max (max, p + node.val);
        }
        max = maxPathSumInner (map, node, node.right, max);
        return max;
    }
	    
}
