package challenges.hackerearth;

import challenges.AbstractCustomTestRunner;

import java.util.Scanner;

/**
 * Find the substrings
 *
 * Given a string S made of letters a, b and c, find the number of sub strings that do not contain all the letters a, b and c.
 * That is the number of sub strings that do not contain at least one of the letters a or b or c.
 *
 * Note that the sub string should contain atleast one letter, that is it should not be empty string.
 *
 * Input: First line of the input is the number of test cases T. It is followed by T lines. Each test case is a single line which is the string S.
 * Output: For each test case, print a single number, the required answer.
 *
 * Constraints
 * 1 <= |S| <= 10^6
 *
 * SAMPLE INPUT
 * 3
 * ababa
 * abc
 * babac
 *
 * SAMPLE OUTPUT
 * 15
 * 5
 * 12
 *
 * link: https://www.hackerearth.com/practice/algorithms/string-algorithm/basics-of-string-manipulation/practice-problems/algorithm/find-the-substrings/description/
 *
 * @author Hxkandwal
 */
public class FindTheSubstrings extends AbstractCustomTestRunner {

    public static void main(String args[] ) throws Exception {
        //Scanner
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        while (n -- > 0) {
            String str = s.next();

            long ans =  (str.length() * (str.length() + 1l)) / 2;
            int aIdx = -1, bIdx = -1, cIdx = -1;        // last seen indexes of 'a', 'b' and 'c'.

            for (int idx = 0; idx < str.length(); idx ++) {
                char c = str.charAt(idx);

                if (c == 'a') {
                    aIdx = idx;
                    ans -= Math.min(bIdx, cIdx) + 1;    // remove that number of substrings.
                } else if (c == 'b') {
                    bIdx = idx;
                    ans -= Math.min(aIdx, cIdx) + 1;
                } else {
                    cIdx = idx;
                    ans -= Math.min(aIdx, bIdx) + 1;
                }
            }
            System.out.println(ans);
        }
    }
}
