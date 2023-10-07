package model;

public class Player {
	
	 private String TenDangNhap;
	 private String MatKhau;
	 private String Email;
	 private String Socer;
	 private int id;
	 
     public Player(String tenDangNhap, String matKhau, String email, String socer, int id) {
    	 
	   this.TenDangNhap = tenDangNhap;
	   this.MatKhau = matKhau;
	   this.Email = email;
	   this.Socer = socer;
	   this.id = id;
	
	}
     
	public Player() {
	}

	@Override
	public String toString() {
		return "Login [TenDangNhap=" + TenDangNhap + ", MatKhau=" + MatKhau + ", Email=" + Email + ", Socer=" + Socer
				+ ", id=" + id + "]";
	}

	public String getTenDangNhap() {
		return TenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		TenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return MatKhau;
	}
	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getSocer() {
		return Socer;
	}
	public void setSocer(String socer) {
		Socer = socer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}