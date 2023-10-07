package Client;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ViewEnter extends JFrame {
	private Client main;
	private ViewMain vCli;
	private JPanel contentPane;
	private JTextField txtNhapId;
	public JLabel you,me;
	final ViewEnter self = this;

	public ViewEnter(Client main, ViewMain vClient) {
		this.main = main;
		this.vCli = vClient;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 979, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JButton Start = new JButton("START");
		Start.addActionListener(new Lis());
		Start.setBorder(null);
		Start.setBackground(new Color(205, 133, 63));
		Start.setForeground(new Color(248, 248, 255));
		Start.setBounds(816, 430, 81, 35);
		contentPane.add(Start);
		
		txtNhapId = new JTextField();
		txtNhapId.setForeground(new Color(169, 169, 169));
		txtNhapId.setText("Nhập id");
		txtNhapId.setBounds(383, 354, 202, 35);
		contentPane.add(txtNhapId);
		txtNhapId.setColumns(10);
		
		ImageIcon ten3 = new ImageIcon("src/Icon/ten3.jpg");
		
		JButton back = new JButton("");
		back.addActionListener(new Lis());
		back.setBounds(new Rectangle(4, 0, 0, 0));
		back.setBorder(null);
		back.setBackground(new Color(210, 105, 30));
		back.setBounds(10, 430, 78, 65);
		back.setIcon(ten3);
		contentPane.add(back);
		
		Image img = Toolkit.getDefaultToolkit().getImage("src/image/viewWait.jpg");
		
		you = new JLabel("New label");
		you.setBounds(318, 223, 86, 28);
		contentPane.add(you);
		
		me = new JLabel("New label");
		me.setBounds(563, 223, 86, 28);
		contentPane.add(me);
		
		JButton btn_OK = new JButton("OK");
		btn_OK.addActionListener(new Lis());
		btn_OK.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_OK.setForeground(new Color(139, 0, 0));
		btn_OK.setBackground(new Color(222, 184, 135));
		btn_OK.setBorder(null);
		btn_OK.setBounds(438, 437, 61, 21);
		contentPane.add(btn_OK);
		
		
		JLabel nen = new JLabel("");
		nen.setBackground(new Color(160, 82, 45));
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
						main.getThread().doSendData("ViewEnter_Start");
					    
					}
					else if(e.getActionCommand().equals(""))
					{
						main.getThread().doSendData("GetDVC");	
						 self.setVisible(false);
						
					}else if(e.getActionCommand().equals("OK"))
					{
						main.getThread().doSendData("Start_E",me.getText(),txtNhapId.getText());
					
					}
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		}
		
	}
}
