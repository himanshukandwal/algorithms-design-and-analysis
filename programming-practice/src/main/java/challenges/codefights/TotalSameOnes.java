package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

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
	
	private static TotalSameOnes _instance = new TotalSameOnes();

	public int _totalSameOnes(int k) {
		int n = 0, c = 0;
		while (n++ < k)
			if (Long.bitCount(n) == Long.bitCount(k))
				c++;

		return c;
	}
	
    // driver method
    public static void main(String[] args) {
    	_instance.runTest(8, 4);
    }

    public void runTest(final int k, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { k });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }	
}
