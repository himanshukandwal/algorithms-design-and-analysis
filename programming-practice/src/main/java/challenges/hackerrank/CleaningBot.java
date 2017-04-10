package challenges.hackerrank;

public class CleaningBot {

	public static void main(String[] args) {
		String[] board = new String[] { 
				"-b---",
				"-d---",
				"---d-",
				"---d-",
				"--d-d"
		};
		
        next_move(0, 1, board);
    }
	
   static void next_move(int posr, int posc, String[] board){
        char[][] boardGrid = new char[board.length][board[0].length()];
        int idx = 0;
        for (String boardrow : board)
           boardGrid[idx++] = boardrow.toCharArray();
        
        if (boardGrid[posr][posc] == 'd')
            System.out.println("CLEAN");
        else {
            // greedy approach
            int minDirtyDistance = -1;
            int minDirtyDirection = -1;
            
            // checking left
            if (posc > 0) {
                int tempPosc = posc;
                while (tempPosc > 0) {
                    if (boardGrid[posr][tempPosc] == 'd')
                        break;
                    else
                        tempPosc --;
                }   
                
                if (boardGrid[posr][tempPosc] == 'd') {
                    if (minDirtyDistance == -1) {
                        minDirtyDistance = (posc - tempPosc);
                        minDirtyDirection = 1;
                    }
                }
            }
            
            // checking right
            if (posc < (boardGrid[0].length - 1)) {
                int tempPosc = posc;
                while (tempPosc < boardGrid[0].length - 1) {
                    if (boardGrid[posr][tempPosc] == 'd')
                        break;
                    else
                        tempPosc ++;
                }   
                                
                if (boardGrid[posr][tempPosc] == 'd') {
                    if (minDirtyDistance == -1) {
                        minDirtyDistance = (tempPosc - posc);
                        minDirtyDirection = 2;
                    } else {
                        if (minDirtyDistance > (tempPosc - posc)) {
                            minDirtyDistance = (tempPosc - posc);
                            minDirtyDirection = 2;
                        }
                    }
                }
            }
            
            // checking top
            if (posr > 0) {
                int tempPosr = posr;
                while (tempPosr > 0) {
                    if (boardGrid[tempPosr][posc] == 'd')
                        break;
                    else
                        tempPosr --;
                }
                
                if (boardGrid[tempPosr][posc] == 'd') {
                    minDirtyDistance = (posr - tempPosr);
                    minDirtyDirection = 3;
                } else {
                    if (minDirtyDistance > (posr - tempPosr)) {
                        minDirtyDistance = (posr - tempPosr);
                        minDirtyDirection = 3;
                    }
                }
            } 
            
            // checking bottom
            if (posr < (boardGrid.length - 1)) {
                int tempPosr = posr;
                while (tempPosr < boardGrid.length - 1) {
                    if (boardGrid[tempPosr][posc] == 'd')
                        break;
                    else
                        tempPosr ++;
                }
                
                if (boardGrid[tempPosr][posc] == 'd') {
                    if (minDirtyDistance == -1) {
                        minDirtyDistance = (tempPosr - posr);
                        minDirtyDirection = 4;
                    } else {
                        if (minDirtyDistance > (tempPosr - posr)) {
                            minDirtyDistance = (tempPosr - posr);
                            minDirtyDirection = 4;
                        }
                    }
                }
            } 
            
            System.out.println(minDirtyDirection == 1 ? "LEFT" : 
                               (minDirtyDirection == 2 ? "RIGHT" : 
                               (minDirtyDirection == 3 ? "TOP" : "DOWN")));
        }
    }
}
