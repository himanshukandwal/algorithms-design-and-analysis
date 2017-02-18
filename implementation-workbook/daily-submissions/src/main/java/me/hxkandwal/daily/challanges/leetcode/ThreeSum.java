package me.hxkandwal.daily.challanges.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 15. 3Sum
 * 
 * Given an array S of n integers, are there elements a, b, c in S such that a +
 * b + c = 0? Find all unique triplets in the array which gives the sum of zero.
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

	private static ThreeSum _instance = new ThreeSum();

	private ThreeSum() {
	}

	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);

		List<List<Integer>> r = new LinkedList<>();
		
		for (int i = 0; i < nums.length - 2; i++)
			
			if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
				int j = i + 1, k = nums.length - 1, sum = -nums[i];
				while (j < k) {
					if (j != i + 1 && nums[j] == nums[j - 1])
						j++;
					else if (k != nums.length - 1 && nums[k] == nums[k + 1])
						k--;
					else if (nums[j] + nums[k] == sum) {
						r.add(Arrays.asList(nums[i], nums[j], nums[k]));
						while (j < k && nums[j] == nums[j + 1])
							j++;
						while (j < k && nums[k] == nums[k - 1])
							k--;
						j++;
						k--;
					} else if (nums[j] + nums[k] < sum)
						j++;
					else
						k--;
				}
			}
		return r;
	}

}
