package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import db.Database;

import models.ShortcutTableMain;

public class Shortcuts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = Database.Connect();
		
		if(conn != null) {
			try {
				String search = request.getParameter("search");

				PreparedStatement sqlState;
				
				if(search != null) {
//					sqlState = conn.prepareStatement("SELECT S.id, S.name, S.content, S.author_ID, U.username, S.stars, S.timestamp FROM shortcuts AS S INNER JOIN users AS U ON S.author_ID=U.id WHERE S.name LIKE ? ORDER BY S.stars DESC");
					sqlState = conn.prepareStatement(""
							+ "SELECT S.id, S.name, "
							+ "S.content, "
							+ "S.author_ID, "
							+ "U.username, "
							+ "COUNT(Stars.id) AS starsCount, "
							+ "S.timestamp FROM shortcuts AS S "
							+ "INNER JOIN users AS U "
							+ "ON S.author_ID=U.id "
							+ ""
							+ "LEFT JOIN stars AS Stars "
							+ "ON Stars.shortcut_ID=S.id "
							+ "WHERE S.name LIKE ? "
							+ "GROUP BY S.id "
							+ "ORDER BY starsCount DESC"
					);
					sqlState.setString(1, "%" + search + "%");
				} else {
					
					sqlState = conn.prepareStatement(""
							+ "SELECT S.id, S.name, "
							+ "S.content, "
							+ "S.author_ID, "
							+ "U.username, "
							+ "COUNT(Stars.id) AS starsCount, "
							+ "S.timestamp FROM shortcuts AS S "
							+ "INNER JOIN users AS U "
							+ "ON S.author_ID=U.id "
							+ ""
							+ "LEFT JOIN stars AS Stars "
							+ "ON Stars.shortcut_ID=S.id "
							+ "GROUP BY S.id "
							+ "ORDER BY starsCount DESC"
					);
				}
				
				ResultSet rows = sqlState.executeQuery();
				
				ArrayList<ShortcutTableMain> shortcuts = new ArrayList<ShortcutTableMain>() ; 
				
				while(rows.next()) {
					ShortcutTableMain shortcut = new ShortcutTableMain(
							rows.getInt("id"), 
							rows.getString("name"), 
							rows.getString("content"), 
							rows.getInt("author_ID"), 
							rows.getInt("starsCount"), 
							rows.getString("timestamp"), 
							rows.getString("username")
					);
					
					shortcuts.add(shortcut);
				}
				conn.close();
				
				request.setAttribute("title", "Shortcuts Boss");
				request.setAttribute("shortcuts", shortcuts);
				request.getRequestDispatcher("Shortcuts.jsp").forward(request, response);
				
			} catch(SQLException ex) {
				System.out.println("SQL exception" + ex.getMessage());
			}
			
			
		}
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
