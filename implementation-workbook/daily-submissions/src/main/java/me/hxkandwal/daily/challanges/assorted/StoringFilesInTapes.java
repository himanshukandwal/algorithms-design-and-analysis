package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
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
	
	public static int _tapeCount(int[] array, int tapeSize) {
		Arrays.sort(array);
		boolean[] usedFilesInfo = new boolean[array.length];
		int tapes = 0;
		int usedFiles = 0;
		
		while (array.length - usedFiles > 1) {
			int low = 0;
			int high = array.length - 1;
			
			while (low <= high) {
				if (!usedFilesInfo [low] && !usedFilesInfo [high]) {
					if (array [low] + array [high] <= tapeSize) {
						usedFilesInfo [low] = true;
						usedFilesInfo [high] = true;
						tapes ++;
						usedFiles += (low == high ? 1 : 2);
						break;
					} else
						high --;	
				} else if (usedFilesInfo [low])
					low ++;
				else
					high --;
			}
		}
		
		if (array.length - usedFiles == 1) 
			tapes ++;
		
		return tapes;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 70, 10, 20 }, 100, 2);
		_instance.runTest(new int[] { 70, 10, 20, 30 }, 70, 3);
	}

	public void runTest(final int[] input, final int tapeSize, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input, tapeSize });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
