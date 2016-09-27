package me.hxkandwal.daily.challanges.assorted;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 *
 * Created by Hxkandwal
 */
public class ArrangeCoins extends AbstractCustomTestRunner {

    private static ArrangeCoins _instance = new ArrangeCoins();

    private ArrangeCoins() {}

    // method 1 : regular hill climbing method.
    public static long _arrangeCoin(int coin) {
        long step = 0;
        while (coin >= (step + 1)) {
            step ++;
            coin -= step;
        }

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
