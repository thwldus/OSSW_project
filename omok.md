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
	private JLabel label_0 = new JLabel(); //제한시간
    private JLabel label_1 = new JLabel(); //환영문구
	private JLabel order = new JLabel();
	private int on = 1; //진행중이면 1, 승패결정 시 0 
    private JProgressBar time1 = new JProgressBar(JProgressBar.HORIZONTAL, 0,10);
	private JProgressBar time2 = new JProgressBar(JProgressBar.HORIZONTAL, 0,10);

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

		label_0.setBounds(220, 30, 1000, 80);
		label_1.setBounds(180, 10, 1000, 80);
		add(label_0);
		add(label_1);
		order.setBounds(190, 50, 1000, 80);
		add(order);
	
        JLabel p1 = new JLabel("◈Player 1◈");
		p1.setBounds(25, 280, 80, 20);
		add(p1);
		time1.setForeground(Color.PINK);
		time1.setBackground(getBackground());
		time1.setBounds(20, 300, 80, 20);
		time1.setValue(10);
		add(time1);
		JLabel p2 = new JLabel("◈Player 2◈");
		p2.setBounds(480, 280, 80, 20);
		add(p2);
		time2.setForeground(Color.PINK);
		time2.setBackground(getBackground());
		time2.setBounds(475, 300, 80, 20);
		time2.setValue(10);
		add(time2);
	}
	
	public void update() {
		int player;
		if(win==0) {
			label_0.setText("제한시간은 10초입니다.");
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
			label_0.setText(" ");
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
		 
		for(int i=2; i<SIZE-2; i++) {
			for(int j=2; j<SIZE-2; j++) {
				if(omok[i][j] ==1 && omok[i-1][j-1] == 1 && omok[i-2][j-2] ==1 && omok[i+1][j+1]==1 && omok[i+2][j+2] ==1) {
					check = 1;
				}else if(omok[i][j] ==2 && omok[i-1][j-1] == 2 && omok[i-2][j-2] ==2 && omok[i+1][j+1]==2 && omok[i+2][j+2] ==2) {
					check = 2;
				}
			}
		}
		
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
			on = 0;
		}else if(check ==2) {
			win =2;
			on = 0;
		}
		System.out.println("win: " + win);
		System.out.println("on: " + on);
		System.out.println();
		
	}
	
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				if(turn %2 ==0) {
					if(omok[i][j] ==0) {
						if(e.getSource() == btn[i][j]) {
							btn[i][j].setBackground(Color.BLACK);
							omok[i][j] = 1;
							turn +=1;
						}
					}
				}else if(turn %2 ==1) {
					if(omok[i][j] ==0) {
						if(e.getSource() == btn[i][j]) {
							btn[i][j].setBackground(Color.WHITE);
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
		
		if(e.getSource() == restart) {
			for(int i=0; i<SIZE; i++) {
				for(int j= 0; j<SIZE; j++) {
					btn[i][j].setBackground(getBackground());
				}
			}
			omok = new int[SIZE][SIZE];
			turn = 0;
			win = 0;
			on = 1;		
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

	public void timelimit() {
		int count = 0;
		if (turn % 2 == 0) {
		    time2.setValue(10);
			for (int i = 10; i >= 1; i--) 	
				if (turn % 2 == 0) {
					try {
						Thread.sleep(1000);
					}
					catch (InterruptedException e) {	}
					count++;
					label_0.setText("제한시간 "+(10-count)+"초 남았습니다.");
			        time1.setValue((10-count));
				}
			if (count == 10) {
				add(result);
				result.setText("시간초과 P2 승리");
				win = 2;
				on = 0;
				time1.setValue(10);
			}
		}
		else if (turn % 2 == 1) {
			for (int i = 10; i >= 1; i--)
				time1.setValue(10);
				if (turn % 2 == 1) {
					try {
						Thread.sleep(1000);
					}
					catch (InterruptedException e) {	}
					count++;
					label_0.setText("제한시간 "+(10-count)+"초 남았습니다.");
			        time2.setValue((10-count));
				}
			if (count == 10) {
				add(result);
				result.setText("시간초과 P1 승리");
				win = 1;
				on = 0;
				time2.setValue(10);
			}
		}	
	}
	
    public int on() { return on; }

}

public class gui {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		myPanel mp = new myPanel();
		frame.setBounds(100, 100, 600, 600);
		frame.setTitle("오목");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(mp);
		while(true) {
			while(mp.on()==1){
		        mp.update();
			    mp.timelimit();
			}
			mp.update();
		}	

	}
}
