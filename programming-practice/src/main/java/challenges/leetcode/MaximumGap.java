package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import challenges.AbstractCustomTestRunner;

/**
 * 164. Maximum Gap
 * 
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * 
 * Try to solve it in linear time/space.
 * 
 * Return 0 if the array contains less than 2 elements.
 * 
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 * 
 * @author Hxkandwal
 */
public class MaximumGap extends AbstractCustomTestRunner {
	
	private static MaximumGap _instance = new MaximumGap();

	// idea: BucketSort and Radix sort or O (n) not O (nLogn)
	public int _maximumGapBucketSort(int[] nums) {
        if (nums.length <= 1) return 0;
        int max = 0, min = Integer.MAX_VALUE;
        for (int num : nums) { max = Math.max (max, num); min = Math.min (min, num); }
        int [] bucketsMin = new int [nums.length - 1];
        int [] bucketsMax = new int [nums.length - 1];
        
        Arrays.fill (bucketsMin, Integer.MAX_VALUE);
        Arrays.fill (bucketsMax, Integer.MIN_VALUE);
        int gap = (int) Math.ceil ((double) (max - min)/ (nums.length - 1));
        
        for (int num : nums) {
            if (num == min || num == max) continue;
            int idx = (num - min)/gap;
            bucketsMin [idx] = Math.min (num, bucketsMin [idx]);
            bucketsMax [idx] = Math.max (num, bucketsMax [idx]);
        }
        
        int maxGap = 0;
        for (int idx = 0; idx < nums.length - 1; idx ++) {
            if (bucketsMin [idx] == Integer.MAX_VALUE && bucketsMax [idx] == Integer.MIN_VALUE) continue;
            maxGap = Math.max (maxGap, bucketsMin [idx] - min);
            min = bucketsMax [idx];
        }
        maxGap = Math.max(maxGap, max - min); 
        return maxGap;
    }
	
	// Using sorting logic of TreeSet.
	public int _maximumGapLibraray(int[] nums) {
		if (nums.length <= 1) return 0;
        int max = 0;
        TreeSet<Integer> tset = new TreeSet<>();
        for (int num : nums) tset.add (num);
        for (int num : tset) {
            max = Math.max (max, tset.floor (num - 1) == null ? max : Math.abs (tset.floor (num - 1) - num));
            max = Math.max (max, tset.ceiling (num + 1) == null ? max : Math.abs (tset.ceiling (num + 1) - num));
            tset.add (num);
        }
        return max;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 10000000 }, 9999999);
	}

	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}
