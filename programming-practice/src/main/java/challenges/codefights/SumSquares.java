package challenges.codefights;

import challenges.AbstractCustomTestRunner;

/**
 * Sum Squares
 * 
 * Given a non-negative integer k, to count the number of way to represent it as a sum of no more than 2 squares 
 * of positive integers. In other words, your task is to calculate the number of representations of k in the formats 
 * 
 * k = a2 + b2 and k = c2.
 * 
 * Example:
 * 	a)	For k = 1, the output should be sumSquares(k) = 1.
 * 		There's only one way to represent 1 as described above: 1 = 12.
 * 
 *	b)	For k = 25, the output should be sumSquares(k) = 3.
 *		Here are all the possible ways to represent 25 as a sum of no more than 2 squares:
 *
 *			25 = 5^2;
 *			25 = 3^2 + 4^2;
 *			25 = 4^2 + 3^2.
 * 
 * @author Hxkandwal
 */
public class SumSquares extends AbstractCustomTestRunner {

	long sumSquares(long k) {
		long z = 0, t, i = 0;
		for (; i * i < k;) {
			t = (int) Math.sqrt(k - i * i);
			if (t * t + i * i++ == k) z++;
		}
		return z;
	}

}
