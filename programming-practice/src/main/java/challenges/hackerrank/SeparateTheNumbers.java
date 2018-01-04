package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Separate the Numbers
 *
 * link: https://www.hackerrank.com/challenges/separate-the-numbers/problem
 */
public class SeparateTheNumbers extends AbstractCustomTestRunner {

    private static SeparateTheNumbers _instance = new SeparateTheNumbers();

    public boolean _seperateNumbers(String s) {
        char[] arr = s.toCharArray();
        int num = 0;
        for (int idx = 0; idx < arr.length - 1; idx ++) {
            num = 10 * num + (arr [idx] - '0');
            if (find (num + 1, arr, idx + 1)) return true;
        }
        return false;
    }

    private boolean find(int pnum, char[] arr, int index) {
        if (index == arr.length) return true;
        if (arr [index] == '0') return false;
        int num = 0;
        for (int idx = index; idx < arr.length; idx ++) {
            num = 10 * num + (arr [idx] - '0');
            if (num > pnum) return false;
            else if (num == pnum) return find (num + 1, arr, idx + 1);
        }
        return false;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest("1234", true);
        _instance.runTest("91011", true);
        _instance.runTest("99100", true);
        _instance.runTest("101103", false);
        _instance.runTest("010203", false);
        _instance.runTest("13", false);
        _instance.runTest("1", false);
    }

    public void runTest(final String input, final boolean expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input });

        for (Object answer : answers)
            assertThat((Boolean) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
