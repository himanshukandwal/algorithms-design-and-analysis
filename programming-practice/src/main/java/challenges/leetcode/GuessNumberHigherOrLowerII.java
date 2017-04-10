package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 375. Guess Number Higher or Lower II
 * 
 * We are playing the Guess Game. The game is as follows:
 * 	I pick a number from 1 to n. You have to guess which number I picked.
 * 	Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
 * 	However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.
 * 
 * Example:
 * 	n = 10, I pick 8.
 * 
 * First round:  You guess 5, I tell you that it's higher. You pay $5.
 * Second round: You guess 7, I tell you that it's higher. You pay $7.
 * Third round:  You guess 9, I tell you that it's lower. You pay $9.
 * 
 * Game over. 8 is the number I picked.
 * You end up paying $5 + $7 + $9 = $21.
 * 
 * @author Hxkandwal
 */
public class GuessNumberHigherOrLowerII extends AbstractCustomTestRunner {
	
	private static GuessNumberHigherOrLowerII _instance = new GuessNumberHigherOrLowerII();

	public int _getMoneyAmount(int n) {
		int [][] dp = new int [n + 1][n + 1];
        return minimax (dp, 1, n);
    }
    
    private int minimax (int [][] dp, int start, int end) {
        if (start >= end) return 0;
        if (dp [start][end] != 0) return dp [start][end];
        int res = Integer.MAX_VALUE;
        for (int k = start; k <= end; k ++) {
            int local = k + Math.max (minimax (dp, start, k - 1), minimax (dp, k + 1, end));
            res = Math.min (res, local);
        }   
        return dp [start][end] = res;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(10, 16);
	}

	public void runTest(final int num, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { num });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
    
}
