package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 117. Populating Next Right Pointers in Each Node II
 * 
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * 
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * 
 * Note:
 * 		You may only use constant extra space.
 * 
 * For example,
 * 	 Given the following binary tree,
 *   
 *          1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 *     
 * 	 After calling your function, the tree should look like:
 * 
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \    \
 *     4-> 5 -> 7 -> NULL
 * 
 * @author Hxkandwal
 */
public class PopulatingNextRightPointersInEachNodeII extends AbstractCustomTestRunner {
	
	private static PopulatingNextRightPointersInEachNodeII _instance = new PopulatingNextRightPointersInEachNodeII();
	
	private PopulatingNextRightPointersInEachNodeII() {}

	public static class TreeLinkNode {
		 int val;
		 TreeLinkNode left, right, next;
		 
		 public TreeLinkNode(int x) { val = x; }
		 
		 @Override
		public String toString() {
			return "(" + val + ")";
		}
	}
	
	public TreeLinkNode _connect(TreeLinkNode root) {
		TreeLinkNode nextHead = null, head = root;
        while (head != null) {
            TreeLinkNode paired = null; boolean isFirst = true;
            for (TreeLinkNode node = head; node != null; node = node.next) {
                if (node.left != null) { 
                    if (nextHead == null)  nextHead = node.left;
                    if (isFirst) { paired = node.left;  isFirst = !isFirst; }
                    else { paired.next = node.left; paired = node.left; }
                }
                if (node.right != null) { 
                    if (nextHead == null)  nextHead = node.right;
                    if (isFirst) { paired = node.right;  isFirst = !isFirst; }
                    else { paired.next = node.right; paired = node.right; }
                }
            }
            head = nextHead;
            nextHead = null;
        }
        
        return root;
    }	
	
	// driver method
	public static void main(String[] args) {
		TreeLinkNode node = new TreeLinkNode(1);
		node.left = new TreeLinkNode(2);
		node.right = new TreeLinkNode(3);
		
		_instance.runTest(node, node);
	}

	public void runTest(final TreeLinkNode node, final TreeLinkNode expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { node });

		for (Object answer : answers)
			assertThat((TreeLinkNode) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
}
