package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 437. Path Sum III
 * 
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 * 
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 * 
 * Example:
 * 		root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * 
 *
 *	      10
 *	     /  \
 *	    5   -3
 *	   / \    \
 *	  3   2   11
 *	 / \   \
 *	3  -2   1
 *
 * Return 3. 
 * 
 * The paths that sum to 8 are:
 * 	1.  5 -> 3
 * 	2.  5 -> 2 -> 1
 * 	3. -3 -> 11
 * 
 * @author Hxkandwal
 *
 */
public class PathSumIII extends AbstractCustomTestRunner {
	
	private static PathSumIII _instance = new PathSumIII();

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	/**
	 * O (n) solution.
	 * 
	 * So the idea is similar as Two sum, using HashMap to store ( key : the prefix sum, value : how many ways get to this prefix sum) , 
	 * and whenever reach a node, we check if prefix sum - target exists in hashmap or not, if it does, we added up the ways of prefix
	 * sum - target into res.
	 * 
	 * For instance : 
	 * 		in one path we have 1,2,-1,-1,2, then the prefix sum will be: 1, 3, 2, 1, 3, let's say we want to find target sum is 2, then 
	 * 		we will have { 2 }, { 1, 2, -1 }, { 2, -1, -1, 2 } and { 2 } ways.
	 * 
	 * I used global variable count, but obviously we can avoid global variable by passing the count from bottom up. The time complexity is O(n).
	 */
	public int _pathSum(TreeNode root, int sum) {
        Map <Integer, Integer> map = new HashMap<>();
        map.put(0, 1);  //Default sum = 0 has one count
        return backtrack(root, 0, sum, map); 
    }
	
    //BackTrack one pass
    public int backtrack(TreeNode root, int sum, int target, Map<Integer, Integer> map){
        if (root == null) return 0;
        
        sum += root.val;
        int res = map.getOrDefault (sum - target, 0);    // See if there is a subarray sum equals to target
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        
        //Extend to left and right child
        res += backtrack (root.left, sum, target, map) + backtrack(root.right, sum, target, map);
        
        map.put(sum, map.get(sum) - 1);   				//Remove the current node so it wont affect other path
        return res;
    }
    
    // my take
    public int pathSum(TreeNode root, int sum) {
    	if (root == null) return 0;
        int count = 0;
        
        if (root.val == sum) count ++;
        if (root.left != null)  {
            count += _pathSum(root.left, sum);
            count += pathSumContinuous(root.left, sum - root.val);
        }
        if (root.right != null)  {
            count += _pathSum(root.right, sum);
            count += pathSumContinuous(root.right, sum - root.val);
        }
        
        return count;
    }
    
    private int pathSumContinuous(TreeNode node, int sum) {
        int count = 0;
        if (node.val == sum) count ++;
        if (node.left != null)  count += pathSumContinuous(node.left, sum - node.val);
        if (node.right != null) count += pathSumContinuous(node.right, sum - node.val);
        return count;
    }
    
	// driver method
	public static void main(String[] args) {
		TreeNode node = new TreeNode(10);
		node.left = new TreeNode(5);
		node.left.left = new TreeNode(3);
		node.left.left.left = new TreeNode(3);
		node.left.left.right = new TreeNode(-2);
		node.left.right = new TreeNode(2);
		node.left.right.right = new TreeNode(1);
		node.right = new TreeNode(-3);
		node.right.right = new TreeNode(11);
		
		_instance.runTest(node, 8, 3);
	}

	public void runTest(final TreeNode root, int sum, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { root, sum });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}    
    
}