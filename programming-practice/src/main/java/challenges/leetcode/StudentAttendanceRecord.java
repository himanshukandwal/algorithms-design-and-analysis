package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 551. Student Attendance Record I
 * 
 * You are given a string representing an attendance record for a student. The record only contains the following three 
 * characters:
 * 	'A' : Absent.
 * 	'L' : Late.
 * 	'P' : Present.
 * 
 * A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 
 * 'L' (late).
 * 
 * You need to return whether the student could be rewarded according to his attendance record.
 * Example 1:
 * 		Input: "PPALLP" Output: True
 * 
 * Example 2:
 * 		Input: "PPALLL"	Output: False
 * 
 * @author Hxkandwal
 */
public class StudentAttendanceRecord extends AbstractCustomTestRunner {
	
	private static StudentAttendanceRecord _instance = new StudentAttendanceRecord();

	public boolean checkRecord(String s) {
       int acount = 0, lcount = 0, prevL = -1;
       for (int idx = 0; idx < s.length(); idx ++) {
           char c = s.charAt (idx);
           if (c == 'A') acount ++;
           else if (c == 'L') {
               if (idx > 0 && prevL == idx - 1) lcount ++;
               prevL = idx;
           } else { prevL = -1; lcount = 0; }
           if (acount > 1 || lcount > 1) return false;
       }
       return true;
    }
    
    // driver method
 	public static void main(String[] args) {
 		_instance.runTest("PPALLP", true);
 		_instance.runTest("PPALLL", false);
 		_instance.runTest("LLPPALL", false);
 	}

 	public void runTest(final String s, final boolean expectedOutput) {
 		List<Object> answers = runAll(getClass(), new Object[] { s });

 		for (Object answer : answers)
 			assertThat((Boolean) answer).isEqualTo(expectedOutput);

 		System.out.println("ok!");
 	} 	
}
