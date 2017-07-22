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

import models.User;


public class Stars extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loggedIn") != null) {
			Connection conn = db.Database.Connect();
			
			if(conn != null) {
				try {
					
					
					User user = (User)session.getAttribute("user");
					int user_ID = user.getId();
					System.out.println(request.getParameter("shortcut_ID"));
					int shortcut_ID = Integer.parseInt(request.getParameter("shortcut_ID"));
					
					PreparedStatement sqlState = conn.prepareStatement("SELECT * FROM stars WHERE user_ID=? AND shortcut_ID=?");	
					sqlState.setInt(1, user_ID);
					sqlState.setInt(2, shortcut_ID);
					
					ResultSet rows = sqlState.executeQuery();
					
					if(rows.next()) {
						// remove star
						conn.setAutoCommit(false);
						
						sqlState = conn.prepareStatement("DELETE FROM stars WHERE user_ID=? AND shortcut_ID=?");	
						sqlState.setInt(1, user_ID);
						sqlState.setInt(2, shortcut_ID);
						sqlState.executeUpdate();
						
						conn.commit();
						
												
					} else {
						// add star
						conn.setAutoCommit(false);
						
						PreparedStatement state = conn.prepareStatement("INSERT INTO stars (shortcut_ID, user_ID) VALUES (?,?)");	
						state.setInt(1, shortcut_ID);
						state.setInt(2, user_ID);
						state.executeUpdate();
						
						conn.commit();
					}
					
					conn.close();
					response.sendRedirect(request.getContextPath() + "/shortcut/" + shortcut_ID);
					
				} catch(SQLException ex) {					
					System.out.println("SQL exception" + ex.getMessage());
				}
			}			
		}
		
	}

}
