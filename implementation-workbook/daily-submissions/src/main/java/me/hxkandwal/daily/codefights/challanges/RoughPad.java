package me.hxkandwal.daily.codefights.challanges;

import java.util.HashMap;
import java.util.Map;

public class RoughPad {

	public static void main(String[] args) {
		System.out.println(TwoFive(new int[] {1, 2}));
	}

	public static String TwoFive(int[] a) {
	    char[] dict = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ".toCharArray();
	    Map<Integer, String> dictionary = new HashMap<>();
	    
	    for (int idx = 0; idx < dict.length; idx ++)
	    	dictionary.put(idx, String.valueOf(dict[idx]));
	    
	    StringBuilder sb = new StringBuilder();
	    
	    for (int idx = 0; idx < a.length; idx ++) {
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
