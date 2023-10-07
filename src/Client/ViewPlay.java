package Client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Client.Process;

public class ViewPlay extends JFrame {
	private Client main;
	private ViewMain vCli;
	final ViewPlay self = this;
	public static final int 
		xUnit=16,
		yUnit=9;
	public static int multiple=60;
	MainGame mainGame;
	public static JLabel namePlayer2; 
	public static JLabel namePlayer1; 
	
	public ViewPlay(Client main, ViewMain vClient){
		this.main = main;
		this.vCli = vClient;
		init();
	}
	void init(){
		setTitle("Ô ăn quan 3.0");
		setResizable(false);
		
		ImageIcon icon=new ImageIcon("src\\image\\stone.jpg");
		setIconImage(icon.getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        mainGame=new MainGame(main);
       
        namePlayer2 = new JLabel("");
		namePlayer2.setForeground(Color.WHITE);
		namePlayer2.setBorder(null);
		namePlayer2.setBackground(new Color(255, 255, 240));
		namePlayer2.setBounds(150, 21, 103, 26);
		mainGame.add(namePlayer2);
					
		namePlayer1 = new JLabel("");
		namePlayer1.setBorder(null);
		namePlayer1.setBackground(new Color(255, 250, 240));
		namePlayer1.setBounds(715, 461, 200, 50);
		mainGame.add(namePlayer1);
		
        getContentPane().add(mainGame);
		resize();
	}
	void resize() {
		setSize(xUnit*multiple,yUnit*multiple+50);
		mainGame.resize();
	}

}

