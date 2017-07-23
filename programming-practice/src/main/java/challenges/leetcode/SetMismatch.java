package challenges.leetcode;

import java.util.Arrays;

import challenges.AbstractCustomTestRunner;

/**
 * 645. Set Mismatch
 * 
 * The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got 
 * duplicated to another number in the set, which results in repetition of one number and loss of another number.
 * 
 * Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice 
 * and then find the number that is missing. Return them in the form of an array.
 * 
 * Example 1:
 * 		Input: nums = [1,2,2,4]
 * 		Output: [2,3]
 * 
 * Note:
 * 	-	The given array size will in the range [2, 10000].
 * 	-	The given array's numbers won't have any order.
 * 
 * @author Hxkandwal
 */
public class SetMismatch extends AbstractCustomTestRunner {

	public int[] findErrorNums(int[] nums) {
        int [] ans = { -1, -1 };
        Arrays.sort (nums);
        for (int idx = 0; idx < nums.length; idx ++) {
            if (idx > 0) {
                if (nums [idx - 1] == nums [idx]) ans [0] = nums [idx];
                else if (nums [idx] != nums [idx - 1] + 1) ans [1] = nums [idx - 1] + 1;
            }
        }
        if (ans [1] < 0) ans [1] = (nums [0] == 1) ? nums.length : 1;
        return ans;
    }
	
}
