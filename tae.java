import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class myPanel extends JPanel implements ActionListener{
	JButton btn[][];
	JButton restart;
	JButton result;
	final int SIZE = 20;
	int omok[][] = new int[SIZE][SIZE];
	int turn = 0;
	int win = 0;

    private JLabel label_1 = new JLabel();
	private JLabel order = new JLabel();

	public myPanel() {
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		set();
	}
	public void set() {
		btn = new JButton[SIZE][SIZE];
		restart = new JButton();
		result = new JButton();
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				btn[i][j] = new JButton();
				btn[i][j].setBounds(100+17*(j+1), 100+17*(i+1), 17, 17);
				btn[i][j].setBackground(getBackground());
				btn[i][j].addActionListener(this);
				add(btn[i][j]);				
			}
		}		
		restart.setBounds(100+17*8, 100+17*22, 100, 50);
		restart.addActionListener(this);
		restart.setText("다시 시작");
		add(restart);
		
		result.setBounds(100+17*8, 100+17*9, 100, 50);
		result.setBackground(Color.GREEN);

		label_1.setBounds(180, 30, 1000, 80);
		add(label_1);
		order.setBounds(190, 50, 1000, 80);
		add(order);
	}
	
	public void update() {
		int player;
		if(win==0) {
			label_1.setText("환영합니다! 즐거운 오목 게임 되십시오!");
			if(turn%2==0) {
				player = 1;
				order.setText("이번 차례는 플레이어" + player + " 차례입니다.");
			}
			else {
				player = 2;
				order.setText("이번 차례는 플레이어" + player + " 차례입니다.");
			}
		}
		else {
			label_1.setText("플레이어"+win+"이(가) 이겼습니다! 축하합니다!!");
			order.setText(" ");
		}
	}

	public void Win() {
		int check =0;		
		for(int i=2; i<SIZE-2; i++) {
			for(int j=2; j<SIZE-2; j++) {
					if(omok[i][j] ==1 && omok[i][j-1] == 1 && omok[i][j-2] ==1 && omok[i][j+1]==1 && omok[i][j+2] ==1) {
						check = 1;
					}else if(omok[i][j] ==2 && omok[i][j-1] == 2 && omok[i][j-2] ==2 && omok[i][j+1]==2 && omok[i][j+2] ==2) {
						check =2;
					}
					if(omok[i][j] ==1 && omok[i-1][j] == 1 && omok[i-2][j] ==1 && omok[i+1][j]==1 && omok[i+2][j] ==1) {
						check =1;
					}else if(omok[i][j] ==2 && omok[i-1][j] == 2 && omok[i-2][j] ==2 && omok[i+1][j]==2 && omok[i+2][j] ==2) {						check = 2;
				}
			}
		}
		
		// "|" 대각선검사, 추가할 부분 몇개 더 있음. 
		for(int i=2; i<SIZE-2; i++) {
			for(int j=2; j<SIZE-2; j++) {
				if(omok[i][j] ==1 && omok[i-1][j-1] == 1 && omok[i-2][j-2] ==1 && omok[i+1][j+1]==1 && omok[i+2][j+2] ==1) {
					check = 1;
				}else if(omok[i][j] ==2 && omok[i-1][j-1] == 2 && omok[i-2][j-2] ==2 && omok[i+1][j+1]==2 && omok[i+2][j+2] ==2) {
					check = 2;
				}
			}
		}
		
		// "/" 방향 검사, 범위 추가해야함
		
		for(int i=2; i<SIZE-2; i++) {
			for(int j=2; j<SIZE-2; j++) {
				if(omok[i][j] ==1 && omok[i-1][j+1] == 1 && omok[i-2][j+2] ==1 && omok[i+1][j-1]==1 && omok[i+2][j-2] ==1) {
					check =1;
				}else if(omok[i][j] ==2 && omok[i-1][j+1] == 2 && omok[i-2][j+2] ==2 && omok[i+1][j-1]==2 && omok[i+2][j-2] ==2) {
					check =2;
				}
			}
		}
		
		if(check ==1) {
			win =1;
		}else if(check ==2) {
			win =2;
		}
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				if(turn %2 ==0) {
					if(omok[i][j] ==0) {
						if(e.getSource() == btn[i][j]) {
							btn[i][j].setBackground(Color.WHITE);
							omok[i][j] = 1;
							turn +=1;
						}
					}
				}else if(turn %2 ==1) {
					if(omok[i][j] ==0) {
						if(e.getSource() == btn[i][j]) {
							btn[i][j].setBackground(Color.BLACK);
							omok[i][j] = 2;
							turn +=1;
						}
					}
				}
			}
		}
		for(int i =0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				System.out.print(omok[i][j] +" ");
			}
			System.out.println();
		}
		
		System.out.println("win: "+win);
		System.out.println();
		
		if(e.getSource() == restart) {
			for(int i=0; i<SIZE; i++) {
				for(int j= 0; j<SIZE; j++) {
					btn[i][j].setBackground(getBackground());
				}
			}
			omok = new int[SIZE][SIZE];
			turn = 0;
			win = 0;
			//remove(result);
		}
		Win();
		if(win ==1) {
			add(result);
			result.setText("P1 승리!");
		}else if(win ==2) {
			add(result);
			result.setText("P2 승리!");
		}
	}
}


public class gui {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		myPanel mp = new myPanel();
		
		frame.setBounds(100, 100, 1000, 1000);
		frame.setTitle("오목");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(mp);
		while(true) {
			mp.update();
		}	

	}
}
