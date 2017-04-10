package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 137. Single Number II
 * 
 * Given an array of integers, every element appears three times except for one, which appears exactly once. 
 * Find that single one.
 * 
 * @author Hxkandwal
 */
public class SingleNumberII extends AbstractCustomTestRunner {

	// further reading: https://discuss.leetcode.com/topic/2031/challenge-me-thx/33
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int pos = 31; pos >= 0; pos --) {
            int count = 0;
            for (int num : nums) count += ((num >> pos) & 1) == 1 ? 1 : 0;
            res |= (count % 3) << pos;
        }
        return res;
    }
    
}
