package challenges.hackerearth.tutorial;

import java.util.Scanner;

/**
 * Z Algorithm
 *
 * link: https://www.hackerearth.com/practice/algorithms/string-algorithm/z-algorithm/tutorial/
 *
 * @author Hxkandwal
 */
public class ZAlgorithm {

    public static void main(String args[] ) throws Exception {
        Scanner sc = new Scanner(System.in);
        String a = sc.next(), b = sc.next();
        String ab = a + "#" + b;

        int n = ab.length();
        int [] pre = new int [n];

        int l = 0, r = 0;
        for (int idx = 1; idx < n; idx ++) {
            if (idx > r) {
                l = r = idx;
                while (r < n && ab.charAt(r - l) == ab.charAt(r)) r ++;
                pre [idx] = r - l;
                r --;
            } else {
                if (idx + pre [idx - l] - 1 < r) {
                    pre [idx] = pre [idx - l];
                } else {
                    l = idx;
                    while (r < n && ab.charAt(r - l) == ab.charAt(r)) r ++;
                    pre [idx] = r - l;
                    r --;
                }
            }
        }

        int ans = 0;
        for (int p : pre) if (p == a.length()) ans ++;
        System.out.println(ans);
    }
    
}
