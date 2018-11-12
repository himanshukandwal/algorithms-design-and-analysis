package challenges.hackerrank;

/**
 * Binary Search Tree : Lowest Common Ancestor
 *
 * link : https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor/problem
 */
public class BSTLowestCommonAncestor {

    class Node {
        int data;
        Node left, right;
    }

    public Node lca(Node root, int v1, int v2) {
        if (root == null) return null;
        return (root.data > Math.max(v1, v2)) ?
                lca (root.left, v1, v2) :
                (root.data < Math.min(v1, v2) ? lca (root.right, v1, v2) : root);
    }
}
