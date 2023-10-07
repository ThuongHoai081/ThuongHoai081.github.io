package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import model.Player;

public class ThreadClient extends Thread{
	private ViewMain viewMain;
	private ViewSignUp viewSignUp;
	private ViewClient  viewClient;
	private ViewChoose   viewChoose;
	private ViewInvite   viewInvite;
	private ViewEnter   viewEnter;
	private ViewPlay   viewPlay;
	private Socket socket;
	private DataOutputStream dos ;
	private DataInputStream dis;
	private static final String c = "<?>";
	private Player lg = new Player();
	private Player pl2 = new Player();

	public ThreadClient(Socket socket, ViewMain viewM, ViewSignUp viewS,ViewChoose viewC ,ViewClient view, ViewInvite viewI,ViewEnter viewE,ViewPlay viewP) {
		this.socket     = socket;
		this.viewMain   = viewM ;
		this.viewSignUp = viewS ;
		this.viewChoose = viewC ;
		this.viewClient = view  ;
		this.viewInvite = viewI ;
		this.viewEnter   = viewE ;
		this.viewPlay   = viewP ;
		
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (!socket.isClosed()) {
				String 			msg 	= dis.readUTF();
				StringTokenizer cont 	= new StringTokenizer(msg, c);
				String 			cmd 	= cont.nextToken();
				
						
				if(cmd.equals("login_OK") || cmd.equals("login_KO")) {
					
					viewMain.doCheckMsgFromServer (cmd);
					
				  if(cmd.equals("login_OK")) {
					String          name     = cont.nextToken();
					String          score    = cont.nextToken();
					
					lg.setTenDangNhap(name);
					lg.setSocer(score);
					doSendData("LoginOK",lg.getTenDangNhap());
				 
				}

				}else if(cmd.equals("SignUp_OK") || cmd.equals("SignUp_KO")) {
					
					viewSignUp.doCheckMsgFromServer2(cmd);
					
				}else if(cmd.equals("GetDVC")){
					
					viewChoose.ten.setText(lg.getTenDangNhap());
  				    viewChoose.diem.setText("Điểm : " + lg.getSocer());
  				    
					viewChoose.setVisible(true);
					System.out.println(lg.getTenDangNhap());
					
				}else if(cmd.equals("Back")){
					
					viewChoose.ten.setText(lg.getTenDangNhap());
  				    viewChoose.diem.setText("Điểm : " + lg.getSocer());
  				    
  				    viewPlay.setVisible(false);
					viewChoose.setVisible(true);
					
				}
				else if(cmd.equals("ViewInvite_Start")) {
					String name2 = cont.nextToken();
					String score2 = cont.nextToken();
					String cmd2 = cont.nextToken();
					
					viewInvite.you.setText(name2);
					viewInvite.repaint();
					
                   if(cmd2.equals("Start")) {
                	   if(viewInvite.you.getText().equals(name2)) {
                		   int result = JOptionPane.showConfirmDialog(viewPlay,
			                        "Start!",
			                        "Xác nhận",
			                        JOptionPane.YES_NO_OPTION,
			                        JOptionPane.QUESTION_MESSAGE);
			                if(result == JOptionPane.YES_OPTION){
			                	String id = cont.nextToken();
			                	viewPlay.namePlayer1.setText(lg.getTenDangNhap());
								viewPlay.namePlayer2.setText(name2);
								
								
								viewPlay.setVisible(true);
								viewEnter.setVisible(false);
								viewInvite.setVisible(false);
			                }else if (result == JOptionPane.NO_OPTION){
			                	viewInvite.setVisible(true);
			                }
                	   }
                	   else {
                		   JOptionPane.showMessageDialog(null, "player2 null!!");
                	   }
   				 }
				}

				else if(cmd.equals("Start_E")) {					
					String name2 = cont.nextToken();
					String score2 = cont.nextToken();
					
					pl2.setTenDangNhap(name2);
					pl2.setSocer(score2);
		
					viewEnter.you.setText(name2);
					viewEnter.repaint();
					
					
			
				}else if(cmd.equals("ViewEnter_Start")) {	
					if(viewEnter.you.getText().equals(pl2.getTenDangNhap())) {
						 int result = JOptionPane.showConfirmDialog(viewPlay,
			                        "Start!",
			                        "Xác nhận",
			                        JOptionPane.YES_NO_OPTION,
			                        JOptionPane.QUESTION_MESSAGE);
						 
			                if(result == JOptionPane.YES_OPTION){
			                	String id = cont.nextToken();

			                	viewPlay.namePlayer1.setText(lg.getTenDangNhap());
			                	viewPlay.namePlayer2.setText(pl2.getTenDangNhap());
			                	
			                	viewPlay.mainGame.process.scBox[0].setTurn(true);
			                	viewPlay.mainGame.process.scBox[1].setTurn(false);

								
								viewPlay.setVisible(true);
								viewEnter.setVisible(false);
								viewInvite.setVisible(false);
								
								for(int i=0;i<12;i++) {
			                		viewPlay.mainGame.process.box[i].boxBtn.setEnabled(false);
			                	}
								
								
			                }else if (result == JOptionPane.NO_OPTION){
			                	viewEnter.setVisible(true);
			                }
					}else {
						JOptionPane.showMessageDialog(null, "player2 null!!");
					}
						
						
			
				}else if(cmd.equals("Nhap_ID_OK")){
					
					viewEnter.me.setText(lg.getTenDangNhap());
				
					viewEnter.setVisible(true);
					viewChoose.setVisible(false);
					
					
				}else if(cmd.equals("TaoPhong_OK")){
			        
					String id_r = cont.nextToken();
					viewInvite.me.setText(lg.getTenDangNhap());
					viewInvite.id.setText(id_r);
					
					viewInvite.setVisible(true);
					viewChoose.setVisible(false);
				
				}

				else if(cmd.equals("reset")){
					viewPlay.mainGame.setCurTeam(1);
					String name = cont.nextToken();

					if(viewPlay.namePlayer2.getText().equals(name)) {
						for(int i = 6; i<=11 ; i++) {
							viewPlay.mainGame.process.box[i].change(Integer.parseInt(cont.nextToken()));
						}
						
						for(int i = 0; i<=5 ; i++) {
							viewPlay.mainGame.process.box[i].change(Integer.parseInt(cont.nextToken()));
						}
						viewPlay.mainGame.process.scBox[0].change(Integer.parseInt(cont.nextToken()));
						viewPlay.mainGame.process.scBox[1].change(Integer.parseInt(cont.nextToken()));
						
						viewPlay.mainGame.process.scBox[1].setTurn(true);
						viewPlay.mainGame.process.scBox[0].setTurn(false);
						
						for(int i=0;i<12;i++) {							
	                		viewPlay.mainGame.process.box[i].boxBtn.setEnabled(true);
	                	}
						
						
					}else if(viewPlay.namePlayer1.getText().equals(name)) {
						viewPlay.mainGame.process.scBox[1].setTurn(false);
						viewPlay.mainGame.process.scBox[0].setTurn(true);
						for(int i=0;i<12;i++) {							
	                		viewPlay.mainGame.process.box[i].boxBtn.setEnabled(false);
	                	}
					}
					viewPlay.repaint();
				
				}else if(cmd.equals("End")){
			        
					String name = cont.nextToken();
					if(viewPlay.namePlayer1.getText().equals(name))
					{
						 JOptionPane.showConfirmDialog(viewPlay,"Thua rồi!!","",JOptionPane.DEFAULT_OPTION);
						 int result = JOptionPane.showConfirmDialog(viewPlay,
				                 "Chơi lại!",
				                 "Xác nhận",
				                 JOptionPane.YES_NO_OPTION,
				                 JOptionPane.QUESTION_MESSAGE);
				         if(result == JOptionPane.YES_OPTION){
				        	 viewPlay.mainGame.process.reDraw();
				         }else if (result == JOptionPane.NO_OPTION){
				        	doSendData("Back");
				         }
						
					}else {
						 JOptionPane.showConfirmDialog(viewPlay,"Bạn đã thắng!!","Chuc mung",JOptionPane.DEFAULT_OPTION);
						 int result = JOptionPane.showConfirmDialog(viewPlay,
				                 "Chơi lại!",
				                 "Xác nhận",
				                 JOptionPane.YES_NO_OPTION,
				                 JOptionPane.QUESTION_MESSAGE);
				         if(result == JOptionPane.YES_OPTION){
				        	 viewPlay.mainGame.process.reDraw();
				         }else if (result == JOptionPane.NO_OPTION){
				        	doSendData("Back");
				         }
					}
				
				}


			System.out.println("cmdcln: "+cmd+",msg: "+msg);
	}

		} catch (IOException e) {
			close();			
		}
	}
	
	public void doSendData(String cmd, String...cont) {
		try {
			synchronized(dos) {
				String msg = cmd;
				for (String s: cont) msg = msg + c + s ;
				dos.writeUTF(msg);
			}
			
		}catch(Exception e) {
			
		}
	}
	
	public void close() {
		try {
			if (socket.isConnected()) {
				socket.close();
			}
			
			if (!dis.equals(null)) {
				dis.close();
			}
			
			if (!dos.equals(null)) {
				dos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
