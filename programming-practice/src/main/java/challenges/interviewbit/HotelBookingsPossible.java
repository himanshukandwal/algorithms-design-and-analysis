package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Hotel Bookings Possible
 * 
 * A hotel manager has to process N advance bookings of rooms for the next season. His hotel has K rooms. Bookings contain an arrival 
 * date and a departure date. He wants to find out whether there are enough rooms in the hotel to satisfy the demand. 
 * 
 * Write a program that solves this problem in time O(N log N) .
 * 
 * Input:
 * 		First list for arrival time of booking.
 * 		Second list for departure time of booking.
 * 		Third is K which denotes count of rooms.
 * 
 * Output:
 * 		A boolean which tells whether its possible to make a booking.
 * 		Return 0/1 for C programs.
 * 		O -> No there are not enough rooms for N booking.
 * 		1 -> Yes there are enough rooms for N booking.
 * 
 * Example :
 * 		Input :
 * 				Arrivals :   [1 3 5]
 * 				Departures : [2 6 8]
 * 				K : 1
 * 
 * 		Return : False / 0
 * 				 At day = 5, there are 2 guests in the hotel. But I have only one room. 
 * 
 * @author Hxkandwal
 */
public class HotelBookingsPossible extends AbstractCustomTestRunner {
	
	private static HotelBookingsPossible _instance = new HotelBookingsPossible();

	public boolean _hotel(List<Integer> arrive, List<Integer> depart, int K) {
        int [][] timings = new int [arrive.size()][2];
        for (int idx = 0; idx <  arrive.size(); idx ++) {
            timings [idx][0] = arrive.get (idx);
            timings [idx][1] = depart.get (idx);
        }
        Arrays.sort (timings, (a, b) -> (a [1] - b [1] == 0 ? a [0] - b [0] : a [1] - b [1]));
        int [] rooms = new int [K];
        for (int [] timing : timings) {
            int bestIdx = -1;
            for (int k = 0; k < K; k ++) {
                if (rooms [k] <= timing [0]) {
                	if (bestIdx < 0 || (timing [0] - rooms [bestIdx]) > (timing [0] - rooms [k]))
                		bestIdx = k;
                }
            }
            if (bestIdx < 0) return false;
            else rooms [bestIdx] = timing [1];
        }
        return true;
    }
			
	// driver method
	public static void main(String[] args) {
		_instance.runTest(Arrays.asList(41, 10, 12, 30, 0, 17, 38, 36, 45, 2, 33, 36, 39, 25, 22, 5, 41, 24, 12, 33, 19, 30, 25, 12, 36, 8), 
						  Arrays.asList(47, 20, 15, 65, 35, 51, 38, 36, 94, 30, 50, 38, 67, 64, 67, 24, 62, 38, 18, 59, 20, 74, 33, 43, 56, 32), 12, true);
	}

	public void runTest(final List<Integer> arrive, final List<Integer> depart, final int K, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { arrive, depart, K });

		for (Object answer : answers) 
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
		
}
