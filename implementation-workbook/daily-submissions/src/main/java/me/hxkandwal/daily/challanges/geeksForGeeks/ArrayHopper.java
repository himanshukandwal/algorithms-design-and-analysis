package me.hxkandwal.daily.challanges.geeksForGeeks;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Array Hopper
 *
 * Minimum number of jumps to reach end
 *
 * Given an array of integers where each element represents the max number of steps that can be made
 * forward from that element. Write a function to return the minimum number of jumps to reach the end
 * of the array (starting from the first element). If an element is 0, then cannot move through that element.
 *
 * Example :    Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
 *              Output: 3 (1-> 3 -> 8 ->9)
 *
 *              First element is 1, so can only go to 3. Second element is 3, so can make at most 3 steps eg to 5 or 8 or 9.
 *
 * Created by Hxkandwal
 *
 */
public class ArrayHopper extends AbstractCustomTestRunner {

    private static ArrayHopper _instance = new ArrayHopper();

    private ArrayHopper() {}

    // method : dp to solve this in O(n)
    public static int _getMinHops(int[] input) {
        int[] dp = new int [input.length];

        for (int idx = 0; idx < input.length; idx ++) {
            if (dp [idx] == 0 && input [idx] == 0)
                break;

            for (int fillIdx = idx + 1; fillIdx < (idx + 1 + input[idx]) && fillIdx < input.length; fillIdx ++)  // because we have to fill ahead from the idx, not starting from idx onwards.
                if (dp [fillIdx] == 0)
                    dp [fillIdx] = dp [idx] + 1;  // dp building upon previous answer

            if (dp [input.length - 1] != 0)
                break;
        }

        return (dp[input.length - 1] != 0 ? dp[input.length -1] : -1);
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int[] { 1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9 }, 3);
        _instance.runTest(new int[] { 0, 1, 1, 1, 1 }, -1);
        _instance.runTest(new int[]{32, 54, 12, 52, 56, 8, 30, 44, 94, 44, 39, 65, 19, 51, 91, 1, 5, 89,
                                    34, 25, 58, 20, 51, 38, 65, 30, 7, 20, 10, 51, 18, 43, 71, 97, 61, 26,
                                    5, 57, 70, 65, 0, 75, 29, 86, 93, 87, 87, 64, 75, 88, 89, 100, 7, 40,
                                    37, 38, 36, 44, 24, 46, 95, 43, 89, 32, 5, 15, 58, 77, 72, 95, 8, 38,
                                    69, 37, 24, 27, 90, 77, 92, 31, 30, 80, 30, 37},
                         2);
    }

    public void runTest(final int[] input, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
