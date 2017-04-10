package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * A simple use-case of sorting using comparators.
 * 
 * @author Hxkandwal
 *
 */
public class ComparatorSorting extends AbstractCustomTestRunner {
	
	private static ComparatorSorting _instance = new ComparatorSorting();
	
	private ComparatorSorting() {}
	
	public static Comparator<String> comparator = new Comparator<String>() {
		
		@Override
		public int compare(String value1, String value2) {
			value1 = value1.toLowerCase();
			value2 = value2.toLowerCase();
			
			int idx1 = 0, idx2 = 0, len = value1.length();
			
			while (idx1 < len && idx2 < len) {
				char ch1 = value1.charAt(idx1);
				char ch2 = value2.charAt(idx2);
				
				if (ch1 == ch2) {
					idx1 ++;
					idx2 ++;
				} else
					return (ch1 > ch2) ? -1 : 1;
			}
			return 0;
		}
	};
	
	public static List<String> _sorter(List<String> list) {
		Collections.sort(list, comparator);
		return list;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(Arrays.asList("a1", "a2", "b1", "b2"), Arrays.asList("b2", "b1", "a2", "a1"));
	}
	
	@SuppressWarnings("unchecked")
	public void runTest(final List<String> input, final List<String>  expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers) 
			assertThat((List<String>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
