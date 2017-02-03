package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Write a program to print all palindromes present in a string.
 * 
 * @author Hxkandwal
 */
public class AllPalindromesPrinter extends AbstractCustomTestRunner {
	
	private static AllPalindromesPrinter _instance = new AllPalindromesPrinter();
	
	private AllPalindromesPrinter() {}
	
	public static int _printPalindromes (String input) {
		if (input == null || input.length() == 0)
			return 0;
		
		int[][] matrix = new int [input.length()][input.length()];
		int maxLength = 0;
		
		for (int row = 0; row < matrix.length; row ++) {
			for (int col = 0; col <= row; col ++) {
				if (input.charAt(row) == input.charAt(col)) {
					if (row == col)
						matrix [row][col] = 1;
					else {
						if (row > col || matrix[row - 1][col + 1] > 0) {
							matrix [row][col] = matrix[row - 1][col + 1] + 2;
							print (input, col, row);
						}
					}	
					
					maxLength = Math.max(maxLength, matrix [row][col]);
				}
			}
		}
		
		return maxLength;
	}
	
	public static void print (String input, int startIndex, int endIndex) {
		System.out.println(input.substring(startIndex, endIndex + 1));
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("helle", 4);
		_instance.runTest("helele", 5);
		_instance.runTest("heleleh", 7);
	}

	public void runTest(final String input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
