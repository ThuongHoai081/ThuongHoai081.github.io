package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import javax.swing.JOptionPane;

import DAO.Service;
import model.Board;
import model.Player;

public class ThreadServer extends Thread{
	public static List<ThreadServer> threads = new ArrayList<>();
	private int id;
	private Socket socket;
	public int kt;
	private DataInputStream dis;
	private DataOutputStream dos ;
	private ViewServerr viewServer;
	private static final String c = "<?>";
	int id_r =(int)(Math.random() * Integer.MAX_VALUE);
	Player pl = new Player();
	Board board = new Board();
	String na;
	
	
	public ThreadServer(Socket socket, ViewServerr view) {
		this.viewServer = view;
		this.id = (int)(Math.random() * Integer.MAX_VALUE);
		this.socket = socket;
		 try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.threads.add(this);
	}

	@Override
	public void run() {
		try {
			while (!socket.isClosed()) {
				String msg = dis.readUTF();
				StringTokenizer cont = new StringTokenizer(msg , c);
				String cmd 	= cont.nextToken();
				synchronized (dis) {
						doCmd (cont,cmd,msg);
				}
				System.out.println("cmdsv: "+cmd);
				
			}
			
		} catch (Exception e) {
			close();
			System.out.println(socket + " Vừa Thoát");
		}
	}
	public Socket getSocket() {
		return socket;
	}
	
	private void doCmd (StringTokenizer cont,String cmd,String msg) throws Exception{
		
		if (cmd.equals("login")) {
			doLogin(cont);
			
		}
		else if(cmd.equals("LoginOK"))
		{
			String name = cont.nextToken();
		    viewServer.doDisplay(name);
			 System.out.println("na: "+name);
			 na = name;
			
		}
		else if (cmd.equals("SignUp")) {
			
			doSignUp(cont);
			
		}else if(cmd.equals("GetDVC")) {
			
			//getDisplayViewChoose
			doSendData("GetDVC");
			
		}else if(cmd.equals("Back")) {
			//back ViewChoose
			doUpdate(cont);
		}
		else if(cmd.equals("Nhap_ID")) {
			
			doSendData("Nhap_ID_OK");
			
		}else if(cmd.equals("TaoPhong")) {
			
			doViewInvite(cont);
			
		}else if(cmd.equals("ViewInvite_Start")) {
			
			doInvite(cont);
			
		}else if(cmd.equals("Start_E")) {
			
			doEnter(cont);
			
		}else if(cmd.equals("ViewEnter_Start")) {
			
			doSendData("ViewEnter_Start",String.valueOf(board.getId_room()));
			
		}else if(cmd.equals("Wait")) {
			for (ThreadServer thr : threads) {
		        thr.doSendData(msg);
				
		    }
			
			
		}else if(cmd.equals("reset")) {
			for (ThreadServer thr : threads) {
		        thr.doSendData(msg);
				
		    }
			
		}else if(cmd.equals("End")) {
			
			doUpdateBoard(cont);
			
		}else if(cmd.equals("Logout")) {
		    viewServer.doRemove(na);
		    
		}
		
	}
	
	private void doSendData(String cmd, String...cont) {
		
		try {
			synchronized(dos) {
				String msg = cmd;
				for (String s: cont) msg = msg + c + s ;
				dos.writeUTF(msg);
			}
			
		}catch(Exception e) {
			
		}
	}
	
	public void doLogin (StringTokenizer cont) throws Exception{
		String name = cont.nextToken();
		String pwd = cont.nextToken();
		System.out.println(this.id + ": "+ name+ "," + pwd);
		Service dnsv= new Service();
		Player l =dnsv.login(name,pwd);
			
		if(l!=null)
		{
			doSendData ("login_OK", name,l.getSocer());
        }
		else
		{
			doSendData ("login_KO", name);
		}
		
	}
	public void doUpdateBoard (StringTokenizer cont) throws Exception{
		String name = cont.nextToken();
		String sc = cont.nextToken();
		Service dnsv= new Service();
		Player pl = dnsv.Select(name);

		dnsv.Update_C(pl.getId(), id_r) ;
		dnsv.Update_P(String.valueOf(Integer.parseInt(pl.getSocer())+1), pl.getId());
		for (ThreadServer thr : threads) {
	        thr.doSendData("End",name);
			
	    }
		
	}

	private void doSignUp (StringTokenizer cont) throws Exception{
		String name = cont.nextToken();
		String pwd = cont.nextToken();
		System.out.println(this.id + ": "+ name + " " + pwd);
		
		Player dn = new Player();	
		dn.setTenDangNhap(name);
		dn.setEmail(name);
		dn.setMatKhau(pwd);
		
		Service sv = new Service();
		if(sv.CreatUser(dn))
		{
			doSendData ("SignUp_OK", name);
		}
		else
		{
			doSendData ("SignUp_KO", name);
		}
		
	}
	private void doViewInvite(StringTokenizer cont) throws Exception{

		String na = cont.nextToken();
		
		Service sv = new Service();
		Player pl = sv.Select(na); 
		
		System.out.println("na: "+na);
		System.out.println("pl.getID(): "+pl.getId());
		
		if(sv.Create_Id_room(id_r) && sv.Create_board(pl.getId(),id_r)) {
				
			doSendData("TaoPhong_OK",String.valueOf(id_r));
			
		}
		else
		{
			doSendData ("G");
		}
	}
	private void doInvite(StringTokenizer cont) throws Exception{

		Service sv = new Service();
		Board br = sv.Select_Board(id_r);
					
		Player pl2 = sv.Select_ID(br.getId_player2());
			
		System.out.println("na: "+pl2.getTenDangNhap());
		System.out.println("score: "+pl2.getSocer());
			
		doSendData("ViewInvite_Start",pl2.getTenDangNhap(),pl2.getSocer(),"Start",String.valueOf(id_r));
			
	}
	
	private void doEnter(StringTokenizer cont) throws Exception{

		String na = cont.nextToken();
		String id_tf = cont.nextToken();
		board.setId_room(Integer.parseInt(id_tf));
		Service sv = new Service();
		Board br = sv.Select_Board(Integer.parseInt(id_tf));
		
		Player pl = sv.Select(na);
		Player pl2 = sv.Select_ID(br.getId_player1());
		
	    System.out.println("pl2: "+pl2.getTenDangNhap());

	    if(sv.Update(pl.getId(), Integer.parseInt(id_tf))) 
	     {
				
			doSendData("Start_E",pl2.getTenDangNhap(),pl2.getSocer());
				
		 }
		else
		 {
			doSendData ("G");
		 }
		}
	private void doUpdate(StringTokenizer cont) throws Exception{
		
		
		String na = cont.nextToken();
		String id = cont.nextToken();
		Service sv = new Service();
		
	
		sv.Delete(Integer.parseInt(id));
		sv.Delete_r(Integer.parseInt(id));
		doSendData("Back");
		
	}

	public void close() {
		System.out.println(socket.getRemoteSocketAddress() + "is closed");
		try {
			if (socket.equals(null)) {
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
