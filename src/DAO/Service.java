package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import model.Player;
import model.Board;

public class Service extends Connect{
	
	
	public Player login(String user,String pass)
	{
		Player dn = null;
		try
		{
			String             sql   =   " select * from ta_per_player where T_Usernam=? and T_Password=?";
			PreparedStatement  pre   =   conn.prepareStatement(sql);
			
			pre.setString(1,user);
			pre.setString(2,pass);
			
			ResultSet         result =   pre.executeQuery();
			
			while(result.next())
			{
				dn=new Player();
				dn.setTenDangNhap(result.getString("T_Usernam"));
				dn.setMatKhau(result.getString("T_Password"));
				dn.setSocer(result.getString("I_Score"));
			}
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dn;
	}
		
	public boolean CreatUser(Player lg) throws Exception{
		    String            sql  = "INSERT INTO ta_per_player (T_Usernam, T_Password, T_Email)"
				                   + "VALUES(?, ?, ?);";
		try {
			PreparedStatement pre  = conn.prepareStatement(sql);
			pre.setString(1, lg.getTenDangNhap());
			pre.setString(2, lg.getMatKhau());
			pre.setString(3, lg.getEmail());
			
			return pre.executeUpdate() > 0;
		}
		catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "lỗi!");
		}
		return false;
		
	}
	
	public Player Select(String name)
	{
		Player lg =null;
		try
		{
			String             sql    =     " select * from ta_per_player where T_Usernam=?";
            PreparedStatement  pre    =   conn.prepareStatement(sql);
			
            lg = new Player();
			pre.setString(1, name);
			
			ResultSet         result =   pre.executeQuery();
			while(result.next())
			{
				lg.setId(result.getInt("I_ID"));
			}
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return lg;
	}
	
	public Player Select_ID(int id)
	{
		Player lg =null;
		try
		{
			String             sql    =     " select * from ta_per_player where I_ID=?";
            PreparedStatement  pre    =   conn.prepareStatement(sql);
			
            lg = new Player();
			pre.setInt(1, id);
			
			ResultSet         result =   pre.executeQuery();
			while(result.next())
			{
				lg.setTenDangNhap(result.getString("T_Usernam"));
				lg.setSocer(result.getString("I_Score"));
			}
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return lg;
	}
	public boolean Create_Id_room(int id)throws Exception
	{
		
	    String  sql      =  "INSERT INTO ta_nso_id_room (I_Id_Room) VALUES(?);";
		try
		{
			PreparedStatement pre   = conn.prepareStatement(sql);
			pre.setInt(1, id);
			
			return pre.executeUpdate() > 0;
		
		}
		catch(Exception ex)
		{
			JOptionPane.showConfirmDialog(null, "lỗi!");
		}
		return false;
	}
	public boolean Create_board(int id_player1, int id_room)throws Exception
	{
		
		 String  sql      =  "INSERT INTO ta_nso_boards (I_Player1_Id, I_Id_Room)"
                          +  "VALUES(?, ?);";
		try
		{
			PreparedStatement pre   = conn.prepareStatement(sql);
			pre.setInt(1,id_player1 );
			pre.setInt(2, id_room);
			return pre.executeUpdate() > 0;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	
	public Board Select_Board(int id)
	{
		Board br =null;
		try
		{
			String             sql    =     " select * from ta_nso_boards where I_Id_Room=?";
            PreparedStatement  pre    =   conn.prepareStatement(sql);
			
            br = new Board();
			pre.setInt(1, id);
	
			
			ResultSet         result =   pre.executeQuery();
			while(result.next())
			{
				br.setId_player1(result.getInt("I_Player1_Id"));
				br.setId_player2(result.getInt("I_Player2_Id"));
				br.setId_room(result.getInt("I_Id_Room"));
			}
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return br;
	}
	
	public boolean Update(int id_player2, int id_room)throws Exception
	{
		
	    String  sql      =  "update ta_nso_boards set I_Player2_Id=? where I_Id_Room=? ";
		try
		{
			PreparedStatement pre   = conn.prepareStatement(sql);
			pre.setInt(1, id_player2);
			pre.setInt(2, id_room);
			
			return pre.executeUpdate() > 0;
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	public boolean Update_id(int id_player1,int id_player2,int id_room)throws Exception
	{
		
	    String  sql      =  "update ta_nso_boards set I_Player1_Id=?,I_Player2_Id=? where I_Id_Room=? ";
		try
		{
			PreparedStatement pre   = conn.prepareStatement(sql);
			pre.setInt(1, id_player1);
			pre.setInt(2, id_player2);
			pre.setInt(3, id_room);
			
			return pre.executeUpdate() > 0;
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	public boolean Update_B(int id_player1,int id_player2,int id_room)throws Exception
	{
		
	    String  sql      =  "update ta_nso_boards set I_Player1_Id=?,I_Player2_Id=? where I_Id_Room=? ";
		try
		{
			PreparedStatement pre   = conn.prepareStatement(sql);
			pre.setInt(1, id_player1);
			pre.setInt(2, id_player2);
			pre.setInt(3, id_room);
			
			return pre.executeUpdate() > 0;
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	
	public boolean Delete(int id_room)throws Exception
	{
		
	    String  sql      =  "delete from ta_nso_boards where I_Id_Room=? ";
		try
		{
			PreparedStatement pre   = conn.prepareStatement(sql);
			pre.setInt(1, id_room);
			
			return pre.executeUpdate() > 0;
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	
	public boolean Delete_r(int id_room)throws Exception
	{
		
	    String  sql      =  "delete from ta_nso_id_room where I_Id_Room=? ";
		try
		{
			PreparedStatement pre   = conn.prepareStatement(sql);
			pre.setInt(1, id_room);
			
			return pre.executeUpdate() > 0;
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	public boolean Update_C(int current_player_id,int id_room) {
		String sql ="update ta_nso_boards set I_Current_Player_id=? where I_Id_Room=? ";
		try
		{
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, current_player_id);
			pre.setInt(2, id_room);
			return pre.executeUpdate() > 0;
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
		
	}
	public boolean Update_P(String Socer, int id)throws Exception
	{
		
	    String  sql      =  "update ta_nso_boards set I_Score=? where I_Id=? ";
		try
		{
			PreparedStatement pre   = conn.prepareStatement(sql);
			pre.setString(1, Socer);
			pre.setInt(2, id);
			
			return pre.executeUpdate() > 0;
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	

}
