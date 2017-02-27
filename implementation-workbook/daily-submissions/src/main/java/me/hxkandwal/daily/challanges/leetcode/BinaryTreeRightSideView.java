package me.hxkandwal.daily.challanges.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 199. Binary Tree Right Side View
 * 
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you 
 * can see ordered from top to bottom.
 * 
 * For example:
 * 		Given the following binary tree,
 * 
 * 		   1            <---
 * 		 /   \
 * 		2     3         <---
 * 		 \     \
 * 		  5     4       <---
 * 
 * You should return [1, 3, 4].
 * 
 * @author Hxkandwal
 */
public class BinaryTreeRightSideView extends AbstractCustomTestRunner {
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        if (root != null) {
            Queue <TreeNode> queue = new LinkedList<>(); 
            TreeNode node = null;
            queue.offer (root);
            
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size -- > 0) {
                    node = queue.poll();
                    if (size == 0) answer.add (node.val);
                    
                    if (node.left != null) queue.offer (node.left);
                    if (node.right != null) queue.offer (node.right);
                }
            }
        }
        return answer;
    }
    
    // another good approach, recurse both side (right side first) and check if the selection has been made of not at every depth (Space efficient solution)
    public List<Integer> rightSideViewSpaceEfficient(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        rightSideView (root, 0, list);
        return list;
    }
    
    public void rightSideView(TreeNode root, int level, List<Integer> list) {
        if (root == null) return;
        if (list.size() == level) list.add (root.val);
        rightSideView (root.right, level + 1, list);
        rightSideView (root.left, level + 1, list);
    }
    
}
