package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 412. Fizz Buzz
 * 
 * Write a program that outputs the string representation of numbers from 1 to n.
 * 
 * But for multiples of three it should output “Fizz” instead of the number and for the 
 * multiples of five output “Buzz”. 
 * 
 * For numbers which are multiples of both three and five output “FizzBuzz”.
 * 
 * Example :
 * 	n = 15,
 * 
 * 	Return:
 * 		[ "1",
 * 		  "2",
 * 		  "Fizz",
 * 		  "4",
 * 		  "Buzz",
 * 		  "Fizz",
 * 		  "7",
 * 		  "8",
 * 		  "Fizz",
 * 		  "Buzz",
 * 		  "11",
 * 		  "Fizz",
 * 		  "13",
 * 		  "14",
 * 		  "FizzBuzz"
 * 		]
 * 
 * @author Hxkandwal
 *
 */
public class FizzBuzz extends AbstractCustomTestRunner {
	
	private static FizzBuzz _instance = new FizzBuzz();
	
	private FizzBuzz() {}
	
	public static int _fizzBuzz(int n) {
		for (int idx = 1; idx <= n; idx++) {
			if (idx % 3 == 0 && idx % 5 == 0)
				System.out.println("FizzBuzz");
			else if (idx % 3 == 0)
				System.out.println("Fizz");
			else if (idx % 5 == 0)
				System.out.println("Buzz");
			else 
				System.out.println(idx);
		}
		
		return 0;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(15, 0);
		_instance.runTest(1, 0);
	}
	
	public void runTest(final int n, int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
