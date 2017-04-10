package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import challenges.AbstractCustomTestRunner;

/**
 * Non-Divisible Subset
 * 
 * Given a set, S, of n distinct integers, print the size of a maximal subset, S', of S  where the sum of 
 * any 2 numbers in S' are not evenly divisible by k.
 * 
 * Sample Input
 * 	4 3
 * 	1 7 2 4
 * 
 * Output: 3 {1, 7, 4}
 * 
 * @author Hxkandwal
 *
 */
public class NonDivisibleSubset extends AbstractCustomTestRunner {

	private static NonDivisibleSubset _instance = new NonDivisibleSubset();
	
	private NonDivisibleSubset() {}
	
	public static int _getUnevenSet(int[] numbers, int k) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int num : numbers) {
			int remainder = num % k;
			
			if (map.containsKey(remainder))
				map.put(remainder, map.get(remainder) + 1);
			else
				map.put(remainder, 1);
		}
		
		int count = 0;
		for (int idx = k - 1; idx > k / 2; idx--) {
			if (map.containsKey(idx) && map.containsKey(k - idx))
				count += (map.get(idx) > map.get(k - idx)) ? map.get(idx) : map.get(k - idx);
			else if (map.containsKey(idx))
				count += map.get(idx);
			else if (map.containsKey(k - idx))
				count += map.get(k - idx);
		}
		
		if (k % 2 == 0 && map.containsKey(k/2) && map.get(k/2) >= 1)
			count ++;
		          
		if (map.containsKey(0) && map.get(0) >= 1)
			count ++; 
		
		return count;
	}
	
	// driver method
	public static void main(String[] args) throws FileNotFoundException {
		_instance.runTest(new int[] { 1, 7, 2, 4 }, 3, 3);
		_instance.runTest(new int[] { 1, 7, 2, 4 }, 2, 2);
		
		testComplex("/src/test/resources/challenges/hackerrank/NonDivisibleSet-Big-1.txt");
		testComplex("/src/test/resources/challenges/hackerrank/NonDivisibleSet-Big-2.txt");
	}
	
	private static void testComplex(String filename) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(System.getProperty("user.dir") + filename));
		
		int[] input = new int[sc.nextInt()];
		int k = sc.nextInt();
		
		for (int idx = 0; idx < input.length; idx ++)
			input [idx] = sc.nextInt();
		
		_instance.runTest(input, k, sc.nextInt());
		
		sc.close();
	}

	public void runTest(final int[] input, final int k, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input, k });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
