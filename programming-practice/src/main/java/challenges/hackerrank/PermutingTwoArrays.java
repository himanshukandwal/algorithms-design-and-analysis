package challenges.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Permuting Two Arrays
 *
 * link: https://www.hackerrank.com/challenges/two-arrays
 *
 * Created by Hxkandwal
 */
public class PermutingTwoArrays {

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        int tests = sc.nextInt ();
        while (tests -- > 0) {
            int [] a = new int [sc.nextInt ()], b = new int [a.length];
            int k = sc.nextInt ();
            for (int idx = 0; idx < a.length; idx ++) a [idx] = sc.nextInt ();
            for (int idx = 0; idx < b.length; idx ++) b [idx] = sc.nextInt ();
            Arrays.sort (a); Arrays.sort (b);
            boolean ok = true;
            for (int idx = 0; idx < a.length; idx ++)
                if (a [idx] + b [b.length - 1 - idx] < k) { ok = false; break; }
            System.out.println (ok ? "YES" : "NO");
        }
    }
}
