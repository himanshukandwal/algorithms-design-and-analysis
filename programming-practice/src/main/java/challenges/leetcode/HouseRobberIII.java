package challenges.leetcode;

import java.util.HashMap;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 337. House Robber III
 * 
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the 
 * "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that 
 * "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses 
 * were broken into on the same night.
 * 
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * 
 * Example 1:
 *     3
 *    / \
 *   2   3
 *    \   \ 
 *     3   1
 *     
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 * 
 *     3
 *    / \
 *   4   5
 *  / \   \ 
 * 1   3   1
 * 
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
 * 
 * @author Hxkandwal
 */
public class HouseRobberIII extends AbstractCustomTestRunner {
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	// only recursive, no memoization
	public int rob(TreeNode root) {
        if (root == null) return 0;
        int value = root.val;
        
        if (root.left != null) {
            value += rob (root.left.left);
            value += rob (root.left.right);
        }
        
        if (root.right != null) {
            value += rob (root.right.left);
            value += rob (root.right.right);
        }
        
        return Math.max (value, rob (root.left) + rob (root.right));
    }
	
	// recursion with memoization
	public int robBetter(TreeNode root) {
        return robSub (root, new HashMap<>());
    }
    
    public int robSub(TreeNode root, Map<TreeNode, Integer> dp) {
        if (root == null) return 0;
        if (dp.containsKey(root)) return dp.get(root);
        
        int value = root.val;
        if (root.left != null) {
            value += robSub (root.left.left, dp);
            value += robSub (root.left.right, dp);
        }
        
        if (root.right != null) {
            value += robSub (root.right.left, dp);
            value += robSub (root.right.right, dp);
        }
        
        dp.put (root, Math.max (value, robSub (root.left, dp) + robSub (root.right, dp)));
        return dp.get (root);
    }
    
    // Best of all
    /**
     *  maxMoney[0] = max Money avoiding root itself
     *  maxMoney[1] = max Money allowing root to be stolen
     *  
     *  the transfer equation can be read as below:
     */
	public int robBest(TreeNode root) {
		return maxMoney(root)[1];
	}

	// return int[2]: 
	// 		maxMoney[0] = max Money avoiding root itself, 
	// 		maxMoney[1] = max Money allowing root to be stolen
	private int[] maxMoney(TreeNode root) {
		if (root == null) return new int[2];
		int[] ans = new int[2], l = maxMoney(root.left), r = maxMoney(root.right);
		ans[0] = l[1] + r[1];
		ans[1] = Math.max(root.val + l[0] + r[0], ans[0]);
		return ans;
	}
	
}
