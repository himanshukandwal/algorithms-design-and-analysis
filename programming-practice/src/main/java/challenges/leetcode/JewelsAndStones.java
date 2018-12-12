package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 771. Jewels and Stones
 *
 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.
 * Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
 *
 * The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a"
 * is considered a different type of stone from "A".
 *
 * Example 1:
 *      Input: J = "aA", S = "aAAbbbb"
 *      Output: 3
 *
 * Example 2:
 *      Input: J = "z", S = "ZZ"
 *      Output: 0
 *
 * Note:
 *   S and J will consist of letters and have length at most 50.
 *   The characters in J are distinct.
 *
 * @author Hxkandwal
 */
public class JewelsAndStones extends AbstractCustomTestRunner {

    private static JewelsAndStones _instance = new JewelsAndStones();

    public int _numJewelsInStones(String J, String S) {
        char[] s = S.toCharArray();
        Arrays.sort(s);
        int ans = 0;
        for (char j : J.toCharArray()) {
            int l = getBegin(s, j, 0, s.length - 1);
            if (l < 0) continue;
            int r = getEnd(s, j, l, s.length - 1);
            ans += (r - l + 1);
        }
        return ans;
    }

    private int getBegin(char[] s, char c, int l, int r) {
        while (l < r) {
            int m = l + (r - l)/2;
            if (s [m] >= c) r = m;
            else l = m + 1;
        }
        return (s [r] == c) ? r : -1;
    }

    private int getEnd(char[] s, char c, int l, int r) {
        while (l < r) {
            int m = l + (r - l + 1)/2;
            if (s [m] <= c) l = m;
            else r = m - 1;
        }
        return (s [l] == c) ? l : -1;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest("aA", "aAAbbbb", 3);
    }

    public void runTest(final String J, final String S, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { J, S });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
