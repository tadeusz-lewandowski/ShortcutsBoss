package controllers;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import auth.Password;

public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("passwordRepeat");
		
		if(username == "" || password == "" || passwordRepeat == "") {
			request.setAttribute("error", "All fields in registraction must be filled");
			request.getRequestDispatcher("/").forward(request, response);
		} else if(!password.equals(passwordRepeat)){
			request.setAttribute("error", "Passwords must be the same");
			request.getRequestDispatcher("/").forward(request, response);
		} else {
			
			password = Password.hashPassword(password);
			
			Connection conn = db.Database.Connect();
			
			try {
				
				PreparedStatement selectState = conn.prepareStatement("SELECT * FROM users WHERE username=?");
				selectState.setString(1, username);
				ResultSet result = selectState.executeQuery();
				if(result.next()) {
					request.setAttribute("error", "User has already exist");
					request.getRequestDispatcher("/").forward(request, response);
				} else {
					conn.setAutoCommit(false);
					
					PreparedStatement sqlState = conn.prepareStatement("INSERT INTO users (username, password, role) VALUES (?,?,'user')");
					sqlState.setString(1, username);
					sqlState.setString(2, password);
					sqlState.executeUpdate();
					
					conn.commit();
					
					request.setAttribute("registrationSuccess", "Your account has been created succesfully! Log in");
					request.getRequestDispatcher("/").forward(request, response);
				}
				
				
				
			} catch(SQLException ex) {
				System.out.println("SQL exception" + ex.getMessage());
			}
			
			
		}
		
		
	}

}
