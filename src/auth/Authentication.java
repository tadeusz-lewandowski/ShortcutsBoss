package auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;

public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loggedIn") != null && (Boolean)session.getAttribute("loggedIn")) {
			session.invalidate();
			request.setAttribute("loggedOut", "You are logged out");
			request.getRequestDispatcher("/").forward(request, response);
		} else {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			String hashedPassword = auth.Password.hashPassword(password);
			
			User user = auth.Auth.Authenticate(username, hashedPassword);
			if(user != null) {
				System.out.println(user.getUsername());
				System.out.println(user.getId());
				
//				HttpSession session = request.getSession();
				session.setAttribute("loggedIn", true);
				session.setAttribute("user", user);
				
				// redirect
				response.sendRedirect(request.getContextPath() + "/shortcut/new");
			} else {
				System.out.println("Authentication error");
				request.setAttribute("error", "Wrong user or password");
				request.getRequestDispatcher("/").forward(request, response);
			}			
		}		
	}

}
