package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
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
	
	private static SlidingWindowMaximum _instance = new SlidingWindowMaximum();

	// using smart queuing.
	public int[] _maxSlidingWindowBest(int[] nums, int k) {
        if (nums.length == 0) return new int [0];
        int n = nums.length - k + 1;
        int [] ans = new int [n];
        Deque <Integer> queue = new ArrayDeque<>();
        for (int idx = 0; idx < nums.length; idx ++) {
            while (!queue.isEmpty () && queue.peek () < idx - k + 1) queue.poll ();
            while (!queue.isEmpty () && nums [queue.peekLast ()] < nums [idx]) queue.pollLast ();
            queue.offer (idx);
            if (idx - k + 1 >= 0) ans [idx - k + 1] = nums [queue.peekFirst ()];
        }
        return ans;
    }
	
	// using priority queue
	public int[] _maxSlidingWindowPriorityQueue(int[] nums, int k) {
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
	
	// using linked list
	public int[] _maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new int [0];
        int n = nums.length - k + 1, max = Integer.MIN_VALUE;
        int [] ans = new int [n];
        LinkedList<Integer> queue = new LinkedList<> ();
        for (int idx = 0; idx < nums.length; idx ++) {
            if (idx < k - 1) {
                max = Math.max (max, nums [idx]);
                queue.offer (nums [idx]);
            } else {
                queue.offer (nums [idx]);
                ans [idx - k + 1] = max = Math.max (max, nums [idx]);
                if (max == queue.poll ()) max = find (new LinkedList<>(queue), 0, 0, queue.size() - 1);
            }
        }
        return ans;
    }
    
    private int find (LinkedList<Integer> nums, int k, int start, int end) {
        if (nums.size () == 0) return Integer.MIN_VALUE;
        if (start >= end) return nums.get (start);
        int left = start, right = end - 1;
        while (left < right) {
            if (nums.get (left) > nums.get (end)) left ++;
            else if (nums.get (right) <= nums.get (end)) right --;
            else swap (nums, left ++, right --);
        }
        swap (nums, nums.get (left) > nums.get (end) ? ++ left : left, end);
        return k > left ? find (nums, k, left + 1, end) : (k < left ? find (nums, k, start, left - 1) : nums.get (k));
    }
    
    private void swap (LinkedList<Integer> nums, int from, int to) {
        int val = nums.get (from);
        nums.set (from, nums.get (to));
        nums.set (to, val);
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { -7, -8, 7, 5, 7, 1, 6, 0 }, 4, new int[] { 7, 7, 7, 7, 7 });
	}

	public void runTest(final int [] nums, int k, final int [] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, k });

		for (Object answer : answers)
			assertThat((int []) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}  	
}
