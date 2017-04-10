package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Luck Balance
 * 
 * Print a single integer denoting the maximum amount of luck Lena can have after all the contests.
 * 
 * Sample Input :
 * 
 * 		6 3
 * 		5 1
 * 		2 1
 * 		1 1
 * 		8 1
 * 		10 0
 * 		5 0
 * 
 * Sample Output : 29
 * 
 * There are N = 6 contests. Of these contests, 4 are important (so she cannot lose any more than K = 3 of them). 
 * Lena maximizes her luck if she wins the 3rd important contest (where Li = 1) and loses all of the other five contests for a 
 * total luck balance of 5 + 2 + 8 + 10 + 5 - 1 = 29.
 * 
 * @author Hxkandwal
 */
public class LuckBalance extends AbstractCustomTestRunner {
	
	private static LuckBalance _instance = new LuckBalance();
	
	public int _totalLuck(Integer [][] matrix, int k) {
		Arrays.sort(matrix, new Comparator<Integer []>() {
			@Override
			public int compare(Integer [] o1, Integer [] o2) {
				return (o1 [1].compareTo(o2 [1]) == 0) ? o2 [0].compareTo(o1 [0]) : o1 [1].compareTo(o2 [1]);
			}
		});
		
		int sum = 0;
		for (Integer [] matrixInput : matrix) {
			if (matrixInput [1] == 0) sum += matrixInput [0];
			else { if (k == 0) sum -= matrixInput [0]; else { sum += matrixInput [0]; k --; } }
		}
		
		return sum;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new Integer [][] { new Integer [] { 5, 1 }, new Integer [] { 2, 1 }, 
											 new Integer [] { 1, 1 }, new Integer [] { 8, 1 }, 
											 new Integer [] { 10, 0 }, new Integer [] { 5, 0 } }, 3, 29);
	}

	public void runTest(final Integer [][] matrix, final int k, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { matrix, k });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}