package challenges.assorted;

import java.util.LinkedList;
import java.util.Queue;

public class Workpad {

	public static void main(String[] args) {
		System.out.println(-1 % 20);
		System.out.println(maxSubsequenceAfterSwap(new int[] { 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1 }, 3));
	}
	
	public static int maxSubsequenceAfterSwap(int[] arr, int k) {
		int count = 0;
		int startIndex = 0;
		int maxLen = 0, maxIndex = 0;
		Queue<Integer> positionQ = new LinkedList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				if (count < k) {
					positionQ.add(i);
					count++;
				} else {
					if (i - startIndex > maxLen) {
						maxLen = i - startIndex;
						maxIndex = startIndex;
					}
					startIndex = positionQ.remove() + 1;
					positionQ.add(i);
				}
			}
		}

		System.out.println("Max len=" + maxLen + ", starting index=" + maxIndex);
		return maxLen;
	}
}
