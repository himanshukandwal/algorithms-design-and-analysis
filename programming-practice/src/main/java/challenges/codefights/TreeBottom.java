package challenges.codefights;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Tree Bottom
 *
 * You are given a recursive notation of a binary tree: each node of a tree is represented as a set of three elements:
 *      value of the node;
 *      left subtree;
 *      right subtree.
 *
 * So, a tree can be written as (value left_subtree right_subtree). It is guaranteed that 1 ≤ value ≤ 109. If a node doesn't exist then it is represented as an empty set: ().
 *
 * Your task is to obtain a list of nodes, that are the most distant from the tree root, in the order from left to right. In the notation of a node its value and subtrees are
 * separated by exactly one space character.
 *
 * Example
 *      For tree = "(2 (7 (2 () ()) (6 (5 () ()) (11 () ()))) (5 () (9 (4 () ()) ())))"
 *      the output should be treeBottom(tree) = [5, 11, 4].
 *
 * link: https://app.codesignal.com/skill-test/ptyXoxtZfrPSNRe5m
 *
 * @author Hxkandwal
 */
public class TreeBottom extends AbstractCustomTestRunner {

    int[] treeBottom(String tree) {
        if (tree.length() <= 2) return new int [0];
        Queue<String> queue = new LinkedList<>();
        queue.offer (tree);
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            list.clear();

            while (size -- > 0) {
                String n = queue.poll();
                n = n.substring(1, n.length() - 1);
                int num = 0, idx = 0;

                while (idx < n.length() && Character.isDigit(n.charAt(idx))) num = 10 * num + (n.charAt(idx ++) - '0');
                list.add (num);

                while (idx < n.length() && n.charAt(idx) == ' ') idx ++;

                // left
                int start = idx;
                idx ++;
                for(int count = 1; idx < n.length() && count != 0; idx ++) {
                    if (n.charAt(idx) == '(') count ++;
                    else if (n.charAt(idx) == ')') count --;
                }

                if (idx - start > 2) queue.offer (n.substring (start, idx));
                while (idx < n.length() && n.charAt(idx) == ' ') idx ++;

                // right
                start = idx;
                idx ++;
                for(int count = 1; idx < n.length() && count != 0; idx ++) {
                    if (n.charAt(idx) == '(') count ++;
                    else if (n.charAt(idx) == ')') count --;
                }

                if (idx - start > 2) queue.offer (n.substring (start, idx));
            }
        }
        int [] ans = new int [list.size()];
        for (int idx = 0; idx < list.size(); idx ++) ans [idx] = list.get (idx);
        return ans;
    }
}
