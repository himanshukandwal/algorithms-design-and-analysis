package me.hxkandwal.daily.challanges.leetcode;

import java.util.Stack;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 255. Verify Preorder Sequence in Binary Search Tree
 * 
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
 * 
 * You may assume each number in the sequence is unique.
 * 
 * Follow up: Could you do it using only constant space complexity?
 * 
 * @author Hxkandwal
 */
public class VerifyPreorderSequenceInBinarySearchTree extends AbstractCustomTestRunner {

	public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) return true;
        Stack<Integer> stk = new Stack<>();
        
        Integer min = Integer.MIN_VALUE;
        for (int item : preorder) {
            if (item < min) return false;
            while (!stk.isEmpty() && stk.peek() < item) min = stk.pop();
            stk.push (item);
        }
        return true;
    }
	
}
