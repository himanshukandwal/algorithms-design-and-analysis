package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 346. Moving Average from Data Stream
 * 
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * 
 * For example,
 * 		MovingAverage m = new MovingAverage(3);
 * 		m.next(1) = 1
 * 		m.next(10) = (1 + 10) / 2
 * 		m.next(3) = (1 + 10 + 3) / 3
 * 		m.next(5) = (10 + 3 + 5) / 3
 * 
 * @author Hxkandwal
 */
public class MovingAverageFromDataStream extends AbstractCustomTestRunner {
	
    int [] values;
    int size = 0;
    int sum = 0;
    int rindex = 0;
    
    /** Initialize your data structure here. */
    public MovingAverageFromDataStream (int size) {
        this.values = new int [size];
    }
    
    public double next(int val) {
        if (size < values.length) return (sum += (values [size ++] = val)) * 1.0d  / size;
        else {
            if (rindex == values.length) rindex = 0;
            double avg = (sum = (sum - values [rindex] + val)) * 1.0d / size;
            values [rindex ++] = val;
            return avg;
        }
    }

}
