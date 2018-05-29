package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 744. Find Smallest Letter Greater Than Target
 *
 * Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is
 * larger than the given target.
 *
 * Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
 *
 * Examples:
 *
 *  Input:
 *          letters = ["c", "f", "j"]
 *          target = "a"
 *          Output: "c"
 *
 *  Input:
 *          letters = ["c", "f", "j"]
 *          target = "c"
 *          Output: "f"
 *
 *  Input:
 *          letters = ["c", "f", "j"]
 *          target = "d"
 *          Output: "f"
 *
 *  Input:
 *          letters = ["c", "f", "j"]
 *          target = "g"
 *          Output: "j"
 *
 *  Input:
 *          letters = ["c", "f", "j"]
 *          target = "j"
 *          Output: "c"
 *
 *  Input:
 *          letters = ["c", "f", "j"]
 *          target = "k"
 *          Output: "c"
 *
 * Note:
 *
 *  letters has a length in range [2, 10000].
 *  letters consists of lowercase letters, and contains at least 2 unique letters.
 *  target is a lowercase letter.
 *
 *  @author Hxkandwal
 */
public class FindSmallestLetterGreaterThanTarget extends AbstractCustomTestRunner {

    public char nextGreatestLetterBetter(char[] letters, char target) {
        int l = 0, r = letters.length - 1;
        while (l < r) {
            int m = l + (r - l)/2;

            if (letters [m] > target)  r = m;
            else l = m + 1;
        }
        return letters [l] <= target ? letters [0] : letters [l];
    }

    // O(n) solution
    public char nextGreatestLetter(char[] letters, char target) {
        for (char c: letters)
            if (c > target) return c;
        return letters[0];
    }

}
