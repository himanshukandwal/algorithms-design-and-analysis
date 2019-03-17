package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 673. Number of Longest Increasing Subsequence
 *
 * Given an unsorted array of integers, find the number of longest increasing subsequence.
 *
 * Example 1:
 *              Input: [1,3,5,4,7]
 *              Output: 2
 *              Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
 *
 * Example 2:
 *              Input: [2,2,2,2,2]
 *              Output: 5
 *              Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
 *
 * Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
 *
 * @author Hxkandwal
 */
public class NumberofLongestIncreasingSubsequence extends AbstractCustomTestRunner {

    public int _findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, int[]> map = new HashMap<>();
        for (int idx = 0; idx < nums.length; idx ++) {
            map.put (idx, new int [] { 1, 1 }); // map [index] = { size/length, count }
            for (int k = idx - 1; k >= 0; k --)
                if (nums [k] < nums [idx]) {
                    if (map.get (k) [0] + 1 > map.get (idx) [0]) {
                        map.get (idx) [0] = map.get (k) [0] + 1;
                        map.get (idx) [1] = map.get (k) [1];
                    } else if (map.get (k) [0] + 1 == map.get (idx) [0])
                        map.get (idx) [1] += map.get (k) [1];
                }
        }
        int maxLen = 0, maxCount = 0;
        for (int k : map.keySet()) {
            if (map.get(k)[0] > maxLen) {
                maxLen = map.get(k)[0];
                maxCount = map.get(k)[1];
            } else if (map.get(k)[0] == maxLen)
                maxCount += map.get(k)[1];
        }
        return maxCount;
    }

    // better: we can use length[idx], count [idx], mapped to index.
    public int _findNumberOfLISNativeArrays(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int [] length = new int [nums.length], count = new int [nums.length];

        int maxLength = 0, ans = 0;
        for (int idx = 0; idx < nums.length; idx ++) {
            count [idx] = length [idx] = 1;
            for (int k = idx - 1; k >= 0; k --) {
                if (nums [k] < nums [idx]) {
                    if (length [k] + 1 > length [idx]) {
                        length [idx] = length [k] + 1;
                        count [idx] = count [k];
                    }
                    else if (length [k] + 1 == length [idx]) count [idx] += count [k];
                }
            }
            maxLength = Math.max (maxLength, length [idx]);
        }
        for (int idx = 0; idx < nums.length; idx ++)
            if (length [idx] == maxLength) ans += count [idx];
        return ans;
    }
}
