package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 717. 1-bit and 2-bit Characters
 *
 * We have two special characters. The first character can be represented by one bit 0. The second character can be represented by two bits (10 or 11).
 *
 * Now given a string represented by several bits. Return whether the last character must be a one-bit character or not.
 * The given string will always end with a zero.
 *
 * Example 1:
 *
 *  Input: bits = [1, 0, 0]
 *  Output: True
 *
 *  Explanation: The only way to decode it is two-bit character and one-bit character. So the last character is one-bit character.
 *
 * Example 2:
 *
 *  Input: bits = [1, 1, 1, 0]
 *  Output: False
 *
 *  Explanation: The only way to decode it is two-bit character and two-bit character. So the last character is NOT one-bit character.
 *
 * Note:
 *
 * 1) 1 <= len(bits) <= 1000.
 * 2) bits[i] is always 0 or 1.
 *
 */
public class The1BitAnd2BitCharacters extends AbstractCustomTestRunner {

    private static The1BitAnd2BitCharacters _instance = new The1BitAnd2BitCharacters();

    public boolean isOneBitCharacter(int[] bits) {
        boolean isOb = false;
        for (int idx = 0; idx < bits.length; idx ++) {
            isOb = (bits [idx] == 0);
            if (!isOb) idx ++;
        }
        return isOb;
    }


    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int [] { -1, 3, 2, 0 }, true);
        _instance.runTest(new int [] { 3, 1, 4, 2 }, true);
    }

    public void runTest(final int[] nums, final boolean expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { nums });

        for (Object answer : answers)
            assertThat((Boolean) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
