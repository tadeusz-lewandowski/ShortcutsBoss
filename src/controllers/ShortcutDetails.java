package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.ShortcutTableMain;
import db.Database;
import models.User;


public class ShortcutDetails extends HttpServlet {
	private static final long serialVersionUID = 2L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = Database.Connect();
		
		try {
			
			String path = request.getPathInfo();
			if(path != null) {
				String escapedPath = path.replaceAll("\\/","");
				
				if(escapedPath.contentEquals("new")) {
					
					HttpSession session = request.getSession();
					if(session.getAttribute("loggedIn") != null && (Boolean)session.getAttribute("loggedIn")) {
						conn.close();
						request.setAttribute("title", "Shortcuts Boss - New");
						request.getRequestDispatcher("/NewShortcut.jsp").forward(request, response);						
					} else {
						conn.close();
						request.setAttribute("error", "You must be logged in");
						request.getRequestDispatcher("/").forward(request, response);
					}
					
					
				} else if(escapedPath.matches("[0-9]+")){
					
					int idFromPath = Integer.parseInt(escapedPath);
					
					PreparedStatement prepared = conn.prepareStatement(""
							+ "SELECT S.id, "
							+ "S.name, "
							+ "S.content, "
							+ "S.author_ID, "
							+ "U.username, "
							+ "COUNT(Stars.id) AS starsCount, "
							+ "S.timestamp "
							+ "FROM shortcuts "
							+ "AS S "
							+ "INNER JOIN users AS U "
							+ "ON S.author_ID=U.id "
							+ "LEFT JOIN stars AS Stars "
							+ "ON Stars.shortcut_ID=S.id "
							+ "WHERE S.id=? "
							+ "GROUP BY S.id "
					);
					prepared.setInt(1, idFromPath);
					ResultSet row = prepared.executeQuery();
					if(row.next()) {
						System.out.println(row.getString("name"));
						ShortcutTableMain shortcut = new ShortcutTableMain(
								row.getInt("id"), 
								row.getString("name"),
								row.getString("content"),
								row.getInt("author_ID"),
								row.getInt("starsCount"),
								row.getString("timestamp"),
								row.getString("username")
						);
						
						request.setAttribute("shortcut", shortcut);
						request.setAttribute("title", "Shortcuts Boss - Details");
						HttpSession session = request.getSession();
						String starSelectedClass = "-empty";
						if(session.getAttribute("loggedIn") != null) {
							User user = (User) session.getAttribute("user");
							
							System.out.println("test 1 ------");
							System.out.println(user.getId());
							System.out.println(shortcut.getId());														
							
							PreparedStatement stat = conn.prepareStatement("SELECT * FROM stars WHERE user_ID=? AND shortcut_ID=?");
							stat.setInt(1, user.getId());
							stat.setInt(2, shortcut.getId());
							ResultSet rows = stat.executeQuery();
							if(rows.next()) {
								starSelectedClass = "";
							}							
							
						}
						request.setAttribute("starSelected", starSelectedClass);
						request.getRequestDispatcher("/ShortcutDetails.jsp").forward(request, response);
						
					}
				
				}
				
				
			}
			
			conn.close();

		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if(session.getAttribute("loggedIn") != null && (Boolean)session.getAttribute("loggedIn")) {
			
			String name = request.getParameter("title");
			String content = request.getParameter("content");
			User user = (User)session.getAttribute("user");
			
			Connection conn = db.Database.Connect();
			
			if(conn != null) {
				try {
					
					PreparedStatement sqlState;
					String[] returnId = { "id" };
					sqlState = conn.prepareStatement("INSERT INTO shortcuts (name, content, author_ID) VALUES (?, ?, ?)", returnId);
					sqlState.setString(1, name);
					sqlState.setString(2, content.trim());
					sqlState.setInt(3, user.getId());
					System.out.println(content);
					
					int affectedRows = sqlState.executeUpdate();
					
					if (affectedRows == 0) {
						throw new SQLException("Creating user failed, no rows affected.");
					} else {
						try (ResultSet rs = sqlState.getGeneratedKeys()) {
							if (rs.next()) {
								System.out.println(rs.getInt(1));
								response.sendRedirect(request.getContextPath() + "/shortcut/" + rs.getInt(1));
							}
							rs.close();
							
						}
					}
					
					
				} catch(SQLException ex) {
					System.out.println("SQL exception" + ex.getMessage());
				}
			}
			
		}
		
		
		
		
		doGet(request, response);
	}

}
