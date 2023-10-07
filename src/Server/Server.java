package Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import Client.Client;
import Client.ThreadClient;
import Client.ViewMain;
import DAO.Service;
import model.Player;

public class Server {
	private List<ThreadServer> threads = ThreadServer.threads;
	private ViewServerr 		frame  ;
	private Server			self   = this ;
	 ThreadServer 	thread ;
	Socket sk;
	private	ServerSocket sv;
	public void StartServer()
	{
		try {	

			  frame = new ViewServerr(self);
			  frame.setVisible(true);
			  frame.setLocationRelativeTo(null);
			 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		Server sv = new Server();
		sv.StartServer();
	}
	
	public void doConnect (int port) throws Exception{
		sv = new ServerSocket(port);
		System.out.println("Server đang chạy trên cổng " + port);
					
		Thread thr = new Thread() {
			public void run() {
				while (!sv.isClosed()) {
					try {
						sk = sv.accept();
						System.out.println("Đã kết nối tới " + sk.getRemoteSocketAddress());
						
						ThreadServer th = new ThreadServer(sk, frame);
						th.start();
					} catch (IOException e) {
						doDisConnect();
					}
					
			     }
			}
		};
		thr.start();
		
	}
	public ThreadServer getThread() {
		return thread;
	}
	public void doDisConnect() {
	    try {
	    	System.out.println("close socket");
	    	for (ThreadServer thread : threads) {
	    		thread.close();
	    	}
	    	sv.close();
	       
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	

}
