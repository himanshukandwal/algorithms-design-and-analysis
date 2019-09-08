package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 914. X of a Kind in a Deck of Cards
 *
 * In a deck of cards, each card has an integer written on it. Return true if and only if you can choose X >= 2 such that it is possible to split the entire
 * deck into 1 or more groups of cards, where:
 *  Each group has exactly X cards.
 *  All the cards in each group have the same integer.
 *
 * Example 1:
 *      Input: [1,2,3,4,4,3,2,1]
 *      Output: true
 *      Explanation: Possible partition [1,1],[2,2],[3,3],[4,4]
 *
 * Example 2:
 *      Input: [1,1,1,2,2,2,3,3]
 *      Output: false
 *      Explanation: No possible partition.
 *
 * Example 3:
 *      Input: [1]
 *      Output: false
 *      Explanation: No possible partition.
 *
 * Example 4:
 *      Input: [1,1]
 *      Output: true
 *      Explanation: Possible partition [1,1]
 *
 * Example 5:
 *      Input: [1,1,2,2,2,2]
 *      Output: true
 *      Explanation: Possible partition [1,1],[2,2],[2,2]
 *
 * Note:
 *  1 <= deck.length <= 10000
 *  0 <= deck[i] < 10000
 *
 * @author Hxkandwal
 */
public class XOfAKindInADeckOfCards extends AbstractCustomTestRunner {

    public boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length <= 1) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int d : deck) map.put (d, map.getOrDefault(d, 0) + 1);

        int gcd = -1;
        for (Integer v : map.values()) gcd = gcd < 0 ? v : gcd (gcd, v);
        for (Integer v : map.values()) if (v % gcd != 0) return false;
        return gcd >= 2;
    }

    private int gcd (int a, int b) {
        if (b == 0) return a;
        return gcd (b, a % b);
    }

}
