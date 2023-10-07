package Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class Box implements ActionListener{
	public JButton boxBtn, lArrow, rArrow;
	private JLabel numLabel=new JLabel("5");
	private int	num=5,
				pos;
	private float mul=ViewPlay.multiple/20;;
	final int	NUM_LABEL_SIZE=14,
				BUTTON_WIDTH=20,
				BUTTON_HEIGHT=37;
	private static Font	fontArrow;
	private Process process;
	private MainGame mainGame;
	Client main;
	Box(MainGame m,Process p, int pos){
		mainGame=m;
		process=p;
		fontArrow=new Font("Times New Romans",Font.PLAIN,(int)(10*mul));
		this.pos=pos;
		create();
		createArrow();
		resize();
		mainGame.add(boxBtn);
		
	}
	void create() { //pos = 0~11
		boxBtn=new JButton();
		boxBtn.setFocusable(false);
		setOutlook(boxBtn);
		boxBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				process.removeAllArrow();
				int team=mainGame.getCurTeam();
				if(team==1&&pos<6&&num>0) showArrow();
			}
		});
		numLabel.setFont(fontArrow);
		mainGame.add(numLabel);
		mainGame.add(boxBtn);
	}
	void createArrow() {
		int row;
		if(pos<6) row=1;
			else row=0;
		rArrow=new JButton("\u25B6");
		lArrow=new JButton("\u25C0");
		ActionListener r=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int chieu;
				if (row == 0) {
				    chieu = -1;
				} else {
				    chieu = 1;
				}
				removeArrow();
				process.move(pos,chieu,false);
			}
		};
		ActionListener l=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int chieu;
				if (row == 0) {
				    chieu = 1;
				} else {
				    chieu = -1;
				}
				removeArrow();
				process.move(pos,chieu,false);
				
			}
		};
		
		setOutlook(rArrow);
		setOutlook(lArrow);
		rArrow.addActionListener(r);
		lArrow.addActionListener(l);
		mainGame.add(rArrow);
		mainGame.add(lArrow);
		rArrow.setVisible(false);
		lArrow.setVisible(false);
	}
	void resize() {
		int col,row;
		mul=ViewPlay.multiple/20;
		fontArrow=new Font("Times New Romans",Font.PLAIN,(int)(10*mul));
		setOutlook(boxBtn);
		setOutlook(rArrow);
		setOutlook(lArrow);
		numLabel.setFont(fontArrow);
		if(pos<6) col=pos+1;
			else if(pos<11) col=11-pos;
				else col=0;
		if(pos<6) row=1;
			else row=0;
		if(col!=0&&col!=6) 
			boxBtn.setBounds(
				(int)(35*mul+col*39*mul),
				(int)(49*mul+row*38*mul),
				(int)(BUTTON_WIDTH*mul),
				(int)(BUTTON_HEIGHT*mul));
		if(col!=0&&col!=6) 
			numLabel.setBounds(
				(int)(28*mul+col*39*mul),
				(int)(52*mul+row*36*mul),
				(int)(NUM_LABEL_SIZE*mul),
				(int)(NUM_LABEL_SIZE*mul));
		else 
			numLabel.setBounds(
				(int)(50*mul+col*35*mul),
				(int)(58*mul),
				(int)(NUM_LABEL_SIZE*mul),
				(int)(NUM_LABEL_SIZE*mul));
		rArrow.setBounds(
			(int)(65*mul+col*39*mul),
			(int)(63*mul+row*35*mul),
			(int)(12*mul),
			(int)(14*mul));
		lArrow.setBounds(
			(int)(54*mul+(col-1)*39*mul),
			(int)(63*mul+row*35*mul),
			(int)(12*mul),
			(int)(14*mul));
	}
	void change(int n){
		num=n;
		numLabel.setText(Integer.toString(num));
	}

	void showArrow() {
		rArrow.setVisible(true);
		lArrow.setVisible(true);
		mainGame.repaint();
	}
	void removeArrow() {
		rArrow.setVisible(false);
		lArrow.setVisible(false);
		mainGame.repaint();
	}

	void setOutlook(JButton b){
		b.setFocusPainted(false);
		b.setFont(fontArrow);
		
		b.setMargin(new Insets(0,0,0,0));
		b.setHorizontalAlignment(SwingConstants.LEFT);
		b.setVerticalAlignment(SwingConstants.TOP);
		
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
	}
	
	int getNum() {
		return num;
	}
	void setColor(Color c) {
		boxBtn.setForeground(c);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
}
