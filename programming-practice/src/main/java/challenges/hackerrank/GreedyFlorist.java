package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import static com.google.common.truth.Truth.assertThat;

/**
 * Greedy Florist
 *
 * A group of k friends want to buy n flowers where each flower i has some base cost, ci. The florist wants to maximize his number of
 * new customers, so he increases the price of flowers purchased by repeat customers; more precisely, if a customer has already purchased
 * x flowers, the price, p, for flower i is pi = (x + 1) * ci.
 *
 * Given n, k, and the base cost for each flower, find and print the minimum cost for the group to purchase n flowers.
 *
 * Note: Flowers can be purchased in any order.
 *
 * Created by Hxkandwal
 */
public class GreedyFlorist extends AbstractCustomTestRunner {

    private static GreedyFlorist _instance = new GreedyFlorist();

    public int _getMinimumCost(int n, int k, int[] c){
        Arrays.sort (c);
        int minCost = 0, kIdx = 0;
        int [] nflowers = new int [k];
        for (int idx = c.length - 1; idx >= 0 && n > 0; idx --, kIdx ++) {
            if (kIdx == k) kIdx = 0;
            nflowers [kIdx] ++;
            minCost += nflowers [kIdx] * c [idx];
        }
        return minCost;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(3, 2, new int[] { 2, 5, 6 }, 15);
    }

    public void runTest(final int n, int k, int[] c, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { n, k, c });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
