package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Sum of pairwise Hamming Distance
 * 
 * Hamming distance between two non-negative integers is defined as the number of positions at which the corresponding 
 * bits are different.
 * 
 * For example,
 * 		HammingDistance(2, 7) = 2, as only the first and the third bit differs in the binary representation of 2 (010) and 
 * 								   7 (111).
 * 
 * Given an array of N non-negative integers, find the sum of hamming distances of all pairs of integers in the array.
 * Return the answer modulo 1000000007.
 * 
 * Example:
 * 	Let f(x, y) be the hamming distance defined above.
 * 	A = [2, 4, 6]
 * 
 * 	We return,
 * 		f(2, 2) + f(2, 4) + f(2, 6) + 
 * 		f(4, 2) + f(4, 4) + f(4, 6) +
 * 		f(6, 2) + f(6, 4) + f(6, 6) =
 * 
 *  	0 + 2 + 1
 *  	2 + 0 + 1
 *  	1 + 1 + 0 = 8
 * 
 * @author Hxkandwal
 */
public class SumOfPairwiseHammingDistance extends AbstractCustomTestRunner {
	
	private static SumOfPairwiseHammingDistance _instance = new SumOfPairwiseHammingDistance();
	
	/**
	 * you take a bit position, count the number of 0's and the number of 1's, multiply those to get the contribution of this bit position. 
	 * Sum those for all bit positions. It's even simpler than the linked problem, because the weight of the contribution of every bit is 1 
	 * in this problem.
	 */
	public int _hammingDistance(final List<Integer> A) {
		int len = A.size();
        int [] ones = new int [32];
        for (int i = 0; i < len; i ++)
            for (int j = 0; j < 32; j ++)
                ones[j] += (A.get (i) >> j) & 1;
        
        long sum = 0;
        for (int count : ones) {
            sum += 2l * count * (len - count);
            sum %= 1000000007;
        }
        return (int)(sum % 1000000007);
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(Arrays.asList(2, 4, 6), 8);
	}

	public void runTest(final List<Integer> A, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { A });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
