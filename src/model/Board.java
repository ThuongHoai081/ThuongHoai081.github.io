package model;

public class Board {
	 private int id;
	 private int id_player1;
	 private int id_player2;
	 private String t_board_state;
	 private String t_result;
	 private int current_player_id;
	 private int id_room;
	 
	 public Board()
	 {
		 
	 }
	 public Board(int id, int id_player1, int id_player2,String t_board_state, String t_result, int current_player_id, int id_room)
	 {
		 this.id = id;
		 this.id_player1 = id_player1;
		 this.id_player2 = id_player2;
		 this.t_board_state = t_board_state;
		 this.t_result = t_result;
		 this.current_player_id = current_player_id;
		 this.id_room = id_room;
		 
	 }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_player1() {
		return id_player1;
	}
	public void setId_player1(int id_player1) {
		this.id_player1 = id_player1;
	}
	public int getId_player2() {
		return id_player2;
	}
	public void setId_player2(int id_player2) {
		this.id_player2 = id_player2;
	}
	public String getT_board_state() {
		return t_board_state;
	}
	public void setT_board_state(String t_board_state) {
		this.t_board_state = t_board_state;
	}
	public String getT_result() {
		return t_result;
	}
	public void setT_result(String t_result) {
		this.t_result = t_result;
	}
	public int getCurrent_player_id() {
		return current_player_id;
	}
	public void setCurrent_player_id(int current_player_id) {
		this.current_player_id = current_player_id;
	}
	public int getId_room() {
		return id_room;
	}
	public void setId_room(int id_room) {
		this.id_room = id_room;
	}

}
