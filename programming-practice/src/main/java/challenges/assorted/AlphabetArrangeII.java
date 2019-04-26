package challenges.assorted;

import challenges.AbstractCustomTestRunner;

import java.util.Scanner;

/**
 * Alphabet Soup (Hackercup 2012)
 */
public class AlphabetArrangeII extends AbstractCustomTestRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();

        String t = "HACKERCUP";
        int [] arr = new int [256];
        for (char c : t.toCharArray()) arr [c] ++;
        int counter = 0, ans = 0;
        for (int idx = 0, start = 0; idx < s.length(); idx ++) {
            if (arr [s.charAt(idx)] -- > 0) counter ++;
            while (counter > t.length()) {
                if (arr [s.charAt(start ++)] ++ >= 0)
                    counter --;
            }
            if (counter == 0) ans ++;
        }
        System.out.println(ans);
    }
}
