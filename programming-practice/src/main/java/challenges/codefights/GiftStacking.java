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
        // link: https://app.codesignal.com/challenge/ZQMreaCmFzshtoETf?solutionId=vaTwb8PhrPAWCR7uS
        //
        // Part 1 - Ordering
        // Before we can use DP, we need to know the order in which we should consider the boxes when doing DP.
        // To find the optimal ordering, we can use EDF (earliest deadline first) greedy algorithm, as EDF maximizes
        // number of intervals scheduled.
        // <https://cse.buffalo.edu/~hartloff/CSE331-Summer2015/greedy.pdf>
        // Here finish time = strength + weight. (you can think of start time as strength).
        Arrays.sort(boxes, (a, b) ->  (a[0] + a[1]) - (b[0] + b[1]));

        // Part 2 - Finding subsequence
        // An O(n^2) can easily be obtained by maintaining a list of minimum weights achieveable from the first x boxes.
        // This DP can be optimized with a heap. The proof for this is a little complex, but intuitively, with each box
        // in the optimal order, we either add it normally because its strength >= the total weight so far, or we update
        // our dp array using the smaller box weight. We will pop out the heaviest box to minimize the weight like what
        // we did in our dp.
        //
        // Here's the DP version:
        // int count = 0;
        // vector<long long> dp(B.size()+1, LLONG_MAX);
        // dp[0] = 0;
        // for (int i=0; i<B.size(); ++i) {
        //     for (int j=count; j>=0; --j) {
        //         if (B[i][0] >= dp[j] && dp[j] + B[i][1] < dp[j+1]) {
        //             dp[j+1] = dp[j] + B[i][1];
        //             if (j == count) count++;
        //         }
        //     }
        // }
        // return count;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int W = 0;
        for (int[] b : boxes) {
            heap.offer (-b [1]);
            if (b [0] < W) W += heap.poll();
            W += b [1];

        }
        return heap.size();
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
