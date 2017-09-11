package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 81. Search in Rotated Sorted Array II
 * 
 * Follow up for "Search in Rotated Sorted Array": What if duplicates are allowed?
 * 
 * Would this affect the run-time complexity? How and why?
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Write a function to determine if a given target is in the array.
 * 
 * The array may contain duplicates.
 * 
 * @author Hxkandwal
 */
public class SearchInRotatedSortedArrayII extends AbstractCustomTestRunner {
	
	private static SearchInRotatedSortedArrayII _instance = new SearchInRotatedSortedArrayII();

	// try to move away from plateaus
    public boolean _search(int[] nums, int target) {
    	int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums [mid] == target) return true;
            int rmid = mid;
    		while (rmid + 1 < nums.length && nums [rmid] == nums [rmid + 1]) rmid ++;
    		if (rmid == nums.length - 1) high = mid - 1;
    		else {
                if (nums [mid] >= nums [low]) {
                    if (nums [low] <= target && nums [mid] > target) high = mid - 1;
                    else low = mid + 1;
                } else {
                    if (nums [high] >= target && nums [mid] < target) low = mid + 1;
                    else high = mid - 1;
                }
    		}
         }
         List<String> ans = new ArrayList<>();
        ans.add ("a");
        System.out.println(ans.toArray().length);
        return false;
    }

    // Shorter code.
    public boolean _searchShorter(int[] nums, int target) {
    	int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right)/2;
            if (nums [mid] == target) return true;
            if (nums [left] < nums [mid]) {
                if (nums [left] <= target && nums [mid] > target) right = mid - 1;
                else left = mid + 1;
            } else if (nums [left] > nums [mid]) {
                if (nums [right] >= target && nums [mid] < target) left = mid + 1;
                else right = mid - 1;
            } else left ++;
        }
        return false;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 2, 1 }, 1, true);
	}

	public void runTest(final int[] nums, final int target, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, target });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
