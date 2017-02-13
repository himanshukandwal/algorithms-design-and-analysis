package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
	
	private static FindMedianFromDataStream _instance = new FindMedianFromDataStream();
	
	private FindMedianFromDataStream() {}
	
	// data-structure.
	public static class MedianFinder {
		
		
		/** initialize your data structure here. */
	    public MedianFinder() {
	    	
	    }
	    
	    public void addNum(int num) {
	        
	    }
	    
	    public double findMedian() {
	        
	    }
	}

}
