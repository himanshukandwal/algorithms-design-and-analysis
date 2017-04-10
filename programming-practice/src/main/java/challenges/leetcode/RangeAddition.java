package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 370. Range Addition
 * 
 * Assume you have an array of length n initialized with all 0's and are given k update operations.
 * Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray 
 * A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
 * 
 * Return the modified array after all k operations were executed.
 * 
 * Example:
 * 		Given:
 * 			length = 5,
 * 			updates = [
 * 						[1,  3,  2],
 * 						[2,  4,  3],
 * 						[0,  2, -2]
 * 					 ]
 * Output:
 * 		[-2, 0, 3, 5, 3]
 * 
 * Explanation:
 * 		Initial state: [ 0, 0, 0, 0, 0 ]
 * 		After applying operation [1, 3, 2]: 	[ 0, 2, 2, 2, 0 ]
 * 		After applying operation [2, 4, 3]: 	[ 0, 2, 5, 5, 3 ]
 * 		After applying operation [0, 2, -2]: 	[-2, 0, 3, 5, 3 ]
 * 
 * @author Hxkandwal
 */
public class RangeAddition extends AbstractCustomTestRunner {

    public int[] getModifiedArrayBetter(int length, int[][] updates) {
        if (length == 0) return new int [] {};
        int [] ans = new int [length];
        
        for (int[] update : updates) {
            ans [update [0]] += update [2];
            if (update [1] + 1 < length) ans [update [1] + 1] += -update [2];
        }
        int sum = 0;
        for (int idx = 0; idx < length; idx ++) {
            ans [idx] = sum + ans [idx];
            sum = ans [idx];
        }
            
        return ans;
    }
    
	// initial idea, time consuming (if have to apply same operation on all elements) 
	public int[] getModifiedArray(int length, int[][] updates) {
        if (length == 0) return new int [] {};
        int [] answer = new int [length];
        for (int[] update : updates)
            for (int idx = update [0]; idx <= update [1] && idx < length; idx ++)
                answer [idx] += update [2];
        return answer;
    }
	
}
