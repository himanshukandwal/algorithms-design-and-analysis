package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Program to find the mean and median in comments.
 * 
 * @author Hxkandwal
 *
 */
public class FindRelatednessInComments extends AbstractCustomTestRunner {
	
	private static FindRelatednessInComments _instance = new FindRelatednessInComments();
	
	private FindRelatednessInComments() {}

	static class Node {
		private final String word;
		private final List<Node> neighbors;
		
		Node(String word) {
			this.word = word;
			this.neighbors = new ArrayList<>();
		}
	}
	
	private static HashMap<String, Node> nodes = new HashMap<>();

	/**
	 * Parse the data into structures that can then be used by the findMean and findMedian functions
	 * You MAY change the method signature as needed!
	 */
	public static void parse(List<List<String>> dataSet) {
		for (List<String> data : dataSet) {
			List<Node> currentNeighbors = new ArrayList<>();
			for (String word : data) {
				Node node = null;
				if (nodes.containsKey(word))
					node = nodes.get(word);

				if (node == null)
					nodes.put(word, node = new Node(word));

				currentNeighbors.add(node);
			}

			for (Node neighbour : currentNeighbors) {
				for (Node innerNeighbour : currentNeighbors) {
					if (neighbour != innerNeighbour) {
						boolean contains = false;
						for (Node neighborNeighbor : innerNeighbour.neighbors) {
							if (neighborNeighbor.word.equals(neighbour.word)) {
								contains = true;
								break;
							}
						}

						if (!contains)
							innerNeighbour.neighbors.add(neighbour);
					}
				}
			}
		}
	}
	
	/** @return mean of the degree of the words
	 *  Please DO NOT change the method signature
	 */
	public static double findMean(List<List<String>> dataSet) {
		parse(dataSet);
		
        double numNodes = nodes.size() * 1d;
        int sum = 0;
		
		for (Map.Entry<String, Node> entry : nodes.entrySet()) 
			sum += entry.getValue().neighbors.size();
		        
		return sum/numNodes ;
	}

	/** @return median of the degree of the words
	 *  Please DO NOT change the method signature
	 */
	public static double _findMedian(List<List<String>> dataSet) {
		parse(dataSet);
		int[] array = new int [nodes.size()];
		
		int idx = 0; 
		for (Map.Entry<String, Node> entry : nodes.entrySet()) 
			array [idx ++] = entry.getValue().neighbors.size();
		
		Arrays.sort(array);
		
		double median = 0; 
		if (array.length % 2 == 0)
			median = (array[array.length / 2] + array[array.length/2 -1]) / 2d;
		else 
			median = array[(array.length - 1) / 2];
		
		return median;
	}
	
	// driver method
	public static void main(String[] args) throws FileNotFoundException {
		_instance.runTest(new String[] { "coding is great", "coding is fun" }, 2.5);
	}
	
	public void runTest(final String[] comments, final double expectedOutput) {
		List<List<String>> dataSet = new ArrayList<>();
		
		for (String comment : comments) {
			List<String> data = Arrays.asList(comment.split(" "))
					.stream().map(word -> word.toLowerCase())
					.collect(Collectors.toList());
			
			dataSet.add(data);
		}
		
		List<Object> answers = runAll(getClass(), new Object[] { dataSet });

		for (Object answer : answers)
			assertThat((Double) answer).isWithin(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
