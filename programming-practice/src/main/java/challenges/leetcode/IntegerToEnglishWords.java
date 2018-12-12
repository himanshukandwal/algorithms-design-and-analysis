package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

/**
 * 273. Integer to English Words
 *
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.
 *
 * Example 1:
 *      Input: 123
 *      Output: "One Hundred Twenty Three"
 *
 * Example 2:
 *      Input: 12345
 *      Output: "Twelve Thousand Three Hundred Forty Five"
 *
 * Example 3:
 *      Input: 1234567
 *      Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * Example 4:
 *      Input: 1234567891
 *      Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 *
 * @author Hxkandwal
 */
public class IntegerToEnglishWords extends AbstractCustomTestRunner {

    private static IntegerToEnglishWords _instance = new IntegerToEnglishWords();

    private String[] power = { "", "Thousand", "Million", "Billion" };

    private Map<Integer, String> map = new HashMap() {{
        put(1, "One"); put(2, "Two"); put(3, "Three"); put(4, "Four"); put(5, "Five"); put(6, "Six");
        put(7, "Seven"); put(8, "Eight"); put(9, "Nine"); put(10, "Ten"); put(11, "Eleven"); put(12, "Twelve");
        put(13, "Thirteen"); put(14, "Fourteen"); put(15, "Fifteen"); put(16, "Sixteen"); put(17, "Seventeen");
        put(18, "Eighteen"); put(19, "Nineteen"); put(20, "Twenty"); put(30, "Thirty"); put(40, "Forty");
        put(50, "Fifty"); put(60, "Sixty"); put(70, "Seventy"); put(80, "Eighty"); put(90, "Ninety");
    }};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        String ans = "";
        for (int p = 0; num > 0; num /= 1000, p ++) {
            if (num % 1000 > 0)
                ans = helper(num % 1000).trim() + " " + power [p] + " " + ans;
            ans = ans.trim();
        }
        return ans;
    }

    private String helper(int num) {
        if (num == 0) return "";
        if (map.containsKey(num)) return map.get(num);
        if (num < 100) return (num > 10 ? map.get((num / 10) * 10) + " " : "") +  helper(num % 10);
        return map.get(num /100) + " Hundred " + helper(num % 100);
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(29, "Twenty Nine");
        _instance.runTest(1234567, "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven");
    }

    public void runTest(final int num, final String expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { num });

        for (Object answer : answers)
            assertThat((String) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
