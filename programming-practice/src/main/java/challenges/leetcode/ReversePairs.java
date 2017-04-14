package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 493. Reverse Pairs
 * 
 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
 * You need to return the number of important reverse pairs in the given array.
 * 
 * Example 1:
 * 		Input: [1,3,2,3,1]
 * 		Output: 2
 * 
 * Example 2:
 * 		Input: [2,4,3,5,1]
 * 		Output: 3
 * 
 * @author Hxkandwal
 */
public class ReversePairs extends AbstractCustomTestRunner {
	
	private static ReversePairs _instance = new ReversePairs();

	class Node {
		long val;
		int sameCount = 1, biggerCount; 
		Node left, right;
		Node (int val) { this.val = (long) val; }
		
		int add (int num) {
			int ret = 0;
			if (num  == this.val) { 
				ret = this.getTwiceBigger(num);
				if (this.right != null) ret = this.right.getTwiceBigger(num);
				this.sameCount ++; 
			} 
			else if (num > this.val) {
				this.biggerCount ++;
				if (this.right == null) this.right = new Node (num);
				else ret = this.right.add(num);
			} else {
				if (2l * num <= this.val) ret = this.sameCount + this.biggerCount + (2l * num == this.val ? -this.sameCount : 0);
				if (this.left == null) this.left = new Node (num);
				else ret += this.left.add(num);
			}
			return ret;
		}
		
		int getTwiceBigger (int num) {
			int ret = 0;
			if (2l * num <= this.val) ret = this.sameCount + this.biggerCount + (2l * num == this.val ? -this.sameCount : 0);
			if  (this.left != null) ret += this.left.getTwiceBigger(num);
			return ret;
		}
	}
	
    public int _reversePairs(int[] nums) {
        if (nums.length == 0) return 0;
        int count = 0;
        Node r = new Node (nums [0]);
        for (int idx = 1; idx < nums.length; idx ++) count += r.add(nums [idx]);
    	return count;
    }
	
	// driver method
    public static void main(String[] args) throws FileNotFoundException {
    	_instance.runTest(new int[] {-5, -5 }, 1);
		_instance.runTest(new int[] { 1, 3, 2, 3, 1 }, 2);
		_instance.runTest(new int[] { 2, 4, 3, 5, 1 }, 3);
		_instance.runTest(new int[] { 2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647 }, 0);
		_instance.runTest(new int[] { 2147483647, 2147483647, -2147483647, -2147483647, -2147483647, 2147483647 }, 9);
    }

	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
