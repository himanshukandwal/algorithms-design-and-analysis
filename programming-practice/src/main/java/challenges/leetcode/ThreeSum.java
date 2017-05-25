package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

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
	
	private static ThreeSum _instance = new ThreeSum ();

	public List<List<Integer>> _threeSum(int[] nums) {
		Arrays.sort (nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int idx = 0; idx < nums.length - 2; idx ++) {
            if (idx == 0 || nums [idx] != nums [idx - 1]) {
                for (int jdx = idx + 1; jdx < nums.length - 1; jdx ++) {
                    if (jdx == idx + 1 || nums [jdx] != nums [jdx - 1]) {    
                        int search = -(nums [idx] + nums [jdx]);
                        int lo = jdx + 1, hi = nums.length - 1;
                        while (lo <= hi) {
                            int m = (lo + hi) >>> 1;
			                if (nums [m] > search) hi = m - 1;
			                else if (nums [m] < search) lo = m + 1;
                            else { ans.add (Arrays.asList (nums [idx], nums [jdx], nums [m])); break; }
                        }
                    }
                }
            }
        }
        return ans;
	}
	
	public List<List<Integer>> _threeSumOther(int[] nums) {
		Arrays.sort (nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int idx = 0; idx < nums.length - 2; idx ++) {
            if (idx == 0 || nums [idx] != nums [idx - 1]) {
                int left = idx + 1, right = nums.length - 1, search = - nums [idx];
                while (left < right) {
                    if (nums [left] + nums [right] > search) right --;
                    else if (nums [left] + nums [right] < search) left ++;
                    else { 
                        ans.add (Arrays.asList (nums [idx], nums [left], nums [right])); 
                        while (left < right && nums [left] == nums [left + 1]) left ++;
                        while (left < right && nums [right] == nums [right - 1]) right --;
                        left ++; right --; 
                    }
                }
            }
        }
        return ans;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { -1, 0, 1, 2, -1, -4 }, Arrays.asList(Arrays.asList(-1, -1, 2), Arrays.asList(-1, 0, 1)));
	}
	
	@SuppressWarnings("unchecked")
	public void runTest(final int[] nums, final List<List<Integer>> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });
		
		for (Object answer : answers) 
			assertThat((List<List<Integer>>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
