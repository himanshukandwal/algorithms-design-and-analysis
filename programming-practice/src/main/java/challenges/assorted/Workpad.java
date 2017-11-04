package challenges.assorted;

import java.util.*;

public class Workpad {

	public static void main(String[] args) {
//		System.out.println(maxSubsequenceAfterSwap(new int[] { 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1 }, 3));
//		System.out.println(Arrays.toString(minimalOperations(new String[] { "aab", "abab", "abb", "abaaaba", "aaaa", "aaaaab" })));
		System.out.println(maxStockPrice2(new int [] { 5, 7 }));
		System.out.println(maxStockPrice2(new int [] { 5, 3, 2 }));
		System.out.println(maxStockPrice2(new int [] { 1, 2, 100 }));
		System.out.println(maxStockPrice2(new int [] { 1, 3, 1, 2 }));
		System.out.println(maxStockPrice2(new int [] { -1, 0, -1, 2 }));
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

	public static int [] minimalOperations (String [] words) {
		int [] ans = new int [words.length];
		if (words.length == 0) return ans;

		for (int idx = 0; idx < words.length; idx ++){
			String word = words [idx];
			int count = 0;
			for (int jdx = 0, start = 0; jdx < word.length(); jdx ++) {
				if (jdx + 1 == word.length() || word.charAt(jdx) != word.charAt(jdx + 1)) {
					if (start != jdx) count += (jdx - start + 1)/2;
					start = jdx + 1;
				}
			}
			ans [idx] = count;
		}
		return ans;
	}

	public static void main1 (String [] args) {
		Scanner sc = new Scanner(System.in);
		int tests = sc.nextInt();
		while (tests -- > 0) {
			int [] arr = new int [sc.nextInt()];
			for (int idx = 0; idx < arr.length; idx ++) arr [idx] = sc.nextInt();
			System.out.println(maxStockPrice2(arr));
		}
	}

	public static int maxStockPrice1 (int[] stocks) {
		if (stocks.length <= 1) return 0;
		int ans = 0;
		for (int idx = stocks.length - 2, maxIdx = stocks.length - 1; idx >= 0; idx --) {
			if (stocks [idx] < stocks [maxIdx]) ans += stocks [maxIdx] - stocks [idx];
			else maxIdx = idx;
		}
		return ans;
	}

	public static int maxStockPrice2 (int[] stocks) {
		
		int [][] dp = new int [stocks.length + 1][stocks.length + 1];
		for (int i = 0; i < stocks.length; i ++) {
			for (int j = i; j <  stocks.length; j ++) {
				if (stocks [j] > stocks [i] && dp [i + 1][j] == 0) {
					dp [i + 1][j + 1] = Math.max (dp [i + 1] [j], dp [i][j + 1] + Math.max (0, stocks [j] - stocks [i]));
				}
				dp [i + 1][j + 1] = Math.max (dp [i + 1] [j], dp [i][j + 1] + Math.max (0, stocks [j] - stocks [i]));
			}
		}
		return dp [stocks.length][stocks.length];
	}
}
