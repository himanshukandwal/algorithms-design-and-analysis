package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 623. Add One Row to Tree
 * 
 * Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the given 
 * depth d. The root node is at depth 1.
 * 
 * The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, create two tree nodes 
 * with value v as N's left subtree root and right subtree root. And N's original left subtree should be the left subtree of 
 * the new left subtree root, its original right subtree should be the right subtree of the new right subtree root. If depth 
 * d is 1 that means there is no depth d-1 at all, then create a tree node with value v as the new root of the whole original 
 * tree, and the original tree is the new root's left subtree.
 * 
 * Example 1:
 * 		Input:	A binary tree as following:
 * 					
 * 		       4
 * 		     /   \
 * 		    2     6
 * 		   / \   / 
 * 		  3   1 5   
 * 		
 * 		v = 1
 * 		d = 2
 * 
 * 		Output:
 * 
 * 		       4
 * 		      / \
 * 		     1   1
 * 		    /     \
 * 		   2       6
 * 		  / \     / 
 * 		 3   1   5   
 * 
 * Example 2:
 * 		Input:	A binary tree as following:
 * 
 * 		      4
 * 		     /   
 * 		    2    
 * 		   / \   
 * 		  3   1    
 * 		
 * 		v = 1
 * 		d = 3
 * 
 * 		Output:
 * 
 * 		      4
 * 		     /   
 * 		    2
 * 		   / \    
 * 		  1   1
 * 		 /     \  
 * 		3       1
 * 
 * Note:
 * 	-	The given d is in range [1, maximum depth of the given tree + 1].
 * 	-	The given binary tree has at least one tree node.
 * 
 * @author Hxkandwal
 */
public class AddOneRowToTree extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left, right;
		public TreeNode(int x) { val = x; }
	}
	
	public TreeNode addOneRow(TreeNode root, int v, int d) {
        d --;
        if (d == 0) {
            TreeNode node = new TreeNode (v);
            node.left = root;
            return node;
        } else {
            addOneRowInner (root, null, v, d);
            return root;
        }
    }
    
    private void addOneRowInner (TreeNode node, TreeNode parent, int v, int d) {
        if (d == 0) {
            TreeNode n = new TreeNode (v);
            if (parent.left == node) {
                parent.left = n;
                n.left = node;
            } else {
                parent.right = n;
                n.right = node;
            }
        } else if (node != null) {
            addOneRowInner (node.left, node, v, d - 1);
            addOneRowInner (node.right, node, v, d - 1);
        }
    }
}
