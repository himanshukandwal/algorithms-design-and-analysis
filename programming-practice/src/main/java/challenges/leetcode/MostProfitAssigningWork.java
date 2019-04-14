package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;

/**
 * 826. Most Profit Assigning Work
 *
 * We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit of the ith job.
 * Now we have some workers. worker[i] is the ability of the ith worker, which means that this worker can only complete a job with difficulty at most worker[i].
 *
 * Every worker can be assigned at most one job, but one job can be completed multiple times.
 * For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.  If a worker cannot complete any job, his profit is $0.
 *
 * What is the most profit we can make?
 *
 * Example 1:
 *
 *      Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 *      Output: 100
 *      Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get profit of [20,20,30,30] seperately.
 *
 * Notes:
 *
 *      1 <= difficulty.length = profit.length <= 10000
 *      1 <= worker.length <= 10000
 *      difficulty[i], profit[i], worker[i]  are in range [1, 10^5]
 *
 * @author hxkandwal
 */
public class MostProfitAssigningWork extends AbstractCustomTestRunner {

    private static MostProfitAssigningWork _instance = new MostProfitAssigningWork();

    public int _maxProfitAssignmentTreeMap(int[] difficulty, int[] profit, int[] worker) {
        class Item {
            public int profit, difficulty;

            public Item(int profit, int difficulty) {
                this.profit = profit;
                this.difficulty = difficulty;
            }
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int w : worker) map.put (w, map.getOrDefault(w, 0) + 1);

        PriorityQueue<Item> mh = new PriorityQueue<Item>((a, b) -> (b.profit == a.profit ? a.difficulty - b.difficulty : b.profit - a.profit));
        for (int idx = 0; idx < difficulty.length; idx ++) mh.offer (new Item (profit [idx], difficulty [idx]));

        int p = 0;
        while (!mh.isEmpty()) {
            Item item = mh.poll();
            java.util.SortedMap<Integer, Integer> m = map.tailMap(item.difficulty);
            for (Iterator<Map.Entry<Integer, Integer>> itr = m.entrySet().iterator(); itr.hasNext();) {
                Map.Entry<Integer, Integer> entry = itr.next();
                p += entry.getValue() * item.profit;
                itr.remove();
            }
        }
        return p;
    }

    // uwi solution (ideas:  created 2d array if we wanna keep things together like here difficulty <-> profit. Monolith array approach.)
    public int _maxProfitAssignmentBetter(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int [][] dp = new int [n][];

        for (int i = 0; i < n; i ++)
            dp [i] = new int[] { difficulty[i], profit[i] };

        Arrays.sort(dp, (a, b) -> (a [0] != b [0]) ? (a [0] - b [0]) : -(a [1] - b [1]));

        int[] xs = new int[n];
        for (int i = 0; i < n; i ++) xs [i] = dp [i][0];
        for (int i = 1; i < n; i ++) dp[i][1] = Math.max(dp [i][1], dp [i - 1][1]);

        int ret = 0;
        for(int w : worker){
            int ind = Arrays.binarySearch(xs, w);
            if (ind < 0) ind = -ind - 2;
            if (ind >= 0)
                ret += dp[ind][1];
        }
        return ret;
    }

    // we can use the first dimension of 2d array as index of 1d array itself use the other dimension as values. (optimized monolith approach)
    public int _maxProfitAssignmentBest(int[] difficulty, int[] profit, int[] worker) {
        // value index reverse array
        int [] dp = new int [100001];

        // nice strategy to use in multi-array approach
        // using difficulty as index, profit as value.
        // (difficulty array sorted by default, effort saved)
        for (int idx = 0; idx < difficulty.length; idx ++) {
            // overwrite profit values and keep the max one. (in case of conflict)
            dp [difficulty [idx]] = Math.max (dp [difficulty [idx]], profit [idx]);
        }

        // maximize profits stored linearly
        for (int idx = 1; idx < dp.length; idx ++)
            dp [idx] = Math.max (dp [idx - 1], dp [idx]);

        // build ans
        int ans = 0;
        for (int w : worker)
            ans += dp [w];

        return ans;
    }

    // we can optimize while collection. Build forward during the Collection time. (optimized monolith approach)
    public int _maxProfitAssignmentSortedCollectors(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int [][] dp = new int [n][2]; // difficulty, profit pair (dp)

        for (int idx = 0; idx < n; idx ++) {
            dp [idx][0] = difficulty [idx];
            dp [idx][1] = profit [idx];
        }

        Arrays.sort (dp, (a, b) -> a [0] - b [0]);

        // sort the collectors.
        Arrays.sort (worker);

        int ans = 0, best = 0, idx = 0;
        for (int w : worker) {
            // compute maxima and save it for next we well to build upon the max (best) seen so far.
            while (idx < n && w >= dp [idx][0]) { // increase difficulty slowly
                best = Math.max (best, dp [idx][1]);
                idx ++;
            }
            ans += best;
        }
        return ans;
    }

    // my approach similar to uwi. (ideas:  created a third array indexes to store the relation between difficulty and profit. Distributed arrays approach.)
    public int _maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        Integer [] indexes = new Integer [difficulty.length];
        for (int idx = 0; idx < indexes.length; idx ++) indexes [idx] = idx;

        Arrays.sort(indexes, (a, b) -> difficulty [a] != difficulty [b] ?
                difficulty [a] - difficulty [b] :
                (profit [b] - profit [a]));
        // maxprofit is not working until we sort difficulty properly.

        int [] maxProfit = new int [profit.length];
        maxProfit [0] = profit [indexes [0]];

        for (int idx = 1; idx < indexes.length; idx ++)
            maxProfit [idx] = Math.max(maxProfit [idx - 1], profit [indexes [idx]]);

        int ans = 0;
        for (int val : worker) {
            int idx = binarySearch(indexes, difficulty, val);
            if (idx >= 0) ans += maxProfit [idx];
        }
        return ans;
    }

    private int binarySearch(Integer[] indexes, int[] d, int val) {
        int l = 0, r = d.length - 1;
        while (l <= r) {
            int m = l + (r - l)/2;

            if (d [indexes[m]] > val) r = m - 1;
            else if (d [indexes[m]] < val) l = m + 1;
            else return m;
        }
        return (l >= d.length) ? (l - 1) : ((d [indexes[l]] == val) ? l : -- l);
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(
                new int [] { 68, 35, 52, 47, 86 },
                new int [] { 67, 17, 1, 81, 3 },
                new int [] { 92, 10, 85, 84, 82 },
                324);
    }

    public void runTest(final int[] difficulty, final int[] profit, final int[] worker, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { difficulty, profit, worker });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
