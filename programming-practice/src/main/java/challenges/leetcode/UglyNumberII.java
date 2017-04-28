package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 264. Ugly Number II
 * 
 * Write a program to find the n-th ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * 
 * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * 
 * Note that 1 is typically treated as an ugly number, and n does not exceed 1690.
 * 
 * @author Hxkandwal
 *
 */
public class UglyNumberII extends AbstractCustomTestRunner {

	public int nthUglyNumber(int n) {
        if (n <= 0) return 0;
        int [] arr = new int [n];
        arr [0] = 1;
        int t2 = 0, t3 = 0, t5 = 0;
        
        for (int idx = 1; idx < n; idx ++) {
            arr [idx] = Math.min (arr [t2] * 2, Math.min (arr [t3] * 3, arr [t5] * 5));
            if (arr [idx] == arr [t2] * 2) t2 ++;
            if (arr [idx] == arr [t3] * 3) t3 ++;
            if (arr [idx] == arr [t5] * 5) t5 ++;
        }
        
        return arr [n - 1];
    }
	
}
