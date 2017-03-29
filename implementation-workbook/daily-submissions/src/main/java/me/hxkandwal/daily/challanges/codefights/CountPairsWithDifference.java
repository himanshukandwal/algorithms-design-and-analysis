package me.hxkandwal.daily.challanges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Given an array a of positive integers, find the number of pairs of integers for which the difference between the two 
 * numbers is equal to a given number k. 
 * 
 * Since the number of pairs could be quite large, take it modulo 109 + 7 for your output.
 * 
 * Example
 * 		For k = 3 and a = [1, 6, 8, 2, 4, 9, 12], the output should be countPairsWithDifference(k, a) = 3.
 * 
 * There are three pairs of integers whose difference is equal to 3: (1, 4), (6, 9) and (9, 12).
 * 
 * link : https://codefights.com/interview/embtGKRbwNJaCEpcf/companies/gDDsAwPekpst2TjgW
 * 
 * @author Hxkandwal
 */
public class CountPairsWithDifference extends AbstractCustomTestRunner {

	private static CountPairsWithDifference _instance = new CountPairsWithDifference();
	
	public int _countPairsWithDifference(int k, int[] a) {
	    int count = 0, mod = (int) (1e9 + 7);
	    Map <Integer, Integer> map = new HashMap<>();
	    for (int ai : a) {
	        if (map.containsKey(k + ai))
	            count = (count + map.get (k + ai)) % mod;
	        if (map.containsKey(ai - k))
	            count = (count + map.get (ai - k)) % mod;
	        map.put (ai, map.getOrDefault (ai, 0) + 1);
	    }
	    return count;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 6, 8, 2, 4, 9, 12 }, 3, 3);
		_instance.runTest(new int[] { 754, 399, 932, 60 }, 51, 0);
		_instance.runTest(new int[] { 1, 5, 3, 4, 2 }, 2, 3);
		_instance.runTest(new int[] { 777, 915, 793, 335, 386, 492, 649, 421, 362, 27, 690, 59, 763, 926, 540, 426, 172, 736, 211,
									  368, 567, 429, 782, 530, 862, 123, 67, 135, 929, 802, 22, 58, 69, 167, 393, 456, 11, 42, 229,
									  373, 421, 919, 784, 537, 198, 324, 315, 370, 413, 526, 91, 980, 956, 873, 862, 170, 996, 281,
									  305, 925, 84, 327, 336, 505, 846, 729, 313, 857, 124, 895, 582, 545, 814, 367, 434, 364, 43,
									  750, 87, 808, 276, 178, 788, 584 }, 86, 8);
	}

	public void runTest(final int[] a, final int k, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { k, a });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}