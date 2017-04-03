package me.hxkandwal.daily.challanges.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 15. 3Sum
 * 
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the 
 * array which gives the sum of zero.
 * 
 * Note: The solution set must not contain duplicate triplets.
 * 
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * 
 * A solution set is: [ [-1, 0, 1], [-1, -1, 2] ]
 * 
 * @author Hxkandwal
 */
public class ThreeSum extends AbstractCustomTestRunner {

	public List<List<Integer>> _threeSum(int[] nums) {
		List<List<Integer>> res =  new ArrayList<> ();
        Arrays.sort (nums);
        for (int idx = 0; idx < nums.length - 2; idx ++) {
            if (idx == 0 || nums [idx - 1] != nums [idx]) {
                int low = idx + 1, high = nums.length - 1, sum = -nums [idx];
                
                while (low < high) {
                    if (nums [low] + nums [high] == sum) {
                        res.add (Arrays.asList (nums [idx], nums [low], nums [high]));
                        
                        while (low < high && nums [low] == nums [low + 1]) low ++;
                        while (low < high && nums [high] == nums [high - 1]) high --;
                        low ++; high --;
                    } 
                    else if (nums [low] + nums [high] < sum) low ++;
                    else high --;
                }
            }
        }
        return res;
	}

}
