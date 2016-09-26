package me.hxkandwal.daily.challanges.assorted;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 *
 * Created by Hxkandwal
 */
public class ArrangeCoins extends AbstractCustomTestRunner {

    public static void main(String[] args) {
        System.out.println(arrangeCoin(2));
        System.out.println(arrangeCoin(5));
        System.out.println(arrangeCoin(8));
        System.out.println(arrangeCoin(3));
        System.out.println(arrangeCoin(6));
        
    }

    public static long arrangeCoin(int coin) {
        long step = coin/2 + 2;
        while (step * (step + 1)/2 > coin) {
            step --;
        }

        return step;
    }
}
