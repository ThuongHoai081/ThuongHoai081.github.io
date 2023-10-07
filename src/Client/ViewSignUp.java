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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import Client.ViewMain;
import DAO.Service;
import model.Player;

import javax.swing.JPasswordField;

public class ViewSignUp extends JFrame {
	private Client main;
	private ViewMain vCli;
	final ViewSignUp self = this;
	
	private JPanel contentPane;
	private JTextField tf_name;
	private JTextField tf_email;
	private JPasswordField pass;
	private JPasswordField pass2;
	
	public ViewSignUp(Client main, ViewMain vClient) {
		this.main = main;
		this.vCli = vClient;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 979, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Image img = Toolkit.getDefaultToolkit().getImage("src\\image\\InSignUp.jpg");		
		contentPane.setLayout(null);
		
		JButton OK = new JButton("OK");
		OK.addActionListener(new Lis());
		OK.setForeground(new Color(160, 82, 45));
		OK.setBackground(new Color(245, 222, 179));
		OK.setBounds(823, 417, 78, 43);
		contentPane.add(OK);
		
		JButton Cancel = new JButton("");
		Cancel.setBorder(null);
		Cancel.addActionListener(new Lis());
		 ImageIcon ten = new ImageIcon("src\\Icon\\ten.jpg");
		Cancel.setBackground(new Color(245, 222, 179));
		Cancel.setForeground(new Color(160, 82, 45));
		Cancel.setBounds(10, 10, 78, 59);
		Cancel.setIcon(ten);
		contentPane.add(Cancel);
		
		tf_name = new JTextField();
		tf_name.setBackground(new Color(205, 133, 63));
		tf_name.setBounds(336, 187, 124, 30);
		contentPane.add(tf_name);
		tf_name.setColumns(10);
		
		tf_email = new JTextField();
		tf_email.setBackground(new Color(205, 133, 63));
		tf_email.setColumns(10);
		tf_email.setBounds(544, 263, 124, 30);
		contentPane.add(tf_email);
		
		pass = new JPasswordField();
		pass.setBackground(new Color(205, 133, 63));
		pass.setBounds(336, 348, 124, 30);
		contentPane.add(pass);
		
		pass2 = new JPasswordField();
		pass2.setBackground(new Color(205, 133, 63));
		pass2.setBounds(544, 402, 124, 30);
		contentPane.add(pass2);
		JLabel nen = new JLabel("");
		nen.setBounds(0, 0,965,505);
		getContentPane().add(nen);
		img = img.getScaledInstance(nen.getWidth(),nen.getHeight() ,Image.SCALE_SMOOTH);
		nen.setIcon(new ImageIcon(img));
	}
	
    public void doCheckMsgFromServer2 (String cmd) {
		
		if (cmd.equals("SignUp_OK")) {
			JOptionPane.showMessageDialog(null, "Đăng Ký Thành Công!!");
			vCli.setVisible(true);
			self.setVisible(false);
		}
		else if(cmd.equals("SignUp_KO")) {
			JOptionPane.showMessageDialog(null, "Đăng Ký Không Thành Công!!");
		}
	}
	public class Lis implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				
				if (e.getActionCommand().equals("OK"))
				{
					main.getThread().doSendData ("SignUp", tf_name.getText(), pass.getText());
					
				}else if (e.getActionCommand().equals("")) {
					vCli.setVisible(true);
					self.setVisible(false);
					
				}
			}
				catch(Exception ex)
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,"Lỗi");
				}
			}
		}
}
