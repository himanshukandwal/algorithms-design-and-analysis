package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import challenges.AbstractCustomTestRunner;

/**
 * We have a system that records scores. We care about how many times we see the same score, and we want to 
 * maintain a rough ordering. We also want to send this information over the wire so that it can be collated 
 * with other results. As such we have decided to represent the stream of scores, and the count of how many 
 * times we see the same score, as an unbalanced binary search tree.
 * 
 * Your job is to write a method that will take a stream of integer scores, and put them into a tree while 
 * counting the number of times each score is seen. The first score from the given list should occupy the 
 * root node. Then you need to traverse the tree breadth-first to generate a string representation of the tree. 
 * 
 * Scores are to be inserted into the tree in the order that they are given.
 *
 * For example, if you were given the stream of scores: [4, 2, 5, 5, 6, 1, 4].
 * 
 * That would result in the tree with the following structure where each node is represented as score:count.
 * 
 * link: https://discuss.leetcode.com/topic/9/score-gathering
 * 
 * @author Hxkandwal
 *
 */
public class ScoreGathering extends AbstractCustomTestRunner {

	private static ScoreGathering _instance = new ScoreGathering();

	private ScoreGathering() {}

	private static class Node {
		private Node left;
		private Node right;
		private final int value;
		private int frequency = 1;
		
		public Node(int value) {
			this.value = value;
		}
		
		// DS operations
		public void findAndUpdate(int value) {
			if (value > this.value) {
				if (right == null)
					right = new Node(value);
				else
					right.findAndUpdate(value);
			} else if (value < this.value) {
				if (left == null)
					left = new Node(value);
				else
					left.findAndUpdate(value);
			} else 
				this.frequency ++;
		}
		
	}
	
	public static String _gatherScore(int[] scores) {
		if (scores == null || scores.length == 0)
			return null;
		
		Node _root = new Node(scores[0]);
		for (int idx = 1; idx < scores.length; idx++)
			_root.findAndUpdate(scores [idx]);
		
		return printLevelOrder(_root);
	}
	
	// BFS
	private static String printLevelOrder(Node node) {
		Queue<Node> queue = new LinkedList<>();
		
		// initialization
		queue.add(node);
		int size = 1;
		StringBuilder builder = new StringBuilder();
		boolean foundData = true, isFirst = true;
		
		// processing
		while (foundData) {
			int localSize = size;
			size = 0;
			foundData = false;
			
			// level printing (generic, else we could have done 2^n thing)
			while (localSize -- != 0) {
				Node workingNode = queue.poll();
				
				if (!isFirst)
					builder.append(",");
				else
					isFirst = false;
				
				if (workingNode != null) {
					builder.append(workingNode.value + ":" + workingNode.frequency);
					queue.add(workingNode.left);
					queue.add(workingNode.right);
					foundData = (workingNode.left != null || workingNode.right != null ? true : false);
				} else {
					queue.add(null);
					queue.add(null);
				}
				
				size += 2;
			}
		}
		
		return builder.toString();
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 4, 2, 5, 5, 6, 1, 4 }, "4:2,2:1,5:2,1:1,,,6:1");
	}

	public void runTest(final int[] input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
