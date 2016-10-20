package me.hxkandwal.daily.challanges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * You and your friends love to play bowling at the end of a long week. You play by your very own rules. 
 * Each team has an even number of rolls to make, two rolls in a row. A pair of consecutive rolls is called a frame.
 * 
 * Each frame 10 pins are placed on a lane. If a player manages to knock down all 10 pins, he gets 30 points, and can 
 * try to knock down another 10 pins in the next roll. If he knocks them down again, he gets another 30 points. 
 * Otherwise the number of points he gains is equal to the number of pins he knocked down. If a player doesn't knock down all 
 * the pins in the first roll of a frame, by the end of the frame he gets the number of points equal to the number of pins knocked 
 * down in that frame.
 * 
 * You are given the results of the rolls your team played. Return the total number of points you received this game.
 * 
 * Example
 * 
 * For rolls = [10, 10, 10, 4, 9, 1, 0, 10, 3, 3] the output should be bowlingScore(rolls) = 120.
 * 
 * Here's the number of points you got in each frame:
 * 
 * 30 + 30 = 60
 * 30 + 4 = 34
 * 9 + 1 = 10
 * 0 + 10 = 10
 * 3 + 3 = 6.
 * 
 * Thus, the answer is 60 + 34 + 10 + 10 + 6 = 120.
 * 
 * @author Hxkandwal
 *
 */
public class BowlingScore extends AbstractCustomTestRunner {
	
	private static BowlingScore _instance = new BowlingScore();
	
	private BowlingScore() {}
	
	public static int _bowlingScore(int[] rolls) {
		int sum = 0;
		
		boolean previousSuccess = false;
		for (int idx = 0; idx  < rolls.length; idx ++) {
			if (idx % 2 == 0) {
				previousSuccess = false;
				if (rolls[idx] == 10) {
					previousSuccess = true;
					sum += 30;
				} else 
					sum += rolls[idx];
				
			} else {
				sum += (rolls[idx] == 10 && previousSuccess) ? 30 : rolls[idx];
			}
		}
		
		return sum;
	}
	
	// a faster method (check FunTricks.java for more)
	public static int _fasterBowlingScore(int[] rolls) {
		int sum = 0, idx = 0;
		
	    for (int e: rolls) {
	    	sum += (e + rolls[idx ++ & -2]) > 19 ? 30 : e;
	    }
	    	
	    return sum;
	}
	
	
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] {10, 10, 10, 4, 9, 1, 0, 10, 3, 3}, 120);
		_instance.runTest(new int[] {10, 0, 10, 4, 9, 1, 0, 10, 3, 3}, 90);
	}

	public void runTest(final int[] rolls, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { rolls });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
