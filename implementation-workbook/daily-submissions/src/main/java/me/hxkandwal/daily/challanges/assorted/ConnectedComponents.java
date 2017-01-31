package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * ConnectedComponents
 * 
 * find count of connected components in a graph.
 * 
 * @author Hxkandwal
 *
 */
public class ConnectedComponents extends AbstractCustomTestRunner {

	private static ConnectedComponents _instance = new ConnectedComponents();
	
	private ConnectedComponents() {}
	
	public static int _findConnectedComponents(int[][] matrix) {
		
		return 0;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[][] { new int[] { 1, 2, 3, 4 }, new int[] { 4, 5, 6, 8 }}, 0, 
						  new int[][] { new int[] { 4, 8 }, new int[] { 3, 6 },
										new int[] { 2, 5 }, new int[] { 1, 4 }});
		
		_instance.runTest(new int[][] { new int[] { 1, 2, 3, 4 }, new int[] { 4, 5, 6, 8 }}, 1, 
						  new int[][] { new int[] { 4, 1 }, new int[] { 5, 2 },
										new int[] { 6, 3 }, new int[] { 8, 4 }});
	}

	public void runTest(final int[][] array, final int flag, final int[][] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array, flag });

		for (Object answer : answers) {
			int[][] actualAnswer = (int[][]) answer;
			
			for (int idx = 0; idx < actualAnswer.length; idx ++)
				assertThat(actualAnswer[idx]).isEqualTo(expectedOutput[idx]);
		}

		System.out.println("ok!");
	}	
	
}
