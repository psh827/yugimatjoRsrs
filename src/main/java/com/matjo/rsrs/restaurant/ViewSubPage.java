package com.matjo.rsrs.restaurant;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewSubPage
 */
@WebServlet("/restaurant/subpage")
public class ViewSubPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private RestaurantService restaurantService;

	@Override
	public void init() throws ServletException {
		restaurantService = new RestaurantService(new RestaurantDao());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resName = request.getParameter("resName");
		
		Restaurant s = null;
		s = restaurantService.findResToSubpage(resName);
		request.setAttribute("restaurant", s);
		request.setAttribute("resName", resName);
		RequestDispatcher rd = request.getRequestDispatcher("/restaurant/subpage.jsp");
		rd.forward(request, response);
	}	
}
