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
		_instance.runTest(new int [][] {{0,1,0,1},
										{0,1,0,1},
										{0,1,0,1},
										{0,1,0,1}}, 2);
		
	}

	public void runTest(final int[][] array, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}
