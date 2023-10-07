package Client;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Player;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import Client.ViewMain;
import Client.ViewMain.Lis;
import Client.ViewChoose;

public class ViewClient extends JFrame {
	private Client main;
	private ViewMain vCli;
	final ViewClient self = this;
	
	private JPanel contentPane;
	public String name,email;
	public int score;

	public ViewClient(Client main, ViewMain vClient) {
		this.main = main;
		this.vCli = vClient;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 979, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Start = new JButton("START");
		Start.setBorder(null);
		Start.addActionListener(new Lis());
		Start.setBackground(new Color(192, 192, 192));
		Start.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Start.setForeground(new Color(240, 255, 255));
		Start.setBounds(648, 244, 154, 40);
		contentPane.add(Start);

		Image img = Toolkit.getDefaultToolkit().getImage("src/image/view.jpg");		
		
		ImageIcon logout = new ImageIcon("src/Icon/logout.jpg");
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new Lis());
		btnLogout.setBorder(null);
		btnLogout.setBounds(883, 10, 72, 56);
		btnLogout.setIcon(logout);
		contentPane.add(btnLogout);
		
		JLabel nen = new JLabel("");
		nen.setBounds(0, 0,965,505);
		getContentPane().add(nen);
		img = img.getScaledInstance(nen.getWidth(),nen.getHeight() ,Image.SCALE_SMOOTH);
		nen.setIcon(new ImageIcon(img));
	}
	
	public class Lis implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
					if (e.getActionCommand().equals("START"))
					{	
						main.getThread().doSendData("GetDVC");
						self.setVisible(false);
				
					}else if (e.getActionCommand().equals("Logout"))
					{	
						int result = JOptionPane.showConfirmDialog(self,
			                 "Bạn có muốn đăng xuất không!",
			                 "Xác nhận",
			                 JOptionPane.YES_NO_OPTION,
			                 JOptionPane.QUESTION_MESSAGE);
			         if(result == JOptionPane.YES_OPTION){
			        	 main.getThread().doSendData("Logout");
							self.setVisible(false);
							ViewMain vm = new ViewMain(main);
							vm.setVisible(true);
					
			         }
						
					}
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		}
		
	}
}
