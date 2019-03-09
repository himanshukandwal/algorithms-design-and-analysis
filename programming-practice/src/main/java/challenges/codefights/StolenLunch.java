package challenges.codefights;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Stolen Lunch
 *
 * When you recently visited your little nephew, he told you a sad story: there's a bully at school who steals his lunch every day, and locks it away in his locker.
 * He also leaves a note with a strange, coded message. Your nephew gave you one of the notes in hope that you can decipher it for him. And you did: it looks like all
 * the digits in it are replaced with letters and vice versa. Digit 0 is replaced with 'a', 1 is replaced with 'b' and so on, with digit 9 replaced by 'j'.
 *
 * The note is different every day, so you decide to write a function that will decipher it for your nephew on an ongoing basis.
 *
 * link: https://app.codesignal.com/challenge/ajSesXvQbhEoayaxR?solutionId=kpSyAWigojtTkGfiv
 *
 * @author Hxkandwal
 */
public class StolenLunch extends AbstractCustomTestRunner {

    private static StolenLunch _instance = new StolenLunch();

    public String _stolenLunch(String s) {
        String r = "";
        for (Byte b : s.getBytes()) {
            System.out.println(Integer.toString(b));
            r += (char) (b + (b > 47 & b < 58 ? 49 :
                    b > 96 & b < 107 ? -49 :
                            0));
            System.out.println(r);
        }
        return r;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest("", "");
        _instance.runTest("jihgfedcba", "9876543210");
        _instance.runTest("0123456789", "abcdefghij");
        _instance.runTest("you won't know!!", "you won't know!!");
        _instance.runTest("you'll n4v4r 6u4ss 8t: cdja", "you'll never guess it: 2390");
    }

    public void runTest(final String s, final String expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { s });

        for (Object answer : answers)
            assertThat((String) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
