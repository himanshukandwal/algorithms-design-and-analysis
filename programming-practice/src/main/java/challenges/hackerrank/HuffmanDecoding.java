package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

/**
 * Huffman Decoding
 *
 * link: https://www.hackerrank.com/challenges/tree-huffman-decoding/problem
 */
public class HuffmanDecoding extends AbstractCustomTestRunner {

    public class Node implements Comparable<Node> {
        public  int frequency;
        public  char data;
        public  Node left, right;

        public Node(int freq) {
            frequency = freq;
        }

        // compares on the frequency
        public int compareTo(Node tree) {
            return frequency - tree.frequency;
        }
    }

    void decode(String s, Node root) {
        int idx = 0;
        while (idx < s.length()) idx = dfs (root, s, idx);
        System.out.println();
    }

    private int dfs (Node n, String s, int i) {
        if (n == null) return i;
        if (n.left == null && n.right == null) {
            System.out.print(n.data);
            return i;
        } else return dfs (s.charAt(i) == '0' ? n.left : n.right, s, i + 1);
    }
}
