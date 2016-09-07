package me.hxkandwal.daily.challanges.personal;

import java.util.Arrays;

public class TargetSum {

	// make target sum from array, O(n)
	public static void main(String[] args) {
		
	}
	
	public static boolean checkPossible(int[] batteryOne, int[] batteryTwo, int sum) {
		Arrays.sort(batteryOne);
		Arrays.sort(batteryTwo);

		int len1 = batteryOne.length;
		int len2 = batteryTwo.length;
		
		if (batteryOne [len1 - 1] + batteryTwo [len2 - 1] < sum)
			return false;

		int start = 0;
		int end = len1 -1;
		
		while (start < end) {
			int mid = (start + end) >>> 1;
			
		}
		
		return false;
	}

}
