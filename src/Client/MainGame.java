package Client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;


public class MainGame extends JPanel {
	Client	main;
	Process process;
	int 
		currentTeam=1,
		numberInBox=5,
		numberInScoreBox=10,
		multiple=ViewPlay.multiple;
	final int 	
		xUnit=ViewPlay.xUnit,
		yUnit=ViewPlay.yUnit;
	public int speed=4;
	public MainGame(Client main) {
		this.main = main;
		init();
	}
	public void init() {
		setLocation(0,0);
		setLayout(null);
		
		process=new Process(this, main);
		process.init();
		process.reDraw();
		resize();
	}
	public void resize() {
		multiple=ViewPlay.multiple;
		setSize(319,173);
		process.resize();
	}

	public void nextTurn() {
		currentTeam=1-currentTeam;
		process.nextTurn();
	}
	public int getCurTeam() {
		return currentTeam;
	}
	public int getNumberInBox() {
		return numberInBox;
	}
	public int getNumberInScoreBox() {
		return numberInScoreBox;
	}
	public void setNumberInBox(int n) {
		numberInBox=n;
	}
	public void setNumberInScoreBox(int n) {
		numberInScoreBox=n;
	}
	public void setCurTeam(int n) {
		this.currentTeam = n;
	}

   @Override
   protected void paintComponent(Graphics g) {
       Graphics2D g2d = (Graphics2D) g;
       Image i=new ImageIcon("src\\image\\play.jpg").getImage();
       g2d.drawImage(i, 0, 0, xUnit*multiple, yUnit*multiple, null);
   }
} 

