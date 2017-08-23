package challenges.assorted;

import java.util.List;

import challenges.AbstractCustomTestRunner;

import static com.google.common.truth.Truth.assertThat;

/**
 * 441. Arranging Coins
 * 
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
 * Given n, find the total number of full staircase rows that can be formed.
 * 
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
 * 
 * @author Hxkandwal
 */
public class ArrangingCoins extends AbstractCustomTestRunner {

    private static ArrangingCoins _instance = new ArrangingCoins();
    
    // method 1 : regular hill climbing method.
    public static long _arrangeCoin(int n) {
        int step = 0;
        while ((step + 1) <= n) n-= ++ step;
        return step;
    }

    // method 2 : faster
    public static long _arrangeCoinQuick(int coin) {
        long step = (long) (Math.sqrt(1 + (4 * 1 * 2 * coin)) - 1)/2;
        return step;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(2, 1);
        _instance.runTest(5, 2);
        _instance.runTest(8, 3);
        _instance.runTest(3, 2);
        _instance.runTest(6, 3);
    }

    public void runTest(final int input, final long expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input });

        for (Object answer : answers)
            assertThat((long) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
