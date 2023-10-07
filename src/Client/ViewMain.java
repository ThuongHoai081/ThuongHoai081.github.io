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

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import DAO.Service;
import DAO.Connect;
import model.Player;

public class ViewMain extends JFrame {

	private Client main;
	final ViewMain self = this;
	
	private JPanel contentPane;
	private JTextField name;
	private JPasswordField password;
	private JButton OK;
	
	public ViewMain(Client main) {
		this.main = main;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 979, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon in = new ImageIcon("src/Icon/Ok.jpg");
		
		OK = new JButton("Login");
		OK.addActionListener(new Lis());
		OK.setBorder(null);
		OK.setForeground(new Color(160, 82, 45));
		OK.setBackground(new Color(205, 133, 63));
		OK.setBounds(867, 373, 49, 30);
		OK.setIcon(in);
		contentPane.add(OK);
		
		ImageIcon can = new ImageIcon("src/Icon/Cancel.jpg");
		JButton insignUp = new JButton("SignUp");
		insignUp.addActionListener(new Lis());
		insignUp.setBorder(null);
		insignUp.setForeground(new Color(160, 82, 45));
		insignUp.setBackground(new Color(255, 255, 255));
		insignUp.setBounds(845, 429, 92, 30);
		insignUp.setIcon(can);
		contentPane.add(insignUp);
		
		name = new JTextField();
		name.setBackground(new Color(205, 133, 63));
		name.setColumns(10);
		name.setBounds(727, 212, 133, 30);
		contentPane.add(name);
		
		password = new JPasswordField();
		password.setBackground(new Color(205, 133, 63));
		password.setBounds(727, 301, 133, 30);
		contentPane.add(password);
		
		Image img = Toolkit.getDefaultToolkit().getImage("src/image/SignUp.jpg");		
		JLabel nen = new JLabel("");
		nen.setBounds(0, 0,965,505);
		getContentPane().add(nen);
		img = img.getScaledInstance(nen.getWidth(),nen.getHeight() ,Image.SCALE_SMOOTH);
		nen.setIcon(new ImageIcon(img));
		
	}
	

	public void doCheckMsgFromServer (String cmd) {
		
		if (cmd.equals("login_OK")) {
			ViewClient lg = new ViewClient(main, self);
			lg.setVisible(true);
			this.setVisible(false);
		}
		else if(cmd.equals("login_KO")) {
			JOptionPane.showMessageDialog(null, "Đăng Nhập Không Thành Công!!");
		}
	}

	public class Lis implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand().equals("Login"))
				{
					main.getThread().doSendData ("login", name.getText(), password.getText());					 
				}
				else if (e.getActionCommand().equals("SignUp")) {
					main.getThread().doSendData ("signUp");
					
					ViewSignUp lg = new ViewSignUp(main, self);
					lg.setVisible(true);
					self.setVisible (false); 
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
