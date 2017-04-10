package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 477. Total Hamming Distance
 * 
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Now your job is to find the total Hamming distance between all pairs of the given numbers.
 * 
 * Example:
 * 		Input: 4, 14, 2
 * 		Output: 6
 * 		Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
 * 					 showing the four bits relevant in this case). So the answer will be:
 * 					 HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 * 
 * Note:
 * 	Elements of the given array are in the range of 0 to 10^9
 * 	Length of the array will not exceed 10^4.
 *  
 * @author Hxkandwal
 */
public class TotalHammingDistance extends AbstractCustomTestRunner {
	
	private static TotalHammingDistance _instance = new TotalHammingDistance();

	public int totalHammingDistance(int[] nums) {
        int total = 0;
        int [][] store = new int [32][2];
        for (int num : nums) {
            for (int idx = 31; idx >= 0; idx --) {
                int bit = (num >> idx & 1);
                store [idx][bit] ++;
                total += store [idx][bit ^ 1];
            }
        }
        return total;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 4, 14, 2 } , 6);
	}

	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}