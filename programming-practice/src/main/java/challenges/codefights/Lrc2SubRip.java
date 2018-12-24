package challenges.codefights;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Lrc 2 SubRip
 *
 * During your most recent trip to Codelandia you decided to buy a brand new CodePlayer, a music player that (allegedly) can work with any possible media format.
 * As it turns out, this isn't true: the player can't read lyrics written in the LRC format. It can, however, read the SubRip format, so now you want to translate
 * all the lyrics you have from LRC to SubRip.
 *
 * Since you are a pro programmer (no noob would ever get to Codelandia!), you're happy to implement a function that, given lrcLyrics and songLength, returns the
 * lyrics in SubRip format.
 *
 * Example
 *      For  lrcLyrics = ["[00:12.00] Happy birthday dear coder,", "[00:17.20] Happy birthday to you!"] and songLength = "00:00:20", the output should be
 *      lrc2subRip(lrcLyrics, songLength) =
 *          [
 *              "1",
 *              "00:00:12,000 --> 00:00:17,200",
 *              "Happy birthday dear coder,",
 *              "",
 *              "2",
 *              "00:00:17,200 --> 00:00:20,000",
 *              "Happy birthday to you!"
 *          ]
 *
 * link: https://app.codesignal.com/tournaments/mDti2jqL5Fq9dsPWJ/C
 *
 * @author Hxkandwal
 */
public class Lrc2SubRip extends AbstractCustomTestRunner {

    private static Lrc2SubRip _instance = new Lrc2SubRip();

    public String[] _lrc2subRip(String[] lrcLyrics, String songLength) {
        int l = lrcLyrics.length;
        List<String> ans = new ArrayList<>();;
        int i = 0;
        for (int idx = 1; idx < l; idx ++) {
            ans.add(String.valueOf(idx));

            String a = lrcLyrics [idx - 1], b = lrcLyrics [idx];
            ans.add(getTime(a.split(" ")[0]) + " --> " + getTime(b.split(" ")[0]));
            int li = getLyricsIndex(a);

            ans.add(li < 0 ? "" : a.substring(li + 2));
            ans.add("");
        }

        ans.add(String.valueOf(l));
        ans.add(getTime(lrcLyrics [l - 1].split(" ")[0]) + " --> " + songLength + ",000");
        int li = getLyricsIndex(lrcLyrics [l - 1]);
        ans.add(li < 0 ? "" : lrcLyrics [l - 1].substring(li + 2));
        return ans.toArray(new String [0]);
    }

    private String getTime(String s) {
        s = s.substring(1, s.length() - 1).replaceAll("\\.", ",") + "0";
        int min = Integer.valueOf(s.split("\\:")[0]);
        int hour = min/60;
        min = min % 60;
        String sec = s.split("\\:")[1];

        return String.format("%02d", hour) + ":" + String.format("%02d", min) + ":" + s.split("\\:")[1];
    }

    private int getLyricsIndex(String s) {
        int idx = 0;
        for (; idx < s.length(); idx ++) if (s.charAt(idx) == ']') break;
        return idx + 2 < s.length() ? idx : -1;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new String [] { "[00:12.00] Happy birthday dear coder,", "[00:17.20] Happy birthday to you!" }, "00:00:20",
                new String [] { "1", "00:00:12,000 --> 00:00:17,200", "Happy birthday dear coder,", "", "2", "00:00:17,200 --> 00:00:20,000", "Happy birthday to you!" });
    }

    public void runTest(String[] lrcLyrics, String songLength, final String[] expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { lrcLyrics, songLength });

        for (Object answer : answers)
            assertThat((String []) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
