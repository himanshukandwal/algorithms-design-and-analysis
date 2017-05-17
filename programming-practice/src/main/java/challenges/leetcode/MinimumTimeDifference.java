package challenges.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 539. Minimum Time Difference
 * 
 * Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time 
 * points in the list.
 * 
 * Example 1:
 * 		Input: ["23:59","00:00"]
 * 		Output: 1
 * 
 * Note:
 * 	-	The number of time points in the given list is at least 2 and won't exceed 20000.
 * 	-	The input time is legal and ranges from 00:00 to 23:59.
 *  
 * @author Hxkandwal
 */
public class MinimumTimeDifference extends AbstractCustomTestRunner {

	class Time {
        int hour, minute;
        Time (String timePoint) { 
            String [] timePointArr = timePoint.split (":");
            this.hour = Integer.valueOf (timePointArr [0]); this.minute = Integer.valueOf (timePointArr [1]); 
        }
        
        public int getDiff (Time other) {
            return (this.hour - other.hour) * 60 + (this.minute - other.minute);
        }
    }
    
	// There is only 24 * 60 = 1440 possible time points. Just create a boolean array, each element stands for if we see that time point or not. 
    public int findMinDifference(List<String> timePoints) {
        Time [] points = new Time [timePoints.size()];
        for (int idx = 0; idx < timePoints.size(); idx ++) points [idx] = new Time (timePoints.get (idx));
        Arrays.sort (points, (a, b) -> a.hour - b.hour == 0 ? a.minute - b.minute : a.hour - b.hour);
        
        int minDiff = Integer.MAX_VALUE;
        for (int idx = 1; idx < points.length; idx ++)
            minDiff = Math.min (minDiff, points [idx].getDiff (points [idx - 1]));
        return Math.min (minDiff, points [0].getDiff(points [points.length - 1]) + 1440) ;
    }
    
    // simpler.
    private int getSeconds(String timePoint) {
        String[] splittedTimePoint = timePoint.split(":");
        return Integer.parseInt(splittedTimePoint[0]) * 60 + Integer.parseInt(splittedTimePoint[1]);
    }
    
    public int findMinDifferenceSimpler(List<String> timePoints) {
        Collections.sort(timePoints);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < timePoints.size(); ++i) {
            minDiff = Math.min(minDiff, getSeconds(timePoints.get(i)) - getSeconds(timePoints.get(i - 1)));
        }
        return Math.min(minDiff, getSeconds(timePoints.get(0)) + 1440 - getSeconds(timePoints.get(timePoints.size() - 1)));
    }
}
