package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * You are given a list of size N, initialized with zeroes. You have to perform M operations on the list 
 * and output the maximum of final values of all the N elements in the list. 
 * 
 * For every operation, you are given three integers a, b and k and you have to add value k to all the elements 
 * ranging from index a to b (both inclusive).
 * 
 * Input Format
 * First line will contain two integers N and M separated by a single space. Next M lines will contain three 
 * integers a, b and c separated by a single space. Numbers in list are numbered from 1 to N.
 * 
 * link: https://www.hackerrank.com/contests/w4/challenges/crush
 * 
 * @author Hxkandwal
 *
 */
public class AlgorithmicCrush extends AbstractCustomTestRunner {
	
	private static AlgorithmicCrush _instance = new AlgorithmicCrush();
	
	private AlgorithmicCrush() {}
	
	public static int _crush(int[][] input, int size) {
		int[] array = new int [size];
		int maxValue = 0;
		
		for (int rowIdx = 0; rowIdx < input.length; rowIdx ++) {
			int[] row = input [rowIdx];
			int startIdx = row [0], endIdx = row [1], value = row [2];
			
			for (int idx = startIdx - 1; idx < endIdx; idx ++)
				maxValue = Math.max (maxValue, array [idx] += value);
		}
		
		return maxValue;
	}

    // driver method
    public static void main(String[] args) throws FileNotFoundException {
        _instance.runTest(new int[][] { new int[] { 1, 2, 100 }, new int[] { 2, 5, 100 }, new int[] { 3, 4, 100 }}, 5, 200);
    }

    public void runTest(final int[][] input, final int size, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input, size });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }	

}