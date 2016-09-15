package me.hxkandwal.daily.challanges.hackerrank;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

import java.util.List;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;

/**
 * Anna and Brian order n items at a restaurant, but Anna declines to eat any of the kth item due to an allergy.
 *
 * When the check comes, they decide to split the cost of all the items they shared; however, Brian may have forgotten
 * that they didn't split the kth item and accidentally charged Anna for it.
 *
 * You are given n, k, the cost of each of the n items, and the total amount of money that Brian charged Anna for her portion of the bill.
 * If the bill is fairly split, print Bon Appetit; otherwise, print the amount of money that Brian must refund to Anna.
 *
 * Example :
 *      4 1
 *      3 10 2 9
 *      12
 *
 * Output : 5
 *
 * Example :
 *      4 1
 *      3 10 2 9
 *      7
 *
 * Output : Bon Appetit
 *
 * Created by Hxkandwal
 *
 */
public class BonAppetit extends AbstractCustomTestRunner {

    private static BonAppetit _instance = new BonAppetit();

    private BonAppetit() {}

    public static int _computeCorrectBill(int[] items, int k, int share) {
        int total = 0;
        for (int idx = 0; idx < items.length; idx ++)
            if (idx != k)
                total += items [idx];

        if (total / 2 == share)
            return 0;
        else
            return share - (total/2);
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int[] {3, 10, 2, 9}, 1, 12, 5);
        _instance.runTest(new int[] {3, 10, 2, 9}, 1, 7, 0);
    }

    public void runTest(final int[] input, final int k, final int share, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input, k, share });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}