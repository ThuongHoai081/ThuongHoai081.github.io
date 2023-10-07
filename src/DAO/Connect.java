package DAO;

import java.util.Properties;

import model.Player;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.Statement;

		public class Connect {
			
			public static java.sql.Connection conn      = null;
			public static java.sql.Statement  statement = null;
			public Connect() {
				try {
					String strConn = "jdbc:mysql://localhost/game-cscld";
					Properties pro = new Properties();
					
					pro.put("user", "root");
					pro.put("password", "th4940");
					
					Driver driver = new org.gjt.mm.mysql.Driver();
					conn          = driver.connect(strConn, pro);	
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			
			}

}
