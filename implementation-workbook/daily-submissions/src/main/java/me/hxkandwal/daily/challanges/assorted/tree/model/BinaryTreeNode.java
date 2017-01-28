package me.hxkandwal.daily.challanges.assorted.tree.model;

/**
 * binary tree data structure. 
 * 
 * @author Hxkandwal
 */
public class BinaryTreeNode {
	
	private int value;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public BinaryTreeNode(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public BinaryTreeNode getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}

	public BinaryTreeNode getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}
	
}