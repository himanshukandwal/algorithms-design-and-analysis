package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import challenges.AbstractCustomTestRunner;

/**
 * Merge Overlapping Intervals
 * 
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example:
 * 		Given  :	[1,3],[2,6],[8,10],[15,18]
 * 		return :	[1,6],[8,10],[15,18].
 * 
 * Make sure the returned intervals are sorted.
 * 
 * link: https://www.interviewbit.com/problems/merge-overlapping-intervals/
 * 
 * @author Hxkandwal
 */
public class MergeOverlappingIntervals extends AbstractCustomTestRunner {
	
	private static MergeOverlappingIntervals _instance = new MergeOverlappingIntervals();

	static class Interval {
		int start, end;
		Interval(int s, int e) { start = s; end = e; }
		
		@Override
		public String toString() {
			return "(" + start + "," + end + ")";
		}
		
		static List<Interval> generate (String s) {
			StringTokenizer tokenizer = new StringTokenizer(s, ",([])");
			Integer start = null, end = null;
			List<Interval> intervals = new ArrayList<>();
			
			while (tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken().trim();
				if (token.length() > 0) {
					if (start == null) start = Integer.valueOf(token);
					else if (end == null) end = Integer.valueOf(token);
					else {
						intervals.add(new Interval(start, end));
						end = start = null;
					}
				}
			}
			if (start != null && end != null) intervals.add(new Interval(start, end));
			return intervals;
		}
	}
	
	public List<Interval> _merge(List<Interval> intervals) {
		for (Interval interval : intervals) 
            if (interval.start > interval.end) {
                int start = interval.start;
                interval.start = interval.end;
                interval.end = start;
            }
            
        Collections.sort (intervals, (a, b) ->  a.start - b.start == 0 ? b.end - a.end : a.start - b.start);
        ArrayList<Interval> ans = new ArrayList<Interval> ();
        int idx = 0;
        while (idx < intervals.size()) {
            Interval interval = intervals.get (idx);
            int end = interval.end;
            while (idx + 1 < intervals.size() && end >= intervals.get (idx + 1).start) {
                idx ++;
                end = Math.max (end, intervals.get (idx).end);
            }
            ans.add (end == interval.end ? interval : new Interval (interval.start, end));
            idx ++;
        }
        return ans;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(Interval.generate("[ (4, 100), (48, 94), (16, 21), (58, 71), (36, 53), (49, 68), (18, 42), (37, 90), "
				+ "(68, 75), (6, 57), (25, 78), (58, 79), (18, 29), (69, 94), (5, 31), (10, 87), (21, 35), (1, 32), (7, 24), (17, 85), "
				+ "(30, 95), (5, 63), (1, 17), (67, 100), (53, 55), (30, 63), (7, 76), (33, 51), (62, 68), (78, 83), (12, 24), (31, 73), "
				+ "(64, 74), (33, 40), (51, 63), (17, 31), (14, 29), (9, 15), (39, 70), (13, 67), (27, 100), (10, 71), (18, 47), (48, 79), "
				+ "(70, 73), (44, 59), (68, 78), (24, 67), (32, 70), (29, 94), (45, 90), (10, 76), (12, 28), (31, 78), (9, 44), (29, 83), "
				+ "(21, 35), (46, 93), (66, 83), (21, 72), (29, 37), (6, 11), (56, 87), (10, 26), (11, 12), (15, 88), (3, 13), (56, 70), "
				+ "(40, 73), (25, 62), (63, 73), (47, 74), (8, 36) ]"), Interval.generate("(1, 100)"));
	}
	
	@SuppressWarnings("unchecked")
	public void runTest(final List<Interval> intervals, final List<Interval> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { intervals });
		
		for (Object answer : answers) 
			assertThat(((List<Interval>) answer).toString()).isEqualTo(expectedOutput.toString());
		
		System.out.println("ok!");
	}
	
}
