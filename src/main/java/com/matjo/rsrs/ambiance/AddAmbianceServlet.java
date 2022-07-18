package com.matjo.rsrs.ambiance;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class AddAmbianceServlet
 */
@WebServlet("/restaurant/save")
public class AddAmbianceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AmbianceService ambianceService;
	
	public void init() {
		ambianceService = new AmbianceService(new AmbianceDao());
   }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/restaurant/save.jsp");
		  rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String rate = request.getParameter("rating"); 
		
		
		
	}

}
