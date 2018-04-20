package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 686. Repeated String Match
 *
 * Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.
 *
 * For example, with A = "abcd" and B = "cdabcdab".
 *
 * Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").
 *
 * Note: The length of A and B will be between 1 and 10000.
 *
 * @author hxkandwal
 */
public class RepeatedStringMatch extends AbstractCustomTestRunner {

    public int repeatedStringMatch(String A, String B) {
        int i = 0;
        while (i < A.length()) {
            int j = 0;
            while (j < B.length() && A.charAt((i + j) % A.length()) == B.charAt(j)) j ++;
            if (j == B.length()) return (i + j) / A.length() + ((i + j) % A.length() == 0 ? 0 : 1);

            i ++;
        }
        return -1;
    }

}
