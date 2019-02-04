package challenges.codefights;

import challenges.AbstractCustomTestRunner;

/**
 * Replace Digit With Prime
 *
 * This one's a reverse challenge!
 *
 * link: https://app.codesignal.com/challenge/GH8mWR28aYJQTuh7A
 *
 * @author Hxkandwal
 */
public class ReplaceDigitWithPrime extends AbstractCustomTestRunner {

    public int _replaceDigitWithPrime(int n) {
        int [] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 };
        char [] arr = String.valueOf(n).toCharArray();

        String ans = "";
        int carry = 0;
        for (int idx = arr.length - 1; idx >= 0; idx --) {
            int val = carry + primes [arr [idx] - '0'];
            ans = val % 10 + ans;
            carry = val / 10;
        }
        if (carry > 0) ans = carry + ans;
        return Integer.valueOf(ans);
    }

}
