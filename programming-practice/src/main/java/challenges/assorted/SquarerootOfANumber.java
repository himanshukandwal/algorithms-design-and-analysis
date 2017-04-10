package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Square-root of a Number
 * 
 * Find the square-root of a given number rounded down to the nearest integer, without using the sqrt function. 
 * For example, square-root of a number between [9, 15] should return 3, and [16, 24] should be 4.
 * 
 * link: http://www.ardendertat.com/2012/01/26/programming-interview-questions-27-squareroot-of-a-number/
 * 
 * @author Hxkandwal
 *
 */
public class SquarerootOfANumber extends AbstractCustomTestRunner {
	
	private static SquarerootOfANumber _instance = new SquarerootOfANumber();
	
	private SquarerootOfANumber() {}

	public static int _findSqrt(int num) {
		if (num == 1) return num;
		int low = 0, high = num;
		
		while (high > low) {
			int mid = (low + high) >>> 1;
			
			if (low == mid) return mid;
			if ((mid * mid) > num) high = mid;
			else if ((mid * mid) < num) low = mid;
			else return mid;
		}
		
		return 0;
	}

	// newton's method
	public static int _mySqrt(int x) {
		long r = x;
        while (r * r > x)  r = (r + x/r)/2;
        return (int) r;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(1, 1);
		_instance.runTest(4, 2);
		_instance.runTest(5, 2);
		_instance.runTest(6, 2);
		_instance.runTest(9, 3);
		_instance.runTest(16, 4);
	}

	public void runTest(final int number, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { number });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
