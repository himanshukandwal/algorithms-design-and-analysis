package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;
import me.hxkandwal.daily.challanges.leetcode.SortCharactersByFrequency.MostFrequentlyCharacterHeap.DataNode;

/**
 * 451. Sort Characters By Frequency
 * 
 * Given a string, sort it in decreasing order based on the frequency of characters.
 * 
 * Example 1:
 * 		Input: "tree"
 * 		Output: "eert"
 * 		Explanation: 'e' appears twice while 'r' and 't' both appear once.
 * 				     So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * 
 * Example 2:
 * 		Input: "cccaaa"
 * 		Output: "cccaaa"
 * 		Explanation: Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * 					 Note that "cacaca" is incorrect, as the same characters must be together.
 * 
 * Example 3:
 * 		Input: "Aabb"
 * 		Output: "bbAa"
 * 		Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * 					 Note that 'A' and 'a' are treated as two different characters.
 * 	
 * @author Hxkandwal
 */
public class SortCharactersByFrequency extends AbstractCustomTestRunner {
	
	private static SortCharactersByFrequency _instance = new SortCharactersByFrequency();
	
	private SortCharactersByFrequency() {}
	
	// character frequency max heap
	public static class MostFrequentlyCharacterHeap {
		
		private DataNode[] array = new DataNode [256 + 1];
		private Map<Character, DataNode> bookkeepingMap = new HashMap<>();
		private int fillSize;
		
		public static class DataNode {
			private char ch;
			private int index;
			private int frequency;
			
			public DataNode(char ch, int index) {
				this.ch = ch;
				this.index = index;
				this.frequency = 1;
			}
			
			@Override
			public String toString() {
				return "(" + ch + ":" + frequency + ")";
			}
		}

		public void percolateUp(int index) {
			DataNode node = array [index];
			
			while (index > 1 && (array [index].frequency > array [index / 2].frequency)) {
				array [index / 2].index = index;
				array [index] = array [index / 2];
				index /= 2;
			}
			
			node.index = index;
			array [index] = node;
		}
		
		public void percolateDown(int index) {
			DataNode node = array [index];
			
			if (fillSize >= 2 * index) {
				int maxIndex = 2 * index;
				
				if (fillSize >= maxIndex + 1) 
					maxIndex =  (array [maxIndex].frequency > array [maxIndex + 1].frequency ? maxIndex : maxIndex + 1);
				
				if (array [index].frequency < array [maxIndex].frequency) {
					node.index = maxIndex;
					array [index] = array [maxIndex];
					array [maxIndex].index = index;
					array [maxIndex] = node;
					
					percolateDown(maxIndex);
				}
			}
		}		
		
		public void add (char ch) {
			DataNode node = null;
			
			if (bookkeepingMap.containsKey(ch)) {
				node = bookkeepingMap.get(ch);
				node.frequency ++;
				
				// percolate up.
				percolateUp (node.index);
			} else {
				node = new DataNode(ch, ++ fillSize);
				array [node.index] = node;
				bookkeepingMap.put(ch, node);
			}
		}
		
		public DataNode removeMax () {
			DataNode node = null;
			
			if (fillSize > 0) {
				node = array [1];
				array [1] = array [fillSize --];
				array [1].index = 1;
				
				// percolate down.
				percolateDown (1);
			}
			
			return node;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			for (int idx = 1; idx <= fillSize; idx ++) {
				sb.append(array [idx].toString());
				
				if (idx < fillSize) sb.append(",");
			}
				
			return sb.toString();
		}
		
	}
	
	public static String _frequencySort(String s) {
		MostFrequentlyCharacterHeap maxHeap = new MostFrequentlyCharacterHeap();
		
		for (char ch : s.toCharArray()) 
			maxHeap.add(ch);
		
		char[] answer = s.toCharArray();
		
		DataNode node = null;
		int idx = 0;
		while ((node = maxHeap.removeMax()) != null) {
			int repeatitions = node.frequency;
			
			while (repeatitions -- > 0)
				answer [idx ++] = node.ch;
		}
		
		return String.valueOf(answer);
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("tree", "eetr");
		_instance.runTest("cccaaa", "cccaaa");
		_instance.runTest("Aabb", "bbAa");
	}

	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
		
}
