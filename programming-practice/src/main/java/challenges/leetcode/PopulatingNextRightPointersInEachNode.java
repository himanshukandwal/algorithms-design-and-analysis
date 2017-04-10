package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 116. Populating Next Right Pointers in Each Node
 * 
 * Given a binary tree
 * 
 *     struct TreeLinkNode {
 *       TreeLinkNode *left;
 *       TreeLinkNode *right;
 *       TreeLinkNode *next;
 *     }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * 
 * Initially, all next pointers are set to NULL.
 * 
 * Note:
 * 
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 * 
 * For example,
 * 	Given the following perfect binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \  / \
 *     4  5  6  7
 *     
 * After calling your function, the tree should look like:
 * 
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \  / \
 *     4->5->6->7 -> NULL
 * 
 * @author Hxkandwal
 */
public class PopulatingNextRightPointersInEachNode extends AbstractCustomTestRunner {

	public class TreeLinkNode {
		 int val;
		 TreeLinkNode left, right, next;
		 TreeLinkNode(int x) { val = x; }
	}
	
	public void connect(TreeLinkNode root) {
        if (root == null) return;
        connect (root.left, root.right);
    }
    
    public void connect (TreeLinkNode left, TreeLinkNode right) {
        if (left == null) return;
        connect (left);
        connect (right);
        left.next = right;
        
        // parallel tree stitching
        while (left.right != null) { left.right.next = right.left; left = left.right; right = right.left; }
    }
    
}
