package challenges.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public static List<List<Integer>> optimalUtilization(
            int deviceCapacity,
            List<List<Integer>> foregroundAppList,
            List<List<Integer>> backgroundAppList)
    {
        Collections.sort(backgroundAppList, (a, b) -> a.get(1) - b.get(1));
        List<List<Integer>> ans = new ArrayList<>();
        int closest = Integer.MAX_VALUE;
        List<Integer> best = null;
        for (List<Integer> frapp : foregroundAppList) {
            if (deviceCapacity < frapp.get(1)) continue;
            int delta = deviceCapacity - frapp.get(1);

            int index = binarySearch (backgroundAppList, delta);
            if (index < 0) {
                index = -(index + 1) - 1;
                if (index < 0) continue;;

                int diff = delta - backgroundAppList.get(index).get(1);

                if (closest > diff) {
                    closest = diff;
                    best = Arrays.asList(frapp.get(0), backgroundAppList.get(index).get(0));
                }
            } else {
                ans.add (Arrays.asList(frapp.get(0), backgroundAppList.get(index).get(0)));
            }
        }
        if (ans.size() == 0) ans.add (best);
        return ans;
    }

    private static int binarySearch(List<List<Integer>> bgApps, int val) {
        int l = 0, r = bgApps.size() - 1;
        while (l <= r) {
            int m = (l + r) >>> 1;

            if (bgApps.get(m).get(1) < val) l = m + 1;
            else if (bgApps.get(m).get(1) > val) r = m - 1;
            else return m;
        }
        return -(l + 1);
    }

    public static void main(String[] args) {
        System.out.println(optimalUtilization(
                20,
                Arrays.asList(
                        Arrays.asList(1, 8),
                        Arrays.asList(2, 7),
                        Arrays.asList(3, 14)
                ),
                Arrays.asList(
                        Arrays.asList(1, 5),
                        Arrays.asList(2, 10),
                        Arrays.asList(3, 14)
                )
        ));

        System.out.println(optimalUtilization(
                20,
                Arrays.asList(
                        Arrays.asList(1, 8),
                        Arrays.asList(2, 15),
                        Arrays.asList(3, 9)
                ),
                Arrays.asList(
                        Arrays.asList(1, 8),
                        Arrays.asList(2, 11),
                        Arrays.asList(3, 12)
                )
        ));
    }


    // METHOD SIGNATURE ENDS
}
