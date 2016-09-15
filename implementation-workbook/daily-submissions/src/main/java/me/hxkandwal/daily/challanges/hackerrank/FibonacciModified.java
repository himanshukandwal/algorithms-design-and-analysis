package me.hxkandwal.daily.challanges.hackerrank;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

import java.math.BigInteger;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Fibonacci Modified
 *
 * We define a modified Fibonacci sequence using the following definition:
 *
 * t(i+2) = t(i) + t(i+1)^2
 *
 * Given three integers, t1, t2, and n, compute and print term tn of a modified Fibonacci sequence.
 *
 * Example :
 *      0 1 5
 *
 * Output : 5
 *
 * Created by Hxkandwal
 */
public class FibonacciModified extends AbstractCustomTestRunner {

    private static FibonacciModified _instance = new FibonacciModified();

    private FibonacciModified() {}

    // method : using bottom up approach.
    public static String _fibonacci(int t1, int t2, int n) {
        BigInteger[] mem = new BigInteger[n];
        mem [0] = BigInteger.valueOf(t1);
        mem [1] = BigInteger.valueOf(t2);

        for (int idx = 2; idx < n; idx ++)
            mem [idx] = mem [idx - 2].add(mem [idx - 1].multiply(mem [idx - 1]));

        return mem[n - 1].toString();
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(0, 1, 5, "5");
    }

    public void runTest(final int t1, final int t2, final int n, final String expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { t1, t2, n });

        for (Object answer : answers)
            assertThat((String) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
