package com.matjo.rsrs.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login/userInformation")
public class MypageServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
	
	public void init() {
		userService = new UserService(new UserDao());
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("mypage.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String passwd = request.getParameter("passwd");
		String userId = (String) session.getAttribute("userId");
		
		if (!userService.isValidUser((String)session.getAttribute("userId"), passwd)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('비밀번호가 틀렸습니다.'); location.href='"+"/rsrs/login/mypage.jsp"+"';</script>"); 
			writer.close();
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		} else if (passwd == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('비밀번호를 입력하세요..'); location.href='"+"/rsrs/login/mypage.jsp"+"';</script>"); 
			writer.close();
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		String grade = userService.getGrade(userId);
		
		RequestDispatcher dispatcher = null;
		request.setAttribute("grade", grade);
		dispatcher = request.getRequestDispatcher("userInformation.jsp");
		dispatcher.forward(request, response);
	}
	

}
