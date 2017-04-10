package challenges.leetcode;

import java.util.PriorityQueue;

import challenges.AbstractCustomTestRunner;

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

	PriorityQueue<Long> large = new PriorityQueue<>();
	PriorityQueue<Long> small = new PriorityQueue<>();

	/** initialize your data structure here. */
	public FindMedianFromDataStream() {

	}

	public void addNum(int num) {
		large.offer((long) num);
		small.offer(-large.poll());
		if (large.size() < small.size())
			large.offer(-small.poll());
	}

	public double findMedian() {
		return large.size() > small.size() ? large.peek() : (large.peek() - small.peek()) / 2.0;
	}

}
