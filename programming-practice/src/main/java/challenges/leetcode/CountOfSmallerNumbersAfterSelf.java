package challenges.leetcode;

import java.util.LinkedList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

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

	private static CountOfSmallerNumbersAfterSelf _instance = new CountOfSmallerNumbersAfterSelf();

	public List<Integer> countSmaller(int[] nums) {
		LinkedList<Integer> ans = new LinkedList<>();
		if (nums.length == 0) return ans;

		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (int num : nums) { min = Math.min(min, num); max = Math.max(max, num); }
		
		if (min <= 0) { 
			min = (-min + 1);
			for (int idx = 0; idx < nums.length; idx++) nums[idx] += min;
			max += min;
		}

		FenwickTree ft = new FenwickTree(max);
		for (int idx = nums.length - 1; idx >= 0; idx--) {
			ans.addFirst(ft.sum(nums[idx] - 2));
			ft.add(nums[idx] - 1, 1);
		}
		return ans;
	}

	class FenwickTree {
		private int size;
		private int[] array;

		public FenwickTree(int size) {
			this.size = size + 1;
			this.array = new int[size + 1];
		}

		public void add(int index, int val) {
			index++;
			int idx = index;
			while (idx < size) {
				array[idx] += val;
				idx += idx & (~idx + 1); // -x == ~x + 1
			}
		}

		public int sum(int index) {
			index++;
			int sum = 0;
			while (index > 0) {
				sum += array[index];
				index -= index & (~index + 1); // -x == ~x + 1
			}
			return sum;
		}
	}
	
}