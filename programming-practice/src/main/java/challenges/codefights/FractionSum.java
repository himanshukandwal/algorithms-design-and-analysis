package challenges.codefights;

import challenges.AbstractCustomTestRunner;

/**
 * FractionSum
 *
 * Implement a function that can sum two reduced fractions and produce a new one.
 *
 * Example:
 *      For a = [3, 5] and b = [7, 5], the output should be fractionSum(a, b) = [2, 1].
 *      3 / 5 + 7 / 5 = 10 / 5 = 2 / 1, so the answer is [2, 1].
 *
 * link: https://app.codesignal.com/challenge/8gsShDpKnvi4yECNt
 *
 * @author Hxkandwal
 */
public class FractionSum extends AbstractCustomTestRunner {

    public int[] _fractionSum(int[] a, int[] b) {
        int n = a [0] * b[1] + b [0] * a [1], d = a [1] * b [1];
        int gcd = gcd (n, d);
        return new int [] { n / gcd, d / gcd };
    }

    private int gcd (int a, int b) {
        if (b == 0) return a;
        return gcd (b, a % b);
    }

}
