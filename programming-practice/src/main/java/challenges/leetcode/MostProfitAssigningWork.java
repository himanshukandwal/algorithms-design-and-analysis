package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

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

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
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

    // uwi solution
    public int maxProfitAssignmentBetter(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int[][] dp = new int[n][];
        for(int i = 0;i < n;i++){
            dp[i] = new int[]{difficulty[i], profit[i]};
        }
        Arrays.sort(dp, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if(a[0] != b[0])return a[0] - b[0];
                return -(a[1] - b[1]);
            }
        });
        int[] xs = new int[n];
        for(int i = 0;i < n;i++)xs[i] = dp[i][0];
        for(int i = 1;i < n;i++){
            dp[i][1] = Math.max(dp[i][1], dp[i-1][1]);
        }
        int ret = 0;
        for(int w : worker){
            int ind = Arrays.binarySearch(xs, w);
            if(ind < 0)ind = -ind-2;
            if(ind >= 0){
                ret += dp[ind][1];
            }
        }
        return ret;
    }
}
