package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * You would like to create your own little farm. Since you're not an expert (yet!), you bought seeds of 
 * just two types of plants: flowers and florets. Each pack of seeds was provided with the instructions, 
 * explaining when the plant can be planted.
 * 
 * In the instructions two dates are given, to and from, which denote the favorable period for planing the 
 * seeds. Note, that to is not necessarily larger than from: if to is less than from, then the period is 
 * counted from from to to through the beginning of the new year. It is always assumed that there are 365 days 
 * in a year.
 * 
 * Given the dates from the flower instructions flowerFrom and flowerTo and from the floret instructions 
 * floretFrom and floretTo, calculate the number of days of the year in which both plants can be planted.
 * 
 * Example:
 * 
 * For 	flowerFrom = 10, flowerTo = 20,
 * 		floretFrom = 1 and floretTo = 365, 
 * 
 * the output should be flowersAndFlorets(flowerFrom, flowerTo, floretFrom, floretTo) = 11.
 * 
 * Flowers can only be planted in the period from the 10th to the 20th day of the year, and florets can be
 * planted any time. Thus, the output should be equal to the number of days in which flowers can be planted, which 
 * is 11.
 * 
 * @author Hxkandwal
 *
 */
public class FlowersAndFlorets extends AbstractCustomTestRunner {

	public static FlowersAndFlorets _instance = new FlowersAndFlorets();
	
	private FlowersAndFlorets() {}
	
	public static class Range implements Comparable<Range> {
		int start;
		int end;
		boolean requiresNewYear; 
		
		public Range(int start, int end) {
			this.start = start;
			this.end = end;
			this.requiresNewYear = (end < start);
		}
		
		@Override
		public int compareTo(Range o) {
			if (!requiresNewYear && !o.requiresNewYear) {

				if (end < o.start || o.end < start) {
					// no coverage
					return 0;
				} else if ((o.start < end && start < o.start && o.end > end) || (start < o.end && o.start < start && end > o.end)) {
					// overlapping only one end
					return (end > o.end) ? (o.end - start + 1) : (end - o.start + 1);
				} else {
					// completely inside
					return Math.min(end - start + 1, o.end - o.start + 1);
				}
			} else {
				// reuse the previous build up, smartly + recursively !
				int diff = 0;
				
				if (requiresNewYear && !o.requiresNewYear) {
					diff = new Range(start, 365).compareTo(o);
					diff += new Range(1, end).compareTo(o);
				} else if (o.requiresNewYear && !requiresNewYear) {
					diff = new Range(o.start, 365).compareTo(this);
					diff += new Range(1, o.end).compareTo(this);
				} else {
					diff = new Range(start, 365).compareTo(new Range(o.start, 365));
					diff += new Range(1, o.end).compareTo(new Range(1, end));
				}
			
				return diff; 
			}
		}		
	}
	
	public static int _flowersAndFlorets(int flowerFrom, int flowerTo, int floretFrom, int floretTo) {
		return new Range(flowerFrom, flowerTo).compareTo(new Range(floretFrom, floretTo));
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(10, 20, 1, 365, 11);
		_instance.runTest(100, 150, 110, 130, 21);
		_instance.runTest(360, 10, 1, 365, 16);
		_instance.runTest(100, 200, 190, 110, 22);
	}

	public void runTest(final int flowerFrom, final int flowerTo, final int floretFrom, final int floretTo, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { flowerFrom, flowerTo, floretFrom, floretTo });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
