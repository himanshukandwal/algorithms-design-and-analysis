package me.hxkandwal.daily.challanges.assorted;

import java.util.Arrays;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

public class SuffixArray extends AbstractCustomTestRunner {

	// suffix array in O(n*log^2(n))
	public static Integer[] suffixArray(CharSequence s) {
		int n = s.length();
		Integer[] sa = new Integer[n];
		int[] rank = new int[n];
		
		for (int i = 0; i < n; i++) {
			sa[i] = i;
			rank[i] = s.charAt(i) - 'a';
		}
		
		for (int len = 1; len < n; len *= 2) {
			long[] rank2 = new long[n];
			for (int i = 0; i < n; i++)
				rank2[i] = ((long) rank[i] << 32) + (i + len < n ? rank[i + len] : 0);

			Arrays.sort(sa, (a, b) -> Long.compare(rank2[a], rank2[b]));

			for (int i = 0; i < n; i++)
				rank[sa[i]] = i > 0 && rank2[sa[i - 1]] == rank2[sa[i]] ? rank[sa[i - 1]] : i;
		}
		
		return sa;
	}

	// random test
	public static void main(String[] args) {
		Integer[] sa = suffixArray("banana");
		
	}
}
