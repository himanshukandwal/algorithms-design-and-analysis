package challenges.hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import challenges.AbstractCustomTestRunner;

import static com.google.common.truth.Truth.assertThat;

/**
 * The Maximum Subarray
 *
 * Given an array A of n elements, find the maximum possible sum of a :
 * a) Contiguous subarray
 * b) Non-contiguous (not necessarily contiguous) subarray.
 *
 * Empty subarrays/subsequences should not be considered.
 *
 * Example :
 * a)   4
 *      1 2 3 4
 *
 * Output : 10 10
 *          The max sum for both contiguous and non-contiguous elements is the sum of ALL the elements (as they are all positive).
 *
 * b)   6
 *      2 -1 2 3 4 -5
 *
 * Output : 10 11
 *          [2 -1 2 3 4] --> This forms the contiguous sub-array with the maximum sum.
 *          For the max sum of a not-necessarily-contiguous group of elements, simply add all the positive elements.
 *
 * Created by Hxkandwal
 */
public class MaximumSubarray extends AbstractCustomTestRunner {

    private static MaximumSubarray _instance = new MaximumSubarray();

    private MaximumSubarray() {}

    // method : max peak keeping algorithm, with assumption that we need something positive from previous summation to keep it going.
    //          If its equal to or below 0 (negative) we can simply start a fresh hill finding. (repeatedly)
    public static long[] _getMaximum(int[] input) {
        if (input == null || input.length == 0)
            return new long[] { 0, 0 };

        long maximumNonContiguousSum = Long.MIN_VALUE;
        for (int idx = 0; idx < input.length; idx ++)
            if (input [idx] > 0)
                maximumNonContiguousSum = (maximumNonContiguousSum < 0) ? input [idx] : maximumNonContiguousSum + input[idx];
            else if (maximumNonContiguousSum < 0)
                maximumNonContiguousSum = Math.max(maximumNonContiguousSum, input[idx]);

        long maxSum, runningSum;
        maxSum = runningSum = input[0];

        for (int index = 1; index < input.length; index ++) {
            int currentValue = input [index];

            if (runningSum >= 0)
                runningSum += currentValue;
            else
                runningSum = currentValue;

            if (maxSum <= runningSum)
                maxSum = runningSum;
        }

        return new long[] { maxSum, maximumNonContiguousSum };
    }

    // driver method
    public static void main(String[] args) throws FileNotFoundException {
        _instance.runTest(new int[] { }, new long[] { 0, 0 });
        _instance.runTest(new int[] { 1, 2, 3, 4 }, new long[] { 10, 10 });
        _instance.runTest(new int[] { 2, -1, 2, 3, 4, -5 }, new long[] { 10, 11 });
        _instance.runTest(new int[] { 1 }, new long[] { 1, 1 });
        _instance.runTest(new int[] { -1, -2, -3, -4, -5, -6 }, new long[] { -1, -1 });
        _instance.runTest(new int[] { 1, -2 }, new long[] { 1, 1 });
        _instance.runTest(new int[] { 1, 2, 3 }, new long[] { 6, 6 });
        _instance.runTest(new int[] { -10 }, new long[] { -10, -10 });
        _instance.runTest(new int[] {1, -1, -1, -1, -1, 5}, new long[]{5, 6});

        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-1.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-2.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-3.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-4.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-5.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-6.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-7.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-8.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-9.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-10.txt");
    }

    private static void testComplex(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(System.getProperty("user.dir") + filename));

        int[] input = new int[sc.nextInt()];
        for (int idx = 0; idx < input.length; idx ++)
            input [idx] = sc.nextInt();

        _instance.runTest(input, new long[] { sc.nextLong(), sc.nextLong() });

        sc.close();
    }

    public void runTest(final int[] input, final long[] expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input });

        for (Object answer : answers)
            assertThat((long[]) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
