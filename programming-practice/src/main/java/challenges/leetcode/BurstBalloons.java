package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 312. Burst Balloons
 *
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons.
 * If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Note:
 *
 *  (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 *  (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * Example:
 *
 *     Given [3, 1, 5, 8]
 *     Return 167
 *
 *     nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *     coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 * @author hxkandwal
 */
public class BurstBalloons extends AbstractCustomTestRunner {

    public int maxCoins(int[] nums) {
        boolean [] state = new boolean [nums.length];
        Arrays.fill(state, true);
        return dfs (nums, state, new HashMap<>());
    }

    private int dfs (int[] nums, boolean[] state, Map<String, Integer> map) {
        boolean complete = true;
        for (boolean b : state) complete = complete && !b;
        if (complete) return 0;

        String key = Arrays.toString(state);
        if (map.containsKey(key)) return map.get(key);

        int max = 0;
        for (int idx = 0; idx < nums.length; idx ++) {
            if (state [idx]) {
                state [idx] = false;
                int l = 1, r = 1;
                for (int j = 0; j < idx; j ++) if (state [j]) l = nums [j];
                for (int j = idx + 1; j < nums.length; j ++) if (state [j]) { r = nums [j]; break; }
                max = Math.max (max, l * nums [idx] * r + dfs (nums, state, map));
                state [idx] = true;
            }
        }

        map.put (key, max);
        return max;
    }
}
