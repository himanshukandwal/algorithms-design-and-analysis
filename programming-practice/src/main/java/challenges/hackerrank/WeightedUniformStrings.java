package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Weighted Uniform Strings
 *
 * link: https://www.hackerrank.com/challenges/weighted-uniform-string/problem
 */
public class WeightedUniformStrings extends AbstractCustomTestRunner {

    private static WeightedUniformStrings _instance = new WeightedUniformStrings();

    public boolean _canBeMade(String s, int target) {
        for (int idx = 0, start = 0, sum = 0; idx < s.length(); idx ++) {
            if (s.charAt(idx) == s.charAt(start)) sum += (s.charAt(idx) - 'a' + 1);
            else { sum = (s.charAt(idx) - 'a' + 1); start = idx; }
            if (sum == target) return true;
        }
        return false;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest("abccddde", 1, true);
        _instance.runTest("abccddde", 3, true);
        _instance.runTest("abccddde", 12, true);
        _instance.runTest("abccddde", 5, true);
        _instance.runTest("abccddde", 9, false);
        _instance.runTest("abccddde", 10, false);
    }

    public void runTest(final String input, final int target, final boolean expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input, target });

        for (Object answer : answers)
            assertThat((Boolean) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
