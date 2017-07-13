package challenges.assorted;

import javax.management.RuntimeErrorException;

// X - Humans (player = true)
// O - AI	  (player = false)
public class TripleByte {
	
	private char [][] board;
	private int available = 9;
	private int n;
	private boolean player = true;
//	private Scanner sc;
	
	public TripleByte(char [][] board) {
		this.board = board;
		this.n = board.length;
//		Scanner sc = new Scanner(System.in);
	}
	
	public void print() {
		for (int row = 0; row < n; row ++) {
			for (int col = 0; col < n; col ++) {
				System.out.print(board [row][col]);
				if (col != board [0].length - 1) System.out.print('|');
			}
			System.out.println();
		}
	}
	
	public void move(int x, int y) {
		if (isFull()) System.out.println("No legal moves exists !");
		else {
			board [x][y] = player ? 'X' : 'O';
			available --;
			player = !player;
		}
	}
	
	// true : successful , false : no legal moves 
	public void makeMove() {
		for (int row = 0; row < n; row ++) {
			for (int col = 0; col < n; col ++) {
				if (board [row][col] == '-') {
					player = false;
					move(row, col);
					return;
				}
			}
		}
		throw new RuntimeErrorException(" message");
	}
	
	
	public boolean isFull() {
		return available == 0; 
	}
	
	public static void main(String[] args) {
		TripleByte t = new TripleByte(new char [][] { { '-', '-', '-'}, { '-', '-', '-'},  {  '-', '-', '-'}});
		t.print();
		t.move(0, 1);
		t.print();
		t.makeMove();
		t.print();
		t.makeMove();
		t.print();
		
	}

}
