package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	public static Connection Connect() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/shortcuts_app", "root", "");
			
			return conn;
			
		} catch(SQLException ex) {
			System.out.println("SQL exception" + ex.getMessage());
			return conn;
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			return conn;
		}
	}
}
