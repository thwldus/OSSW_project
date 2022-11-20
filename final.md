import javax.swing.*;

public class Board {

	private int[][] board = new int[16][16];

	private int row;
	private int col;
	private int turn;

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
				else if(board[i][j]==1)
					System.out.print("●  ");
				else if(board[i][j]==2)
					System.out.print("○  ");
			}
			System.out.print("\n");
		}
	}

	//둘 수 있는 자리인지 판단
	public boolean put() {
		if(board[row][col]==0) 
			return true;
		else
			return false;
	}
	//플레이어로부터 놓고자 하는 자리를 입력받음
	public void player_input(int turn) {
		row = Integer.parseInt(JOptionPane.showInputDialog("몇번째 행인지 입력해주세요."));
		while(row<1 || row>15)
			row = Integer.parseInt(JOptionPane.showInputDialog("몇번째 행인지 입력해주세요."));
		col = Integer.parseInt(JOptionPane.showInputDialog("몇번째 열인지 입력해주세요."));
		while(col<1 || col>15)
			col = Integer.parseInt(JOptionPane.showInputDialog("몇번째 열인지 입력해주세요."));
		if(!this.put()) { //놓고 싶은 자리에 둘 수 없는 경우
			JOptionPane.showMessageDialog(null, "이미 다른 플레이어가 둔 자리입니다.\n 위치 선택을 다시 진행합니다.");
			this.player_input(turn);
		}
		else {
			board[row][col] = turn;
		}
	}
	//차례 변경
	public void turnChange(int t) {
		turn = t;
		if(turn==1)
			turn = 2;
		else
			turn = 1;
	}
	
	public int turn() { return turn; }

	public static void main(String[] args) {
		Board b = new Board();
		b.showBoard();
		int t = 1;
		while(true) {
			System.out.println("지금은 플레이어" + t + " 차례입니다.");
			b.player_input(t);
			b.showBoard();
			b.turnChange(t); //플레이어 순서 교체
			t = b.turn(); //누구 차례인지 업데이트
		}
	}
}
