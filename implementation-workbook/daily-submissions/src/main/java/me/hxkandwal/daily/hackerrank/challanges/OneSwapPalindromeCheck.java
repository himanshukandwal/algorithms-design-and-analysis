package me.hxkandwal.daily.hackerrank.challanges;

/**
 * Hello world!
 *
 */
public class OneSwapPalindromeCheck 
{
    public static void main( String[] args )
    {
    	System.out.println(isOneSwapEnough("abab"));
    }
    
    static boolean isOneSwapEnough(String inputString) {
        
        boolean isPal= isPal(inputString);
        
        if (!isPal) {
            int idx = 0;
            for (; idx < inputString.length()/2; idx ++) {
                if (inputString.charAt(idx) != inputString.charAt(inputString.length() -1 - idx)) 
                    break;
            }
            
            StringBuffer sb = new StringBuffer();
        
            for (int d = 0; d < inputString.length(); d ++) {
                if (d == idx) {
                    sb.append(inputString.charAt(idx+1));
                    sb.append(inputString.charAt(idx));
                    d ++;
                } else {
                    sb.append(inputString.charAt(d));
                }   
            }

            return isPal(sb.toString());
            
        } else {
            return true;
        }
        
    }

    static boolean isPal (String inputString) {
        for (int i =0; i < inputString.length()/2; i ++) {
            if (inputString.charAt(i) != inputString.charAt(inputString.length() -1 - i)) 
                return false;
        }
        return true;
    }

}
