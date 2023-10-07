package Client;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	 
	public static void main(String[] args) {
		Client cli = new Client();
		cli.start();
	}
	
	private ViewMain 		frame  ;
	private ViewSignUp 		frameS ;
	private ViewChoose 		frameC ;
	private ViewInvite 		frameI ;
	private ViewEnter 		frameE ;
	private ViewPlay		frameP ;
	private ViewClient	    frameV ;
	private ThreadClient 	thread ;
	private Client			self   = this ;
	
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					frame = new ViewMain(self);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					
					frameS = new ViewSignUp(self,frame);
					
					frameV = new ViewClient(self,frame);
					frameC = new ViewChoose(self,frame);
				
					frameI = new ViewInvite(self,frame);  // tạo phòng
					
					frameE = new ViewEnter  (self,frame);// nhập id
					 
					frameP = new ViewPlay  (self,frame);
					
					doConnect ("localhost",1234);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void doConnect (String sv, int port) throws Exception{
		Socket s = new Socket(sv, port);
		thread = new ThreadClient (s, frame,frameS,frameC,frameV,frameI,frameE,frameP);
		thread.start();
	}
	public ThreadClient getThread() {
		return thread;
	}
}
