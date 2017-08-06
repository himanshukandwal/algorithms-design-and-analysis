package challenges.assorted;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.Random;

/**
 * Randomly Reorder Array in O(N)
 *
 * link: https://www.youtube.com/watch?v=CoI4S7z1E1Y
 *
 * Created by Hxkandwal
 */
public class RandomlyReorderArray extends AbstractCustomTestRunner {

    private static RandomlyReorderArray _instance = new RandomlyReorderArray();

    private Random random = new Random();

    public int [] reorder (int [] nums) {
        for (int idx = 1; idx < nums.length; idx ++) {
            int swapIdx = floor(rand() * idx);
            int val = nums [idx];
            nums [idx] = nums [swapIdx];
            nums [swapIdx] = val;
        }
        return  nums;
    }

    private double rand () {
        return random.nextDouble();
    }

    private int floor(double value) {
        return (int) Math.floor(value);
    }

    // driver method
    public static void main(String[] args) {
        System.out.println(Arrays.toString(_instance.reorder(new int [] { 1, 0, 3, 9, 2 })));
    }

}
