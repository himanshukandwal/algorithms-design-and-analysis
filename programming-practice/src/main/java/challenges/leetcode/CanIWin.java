package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 464. Can I Win
 * 
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10. 
 * The player who first causes the running total to reach or exceed 100 wins.
 * 
 * What if we change the game so that players cannot re-use integers?
 * 
 * For example, two players might take turns drawing from a common pool of numbers of 1..15 without 
 * replacement until they reach a total >= 100.
 * 
 * Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move 
 * can force a win, assuming both players play optimally. You can always assume that maxChoosableInteger will not be 
 * larger than 20 and desiredTotal will not be larger than 300.
 * 
 * Example: 
 * 		Input: 	maxChoosableInteger = 10
 * 				desiredTotal = 11
 * 		Output:	false
 * 		Explanation:
 * 			No matter which integer the first player choose, the first player will lose.
 * 			The first player can choose an integer from 1 up to 10.
 * 			If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * 			The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * 			Same with other integers chosen by the first player, the second player will always win.
 * 
 * @author Hxkandwal
 */
public class CanIWin extends AbstractCustomTestRunner {
	
	private static CanIWin _instance = new CanIWin();
	
	public boolean _canIWin(int maxChoosableInteger, int desiredTotal) {
		if (maxChoosableInteger >= desiredTotal) return true;
        if (maxChoosableInteger * (maxChoosableInteger + 1)/2 < desiredTotal) return false;
        return turn (new HashMap<>(), 0, maxChoosableInteger, desiredTotal);
    }
    
    private boolean turn (Map<Integer, Boolean> map, int state, int n, int total) {
        if (map.containsKey(state)) return map.get (state);
        for (int idx = 0; idx < n; idx ++) {
            if ((state & (1 << idx)) != 0) continue;
            if (total <= idx + 1 || !turn (map, state | 1 << idx, n, total - (idx + 1))) { 
                map.put (state, true); 
                return true;
            }
        }
        map.put (state, false);
        return false;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(10, 11, false);
	}

	public void runTest(final int maxChoosableInteger, final int desiredTotal, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { 10, 11 });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
    
}
