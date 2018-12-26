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
            if (len + words [idx].length() + 1 < l) len += words [idx].length() + 1;
            else {
                List<String> elements = new ArrayList<>();
                for (int j = start; j < idx; j ++) {
                    elements.add (words [j]);
                    if (j != idx - 1) elements.add(" ");
                }
                int diff = l - len;
                while (diff > 0)
                    for (int j = 1; diff > 0 && j < elements.size(); j+= 2, diff --) elements.set(j, elements.get(j) + " ");

                String line = "";
                for (String e : elements) line += e;
                ans.add (line);

                start = idx;
                len = words [idx].length();
            }
        }

        List<String> elements = new ArrayList<>();
        for (int j = start; j < words.length; j ++) {
            elements.add (words [j]);
            elements.add (" ");
        }

        int diff = l - len - 1;
        while (diff > 0)
            for (int j = 1; diff > 0 && j < elements.size(); j+= 2, diff --) elements.set(j, elements.get(j) + " ");

        String line = "";
        for (String e : elements) line += e;
        ans.add (line);
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
    }

    public void runTest(final String [] words, final int l, final String[] expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { words, l });

        for (Object answer : answers)
            assertThat((String []) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
