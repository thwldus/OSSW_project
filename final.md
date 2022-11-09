import javax.swing.*;

public class Board {

	private int[][] board = new int[16][16];
	
	public Board() {
		for(int i=0; i<16; i++) 
			for(int j=0; j<16;j++) 
				board[i][j] = 0;
	}
	
	public void showBoard() {
		for(int i=0; i<16; i++) { 
			for(int j=0; j<16;j++) {
				if(i==0) { 
					if(j<10)
						System.out.printf("%-3d", j);
					else
						System.out.printf("%-4d", j);
				}
				else if(j==0)
					System.out.printf("%-3d", i);
				else if(board[i][j]==0)
					System.out.printf("%-3s","ㆍ "); 
					//System.out.print(" ○");
					//System.out.print(" ●");
			}
			System.out.print("\n");
		}
	}

	public static void main(String[] args) {
		Board b = new Board();
		b.showBoard();
	}
}
