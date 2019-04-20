package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.PriorityQueue;

/**
 * 295. Find Median from Data Stream
 * 
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is 
 * the mean of the two middle value.
 * 
 * Examples:
 * 		[2,3,4] , the median is 3
 * 		[2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Design a data structure that supports the following two operations:
 * 
 * 	1. void addNum(int num) - Add a integer number from the data stream to the data structure.
 *  2. double findMedian() - Return the median of all elements so far.
 *  
 * For example:
 * 		addNum(1)
 * 		addNum(2)
 * 		findMedian() -> 1.5
 * 		
 * 		addNum(3)
 * 		findMedian() -> 2
 *  
 * @author Hxkandwal
 */
public class FindMedianFromDataStream extends AbstractCustomTestRunner {

	PriorityQueue<Long> min = new PriorityQueue<>();
	PriorityQueue<Long> max = new PriorityQueue<>();

	/** initialize your data structure here. */
	public FindMedianFromDataStream() {

	}

	public void addNum(int num) {
		min.offer((long) num);
		max.offer(-min.poll());
		if (min.size() < max.size())
			min.offer(-max.poll());
	}

	public double findMedian() {
		return min.size() > max.size() ? min.peek() : (min.peek() - max.peek()) / 2.0;
	}

}
