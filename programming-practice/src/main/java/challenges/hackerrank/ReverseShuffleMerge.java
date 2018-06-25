package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Reverse Shuffle Merge
 *
 * Sample Input 0   :  eggegg
 * Sample Output 0  :  egg
 * Explanation 0    :
 *      Split "eggegg" into strings of like character counts: "egg", "egg"
 *
 *      reverse("egg") = "gge"
 *      shuffle("egg") can be "egg"
 *
 *      "eggegg" belongs to the merge of ("gge", "egg")
 *
 *      The merge is: gge.
 *      'egg' < 'gge'
 *
 * https://www.hackerrank.com/challenges/reverse-shuffle-merge/problem
 *
 * @author hxkandwal
 */
public class ReverseShuffleMerge extends AbstractCustomTestRunner {

    private static ReverseShuffleMerge _instance = new ReverseShuffleMerge();

    /* Consider S = "aabb", lexicographically smallest string inside S is "ab". But for A="ab" you can never obtain this S.
     * Hence, the answer should be A="ba".
     *
     * Since reverse(A) is a subsequence of S, hence, A must be subsequence of reverse(S). Thus, to find A, either reverse the array S
     * and move normally, or traverse S in reverse direction (right to left)
     *
     * The step by step procedure is as follows :
     *
     * 1) Used an array freq[26] to store frequency of each character (a to z) in the original string S
     * 2) Characters of A will be known using this frequency array, since, each character will appear in A exactly 1/2 times of the frequency array (or S).
     *    This is because S was formed using A twice, once in form of shuffle(A) and again as reverse(A)
     * 3) Now, size of A (n/2) and characters already known, try building A (getting order of characters) using the original string S
     * 4) To do that, understand the following : Since reverse(A) is a subsequence of S, hence, A must be subsequence of reverse(S). Thus, to find A, either
     *    reverse the array S and move normally, or traverse S in reverse direction (right to left)
     * 5) I selected traversing S in reverse direction from right to left, in order to gradually build A, character by character. For each position in S, a
     *    choice is to be made, whether or not to select the character for A (this is where frequency array will help !)
     * 6) To fulfill the condition of lexicographically smallest A, always try to select lexicographically smallest character. This can be done be leaving
     *    larger characters and selecting the smallest one seen till then, until its critical to select larger character
     * 7) In order to decide upon the criticality of selection, just understand that you can't leave out a character 'i' if freq[i] in A is same as the count
     *    of 'i' left to be seen in string S. You might need some array to keep track of this. To give an example : if 'x' appears 6 times in S, then A would
     *    have exactly 3'x'. Now while traversing S if you leave 'x' 3 times and select some smaller character instead (to form lexicographically smaller A),
     *    you cannot leave it 4th time, because there are only 3 'x' left to be seen in S and you need all of them.
     *
     * Once, you get all the characters required for A, print it as answer !
     */
    public String _reverseShuffleMerge(String st) {
        char[] s = st.toCharArray();
        int n = s.length;

        for (int p = 0, q = n - 1; p < q; p ++, q --) {
            char d = s[p]; s[p] = s[q]; s[q] = d;
        }

        int [] f = new int [26];
        for (int i = 0; i < n; i ++) f [s [i] - 'a'] ++;
        for (int i = 0; i < 26; i ++) f[i] /= 2;

        char [] A = new char[n/2];
        int [] rev = f.clone();

        int ap = 0, start = 0;

        outer: while (ap < n/2) {
            inner: for (int i = 0; i < 26; i ++) {
                int[] h = f.clone();

                for (int j = start; j < n && rev [i] > 0; j ++) {

                    if (s [j] == 'a' + i) {
                        A [ap ++] = s [j];
                        rev [i] --;
                        start = j + 1;
                        f = h.clone();
                        continue outer;
                    } else {
                        if (-- h [s [j] - 'a'] < 0) continue inner;
                    }
                }
            }
        }
        return new String(A);
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest("aabb", "ba");
        _instance.runTest("eggegg", "egg");
        _instance.runTest("bdabaceadaedaaaeaecdeadababdbeaeeacacaba", "aaaaaabaaceededecbdb");
        _instance.runTest("djjcddjggbiigjhfghehhbgdigjicafgjcehhfgifadihiajgciagicdahcbajjbhifjiaajigdgdfhdiijjgaiejgegbbiigida", "aaaaabccigicgjihidfiejfijgidgbhhehgfhjgiibggjddjjd");
    }

    public void runTest(final String input, final String expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input });

        for (Object answer : answers)
            assertThat((String) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
