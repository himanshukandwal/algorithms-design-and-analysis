package me.hxkandwal.daily.challanges.leetcode.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Line reconstruction by height
 * 
 * Suppose you have a line of n people in which the k-th person is described by a pair (h,t) , where h is the height of the k-th person and 
 * t is the number of people in front of k who have a height greater or equal than h . Write an algorithm to reconstruct the line.
 * 
 * For example, if the line is composed by the following people:
 * 
 * 		[(7, 0),(4, 4),(7,1), (5, 0), (6,1), (5, 2)]
 * 
 * The original line should be: 
 * 		
 * 		[(5,0), (7,0), (5,2), (6,1), (4,4),(7,1)]
 * 
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class LineReconstructionByHeight extends AbstractCustomTestRunner {
	
	private static LineReconstructionByHeight _instance = new LineReconstructionByHeight();
	
	private LineReconstructionByHeight() {}
	
	public List<int[]> _getOrder (int[][] items) {
		List<int[]> answer = new ArrayList<>();
		
		Arrays.sort(items, new Comparator<int[]> () {
			public int compare(int[] o1, int[] o2) {
				if (o1 [0] == o2 [0]) return o1 [1] - o2 [1]; 
				return o2 [0] - o1 [0];
			}
		});
		
		Stack<int []> stack = new Stack<>();

		for (int idx = 0; idx < items.length; idx ++) {
			if (idx > 0) {
				int lowRequirement = items [idx][1];
				if (lowRequirement < answer.size())
					while (lowRequirement < answer.size()) stack.push(answer.remove(answer.size() - 1));
				else if (lowRequirement > answer.size())
					if (!stack.isEmpty()) answer.add(stack.pop());
			}
			answer.add(items [idx]);
		}
		
		return answer;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[][] { new int[] { 7, 0 }, new int[] { 4, 4 }, new int[] { 7, 1 }, 
										new int[] { 5, 0 }, new int[] { 6, 1 }, new int[] { 5, 2 } },
				
						  new ArrayList() {{ add(new int[] { 5, 0 }); add(new int[] { 7, 0 }); add(new int[] { 5, 2 });  
						  					 add (new int[] { 6, 1 }); add(new int[] { 4, 4 }); add(new int[] { 7, 1 }); }});
	}

	public void runTest(final int[][] items, final List<int []> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { items });

		for (Object answer : answers) {
			List<int[]> responses =  (List<int []>) answer;
			for (int idx = 0; idx < responses.size(); idx ++)  
				assertThat(responses.get(idx)).isEqualTo(expectedOutput.get(idx));
		}
		
		System.out.println("ok!");
	}
	
}
