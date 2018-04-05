package challenges.hackerrank;

/**
 * Created by Heman on 1/22/18.
 */
public class Test {
    public static void main(String[] args)
    {
        System.out.println(minMoves(new int [] { 1234, 4321 }, new int [] { 2345, 3214 }));
        System.out.println(countConsecutive2(3));
    }

    public static int minMoves(int[] n, int [] m) {
        int ans = 0;
        for (int idx = 0; idx < n.length; idx ++) {
            String ns = String.valueOf(n [idx]), ms = String.valueOf(m [idx]);

            for (int iidx = 0; iidx < ns.length(); iidx ++) {
                ans += Math.abs(ns.charAt(iidx) - ms.charAt(iidx));
            }
        }
        return ans;
    }

    public static int countConsecutive(int num)
    {
        int count = 0;
        for (int i = 1; i * (i + 1) < 2 * num; i ++)
        {
            float a = (float) ((1.0 * num - (i * (i + 1)) / 2) / (i + 1));
            if (a - (int) a == 0.0)  count++;
        }
        return count;
    }

    public static long countConsecutive2(int num)
    {
        long sumOfFirstIntegers = 3;
        long count = 0;
        for (long i = 2; sumOfFirstIntegers <= num; ++ i) {
            if ((i % 2 == 0) ? (num % i == i / 2) : (num % i == 0)) {
                ++count;
            }
            sumOfFirstIntegers += i + 1;
        }
        return count;
    }
}
