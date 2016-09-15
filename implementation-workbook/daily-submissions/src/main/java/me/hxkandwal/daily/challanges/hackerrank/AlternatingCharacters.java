package me.hxkandwal.daily.challanges.hackerrank;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Alternating Characters
 *
 * Shashank likes strings in which consecutive characters are different.
 * For example, he likes ABABA, while he doesn't like ABAA. Given a string containing characters A and B only,
 * he wants to change it into a string he likes. To do this, he is allowed to delete the characters in the string.
 *
 * Your task is to find the minimum number of required deletions.
 *
 * Example :
 *      5
 *      AAAA
 *      BBBBB
 *      ABABABAB
 *      BABABA
 *      AAABBB
 *
 * Output :
 *      3
 *      4
 *      0
 *      0
 *      4
 *
 * Created by Hxkandwal
 *
 */
public class AlternatingCharacters extends AbstractCustomTestRunner {

    private static AlternatingCharacters _instance = new AlternatingCharacters();

    private AlternatingCharacters() {}

    public static int _minDeletions(String input) {
        Character previousChar = null;

        int deletions = 0;
        for (int idx = 0; idx < input.length(); idx ++) {
            if (idx == 0)
                previousChar = input.charAt(idx);
            else {
                if (input.charAt(idx) == previousChar)
                    deletions ++;
                else
                    previousChar = input.charAt(idx);
            }
        }

        return deletions;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest("AAAA", 3);
        _instance.runTest("BBBBB", 4);
        _instance.runTest("ABABABAB", 0);
        _instance.runTest("BABABA", 0);
        _instance.runTest("AAABBB", 4);
    }

    public void runTest(final String input, final Integer expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
