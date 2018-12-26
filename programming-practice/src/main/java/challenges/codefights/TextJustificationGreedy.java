package challenges.codefights;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Text Justification (Greedy)
 *
 * Given an array of words and a length l, format the text such that each line has exactly l characters and is fully justified on both the left and the right.
 * Words should be packed in a greedy approach; that is, pack as many words as possible in each line. Add extra spaces when necessary so that each line has
 * exactly l characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots
 * on the left will be assigned more spaces than the slots on the right. For the last line of text and lines with one word only, the words should be left justified
 * with no extra space inserted between them.
 *
 * Example:
 *  For words = ["This", "is", "an", "example", "of", "text", "justification."] and l = 16, the output should be
 *  textJustification(words, l) = ["This    is    an",
 *                                "example  of text",
 *                                "justification.  "]
 *
 * link: https://app.codesignal.com/skill-test/rak3HBvHDAjHRkTCW
 *
 * @author Hxkandwal
 */
public class TextJustificationGreedy extends AbstractCustomTestRunner {

    private static TextJustificationGreedy _instance = new TextJustificationGreedy();

    public String[] _textJustification(String[] words, int l) {
        List<String> ans = new ArrayList<>();
        int start = 0, len = words [0].length();

        for (int idx = 1; idx < words.length; idx ++) {
            if (len + words [idx].length() + 1 <= l) len += (words [idx] = " " + words [idx]).length();
            else {
                int diff = l - len;
                while (diff > 0)
                    for (int j = start + 1; diff > 0 && j < idx; j++, diff --) words [j] = " " + words [j];

                String line = "";
                for (int j = start; j < idx; j++) line += words [j];
                ans.add (line);

                start = idx;
                len = words [idx].length();
            }
        }

        int diff = l - len;
        String end = "";
        while (diff > 0) {
            for (int j = start + 1; diff > 0 && j <= words.length; j++, diff --) {
                if (j == words.length) end += " ";
                else words [j] = " " + words [j];
            }
        }

        String line = "";
        for (int j = start; j < words.length; j++) line += words [j];
        ans.add (line + end);
        return ans.toArray(new String [0]);
    }

    // driver method
    public static void main(String [] args) {
        _instance.runTest(new String [] { "This", "is", "an", "example", "of", "text", "justification." }, 16,
                new String [] {
                        "This    is    an",
                        "example  of text",
                        "justification.  "
        });

        _instance.runTest(new String [] { "Two", "words." }, 10, new String [] { "Two words." });
    }

    public void runTest(final String [] words, final int l, final String[] expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { words, l });

        for (Object answer : answers)
            assertThat((String []) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
