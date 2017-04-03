package me.hxkandwal.daily.challanges.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 535. Encode and Decode TinyURL
 * 
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl 
 * and it returns a short URL such as http://tinyurl.com/4e9iAk.
 * 
 * Design the encode and decode methods for the TinyURL service. 
 * There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded 
 * to a tiny URL and the tiny URL can be decoded to the original URL.
 * 
 * @author Hxkandwal
 */
public class EncodeAndDecodeTinyURL extends AbstractCustomTestRunner {
	
	/**  
     * Issues;
     * Using increasing numbers as codes like that is simple but has some disadvantages, which the below solution fixes:
     * 		a) If I'm asked to encode the same long URL several times, it will get several entries. That wastes codes and memory.
     * 		b) People can find out how many URLs have already been encoded. Not sure I want them to know.
     * 		c) People might try to get special numbers by spamming me with repeated requests shortly before their desired number comes up.
     * 		d) Only using digits means the codes can grow unnecessarily large. Only offers a million codes with length 6 (or smaller). 
     * 			Using six digits or lower or upper case letters would offer (10+26*2)6 = 56,800,235,584 codes with length 6.
     */
	public class CodecUsingList {
		List<String> storedUrls = new ArrayList<>();
	    
	    // Encodes a URL to a shortened URL.
	    public String encode(String longUrl) {
	        storedUrls.add (longUrl);
	        return String.valueOf (storedUrls.size() - 1);
	    }
	
	    // Decodes a shortened URL to its original URL.
	    public String decode(String shortUrl) {
	        return storedUrls.get (Integer.valueOf(shortUrl));
	    }
	}
    
    public class CodecUsingMaps {
    	String input = "1234567890abcdefghijklmnopqrstuvwxyz";
    	
    	Map<String, String> url2Code = new HashMap<>();
    	Map<String, String> code2Url = new HashMap<>();
    	
    	// Encodes a URL to a shortened URL.
	    public String encode(String longUrl) {
	    	if (url2Code.containsKey(longUrl)) return url2Code.get(longUrl);
	    	
	    	StringBuilder sb = new StringBuilder();
	    	while (true) {
	    		for (int idx = 0; idx < 6; idx ++) sb.append(input.charAt(new Random().nextInt(input.length())));
	    		if (!code2Url.containsKey(sb.toString())) break;
	    	}
	    	
	    	code2Url.put(sb.toString(), longUrl);
	    	url2Code.put(longUrl, sb.toString());
	        return sb.toString();
	    }
	
	    // Decodes a shortened URL to its original URL.
	    public String decode(String shortUrl) {
	        return code2Url.get(shortUrl);
	    }
    }

}
