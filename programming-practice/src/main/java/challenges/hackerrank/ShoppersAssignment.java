package challenges.hackerrank;

import java.util.Arrays;
import java.util.Comparator;

public class ShoppersAssignment {

	public static void main(String[] args) {
		test1();
		test2();
	}
	
	public static void test2() {
		String [][] shoppers = {{"23:00","23:20"}, {"23:10","23:30"}, {"23:15","23:35"}};
		String [][] orders = {{"23:05","23:30"}, {"23:05","23:30"}, {"23:05","23:30"}};
		int[] leadtime = { 15, 15, 15 };
		
		System.out.println(busyHolidays(shoppers, orders, leadtime));
	}
	
	public static void test1() {
		String [][] shoppers = {{"23:00","23:59"}, {"22:30","23:30"}};
		String [][] orders = {{"23:15","23:35"}, {"23:00","23:31"}};
		int[] leadtime = { 20, 30 };
		
		System.out.println(busyHolidays(shoppers, orders, leadtime));
	}
	
	static boolean busyHolidays(String[][] shoppers, String[][] orders, int[] leadTime) {

	    // load orders and shoppers
	    InstaDate[][] instaOrders = new InstaDate[orders.length][2];
	    InstaDate[][] instaShoppers = new InstaDate[shoppers.length][2];
	    
	    boolean[] shoppersAvailable = new boolean [shoppers.length];
	    Arrays.fill (shoppersAvailable, true);
	    
	    for (int idx = 0; idx < leadTime.length; idx ++) {
	        instaOrders[idx][0] = new InstaDate(idx, orders[idx][0]);
	        instaOrders[idx][1] = new InstaDate(idx, orders[idx][1]);
	        
	        instaShoppers[idx][0] = new InstaDate(idx, shoppers[idx][0]);
	        instaShoppers[idx][1] = new InstaDate(idx, shoppers[idx][1]);
	    }
	    
	    Arrays.sort(instaOrders, new Comparator<InstaDate[]>() {
	        public int compare (InstaDate[] o1, InstaDate[] o2) {
	            return o1[0].compareTo(o2[0]);
	        }
	    });
	    
	    Arrays.sort(instaShoppers, new Comparator<InstaDate[]>() {
	        public int compare (InstaDate[] o1, InstaDate[] o2) {
	            return o1[0].compareTo(o2[0]);
	        }
	    });
	    
	    for (int idx = 0; idx < instaOrders.length; idx ++) {
	        InstaDate startOrderTime = instaOrders[idx][0];
	        InstaDate endOrderTime = instaOrders[idx][1];
	        
	        boolean shopperFound = false;
	        for (int innerIdx = 0; innerIdx < instaShoppers.length; innerIdx ++) {
	            
	            if (shoppersAvailable[innerIdx]) {
	                InstaDate shoppersStartTime = instaShoppers[innerIdx][0];
	                InstaDate shoppersEndTime = instaShoppers[innerIdx][1];
	            
	                if (shoppersStartTime.compareTo(endOrderTime) < 0 &&
	                    endOrderTime.diff(shoppersStartTime) >= leadTime[startOrderTime.idx]) {
	                    shopperFound = true;
	                    shoppersAvailable[innerIdx] = false;
	                    break;
	                }
	            }
	        }
	        
	        if (!shopperFound)
	            return false;
	    }
	    
	    return true;
	}

	static class InstaDate implements Comparable <InstaDate> {
	    public int idx;
	    public int hour;
	    public int minutes;
	    
	    public InstaDate(int idx, String date) {
	        this.idx = idx;
	        this.hour = Integer.valueOf(date.substring(0, date.indexOf(":")));
	        this.minutes = Integer.valueOf(date.substring(date.indexOf(":") + 1));
	    }
	    
	    public int compareTo (InstaDate date) {
	        if (this.hour > date.hour) 
	            return 1;
	        else if (this.hour < date.hour)
	            return -1;
	        else {
	            if (this.minutes > date.minutes) 
	                return 1;
	            else if (this.minutes < date.minutes)
	                return -1;
	            else 
	                return 0;
	        }
	    }
	    
	    public int diff (InstaDate date) {
	        int difftime = 0;
	        if (this.compareTo(date) > 0) {
	            if (this.hour > date.hour) {
	                difftime = (this.hour - date.hour) * 60; 
	                
	                difftime -= date.minutes;
	                difftime += this.minutes;
	            } else {
	                difftime = (this.minutes - date.minutes);
	            }
	            
	            return difftime;
	        } 
	        else 
	            return -1;
	    }

	}
}
