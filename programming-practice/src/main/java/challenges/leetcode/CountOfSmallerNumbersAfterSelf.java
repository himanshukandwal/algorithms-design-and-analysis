package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 315. Count of Smaller Numbers After Self
 * 
 * Same as : Count Smaller To The Right
 * 
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where 
 * counts[i] is the number of smaller elements to the right of nums[i].
 * 
 * Example:
 * 		Given nums = [5, 2, 6, 1]
 * 
 * 		To the right of 5 there are 2 smaller elements (2 and 1).
 * 		To the right of 2 there is only 1 smaller element (1).
 * 		To the right of 6 there is 1 smaller element (1).
 * 		To the right of 1 there is 0 smaller element.
 * 
 * 		Return the array [2, 1, 1, 0].
 *  
 * @author Hxkandwal
 */
public class CountOfSmallerNumbersAfterSelf extends AbstractCustomTestRunner {

	public List<Integer> countSmaller(int[] nums) {
		FenwickTree ft = new FenwickTree(nums.length);
		int[] sorted = nums.clone();
		Arrays.sort (sorted);
		int [] index = new int [sorted.length];
		for (int idx = 0; idx < nums.length; idx ++)
			index [idx] = Arrays.binarySearch(sorted, nums [idx]);

		List<Integer> ans = new ArrayList<>();
		for (int idx = nums.length - 1; idx >= 0; idx --) {
			ft.update (index [idx], 1);
			ans.add (0, ft.sum (index [idx] - 1));
		}
		return ans;
	}

	class FenwickTree {
		int [] arr;

		public FenwickTree(int size) {
			arr = new int [size + 1];
		}

		public void update (int idx, int value) {
			idx ++;
			while (idx < arr.length) {
				arr [idx] += value;
				idx += idx & (~idx + 1);
			}
		}

		public int sum (int l, int r) {
			return sum (r) - sum (l - 1);
		}

		public int sum (int idx) {
			idx ++;
			int sum = 0;
			while (idx >= 1) {
				sum += arr [idx];
				idx -= idx & (~idx + 1);
			}
			return sum;
		}
	}
	
}