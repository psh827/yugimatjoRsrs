package com.matjo.rsrs.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/join/add_user.do")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private UserService userService;  

     public void init() {
    	 userService = new UserService(new UserDao());
     }
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		String userName = request.getParameter("userName");
		String userId = request.getParameter("userId");
		String passwd = request.getParameter("passwd");
		String nickname = request.getParameter("nickname");
		
		
		if (userService.isValidUser(userId)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('이미 등록 된 아이디 입니다.'); location.href='"+"/rsrs/join/add_user.jsp"+"';</script>"); 
			writer.close();
			request.getRequestDispatcher("add_user.jsp").forward(request, response);
			return;
		}
		
		if (userService.isValidUserByNickname(nickname)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('이미 등록 된 닉네임 입니다.'); location.href='"+"/rsrs/join/add_user.jsp"+"';</script>"); 
			writer.close();
			request.getRequestDispatcher("add_user.jsp").forward(request, response);
			return;
		}
		
		RequestDispatcher dispatcher = null;
		
		User user = new User();
		user.setUserName(userName);
		user.setUserId(userId);
		user.setPasswd(passwd);
		user.setNickname(nickname);
		
		userService.addUser(user);
		request.setAttribute("user", user);
		
		request.setAttribute("userName", userName);
		dispatcher = request.getRequestDispatcher("/success/add_user_success.jsp");
		dispatcher.forward(request, response);
	}

}
