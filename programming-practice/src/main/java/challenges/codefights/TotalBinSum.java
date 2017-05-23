package challenges.codefights;

import challenges.AbstractCustomTestRunner;

/**
 * Total Bin Sum
 * 
 * Given a number num in its binary representation, your task is to sum all the numbers in base 10 formed by 
 * the prefixes of num. More formally, sum up int(num[0, i]) for all possible i.
 * 
 * Since the answer can be very big, return it modulo 109 + 7.
 * 
 * Example:
 * 		For bin = "1001", the output should be totalBinSum(bin) = 16.
 * 		Here are all the prefixes:
 * 			1 base 2 = 1 base 10;
 * 			10 base 2 = 2 base 10;
 * 			100 base 2 = 4 base 10;
 * 			1001 base 2 = 9 base 10.
 * 
 * 		Thus, the answer is 1 + 2 + 4 + 9 = 16.
 * 
 * @author Hxkandwal
 */
public class TotalBinSum extends AbstractCustomTestRunner {

	public int totalBinSum(String num) {
	    int mod = 1000000007; 
	    long sum = 0, val = 0;
	    for (char ch : num.toCharArray()) {
	        val += (ch == '1' ? 1 : 0);
	        sum += val;
	        sum %= mod;
	        val *= 2;
	        val %= mod;
	    }
	    sum %= mod;
	    return (int) sum;
	}
	
}
