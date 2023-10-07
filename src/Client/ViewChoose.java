package Client;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.Service;
import model.Player;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;

public class ViewChoose extends JFrame {
	private Client main;
	private ViewMain vCli;
	private ViewClient vCli2;
	final ViewChoose self = this;

	private JPanel contentPane;
	private JButton btnNewButton_1;
	public JLabel ten;
	public JLabel diem;
	private JButton btn_NhapID;
	
  
	public ViewChoose(Client main, ViewMain vClient)  {
		this.main = main;
		this.vCli = vClient;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 979, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		ImageIcon tp = new ImageIcon("src/Icon/St.jpg");
		
		JButton TaoPhong = new JButton("Tạo Phòng");
		TaoPhong.addActionListener(new Lis());
		TaoPhong.setBorder(null);
		TaoPhong.setBackground(new Color(245, 222, 179));
		TaoPhong.setForeground(new Color(160, 82, 45));
		TaoPhong.setBounds(724, 335, 206, 137);
		TaoPhong.setIcon(tp);
		contentPane.add(TaoPhong);
		
		ImageIcon ten3 = new ImageIcon("src/Icon/ten3.jpg");
		
		JButton Back = new JButton("");
		Back.addActionListener(new Lis());
		Back.setBounds(new Rectangle(4, 0, 0, 0));
		Back.setBorder(null);
		Back.setBackground(new Color(210, 105, 30));
		Back.setBounds(10, 437, 81, 58);
		Back.setIcon(ten3);
		contentPane.add(Back);
		
		ImageIcon nid = new ImageIcon("src/Icon/nid.jpg");
		
		btn_NhapID = new JButton("Nhập id phòng");
		btn_NhapID.addActionListener(new Lis());
		btn_NhapID.setBorder(null);
		btn_NhapID.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btn_NhapID.setBackground(new Color(160, 82, 45));
		btn_NhapID.setForeground(new Color(255, 255, 255));
		btn_NhapID.setBounds(57, 35, 237, 87);
		btn_NhapID.setIcon(nid);
		contentPane.add(btn_NhapID);
		
	 	ten = new JLabel();
	 	ten.setForeground(SystemColor.activeCaption);
	 	ten.setFont(new Font("Times New Roman", Font.BOLD, 14));
	 	//ten.setText("Tên tài khoản: ");
		ten.setBounds(462, 180, 223, 64);
		contentPane.add(ten);
		
	    diem = new JLabel();
	    diem.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    diem.setText("Điểm số: ");
		diem.setBounds(523, 229, 102, 42);
		contentPane.add(diem);
		
		Image img = Toolkit.getDefaultToolkit().getImage("src/image/viewChoose.jpg");		
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
					if (e.getActionCommand().equals(""))
					{	
						self.setVisible(false);
				        vCli2.setVisible(true);
					}
					else if(e.getActionCommand().equals("Nhập id phòng"))
					{
						main.getThread().doSendData("Nhap_ID",ten.getText());
						self.setVisible(false);
						
					}else if(e.getActionCommand().equals("Tạo Phòng"))
					{
						main.getThread().doSendData("TaoPhong",ten.getText());	
						self.setVisible(false);
						
					}
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		}
		
	}
   
}
