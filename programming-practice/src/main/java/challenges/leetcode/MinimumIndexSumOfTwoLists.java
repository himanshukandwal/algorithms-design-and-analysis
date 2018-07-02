package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 599. Minimum Index Sum of Two Lists
 *
 * Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.
 * You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all
 * of them with no order requirement. You could assume there always exists an answer.
 *
 * Example 1:
 *  Input:  ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 *          ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 *  Output: ["Shogun"]
 *  Explanation: The only restaurant they both like is "Shogun".
 *
 * Example 2:
 *  Input:  ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 *          ["KFC", "Shogun", "Burger King"]
 *  Output: ["Shogun"]
 *  Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
 *
 * Note:
 *  The length of both lists will be in the range of [1, 1000].
 *  The length of strings in both lists will be in the range of [1, 30].
 *  The index is starting from 0 to the list length minus 1.
 *  No duplicates in both lists.
 *
 * @author hxkandwal
 */
public class MinimumIndexSumOfTwoLists extends AbstractCustomTestRunner {

    public String[] _findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int idx = 0; idx < list1.length; idx ++) map.put (list1 [idx], idx + 1);
        List<String> ans = new ArrayList<>();
        for (int idx = 0, min = Integer.MAX_VALUE; idx < list2.length; idx ++) {
            if (map.containsKey(list2 [idx])) {
                map.put (list2 [idx], map.get (list2 [idx]) + idx + 1);
                if (min > map.get (list2 [idx])) {
                    min = map.get (list2 [idx]);
                    ans.clear();
                    ans.add (list2 [idx]);
                } else if (min == map.get (list2 [idx])) ans.add (list2 [idx]);
            }
        }
        return ans.toArray(new String [0]);
    }
}
