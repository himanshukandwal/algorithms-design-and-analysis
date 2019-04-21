package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 480. Sliding Window Median
 *
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * Examples:
 * [2,3,4] , the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Your job is to output the median array for each window in the original array.
 *
 * For example,
 *          Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *
 *          Window position                Median
 *          ---------------               -----
 *          [1  3  -1] -3  5  3  6  7       1
 *          1 [3  -1  -3] 5  3  6  7       -1
 *          1  3 [-1  -3  5] 3  6  7       -1
 *          1  3  -1 [-3  5  3] 6  7       3
 *          1  3  -1  -3 [5  3  6] 7       5
 *          1  3  -1  -3  5 [3  6  7]      6
 *
 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 *
 * Note:
 *  You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
 *
 * @author Hxkandwal
 */
public class SlidingWindowMedian extends AbstractCustomTestRunner {

    public double[] _medianSlidingWindow(int[] nums, int k) {
        MedianQueue window = new MedianQueue();
        double[] median = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            window.add(nums[i]);
            if (i >= k) window.remove(nums[i - k]);
            if (i >= k - 1) median[i - k + 1] = window.median();
        }
        return median;
    }

    static class MedianQueue {
        Queue<Long> maxHeap = new PriorityQueue<>(Collections.reverseOrder()), minHeap = new PriorityQueue<>();

        public void add(long n) {
            maxHeap.add(n);
            minHeap.add(maxHeap.poll());
        }

        public double median() {
            while (maxHeap.size() - minHeap.size() >= 2) minHeap.offer(maxHeap.poll());
            while (minHeap.size() - maxHeap.size() >= 1) maxHeap.offer(minHeap.poll());
            return maxHeap.size() == minHeap.size() ? (maxHeap.peek() + minHeap.peek()) / 2.0 : maxHeap.peek();
        }

        public boolean remove(long n) {
            return maxHeap.remove(n) || minHeap.remove(n);
        }
    }
}
