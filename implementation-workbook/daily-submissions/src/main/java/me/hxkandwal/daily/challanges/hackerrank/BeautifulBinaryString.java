package me.hxkandwal.daily.challanges.hackerrank;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Beautiful Binary String
 *
 * Alice has a binary string, B, of length n. She thinks a binary string is beautiful if and only
 * if it doesn't contain the substring "010".
 *
 * In one step, Alice can change a 0 to a 1 (or vice-versa). Count and print the minimum number of
 * steps needed to make Alice see the string as beautiful.
 *
 * Example :
 *      7
 *      0101010
 *
 * Output : 2
 *
 *      5
 *      01100
 *
 * Output : 0
 *
 * Created by Hxkandwal
 */
public class BeautifulBinaryString extends AbstractCustomTestRunner {

    private static BeautifulBinaryString _instance = new BeautifulBinaryString();

    private BeautifulBinaryString() {}

    public static int _beautify(String input) {
        int count = 0;
        for (int idx = 0; idx < input.length() - 2; idx ++) {
            if (input.charAt(idx) == '0' && input.charAt(idx + 1) == '1' && input.charAt(idx + 2) == '0') {
                count ++;
                idx += 2;
            }
        }
        return count;
    }


    // driver method
    public static void main(String[] args) {
        _instance.runTest("0101010", 2);
        _instance.runTest("01100", 0);
        _instance.runTest("0100101010", 3);
    }

    public void runTest(final String input, final Integer expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
