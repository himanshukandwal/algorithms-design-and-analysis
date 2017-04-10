package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class LargestCombinedWord extends AbstractCustomTestRunner {
	
	private static LargestCombinedWord _instance = new LargestCombinedWord();

	public String maxJoinedWord (String [] strs) {
		Set<String> set = new HashSet<>();
		for (String str : strs) set.add(str);
		
		String ans = null;
		for (String str : strs)
			for (String istr : strs)
				if (istr.startsWith(str) && set.contains(istr.substring(istr.indexOf(str) + str.length())))
					ans = (ans == null || str.length() > ans.length()) ? istr : ans; 
			
		return ans;
	}
	
	public String _maxJoinedWordBetter (String [] strs) {
		Map<Character, Map> trie = new HashMap<>();
		Set<String> set = new HashSet<>();
		for (String str : strs) {
			set.add(str);
			Map<Character, Map> traverser = trie;
			for (char ch : str.toCharArray()) {
				if (!traverser.containsKey(ch)) traverser.put(ch, new HashMap());
				traverser = traverser.get(ch);
			}
		}
		
		String ans = null;
		for (String str : strs) {
			Map<Character, Map> traverser = trie;
			for (char ch : str.toCharArray()) traverser = traverser.get(ch);
			
			if (traverser.size() > 0) {
				List<String> collector = new ArrayList();
				recursiveGenerator(collector, "", traverser);
				
				for (String istr : collector)
					if (set.contains(istr))
						ans = (ans == null || (str  + istr).length() > ans.length()) ? str  + istr : ans;
			}
		}
			
		return ans;
	}
	
	private void recursiveGenerator (List<String> collector, String build, Map<Character, Map> node) {
		if (node.size() == 0) collector.add(build);
		else for (Map.Entry<Character, Map> entry : node.entrySet()) 
			recursiveGenerator (collector, build + entry.getKey(), entry.getValue());
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new String [] { "john", "johnabraham", "abraham" }, "johnabraham");
		_instance.runTest(new String [] { "john", "johnabraham", "abraham", "abrahamjohnabraham" }, "abrahamjohnabraham");
		_instance.runTest(new String [] { "john", "johnabraham", "abraham", "abrahamjohnabraham", "johnabrahamjohnabraham" }, "johnabrahamjohnabraham");
	}

	public void runTest(final String [] strs, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { strs });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	} 
	
}
