package challenges.leetcode;

import challenges.AbstractCustomTestRunner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 997. Find the Town Judge
 *
 * In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.
 * If the town judge exists, then:
 *  The town judge trusts nobody.
 *  Everybody (except for the town judge) trusts the town judge.
 *  There is exactly one person that satisfies properties 1 and 2.
 *
 * You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.
 * If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.
 *
 * Example 1:
 *            Input: N = 2, trust = [[1,2]]
 *            Output: 2
 *
 * Example 2:
 *            Input: N = 3, trust = [[1,3],[2,3]]
 *            Output: 3
 *
 * Example 3:
 *            Input: N = 3, trust = [[1,3],[2,3],[3,1]]
 *            Output: -1
 *
 * Example 4:
 *            Input: N = 3, trust = [[1,2],[2,3]]
 *            Output: -1
 *
 * Example 5:
 *            Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
 *            Output: 3
 *
 * Note:
 *  1 <= N <= 1000
 *  trust.length <= 10000
 *  trust[i] are all different
 *  trust[i][0] != trust[i][1]
 *  1 <= trust[i][0], trust[i][1] <= N
 *
 * @author Hxkandwal
 */
public class FindtheTownJudge extends AbstractCustomTestRunner {

  public int _findJudge(int N, int[][] trust) {
    Map<Integer, Set<Integer>> map = new HashMap<>();
    for (int [] t : trust) map.computeIfAbsent(t [0], k -> new HashSet<>()).add (t [1]);

    if (map.size() != N - 1) return -1;
    for (int idx = 1; idx <= N; idx ++) {
      if (!map.containsKey(idx)) {
        for (int key : map.keySet()) if (!map.get (key).contains (idx)) return -1;
        return idx;
      }
    }
    return -1;
  }

  public int _findJudgeBetter(int N, int[][] trust) {
    int [] peopleTrust = new int [N + 1];
    boolean [] cantBeJudge = new boolean [N + 1];
    for (int [] t : trust) {
      cantBeJudge [t [0]] = true;
      peopleTrust [t [1]] ++;
    }

    for (int idx = 1; idx <= N; idx ++) if (!cantBeJudge [idx] && peopleTrust [idx] == N - 1) return idx;
    return -1;
  }
}
