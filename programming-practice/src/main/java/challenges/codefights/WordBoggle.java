package challenges.codefights;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Word Boggle
 *
 * Boggle is a popular word game in which players attempt to find words in sequences of adjacent letters on a rectangular board.
 *
 * Given a two-dimensional array board that represents the character cells of the Boggle board and an array of unique strings words, find all the possible words
 * from words that can be formed on the board.
 *
 * Note that in Boggle when you're finding a word, you can move from a cell to any of its 8 neighbors, but you can't use the same cell twice in one word.
 *
 * Example:
 *  For board = [
 *                  ['R', 'L', 'D'],
 *                  ['U', 'O', 'E'],
 *                  ['C', 'S', 'O']
 *              ]
 *  and words = ["CODE", "SOLO", "RULES", "COOL"], the output should be wordBoggle(board, words) = ["CODE", "RULES"].
 *
 * link: https://app.codesignal.com/skill-test/v3uf4PGocp2CH62nn
 *
 * @author Hxkandwal
 */
public class WordBoggle extends AbstractCustomTestRunner {

    String[] wordBoggle(char[][] board, String[] words) {
        Node root = new Node (' ');
        for (String w : words) {
            Node n = root;
            for (char c : w.toCharArray()) {
                if (n.children [c] == null) n.children [c] = new Node (c);
                n = n.children [c];
            }
            n.terminal = true;
            n.str = w;
        }

        Set<String> ans = new HashSet<>();
        for (int r = 0; r < board.length; r ++)
            for (int c = 0; c < board [0].length; c ++)
                dfs (board, r, c, root, ans);

        String [] ret = ans.toArray(new String [0]);
        Arrays.sort(ret, (a, b) -> a.compareTo(b));
        return ret;
    }

    int [] rdir = { 0, 1, 0, -1, 1, -1, 1, -1 };
    int [] cdir = { 1, 0, -1, 0, 1, 1, -1, -1 };

    public void dfs (char[][] b, int r, int c, Node n, Set<String> ans) {
        if (r < 0 || r >= b.length || c < 0 || c >= b [0].length || b [r][c] == '*' || n == null || n.children [b [r][c]] == null)
            return;

        char v = b [r][c];
        Node curr = n.children [b [r][c]];
        b [r][c] = '*';

        if (curr.terminal) ans.add (curr.str);
        for (int idx = 0; idx < 8; idx ++)
            dfs (b, r + rdir[idx], c + cdir[idx], curr, ans);
        b [r][c] = v;
    }


    class Node {
        char c;
        boolean terminal;
        String str;
        Node [] children = new Node [256];

        public Node (char c) { this.c = c; }
    }
}
