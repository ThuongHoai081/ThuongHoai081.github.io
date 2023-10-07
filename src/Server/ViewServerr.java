package Server;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Client.ViewClient;
import Client.ViewSignUp;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.ScrollPane;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewServerr extends JFrame {

	private Server     main;
	final   ViewServerr self = this;
	
	private JPanel contentPane;
	Vector vData = new Vector();
	Vector vTitle = new Vector();
	JScrollPane sc;
	DefaultTableModel model;
	JTable tb=new JTable();
	JButton btnConnect,btnDisConnect;
	public JLabel ten = new JLabel();
	//
	
	String[] colums = {"Player online"};
	String[][] Data = {};

	public ViewServerr(Server main) {
		this.main = main;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 792, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		 
		model = new DefaultTableModel(vData,vTitle);
        load();
		tb = new JTable(model);
		tb.setBackground(new Color(245, 222, 179));
		tb.setBounds(10, 10, 758, 374);
		 
		sc = new JScrollPane(tb);
		sc.setBounds(368, 10, 373, 357);
		getContentPane().add(sc);
		
		ten.setBounds(265, 268, 68, 53);
		contentPane.add(ten);
		
		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new Lis());
		btnConnect.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnConnect.setBorder(null);
		btnConnect.setBackground(new Color(245, 245, 220));
		btnConnect.setForeground(new Color(139, 69, 19));
		btnConnect.setBounds(118, 252, 102, 41);
		contentPane.add(btnConnect);
		 ImageIcon originalIcon = new ImageIcon("src/icon/iconuser.png");

	        // Lấy Image từ ImageIcon
	     Image originalImage = originalIcon.getImage();

	        // Chỉnh kích thước của Image
	     Image resizedImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

	        // Tạo một ImageIcon mới từ Image đã chỉnh kích thước
	     ImageIcon resizedIcon = new ImageIcon(resizedImage);
		
		JLabel user = new JLabel("");
		user.setBounds(118, 89, 154, 128);
		user.setIcon(resizedIcon);
		contentPane.add(user);
		
		btnDisConnect = new JButton("DisConnect");
		btnDisConnect.addActionListener(new Lis()); 
		btnDisConnect.setForeground(new Color(139, 69, 19));
		btnDisConnect.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDisConnect.setBorder(null);
		btnDisConnect.setBackground(new Color(245, 245, 220));
		btnDisConnect.setBounds(118, 313, 102, 41);
		contentPane.add(btnDisConnect);
		btnDisConnect.setEnabled(false);
		
	
		
		
	}
  public void doDisplay (String na) {
	  
	 Vector columData = new Vector();
	 columData.add(na);
	 System.out.println("na: "+na);
	 vData.add(columData);
	 model.fireTableDataChanged();
	 repaint();
	}
  public void doRemove (String na) {
	  
	  for (int i = 0; i < model.getRowCount(); i++) {
          String name = (String) model.getValueAt(i, 0);
          if (name.equals(na)) {
              model.removeRow(i);
              return; 
          }
      }
		 model.fireTableDataChanged();
		 repaint();
		}
  
	private void load() 
	{
		try 
		{
			vTitle.clear();
			vData.clear();
			int num_column = colums.length;
			for(int i=0;i<num_column;i++)
			{
				vTitle.add(colums[i]);
			}
			for(int i=0;i<Data.length;i++)
			{
				Vector row = new Vector(num_column);
				for(int j=0;j<num_column;j++)
					row.add(Data[i][j]);
					vData.add(row);	
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public class Lis implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand().equals("Connect"))
				{
					JOptionPane.showMessageDialog(null, "Connect!!");
					 main.doConnect(1234);
					 btnDisConnect.setEnabled(true);
				}
				if (e.getActionCommand().equals("DisConnect"))
				{
					JOptionPane.showMessageDialog(null, "DisConnect!!");					
				    main.doDisConnect();
				    
					
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
