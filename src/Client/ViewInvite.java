package Client;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;

public class ViewInvite extends JFrame {

	private Client main;
	private ViewMain vCli;
	public JLabel me,you, id;
	final ViewInvite self = this;

	private JPanel contentPane;

	public ViewInvite(Client main, ViewMain vClient) {
		this.main = main;
		this.vCli = vClient;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 979, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		id = new JLabel("New label");
		id.setBounds(389, 355, 182, 35);
		contentPane.add(id);
		
		ImageIcon ten3 = new ImageIcon("src/Icon/ten3.jpg");
		JButton back = new JButton("");
		back.addActionListener(new Lis());
		back.setBounds(new Rectangle(4, 0, 0, 0));
		back.setBorder(null);
		back.setBackground(new Color(255, 250, 250));
		back.setBounds(10, 440, 81, 55);
		back.setIcon(ten3);
		contentPane.add(back);
		
		JButton Start = new JButton("START");
		Start.setBorder(null);
		Start.addActionListener(new Lis());
		Start.setBackground(new Color(205, 133, 63));
		Start.setForeground(new Color(248, 248, 255));
		Start.setBounds(816, 430, 81, 35);
		contentPane.add(Start);
		
		you = new JLabel("New label");
		you.setBounds(318, 223, 86, 28);
		contentPane.add(you);
		
		me = new JLabel("New label");
		me.setBounds(563, 223, 86, 28);
		contentPane.add(me);
		
		Image img = Toolkit.getDefaultToolkit().getImage("src/image/viewWait.jpg");
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
						main.getThread().doSendData("ViewInvite_Start");
						
					}
					else if(e.getActionCommand().equals(""))
					{
						main.getThread().doSendData("GetDVC");	
						 self.setVisible(false);
					}
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		}
		
	}
}
