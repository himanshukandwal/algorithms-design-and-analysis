package challenges.assorted.tree.model;

/**
 * binary tree data structure. 
 * 
 * @author Hxkandwal
 */
public class BinaryTree {
	
	private int value;
	private BinaryTree left;
	private BinaryTree right;

	public BinaryTree(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public BinaryTree getLeft() {
		return left;
	}

	public void setLeft(BinaryTree left) {
		this.left = left;
	}

	public BinaryTree getRight() {
		return right;
	}

	public void setRight(BinaryTree right) {
		this.right = right;
	}
	
	@Override
	public String toString() {
		return String.valueOf("[" + value + "]");
	}
	
}