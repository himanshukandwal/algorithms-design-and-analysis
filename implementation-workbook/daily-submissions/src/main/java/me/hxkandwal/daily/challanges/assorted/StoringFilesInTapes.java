package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Amazon wants to implement a new backup system, in which files are stored into data tapes. 
 * This new system must follow the following 2 rules:
 * 
 * a. Never place more than two files on the same tape.
 * b. Files cannot be split across multiple tapes.
 * 
 * It's guaranteed that all tapes have the same size and that they will always be able to store the largest 
 * file. Every time this process is executed, we already know the size of each file, and the capacity of the 
 * tapes. Having that in mind, we want to design a system that is able to count how many tapes will be required 
 * to store the backup in the most efficient way.
 * 
 * The parameter of your function will be a structure that will contain the file sizes and the capacity of the 
 * tapes. You must return the minimum amount of tapes required to store the files.
 * 
 * Example:
 * 		Input: Tape Size = 100; Files: 70, 10, 20
 * 		Output: 2
 * 
 * @author Hxkandwal
 *
 */
public class StoringFilesInTapes extends AbstractCustomTestRunner {
	
	private static final StoringFilesInTapes _instance = new StoringFilesInTapes();
	
	private StoringFilesInTapes() {}
	
	// driver method
	public static void main(String[] args) {
		
		int[] array = new int[] { 1, 2, 3, 3, 4, 5, 6, 6 };
		
		int duplicateStartIdx = -1;
		int uniqueStartIdx = 0;
		
		for (int idx = 0; idx < array.length - 1; idx ++) {
			if (array [idx] == idx + 1) {
				// initialization
				if (duplicateStartIdx == -1)
					uniqueStartIdx
			}
		}
		
//		_instance.runTest(new int[] { 4, 2, 5, 5, 6, 1, 4 }, "4:2,2:1,5:2,1:1,,,6:1");
	}

	public void runTest(final int[] input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
