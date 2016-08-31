package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 70. Climbing Stairs
 * 
 * You are climbing a stair case. It takes n steps to reach to the top.
 * 
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * 
 * @author Hxkandwal
 *
 */
public class ClimbingStairs extends AbstractCustomTestRunner {

	private static ClimbingStairs _instance = new ClimbingStairs();
	
	private ClimbingStairs() {}

	// method 1 : using fibonacci series.
	public int _climbStairs(int n) {
		return count(n + 1);
	}

	public int count(int n) {
		if (n <= 1)
			return n;

		return count(n - 1) + count(n - 2);
	}
    
	// method 2 : optimized (dynamic program, using memoization)
	public int _climbStairs2(int n) {
		int [] ways = new int [n + 1];
		
		if (n <= 2)
			return n;
		
		ways [0] = 1;	// for n == 1
		ways [1] = 2;	// for n == 2
		for (int way = 2; way < ways.length; way ++)
			ways [way] = ways [way - 1] + ways [way - 2];
		
		return ways [n - 1];
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(1, 1);
		_instance.runTest(2, 2);
		_instance.runTest(3, 3);
		_instance.runTest(4, 5);
		_instance.runTest(44, 1134903170);
	}

	public void runTest(final int n, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
