package challenges.codefights;

/**
 * Truncate String
 *
 * Consider the following operation on a string containing digits from 1 to 9:
 *
 * 1. If the leftmost digit of the string is divisible by 3, remove it from the string;
 * 2. Otherwise, if the rightmost digit of the string is divisible by 3, remove it from the string;
 * 3. Otherwise, if the sum of two digits on the sides of the string is divisible by 3, remove both digits from the string;
 *
 * This operation is applied sequentially until the string is empty or neither of the three given conditions is met.
 *
 * For a given string find the result of applying the given algorithm to it.
 *
 * Example:
 *
 *      For s = "312248", the output should be truncateString(s) = "2".
 *
 *      Here's how the answer is obtained:
 *              3 is divisible by 3 and should be removed ("12248");
 *              neither 1 nor 8 is divisible by 3, but their sum is, so both digits should be removed ("224");
 *              neither 2 nor 4 is divisible by 3, but their sum is, so both digits should be removed ("2");
 *              the resulting string "2" doesn't meet any condition, so it is the final answer.
 *
 * link: https://codefights.com/challenge/62tyryaz5noFxaCGT
 */
public class TruncateString {

    public String truncateString(String s) {
        while (s.length() > 0) {
            int sIdx = 0, eIdx = s.length() - 1;
            if (sIdx == eIdx) {
                if (s.charAt (sIdx) != '0' && (s.charAt (sIdx) - '0') % 3 == 0)
                    return "";
                else break;
            } else {
                int sNum = s.charAt (sIdx) - '0', eNum = s.charAt (eIdx) - '0';
                if (sNum != 0 && sNum % 3 == 0) s = s.substring (1);
                else if (eNum != 0 && eNum % 3 == 0) s = s.substring (0, s.length () - 1);
                else if ((sNum + eNum) != 0 && (sNum + eNum) % 3 == 0) s = s.substring (1, s.length() - 1);
                else break;
            }
        }
        return s;
    }

}
