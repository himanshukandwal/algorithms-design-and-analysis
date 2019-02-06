package challenges.interviewbit;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Seats
 *
 * There is a row of seats. Assume that it contains N seats adjacent to each other. There is a group of people who are already seated in that row randomly. i.e. some are
 * sitting together & some are scattered.
 *
 * An occupied seat is marked with a character 'x' and an unoccupied seat is marked with a dot ('.')
 *
 * Now your target is to make the whole group sit together i.e. next to each other, without having any vacant seat between them in such a way that the total number of hops
 * or jumps to move them should be minimum.
 *
 * Return minimum value % MOD where MOD = 10000003
 *
 * Example
 *      Here is the row having 15 seats represented by the String (0, 1, 2, 3, ......... , 14) -
 *               . . . . x . . x x . . . x . .
 *
 *      Now to make them sit together one of approaches is -
 *               . . . . . . x x x x . . . . .
 *
 *      Following are the steps to achieve this -
 *          1 - Move the person sitting at 4th index to 6th index -
 *              Number of jumps by him =   (6 - 4) = 2
 *          2 - Bring the person sitting at 12th index to 9th index -
 *              Number of jumps by him = (12 - 9) = 3
 *
 *      So now the total number of jumps made = ( 2 + 3 ) % MOD = 5 which is the minimum possible jumps to make them seat together.
 *      There are also other ways to make them sit together but the number of jumps will exceed 5 and that will not be minimum.
 *
 *      For example bring them all towards the starting of the row i.e. start placing them from index 0.
 *      In that case the total number of jumps will be ( 4 + 6 + 6 + 9 ) % MOD = 25 which is very costly and not an optimized way to do this movement
 *
 * link: https://www.interviewbit.com/problems/seats/
 *
 * @author Hxkandwal
 */
public class Seats extends AbstractCustomTestRunner {

    private static Seats _instance = new Seats();

    private static final int MOD = 10000003;

    public int _seats(String A) {
        int ans = 0, numRight = 0, numLeft = 0;
        for (int idx = 0; idx < A.length(); idx ++)
            if (A.charAt (idx) == 'x') numRight ++;

        for (int idx = 0; idx < A.length(); idx ++) {
            if (numRight == 0) break;
            if (A.charAt(idx) == 'x') {
                numLeft ++;
                numRight --;
            } else
                ans = (ans + Math.min (numRight, numLeft)) % MOD;  // move min number of elements all per occurence of '.'
        }

        return ans % MOD;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest("....x..xx...x..", 5);
        _instance.runTest(".....", 0);
    }

    public void runTest(final String a, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { a });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
