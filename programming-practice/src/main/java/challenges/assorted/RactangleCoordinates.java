package challenges.assorted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * In Tui t
 */
public class RactangleCoordinates {

    private static RactangleCoordinates _instance = new RactangleCoordinates();

    public List<List<Integer>> locateCoordinates(List<List<Integer>> a) {
        if (a.size() == 0) return Arrays.asList();
        List<List<Integer>> ans = new ArrayList<>();

        for (int row = 0; row < a.size(); row ++) {
            for (int col = 0; col < a.get(row).size(); col++) {
                if (a.get(row).get(col) == 0) {
                    List<Integer> res = Arrays.asList(row, col, row, col);
                    dfs(a, res, row, col);
                    ans.add(res);
                }
            }
        }
        return ans;
    }

    private int [] rdir = { 1, 0 };

    private int [] cdir = { 0, 1 };

    private void dfs (List<List<Integer>> a, List<Integer> res, int row, int col) {
        if (row < 0 || row >= a.size() || col < 0 || col >= a.get (0).size() || a.get(row).get(col) == 1) return;

        res.set(2, Math.max(row, res.get(2)));
        res.set(3, Math.max(col, res.get(3)));

        a.get(row).set(col, 1);
        for (int idx = 0; idx < 2; idx ++)
            dfs(a, res, row + rdir [idx], col + cdir [idx]);
    }

    // driver method
    public static void main(String[] args) {
        System.out.println(_instance.locateCoordinates(
                Arrays.asList(
                        Arrays.asList(1, 1, 1, 1, 1, 1, 1),
                        Arrays.asList(1, 1, 1, 1, 1, 1, 1),
                        Arrays.asList(1, 1, 1, 0, 0, 0, 1),
                        Arrays.asList(1, 0, 1, 0, 0, 0, 1),
                        Arrays.asList(1, 0, 1, 1, 1, 1, 1),
                        Arrays.asList(1, 0, 1, 0, 0, 1, 1),
                        Arrays.asList(1, 1, 1, 0, 0, 0, 1),
                        Arrays.asList(1, 1, 1, 1, 1, 1, 1)
                )
        ));
    }
}