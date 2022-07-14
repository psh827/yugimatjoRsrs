package com.matjo.rsrs.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private UserService userService;
	public void init() {
		userService = new UserService(new UserDao());
	}
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String passwd = request.getParameter("passwd");
		System.out.println(userId);
		System.out.println(passwd);
		
		if (!userService.isValidUser(userId,passwd)) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		if(userService.isValidUser(userId,passwd)) {
			if(!userService.isUserPosition(userId, passwd)) {
				response.sendRedirect("add_res.jsp");
			}
		}
		
		
		HttpSession session = request.getSession(true);
		session.setAttribute("userId", userId);
		response.sendRedirect("/rsrs/main/main.jsp");
	}

}
