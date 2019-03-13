package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * Cloudy Day
 *
 * All year round, the city is covered in clouds. The city has many towns, located on a one-dimensional line. The positions and populations of each town on the number
 * line are known to you. Every cloud covers all towns located at a certain distance from it. A town is said to be in darkness if there exists at least one cloud such
 * that the town is within the cloud's range. Otherwise, it is said to be sunny.
 *
 * The city council has determined that they have enough money to remove exactly one cloud using their latest technology. Thus they want to remove the cloud such that
 * the fewest number of people are left in darkness after the cloud is removed. What is the maximum number of people that will be in a sunny town after removing exactly
 * one cloud?
 *
 * Note: If a town is not covered by any clouds, then it is already considered to be sunny, and the population of this town must also be included in the final answer.
 *
 * Complete the function maximumPeople which takes four arrays representing the populations of each town, locations of the towns, locations of the clouds, and the extents
 * of coverage of the clouds respectively, and returns the maximum number of people that will be in a sunny town after removing exactly one cloud.
 *
 * https://www.hackerrank.com/challenges/cloudy-day/problem
 *
 * @author hxkandwal
 *
 * Solution: https://www.hackerrank.com/rest/contests/master/challenges/cloudy-day/hackers/uwi/download_solution?primary=true
 * Similar : https://github.com/ZXZxin/ZXNotes/blob/a60b80865da5029e66529a9ebb51d871a6173a70/%E5%88%B7%E9%A2%98/Codeforces/Simulation/median/Codeforces%20-%201132C.%20Painting%20the%20Fence.md
 *           https://www.youtube.com/watch?v=4GNUt5unEnM&t=625s
 *
 */
public class CloudyDay extends AbstractCustomTestRunner {

    InputStream is;
    PrintWriter out;
    String INPUT = "";

    void solve() {
        int n = ni();
        int[][] pt = new int[n][2];

        for (int i = 0; i < n; i++) pt[i][0] = ni();
        for (int i = 0; i < n; i++) pt[i][1] = ni();

        Arrays.sort(pt, (a, b) -> a[1] - b[1]);

        int m = ni();
        int[][] cs = new int[m][2];

        for (int i = 0; i < m; i++) cs[i][0] = ni();
        for (int i = 0; i < m; i++) cs[i][1] = ni();

        for (int i = 0; i < m; i++) {
            int l = cs[i][0] - cs[i][1];
            int r = cs[i][0] + cs[i][1];
            cs[i][0] = l;
            cs[i][1] = r;
        }

        int[] ts = new int[n];

        for (int i = 0; i < n; i++) ts[i] = pt[i][1];

        long[] imos = new long[n + 2];

        for (int i = 0; i < m; i++) {
            int l = Arrays.binarySearch(ts, cs[i][0]);
            if (l < 0) l = -l - 1;
            int r = Arrays.binarySearch(ts, cs[i][1]);
            if (r < 0) r = -r - 2;
            imos[l]++;
            imos[r + 1]--;
        }

        for (int i = 0; i < n; i++) imos[i + 1] += imos[i];

        long base = 0;

        for (int i = 0; i < n; i++) {
            if (imos[i] != 1) {
                if (imos[i] == 0)  base += pt[i][0];

                imos[i] = 0;
            } else {
                imos[i] = pt[i][0];
            }
        }

        for (int i = 0; i < n; i++)  imos[i + 1] += imos[i];

        long ans = 0;
        for (int i = 0; i < m; i++) {
            int l = Arrays.binarySearch(ts, cs[i][0]);
            if (l < 0) l = -l - 1;
            int r = Arrays.binarySearch(ts, cs[i][1]);
            if (r < 0) r = -r - 2;
            ans = Math.max(ans, (r < 0 ? 0 : imos[r]) - (l - 1 < 0 ? 0 : imos[l - 1]));
        }

        out.println(base + ans);
    }

    void run() throws Exception {
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);

        long s = System.currentTimeMillis();
        solve();
        out.flush();
        if (!INPUT.isEmpty()) tr(System.currentTimeMillis() - s + "ms");
    }

    public static void main(String[] args) throws Exception {
        new CloudyDay().run();
    }

    private byte[] inbuf = new byte[1024];
    public int lenbuf = 0, ptrbuf = 0;

    private int readByte() {
        if (lenbuf == -1) throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {
                lenbuf = is.read(inbuf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0) return -1;
        }
        return inbuf[ptrbuf++];
    }

    private boolean isSpaceChar(int c) {
        return !(c >= 33 && c <= 126);
    }

    private int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    private double nd() {
        return Double.parseDouble(ns());
    }

    private char nc() {
        return (char) skip();
    }

    private String ns() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    private char[] ns(int n) {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while (p < n && !(isSpaceChar(b))) {
            buf[p++] = (char) b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    private char[][] nm(int n, int m) {
        char[][] map = new char[n][];
        for (int i = 0; i < n; i++) map[i] = ns(m);
        return map;
    }

    private int[] na(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = ni();
        return a;
    }

    private int ni() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private long nl() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private static void tr(Object... o) {
        System.out.println(Arrays.deepToString(o));
    }
}
