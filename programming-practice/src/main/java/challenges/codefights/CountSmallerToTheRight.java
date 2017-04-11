package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;
import challenges.assorted.FenwickTree;

/**
 * Count Smaller To The Right
 * 
 * You have array of integers nums and you need to return a new counts array. In the new counts array, 
 * counts[i] is the number of smaller elements to the right of nums[i].
 * 
 * Example:
 * 		For nums = [3, 8, 4, 1], the output should be countSmallerToTheRight(nums) = [1, 2, 1, 0].
 * 	
 * To the right of 3: there is 1 smaller element (1).
 * To the right of 8: there are 2 smaller elements (4 and 1).
 * To the right of 4: there is 1 smaller element (1).
 * To the right of 1: there are 0 smaller elements.
 * 
 * The resulting array is [1, 2, 1, 0].
 * 
 * @author Hxkandwal
 */
public class CountSmallerToTheRight extends AbstractCustomTestRunner {
	
	private static CountSmallerToTheRight _instance = new CountSmallerToTheRight();

	public int[] _countSmallerToTheRightFaster(int[] nums) {
	    int[] ans = new int [nums.length];
	    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	    for (int num : nums) { min = Math.min(min, num); max = Math.max(max, num); }
	    if (min <= 0) {
	    	min = (-min + 1);
	    	for (int idx = 0; idx < nums.length; idx ++) nums [idx] += min;
	    	max += min;
	    }
	    
	    FenwickTree ft = new FenwickTree(max);
	    for (int idx = nums.length - 1; idx >= 0; idx --) {
	    	ans [idx] = ft.sum(nums [idx] - 2);
	    	ft.add(nums [idx] - 1, 1);
	    }
	    
	    return ans;
	}
	
	// percolate up strategy.
	public class Node {
	    int val;
	    Node left;
	    Node right;
	    int count;

	    public Node(int val, int count) {
	        this.val = val;
	        this.count = count;
	    }
	}

	public int[] _countSmallerToTheRight(int[] nums) {
	    if (nums == null || nums.length == 0) return new int [0];
	    int [] results = new int [nums.length];
	    Node root = new Node(nums [nums.length - 1], 0);
	    for (int i = nums.length - 2; i >= 0; i--) helper (nums[i], root, 0, i, results);
	    return results;
	}

	public void helper(int val, Node root, int count, int index, int[] results) {
	    if (val > root.val) {
	        ++ count; 
	        count += root.count;
	        if (root.right == null) {
	            root.right = new Node(val, 0);
	            results[index] = count;
	        } else helper(val, root.right, count, index, results);
	    } else {
	        root.count++;
	        if (root.left == null) {
	            root.left = new Node(val, 0);
	            results[index] = count;
	        } else helper(val, root.left, count, index, results);
	    }
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 3, 8, 4, 1 }, new int[] { 1, 2, 1, 0 } );
		_instance.runTest(new int[] { 5, 3, 2, 4, 4, 35, 1, 14, 38, 35, 2 }, new int[] { 6, 3, 1, 2, 2, 3, 0, 1, 2, 1, 0 } );
	}

	public void runTest(final int[] nums, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((int []) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	} 
	
}
