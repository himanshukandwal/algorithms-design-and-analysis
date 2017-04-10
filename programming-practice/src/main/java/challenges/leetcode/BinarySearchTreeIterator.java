package challenges.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 173. Binary Search Tree Iterator
 * 
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * 
 * Calling next() will return the next smallest number in the BST.
 * 
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * 
 * @author Hxkandwal
 */
public class BinarySearchTreeIterator extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	Stack<TreeNode> bstk = new Stack<>(); 
    
    public BinarySearchTreeIterator(TreeNode root) {
        get (root);
    }

    private void get(TreeNode node) {
        if (node == null) return;
        bstk.push (node);
        get (node.left);
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !bstk.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode smallestNode = bstk.pop();
        get (smallestNode.right);
        return smallestNode.val;
    }
	
}

class BinarySearchTreeIteratorWholeCache extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	List<Integer> ordered;
    int index = -1;
    
    public BinarySearchTreeIteratorWholeCache (TreeNode root) {
        ordered = new ArrayList<>();
        getInorder (root, ordered);
    }
    
    private void getInorder(TreeNode node, List<Integer> collector) {
        if (node == null) return;
        getInorder (node.left, collector);
        collector.add (node.val);
        getInorder (node.right, collector);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return (index + 1 < ordered.size());
    }

    /** @return the next smallest number */
    public int next() {
        return ordered.get (++ index);
    }
	
}

