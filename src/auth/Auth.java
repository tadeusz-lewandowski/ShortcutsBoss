package auth;

import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Database;

public class Auth {
	public static User Authenticate(String username, String hashedPassword) {
		Connection conn = Database.Connect();
		User user = null;
		if(conn != null) {
			try {
				
				PreparedStatement sqlState;
				sqlState = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
				sqlState.setString(1, username);
				sqlState.setString(2, hashedPassword);
				ResultSet rows = sqlState.executeQuery();
				
				if(rows.next()) {
					user = new User(rows.getString("username"), rows.getInt("id"));
					return user;
				} else {
					return user;
				}
				
				
			} catch(SQLException ex) {
				return user;
			}
		}
		return user;
	}
}
