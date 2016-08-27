package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 345. Reverse Vowels of a String
 * 
 * Write a function that takes a string as input and reverse only the vowels of
 * a string.
 * 
 * Example 1: Given s = "hello", return "holle". Example 2: Given s =
 * "leetcode", return "leotcede".
 * 
 * @author Hxkandwal
 *
 */
@SuppressWarnings("serial")
public class ReverseVowelsOfString extends AbstractCustomTestRunner {
	
	public static ReverseVowelsOfString _instance = new ReverseVowelsOfString();
	
	private ReverseVowelsOfString() {}
	
	// method 1 : using collections.
	public String _reverseVowelsUsingCollection(String s) {
		if (s == null || s.isEmpty() || s.length() == 1) 
			return s;
		
		Set<Character> vowels = new HashSet<Character>() {{ add('a'); add('e'); add('i'); add('o'); add('u');
															add('A'); add('E'); add('I'); add('O'); add('U'); }};
		List<Integer> vowelIndices = new ArrayList<>();
		
		for (int idx = 0; idx < s.length(); idx ++) {
			if (vowels.contains(s.charAt(idx)))
				vowelIndices.add(idx);
		}
		
		char[] chArr = s.toCharArray();
		for (int idx = 0; idx < vowelIndices.size()/2; idx ++) {
			// swap
			char ch = chArr [vowelIndices.get(idx)];
			chArr [vowelIndices.get(idx)] = chArr [vowelIndices.get(vowelIndices.size() - idx -1)];
			chArr [vowelIndices.get(vowelIndices.size() - idx -1)] = ch;
		}
		
		return String.valueOf(chArr);
	}
	
	// method 2 : two index method (simple and sweet)
	public String _reverseVowelsIndexing(String s) {
		if (s == null || s.isEmpty() || s.length() == 1) 
			return s;
		
		Set<Character> vowels = new HashSet<Character>() {{ add('a'); add('e'); add('i'); add('o'); add('u');
															add('A'); add('E'); add('I'); add('O'); add('U'); }};
	 
	    char[] arr = s.toCharArray();
	    
	    // two pointers scanning from beginning and end of the array.
	    int i = 0; 
	    int j = s.length()-1;
	 
	    while(i < j){
	        if(!vowels.contains(arr[i])){
	            i++;
	            continue;
	        }
	 
	        if(!vowels.contains(arr[j])){
	            j--;
	            continue;
	        }
	 
	        char t = arr[i];
	        arr[i]=arr[j];
	        arr[j]=t;
	 
	        i++;
	        j--; 
	    }
	 
	    return new String(arr);
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("hello", "holle");
		_instance.runTest(null, null);
		_instance.runTest("", "");
		_instance.runTest("a", "a");
		_instance.runTest("b", "b");
		_instance.runTest("leetcode", "leotcede");
		_instance.runTest("aA", "Aa");
		
		System.out.println("ok!");
	}
	
	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers) 
			assertThat((String) answer).isEqualTo(expectedOutput);
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		String answer = null;
		
		try {
			answer = (String) method.invoke(_instance, externalVariables);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		
		return answer;
	}
	
}
