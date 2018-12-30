package challenges.codefights;

import challenges.AbstractCustomTestRunner;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;

/**
 * Gift Stacking
 *
 * You and your brother were playing around the Christmas tree when a new game idea suddenly came to your mind. You challenged your brother to see who can stack the
 * most gift boxes without them collapsing under the weight of themselves. Each ith box has a strength value si and a weight value wi. A box may be heavy but fragile
 * (a dumbbell in a paper box) or it may be light but very durable (the box is made out of 3D graphene but there's nothing inside). You can stack the boxes in any
 * order, but a box's strength must be greater than or equal to the total weight of the boxes above it. The game goal is to find the maximum number of boxes you can
 * stack.
 *
 * While your brother was still foolishly working out the optimal arrangement in his head, you quickly rushed to a computer and started coding.
 *
 * Example:
 *  For boxes = [[3, 4], [0, 1], [6, 4], [2, 2]], the output should be giftStacking(boxes) = 3.
 *  Each ith element contains the strength and weight of the ith box.
 *  For example, box 1 has strength value of 3 and weight value of 4. Box 2 has strength value of 0 and weight value of 1.
 *  An optimal arrangement where we can stack 3 boxes is:
 *          [0, 1]
 *          [2, 2]
 *          [3, 4]
 *
 *  Box 2 has strength 2, which is greater than the box 1's weight, so it can support box 1. Box 3 has strength 3, which is equal to the total weight of box 1 and 2,
 *  so it can support box 1 and 2.
 *  There are no arrangements where we can stack 4 boxes together. For example, if we add the last box [6, 4] to the bottom, the tower would collapse: The total weight
 *  of the boxes above is 1 + 2 + 4 = 7, while the box's strength is only 6.
 *
 * link: https://app.codesignal.com/challenge/ZQMreaCmFzshtoETf
 *
 * @author Hxkandwal
 */
public class GiftStacking extends AbstractCustomTestRunner {

    private static GiftStacking _instance = new GiftStacking();

    public int _giftStacking(int[][] boxes) {
        Map<Integer, List<Integer>> compatibleSet = new HashMap<>();
        for (int i = 0; i < boxes.length; i ++) for (int j = 0; j < boxes.length; j ++)
            if (i != j && boxes [i][0] >= boxes [j][1]) compatibleSet.computeIfAbsent(i, k -> new ArrayList<>()).add(j);

        int ans = 0;
        boolean[] seen = new boolean [boxes.length];
        Map<String, Integer> map = new HashMap<>();
        for (int idx = 0; idx < boxes.length; idx ++) {
            if (idx > 0 && boxes [idx - 1][0] == boxes [idx][0]) continue;
            seen [idx] = true;
            ans = Math.max (ans, 1 + dfs (compatibleSet, map, seen, boxes, idx, boxes [idx][0]));
            seen [idx] = false;
        }
        return ans;
    }

    private int dfs (Map<Integer, List<Integer>> compatibleSet, Map<String, Integer> map, boolean[] seen, int[][] b, int index, int support) {
        String key = index + "#" + Arrays.hashCode(seen) + "#" + support;
        if (map.containsKey(key)) return map.get (key);
        int ans = 0;
        if (compatibleSet.containsKey (index)) {
            for (int k : compatibleSet.get (index)) {
                if (!seen [k] && support >= b [k][1]) {
                    seen [k] = true;
                    ans = Math.max(ans, 1 + dfs (compatibleSet, map, seen, b, k, support - b [k][1]));
                    seen [k] = false;
                }
            }
        }
        map.put (key, ans);
        return ans;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int [][] { { 9, 3 }, { 4, 10 } , { 2, 9 }, { 8, 3 }, { 10, 4 } }, 3);
        _instance.runTest(new int [][] { { 3, 4 } , { 0, 1 }, { 6, 4 }, { 2, 2 } }, 3);
        _instance.runTest(new int[][]{
                {4, 54}, {90, 6}, {11, 38}, {95, 54}, {64, 2}, {73, 1}, {49, 60}, {92, 24},
                {77, 32}, {78, 48}, {13, 11}, {51, 14}, {78, 67}, {16, 3}, {24, 24}, {77, 7},
                {61, 59}, {62, 40}, {10, 6}, {51, 4}, {23, 66}, {16, 98}, {39, 59}, {20, 8},
                {83, 33}, {24, 6}, {67, 23}, {93, 94}, {12, 40}, {37, 24}, {59, 65}, {33, 22},
                {56, 80}, {11, 31}, {10, 5}, {63, 83}, {62, 35}, {27, 33}, {26, 82}, {62, 33},
                {24, 34}, {18, 9}, {93, 94}, {91, 62}, {22, 7}, {24, 8}, {69, 89}, {27, 22},
                {30, 15}, {94, 31}}, 13);
    }

    public void runTest(final int[][] boxes, final Integer expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { boxes });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
