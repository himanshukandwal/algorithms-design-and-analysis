package me.hxkandwal.daily.challanges.codefights;

import static com.google.common.truth.Truth.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Base 32 is often chosen to represent binary data in a compressed form that can be easily read and handled by humans (for example, product keys or activation codes). 
 * It's more compact than Base 10 and easier to comprehend than Base 16 (hexadecimal) or Base 64.
 * 
 * To generate Base 32, the input data is processed as a bit stream. Output values for each group of five bits are produced until all input bits are processed. 
 * The last 5-bit group is extended with '0' bits if it is incomplete.
 * 
 * Finally, each 5-bit value is mapped into a dictionary of 32 alphanumeric characters to generate a human readable string.
 * 
 * The dictionary omits ambiguous characters such as '0', '1', 'O', and 'I', so only the following characters remain: d = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ".
 * 
 * Your task is to generate Base 32 from the given input data a.
 * 
 * For a = [1, 2], the output should be TwoFive(a) = "3J22".
 * 
 * @author hxkandwal
 *
 */
public class TwoFive {

	public static void main(String[] args) {
		assertThat(twoFive(new int[] {1, 2})).isEqualTo("3J22");
		
		System.out.println("ok !");
	}

	public static String twoFive(int[] a) {
	    char[] dict = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ".toCharArray();
	    Map<Integer, String> dictionary = new HashMap<>();
	    
	    for (int idx = 0; idx < dict.length; idx ++)
	    	dictionary.put(idx, String.valueOf(dict[idx]));
	    
	    StringBuilder sb = new StringBuilder();
	    
	    for (int idx = 0; idx < a.length; idx ++) {
	    	// cool trick to append computed number of zeros in front of a number.
	        String binaryRep = String.format("%" + Integer.toString(8) + "s", Integer.toString(a[idx], 2)).replace(" ","0");
	        StringBuilder innerSb = new StringBuilder(binaryRep);
	        innerSb = innerSb.reverse();
	        sb.append(innerSb);
	    }
	    
	    while (sb.length() % 5 != 0)
	    	sb.append("0");
	    
	    sb = sb.reverse();
	    
	    StringBuilder answer = new StringBuilder();
	    
	    int idx = 0;
	    while ((idx + 5) <= sb.length()) {
	    	answer.append(dictionary.get(Integer.parseInt(sb.substring(idx, idx + 5), 2)));
	    	idx += 5;
	    }
	    
	    return answer.reverse().toString();
	}
	
}
