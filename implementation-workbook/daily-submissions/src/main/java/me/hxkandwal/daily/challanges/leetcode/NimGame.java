package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 292. Nim Game
 * 
 * You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time 
 * one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. 
 * 
 * You will take the first turn to remove the stones. Both of you are very clever and have optimal strategies for the game. 
 * 
 * Write a function to determine whether you can win the game given the number of stones in the heap.
 * 
 * For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, 
 * the last stone will always be removed by your friend.
 * 
 * @author Hxkandwal
 *
 */
public class NimGame extends AbstractCustomTestRunner {
	
	private static NimGame _instance = new NimGame();
	
	private NimGame() {}

	/**
	 * naive approach : negating as the results are for the other (player B), so if I win in anyone where
	 * player B loses then Its a win for me.
	 * 
	 */
    public static boolean _canWinNim (int n) {
        if (n <= 3)
        	return true;
        
    	return !_canWinNim (n - 1) || !_canWinNim (n - 2) || !_canWinNim (n - 3);
    }
    
    /**
     * According to the hint, we know that if n = 4, no matter how many stones I remove, I lose. 
     * If n = 5, I can remove one stone and there are 4 stones for another player. Thus, I win. 
     * Similarly, if n = 6 or 7, I can remove 2 or 3 stones and i win finally.
     * 
     * If n = 8, no matter how many stones I remove, there are 7 or 6 or 5 stones for another player, 
     * s/he can remove stones as we said before and then wins.
     * If n = 9 or 10 or 11, I can leave 8 stones to another player, then I win.
     * If n = 12, I can leave 9, 10 or 11 stones to another player. Then, s/he can leave 8 stones to me, then I lose.
     * 
     * The rule is: if (n % 4 == 0) then I lose.
     *
     */
    public boolean _canWinNimFaster(int n) {
        if (n <= 0) {return false;}
        return n % 4 != 0;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(4, false);
		_instance.runTest(5, true);
		_instance.runTest(3, true);
		_instance.runTest(42, true);
		_instance.runTest(40, false);
		_instance.runTest(8, false);
		_instance.runTest(12, false);
		_instance.runTest(11, true);
	}
	
	public void runTest(final int n, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });
		
		for (Object answer : answers) 
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
