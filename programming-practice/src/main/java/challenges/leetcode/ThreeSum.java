package challenges.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

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
		Arrays.sort (nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length - 2; i ++) {
            if (i == 0 || nums [i - 1] != nums [i]) {
                int left = i + 1, right = nums.length - 1, target = -nums [i];
                while (left < right) {
                    if (nums [left] + nums [right] < target) left ++;
                    else if (nums [left] + nums [right] > target) right --;
                    else { 
                        ans.add (Arrays.asList (nums [i], nums [left], nums [right])); 
                        while (left < right && nums [left] == nums [left + 1]) left ++;
                        while (left < right && nums [right] == nums [right - 1]) right --;
                        left ++; right --;
                    }
                }
            }
        }
        return ans;
	}

}
