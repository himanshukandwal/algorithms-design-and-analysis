package challenges.leetcode;

import java.util.PriorityQueue;

import challenges.AbstractCustomTestRunner;

/**
 * 239. Sliding Window Maximum
 * 
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can 
 * only see the k numbers in the window. Each time the sliding window moves right by one position.
 * 
 * For example, Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * 
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 * Therefore, return the max sliding window as [3,3,5,5,6,7].
 * 
 * Note: You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 * 
 * Follow up: Could you solve it in linear time?
 * 
 * @author Hxkandwal
 */
public class SlidingWindowMaximum extends AbstractCustomTestRunner {

	public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new int [0];
        int n = nums.length - k + 1;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<> ((a, b) -> b - a);
        int [] ans = new int [n];
        for (int idx = 0; idx < nums.length; idx ++) {
            if (idx < k - 1) maxHeap.offer (nums [idx]);
            else {
                maxHeap.offer (nums [idx]);
                ans [idx - k + 1] = maxHeap.peek ();
                maxHeap.remove (nums [idx - k + 1]);
            }
        }
        return ans;
    }
	
}
