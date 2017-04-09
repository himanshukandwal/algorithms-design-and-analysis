package me.hxkandwal.daily.challanges.codefights;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Total Same Ones
 * 
 * Given a positive integer k, your task is to count all the numbers in the range from 1 to k (inclusive) that have the 
 * same number of set bits in their binary representation as k does.
 * 
 * Example:
 * 	For k = 8, the output should be totalSameOnes(k) = 4.
 * 	
 * There are 4 numbers in the range [1..8] that have the same number of 1s as 8 does:
 * 		8 base 10 = 1000 base 2
 * 		4 base 10 = 100 base 2
 * 		2 base 10 = 10 base 2
 * 		1 base 10 = 1 base 2
 * 
 * For k = 5, the output should be totalSameOnes(k) = 2.
 * 
 * There are 2 numbers in the range [1..5] that have two 1s like 5 does:
 * 		5 base 10 = 101 base 2
 * 		3 base 10 = 11 base 2
 * 
 * @author Hxkandwal
 */
public class TotalSameOnes extends AbstractCustomTestRunner {

	public int totalSameOnes(int k) {
	    int kcount = count (k), ans = 0;
	    for (int idx = 1; idx <= k; idx ++) ans += (count (idx) == kcount ? 1 : 0);
	    return ans;
	}

	private int count (int n) {
	    int count = 0;
	    for (int idx = 31; idx >= 0; idx --) if ((n >> idx & 1) == 1) count ++;
	    return count;
	}
	
}
