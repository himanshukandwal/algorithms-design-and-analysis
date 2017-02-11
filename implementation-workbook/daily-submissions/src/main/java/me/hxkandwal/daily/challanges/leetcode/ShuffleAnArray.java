package me.hxkandwal.daily.challanges.leetcode;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 384. Shuffle an Array
 * 
 * Shuffle a set of numbers without duplicates. 
 * 
 * Example:	
 * 		// Init an array with set 1, 2, and 3.
 * 		int[] nums = {1,2,3};
 * 		Solution solution = new Solution(nums);
 * 
 * 		// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to 
 * 		be returned.
 * 		solution.shuffle();
 * 
 * 		// Resets the array back to its original configuration [1,2,3].
 * 		solution.reset();
 * 
 * 		// Returns the random shuffling of array [1,2,3].
 * 		solution.shuffle();
 * 
 * @author Hxkandwal
 *
 */
public class ShuffleAnArray extends AbstractCustomTestRunner {
	
	private static ShuffleAnArray _instance = new ShuffleAnArray();
	
	private int [] nums;
	private int [] backup;
	
	public ShuffleAnArray() {}
	
	public ShuffleAnArray (int[] nums) {
        this.nums = nums;
        this.backup = new int [nums.length];
        
        for (int idx = 0; idx < nums.length; idx ++)  backup [idx] = nums [idx];
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
    	for (int idx = 0; idx < nums.length; idx ++)  nums [idx] = backup [idx];
    	return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
    	int shuffles = (int) (nums.length * 0.60);
    	
    	Random rnd = new Random ();
    	while (shuffles -- > 0) {
    		int start = 0, end = 0;
    		
    		while ((start = rnd.nextInt(nums.length)) != (end = rnd.nextInt(nums.length))) {
    			int t = nums [start];
    			nums [start] = nums [end];
    			nums [start] = t;
    			break;
    		}
    	}
    	
    	return nums;
        
    }

}
