package challenges.hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import challenges.AbstractCustomTestRunner;

import static com.google.common.truth.Truth.assertThat;

/**
 * Maximum Subarray Sum
 *
 * We define the following:
 *
 * a) A subarray of an n-element array, A, is a contiguous subset of A's elements in the inclusive range from some
 *    index i to some index j where 0 <= i <= j <= n.
 * b) The sum of an array is the sum of its elements.
 *
 * Given an n-element array of integers, A, and an integer, m, determine the maximum value of the sum of any of its
 * subarrays modulo m.
 *
 * This means that you must find the sum of each subarray modulo m, then print the maximum result of this modulo operation
 * for any of the n(n+1)/2 possible subarrays.
 *
 * Example :
 *          5 7
 *          3 3 9 9 5
 * Output : 6
 *          The subarrays of array A = [3, 3, 9, 9, 5] and their respective sums modulo m = 7 are ranked in order of length
 *          and sum in the following list:
 *              [3, 3] = sum % 7 = 6 % 7 = 6 (max)
 *
 * Created by Hxkandwal
 *
 */
public class MaximumSubarraySum extends AbstractCustomTestRunner {

    private static MaximumSubarraySum _instance = new MaximumSubarraySum();

    private MaximumSubarraySum() {}

    // method : remove from back add from front. (eat everything)
    public static long _maxModulo(long[] input, long m) {
        long maxModulo, runningModulo;

        maxModulo = runningModulo = (input [0] % m);
        int start = 0;
        for (int idx = 1; idx < input.length; idx ++) {
            long currentModulo = (input [idx] % m);

            while ((runningModulo + currentModulo) >= m)
                runningModulo -= (input[start ++] % m);

            runningModulo += currentModulo;
            maxModulo = Math.max(maxModulo, runningModulo);
        }

        return  maxModulo;
    }

    // driver method
    public static void main(String[] args) throws FileNotFoundException {
//        _instance.runTest(new long[] { 3, 3, 9, 9, 5 }, 7, 6);

        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarraySum-Big-1.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarraySum-Big-2.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarraySum-Big-3.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarraySum-Big-4.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarraySum-Big-5.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarraySum-Big-6.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarraySum-Big-7.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarraySum-Big-8.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarraySum-Big-9.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarraySum-Big-10.txt");
    }

    private static void testComplex(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(System.getProperty("user.dir") + filename));

        long[] input = new long[sc.nextInt()];
        long m = sc.nextLong();

        for (int idx = 0; idx < input.length; idx ++)
            input [idx] = sc.nextInt();

        _instance.runTest(input, m, sc.nextLong());

        sc.close();
    }

    public void runTest(final long[] input, final long m, final long expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input, m });

        for (Object answer : answers)
            assertThat((Long) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
