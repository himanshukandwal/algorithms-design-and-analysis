package challenges.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Jim and the Orders
 *
 * Jim's Burgers has n hungry burger fans waiting in line. Each unique order, i, is placed by a customer at time ti, and the
 * order takes di units of time to process.
 *
 * Given the information for all n orders, can you find and print the order in which all n customers will receive their burgers?
 * If two or more orders are fulfilled at the exact same time t, sort them by ascending order number.
 *
 * link: https://www.hackerrank.com/challenges/jim-and-the-orders
 *
 * Created by Hxkandwal
 */
public class JimAndTheOrders {

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        int [] nums = new int [sc.nextInt ()];
        Integer [] ans = new Integer [nums.length];
        for (int idx = 0; idx < nums.length; idx ++) { nums [idx] = sc.nextInt () + sc.nextInt (); ans [idx] = idx + 1; }
        Arrays.sort (ans, (a, b) -> nums [a - 1] - nums [b - 1] == 0 ? a - b : nums [a - 1] - nums [b - 1]);
        for (int val : ans) System.out.print (val + " ");
    }
}
