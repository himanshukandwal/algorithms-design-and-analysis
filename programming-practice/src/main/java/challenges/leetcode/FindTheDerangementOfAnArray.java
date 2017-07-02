package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 634. Find the Derangement of An Array
 * 
 * In combinatorial mathematics, a derangement is a permutation of the elements of a set, such that no element appears in 
 * its original position. There's originally an array consisting of n integers from 1 to n in ascending order, you need to 
 * find the number of derangement it can generate.
 * 
 * Also, since the answer may be very large, you should return the output mod 10^9 + 7.
 * 
 * Example 1:	Input: 3
 * 				Output: 2
 * 				Explanation: The original array is [1,2,3]. The two derangements are [2,3,1] and [3,1,2].
 * 
 * Note: n is in the range of [1, 10^6].
 * 
 * @author Hxkandwal
 */
public class FindTheDerangementOfAnArray extends AbstractCustomTestRunner {
	 
	// d (n) = (n - 1) [d (n - 1) + d (n - 2)]
	public int findDerangement(int n) {
		long a = 0, b = 1, i = 3, c;
		for (; i <= n + 1; i ++) {
			c = (i - 1) * (a + b) % 1000000007;
			a = b;
			b = c;
		}
		return (int) a;
	}
	
}
