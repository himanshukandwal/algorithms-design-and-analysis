package challenges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * Find the k most frequent words from a file
 * 
 * Given a book of words. Assume you have enough main memory to accommodate all words. 
 * design a data structure to find top K maximum occurring words. 
 * 
 * The data structure should be dynamic so that new words can be added. 
 * 
 * link : http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/
 * 
 * @author Hxkandwal
 *
 */
public class FindkMostFrequentWords extends AbstractCustomTestRunner {
	
	private static FindkMostFrequentWords _instance = new FindkMostFrequentWords();
	
	private FindkMostFrequentWords() {}
	
	// Indexed min heap data structure
	public static class FrequencyMinHeap {
		
		// inner data structure
		private class Entry {
			String data;
			int frequency;
			
			public Entry(String data, int frequency) {
				this.data = data;
				this.frequency = frequency;
			}
		}
		
		private Entry[] data;
		private int size;
		private int fillingSize;
		private Map<String, Entry> metadata = new HashMap<>();
		
		public FrequencyMinHeap(int size) {
			this.size = size; 		// for data [0]
			this.fillingSize = 0;
			data = new Entry [size + 1];
		}
		
		public int getSize() {
			return size - 1;
		}
		
		// insert operation
		public void insert(String item) {
			if (metadata.containsKey(item)) 
				 decreaseKey(metadata.get(item));
			else {
				metadata.put(item, new Entry(item, 1));
				if (fillingSize == size - 1) 
					remove();
				
				percolateUp(metadata.get(item));
			}
		}
		
		private void decreaseKey(Entry entry) {
			
		}

		private void percolateUp(Entry entry) {
			if (size == data.length) {
				
			}
		}

		// remove operation
		public String remove() {
			return null;
		}
		
	}
	
	/* method : create k size min-heap of words, based on frequency as priority. 
	 * 			Then remove min, if in-case size is fill.
	 */
	public static List<String> _getKMostFrequencyWords (String[] input, int k ) {

		return null;
	}
	
	// driver method
    public static void main(String[] args) throws FileNotFoundException {
    	_instance.runTest("ab", 'a');
    	_instance.runTest("abc", 'a');
    	_instance.runTest("abac", 'b');
    	_instance.runTest("abacdefb", 'c');
    	_instance.runTest("abaaacdefbcd", 'e');
    	_instance.runTest("xxyzzzxyzy", null);
    	_instance.runTest("hqghumeaylnlfdxfircvscxggbwkfnqduxwfnfozv", 'm');	
    }

	public void runTest(final String s, final Character expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });
		
		for (Object answer : answers) 
			assertThat((Character) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
}
