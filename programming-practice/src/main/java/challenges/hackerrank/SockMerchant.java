package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * John's clothing store has a pile of n loose socks where each sock i is labeled with an integer, ci, 
 * denoting its color. He wants to sell as many socks as possible, but his customers will only buy them 
 * in matching pairs. Two socks, i and j, are a single matching pair if ci = cj.
 * 
 * Example:
 * a.		Input:	10 20 20 10 10 30 50 10 20
 * 			Output:	3
 * 
 * link: https://www.hackerrank.com/challenges/sock-merchant
 * 
 * @author Hxkandwal
 *
 */
public class SockMerchant extends AbstractCustomTestRunner {

	private static SockMerchant _instance = new SockMerchant();
	
	private SockMerchant() {}
	
	public static int _getPairedSocks(int[] array) {
		Map<Integer, Integer> counter = new HashMap<>();
		for (int idx = 0; idx < array.length; idx++) {
			if (counter.containsKey(array[idx]))
				counter.put(array [idx], counter.get(array [idx]) + 1);
			else
				counter.put(array [idx], 1);
		}

		int count = 0;
		for (Map.Entry<Integer, Integer> entry : counter.entrySet())
			count += entry.getValue() / 2;

		return count;
	}
	
	// good approach with sets.
	public static int _getPairedSocks2(int[] array) {
		Set<Integer> colors = new HashSet<>();
		int pairs = 0;

		for (int idx = 0; idx < array.length; idx++) {
			if (!colors.contains(array [idx])) {
				colors.add(array [idx]);
			} else {
				pairs++;
				colors.remove(array [idx]);
			}
		}

		return pairs;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 10, 20, 20, 10, 10, 30, 50, 10, 20 }, 3);
		_instance.runTest(new int[] { 10, 20, 30 }, 0);
		_instance.runTest(new int[] { 10, 20, 20, 30 }, 1);
	}

	public void runTest(final int[] array, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array });

		for (Object answer : answers)
			assertThat((int) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
