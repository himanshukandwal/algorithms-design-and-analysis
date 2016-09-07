package me.hxkandwal.daily.challanges.personal;

public class GroupingChallange {

	public static void main(String[] args) {
		System.out.println(matrix(new int[][] { {0,0,0,0,1,0,0,0},
												{0,0,1,0,0,0,0,1},
												{0,1,1,1,0,0,0,0}}));
	}
	
	//TODO complete the task. Make groups.
	public static int matrix (int[][] venue) {
		int groups = 0;
		for (int row = 0; row < venue.length; row ++) {
			for (int col = 0; col < venue[0].length; col ++) {
				boolean rowskip = false;
				boolean colskip = false;
				
				if (venue[row][col] == 1) {
					if (row + 1 == venue.length) {
						if (col + 1 == venue[0].length) {
							if ((venue [row][col -1] == 0 && venue [row -1][col] == 0 && venue [row-1][col -1] == 0)
								|| (venue [row][col - 1] == 1 || venue [row - 1][col] == 1))
								groups ++;
						} else if (col - 1 < 0) {
							if ((venue [row][col +1] == 0 && venue [row +1][col] == 0 && venue [row +1][col +1] == 0)
								|| (venue [row][col +1] == 1 || venue [row +1][col] == 1))
								groups ++;
						} else {
							if (venue [row][col +1] == 1 || venue [row][col -1] == 1)
								groups ++;
						}
					} 
					
					else if (row - 1 < 0) {
						if (col + 1 == venue[0].length) {
							if ((venue [row][col -1] == 0 && venue [row +1][col] == 0 && venue [row+1][col -1] == 0)
								|| (venue [row][col - 1] == 1 || venue [row + 1][col] == 1))
								groups ++;
						} else if (col - 1 < 0) {
							if ((venue [row][col +1] == 0 && venue [row +1][col] == 0 && venue [row +1][col +1] == 0)
								|| (venue [row][col +1] == 1 || venue [row +1][col] == 1))
								groups ++;
						} else {
							if (venue [row][col +1] == 1 || venue [row][col-1] == 1)
								groups ++;
						}
					}
					
					else {
						if (col + 1 == venue[0].length) {
							if (venue [row][col -1] == 0 && venue [row +1][col] == 0 && venue [row+1][col -1] == 0 && venue [row-1][col -1] == 0) {
								groups ++;
							} else if (venue [row][col - 1] == 1 && venue [row + 1][col] == 1) {
								groups ++;
								rowskip = true;
							} else if (venue [row][col - 1] == 1) {
								groups ++;
							}
						} else if (col - 1 < 0) {
							if (venue [row][col +1] == 0 && venue [row +1][col] == 0 && venue [row +1][col +1] == 0 && venue [row -1][col +1] == 0) {
								groups ++;
							} else if (venue [row][col+1] == 1 && venue [row +1][col] == 1) {
								groups ++;
								rowskip = true;
								colskip = true;
							} else if (venue [row][col+1] == 1 && venue [row +1][col] == 0) {
								groups ++;
								colskip = true;
							}  else if (venue [row][col+1] == 0 && venue [row +1][col] == 1) {
								groups ++;
								rowskip = true;
							}
						} else {
							if (venue [row][col +1] == 1) {
								colskip = true;
								groups ++;
							} else if (venue [row][col-1] == 1 && venue [row + 1][col-1] == 0) {
								groups ++;
								rowskip = true;
							} else if (venue [row][col-1] == 1 && venue [row + 1][col-1] == 1) {
								groups ++;
								colskip = true;
								rowskip = true;
							}
						}
					}
				}
				
				if (colskip)
					col += 2;
				
				if (rowskip)
					row += 	2;
			}
		}		
		
		return groups;
	}
}
