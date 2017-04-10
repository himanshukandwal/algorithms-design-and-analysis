package challenges.hackerrank;

import java.util.*;

import challenges.AbstractCustomTestRunner;

import static com.google.common.truth.Truth.assertThat;

/**
 * Ice Cream Parlor
 *
 * Each time Sunny and Johnny take a trip to the Ice Cream Parlor, they pool together m dollars for ice cream.
 * On any given day, the parlor offers a line of n flavors. Each flavor, i, is numbered sequentially with a unique
 * ID number from 1 to n and has a cost, ci, associated with it.
 *
 * Given the value of m and the cost of each flavor for t trips to the Ice Cream Parlor, help Sunny and Johnny choose
 * two flavors such that they spend their entire pool of money (m) during each visit. For each trip to the parlor,
 * print the ID numbers for the two types of ice cream that Sunny and Johnny purchase as two space-separated integers
 * on a new line. You must print the smaller ID first and the larger ID second.
 *
 * Example :
 *          4
 *          1 4 5 3 2
 * Output : 1 4
 *          The first time, they pool together m = 4 dollars.
 *          There are five flavors available that day and flavors 1 and 4 have a total cost of 1 + 3 = 4.
 *          Thus, we print 1 4 on a new line.
 *
 *          4
 *          2 2 4 3
 * Output : 1 2
 *          The second time, they pool together m = 4 dollars.
 *          There are four flavors available that day and flavors 1 and 2 have a total cost of 2 + 2 = 4.
 *          Thus, we print 1 2 on a new line.
 *
 * Created by Hxkandwal
 *
 */
public class IceCreamParlor extends AbstractCustomTestRunner {

    private static IceCreamParlor _instance = new IceCreamParlor();

    private IceCreamParlor() {}

    public static int[] _findPair (int[] input, int sum) {
        Map<Integer, Integer> elementRef = new HashMap<>();

        int[] res = new int[2];
        for (int idx = 0; idx < input.length; idx ++) {
            if (elementRef.containsKey(sum - input[idx])) {
                res[0] = Math.min(idx + 1, elementRef.get(sum - input[idx]));
                res[1] = Math.max(idx + 1, elementRef.get(sum - input[idx]));
                break;
            } else {
                elementRef.put(input [idx], idx + 1);
            }
        }

        return res;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int[] { 1, 4, 5, 3, 2 }, 4, new int[] { 1, 4 });
        _instance.runTest(new int[] { 2, 2, 4, 3 }, 4, new int[] { 1, 2 });
    }

    public void runTest(final int[] input, final int sum, final int[] expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input, sum });

        for (Object answer : answers)
            assertThat((int[]) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
