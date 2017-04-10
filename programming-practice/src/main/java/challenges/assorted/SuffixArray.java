package challenges.assorted;

import java.util.Arrays;

import challenges.AbstractCustomTestRunner;

public class SuffixArray extends AbstractCustomTestRunner {

	// suffix array in O(n*log^2(n))
	public static Integer[] suffixArray(String s) {
		Integer [] index = new Integer [s.length()];
		int [] rank = new int [s.length()];
		for (int idx = 0; idx < s.length(); idx ++) {
			index [idx] = idx;
			rank [idx] = s.charAt(idx) - 'a';
		}
		
		for (int loop = 1; loop < s.length(); loop *= 2) {
			long [] rank2 = new long [s.length()];
			for (int idx = 0; idx < s.length(); idx ++) 
				rank2 [idx] = ((long) rank [idx] << 32 | (idx + loop < s.length() ? s.charAt(idx + loop) - 'a' : 0));
			
			Arrays.sort(index, (a, b) -> Long.compare(rank2 [a], rank2 [b]));
			
			for (int idx = 0; idx < s.length(); idx ++) 
				rank [index [idx]] = (idx > 0 && rank2 [index [idx]] == rank2 [index [idx - 1]] ? rank [index [idx]] : idx);
		}
		
		return index;
	}

	// random test
	public static void main(String[] args) {
		String s = "banana";
		Integer [] ans = suffixArray(s);
 
		String res = "";
		int len = 0;
		for (int idx = 0; idx < ans.length - 1; idx ++) {
			int i1 = ans [idx], i2 = ans [idx + 1], llen = 0;
			while (i1 < s.length() && i2 < s.length() && s.charAt(i1) == s.charAt(i2)) { llen ++; i1 ++; i2 ++; }
			
			if (llen > len) {
				len = llen;
				res = s.substring(ans [idx], ans [idx] + llen);
			}
		}
		
		System.out.println(res);
	}
	
}
