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

	private static ThreeSum _instance = new ThreeSum();

	private ThreeSum() {}

	public List<List<Integer>> _threeSum(int[] nums) {
		List<List<Integer>> answer = new ArrayList<>();
		if (nums != null && nums.length > 0) {
			Arrays.sort(nums);
			int l = 0, h = nums.length - 1;
			
			while (l < h) {
				
				if (nums [l] + nums [m] > nums [h]) answer.add(Arrays.asList(nums [l], nums [m], nums [h]));
				while (m > l && nums [l] + nums [m] > nums [h]) m --;
				m += (m == l) ? 1 : 0;
				
				while (m < h && nums [l] + nums [m] < nums [h]) m ++;
				
				
				h --;
			}
		}
		
		return answer;
	}

}
