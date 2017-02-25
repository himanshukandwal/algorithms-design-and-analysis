package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 11. Container With Most Water
 * 
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the 
 * two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * 
 * Note: You may not slant the container and n is at least 2.
 * 
 * @author Hxkandwal
 */
public class ContainerWithMostWater extends AbstractCustomTestRunner {

	// two pointer mechanism.
	public int maxArea(int[] height) {
        int maxArea = 0, low = 0, high = height.length - 1;
        while (low < high) {
            maxArea = Math.max (maxArea, Math.min (height [low], height [high]) * (high - low));
            if (height [low] > height [high]) high --;
            else low ++;
        }
        return maxArea;
    }
	
}
