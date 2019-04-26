package challenges.assorted;

import challenges.AbstractCustomTestRunner;

import java.util.Scanner;

/**
 * Alphabet Soup (Beginner/Novice)
 *
 * "eLEPhAnt" -> "AEehLnPt"
 * "eLEPhAnttttttTTTTT" -> "AEehLnPTTTTTtttttt"
 */
public class AlphabetArrange extends AbstractCustomTestRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();

        int [] arr = new int [256];
        for (char c : s.toCharArray()) arr [c] ++;
        int dis = 'a' - 'A';
        StringBuilder ans = new StringBuilder();
        for (char c = 'A'; c <= 'Z'; c ++) {
            while (arr [c] -- > 0) ans.append(c);
            while (arr [c + dis] -- > 0) ans.append((char) (c + dis));
        }
        System.out.println(ans.toString());
    }
}
