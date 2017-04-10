package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * Program to implement queue using two stacks.
 * 
 * @author Hxkandwal
 *
 */
public class QueueWithTwoStacks extends AbstractCustomTestRunner {

	private static QueueWithTwoStacks _instance = new QueueWithTwoStacks();
	
	private QueueWithTwoStacks() {}
	
	public static class QueueStacks<T> {
		Stack<T> forwardStack = new Stack<>();
		Stack<T> backupStack = new Stack<>();
		
		// insert operation.
		public void insert(T item) {
			forwardStack.push(item);
		}
		
		// remove/peek operation.
		public T remove() {
			while (!forwardStack.isEmpty())
				backupStack.push(forwardStack.pop());
			
			T itemToReturn = backupStack.pop();
			
			while (!backupStack.isEmpty())
				forwardStack.push(backupStack.pop());
			
			return itemToReturn;
		}
		
		// size information.
		public boolean isEmpty() {
			return forwardStack.isEmpty();
		}
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new Integer[] { 1, 2, 3, 4, 5 }, new Integer[] { 1, 2, 3, 4, 5 });
		_instance.runTest(new Integer[] { 1, 2, 5, 3, 5, 4, 5 }, new Integer[] { 1, 2, 5, 3, 5, 4, 5 });
	}
	
	public void runTest(final Integer[] input, final Integer[] expectedOutput) {
		QueueStacks<Integer> queue = new QueueStacks<>();
		
		for (Integer item : input) 
			queue.insert(item);
			
		List<Integer> result = new ArrayList<>();
		while (!queue.isEmpty()) 
			result.add(queue.remove());
	
		assertThat(result.toArray(new Integer[0])).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
